package com.farhan.pixo.ui.gallery.action

import com.farhan.pixo.arch.mvi.IAction

sealed class GalleryActions: IAction {
    data class GetImages(val query:String): GalleryActions()
}