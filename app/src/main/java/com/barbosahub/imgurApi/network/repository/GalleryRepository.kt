package com.barbosahub.imgurApi.network.repository

import com.barbosahub.imgurApi.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryRepository(private val baseUrl: String) {
    //Buscar uma lista com filtro por parametros
    suspend fun getCatsList() = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getCatsList()
    }
}