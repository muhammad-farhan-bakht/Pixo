package com.farhan.pixo.ui.gallery.state

import com.farhan.pixo.arch.mvi.IState
import com.farhan.pixo.model.Images

sealed class GalleryState : IState {
    data class GetImages(
        val imagesList: List<Images>? = null,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : GalleryState()
    data class OnClickImage(val imageUrl:String): GalleryState()
    object NoInternet: GalleryState()
}