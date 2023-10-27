package com.barbosahub.imgurApi.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.barbosahub.imgurApi.network.repository.GalleryRepository
import com.barbosahub.imgurApi.stateFlow.StateFlow
import com.barbosahub.imgurApi.ui.base.BaseViewModel.BaseViewModel
import com.barbosahub.imgurApi.ui.home.view.model.CatsJson
import org.json.JSONObject

class GalleryViewModel(private val galleryRepository: GalleryRepository) : BaseViewModel(){

    val catsList = MutableLiveData<StateFlow>()
    val listCats = ArrayList<CatsJson>()

    fun getCatsList() = fetchData(catsList){
        galleryRepository.getCatsList()
    }

    fun fillCatsList(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("data")

        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {


                if (it.has("images") && !it.getJSONArray("images").getJSONObject(0).getString("link").contains("mp4"))
                    listCats += CatsJson(it)
            }
    }
}