package com.farhan.pixo.ui.gallery.action

import com.farhan.pixo.arch.mvi.IAction

sealed class GalleryActions: IAction {
    object GetImages: GalleryActions()
    data class OnClickImage(val imageUrl:String): GalleryActions()
    object NoInternet: GalleryActions()
}