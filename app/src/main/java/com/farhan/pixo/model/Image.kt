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