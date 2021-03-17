package com.farhan.pixo.arch.api

import com.farhan.pixo.model.Image
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    companion object{
        const val BASE_URL = "https://pixabay.com/"
    }

    @GET("api/")
    suspend fun getImages(
        @Query("q") query: String = "",
        @Query("key") key: String = "20674327-3911c1290f7b8097d85649f8d",
        @Query("image_type") imageType: String = "photo",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ) : Image
}