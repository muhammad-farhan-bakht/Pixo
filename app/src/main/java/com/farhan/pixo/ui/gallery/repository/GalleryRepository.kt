package com.farhan.pixo.ui.gallery.repository


import com.farhan.pixo.arch.api.PixabayApi
import com.farhan.pixo.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepository @Inject constructor(private val pixabayApi: PixabayApi) {
    suspend fun getImages(): Image {
        return withContext(Dispatchers.IO){
            pixabayApi.getImages()
        }
    }
}