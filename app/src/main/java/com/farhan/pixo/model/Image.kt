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
package com.farhan.pixo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("totalHits")
    @Expose
    val totalHits: Int,
    @SerializedName("hits")
    @Expose
    val imageList: List<Images>,
)

data class Images(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("largeImageURL")
    @Expose
    val imageUrl: String
)