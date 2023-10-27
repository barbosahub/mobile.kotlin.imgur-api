package com.barbosahub.imgurApi.ui.base.BaseViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbosahub.imgurApi.stateFlow.StateFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel: ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    @Suppress("BlockingMethodInNonBlockingContext")
    fun fetchData(liveData: MutableLiveData<StateFlow>,
                  service: suspend () -> Response<*>) {
        viewModelScope.launch(job) {
            liveData.value = StateFlow.Loading(true)
            try {
                val response = service()
                liveData.value = StateFlow.Loading(false)
                if (response.isSuccessful) {
                    val jsonResponse = JSONObject(response.body()!! as Map<*, *>)

                    if (jsonResponse.optBoolean("success", false)) {
                        liveData.value = StateFlow.Success(jsonResponse)
                    } else {
                        liveData.value = StateFlow.Error(
                            jsonResponse.getIgnoreCase("errormessage"),
                            jsonResponse.getIgnoreCase("errorcode"),
                            jsonResponse.getIgnoreCase("detail"),
                            if (jsonResponse.has("errorID")) jsonResponse.getString("errorID") else null
                        )
                    }


                } else if (response.code() == 504) {
                    liveData.value = StateFlow.Error(
                        "Erro ao chamar serviço, por favor tente novamente.", null, null, null
                    )
                } else {
                    liveData.value =
                        StateFlow.Error(response.errorBody()!!.string(), null, null, null)
                }
            } catch (e: Exception) {
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!, null, null, null)
            }
        }
    }

    private fun JSONObject.getIgnoreCase(key: String): String {
        keys().forEach {
            if (it.equals(key, true)) {
                return getString(it)
            }
        }
        return ""
    }
    companion object {
        const val TESTING_CODE = 299
    }
}