/**Designed and developed by 2021 Muhammad Farhan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package com.farhan.pixo.arch.api

import com.farhan.pixo.BuildConfig
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
        @Query("key") key: String = BuildConfig.PIXABAY_API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ) : Image
}