package com.barbosahub.imgurApi.network

import retrofit2.http.GET
interface API {
    @GET("search/?q=cats")
    suspend fun getCatsList(
    ): retrofit2.Response<Map<String, Any>>
}