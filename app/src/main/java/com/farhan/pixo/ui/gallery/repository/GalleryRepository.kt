package com.farhan.pixo.ui.gallery.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.farhan.pixo.arch.api.PixabayApi
import com.farhan.pixo.model.Image
import com.farhan.pixo.model.Images
import com.farhan.pixo.ui.gallery.paging.PixabayPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepository @Inject constructor(private val pixabayApi: PixabayApi) {
    suspend fun getImages(): Image {
        return withContext(Dispatchers.IO) {
            pixabayApi.getImages()
        }
    }

    fun getImages2(query: String) =
            Pager(
                    config = PagingConfig(
                            pageSize = 20,
                            enablePlaceholders = false,
                            prefetchDistance = 5
                    ),
                    pagingSourceFactory = { PixabayPagingSource(pixabayApi, query) }
            ).liveData

    fun getImages3(query: String): Flow<PagingData<Images>> =
            Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
                    pagingSourceFactory = { PixabayPagingSource(pixabayApi, query) }
            ).flow
}