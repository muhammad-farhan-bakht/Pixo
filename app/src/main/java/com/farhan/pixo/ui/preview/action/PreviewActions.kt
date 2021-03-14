package com.farhan.pixo.ui.preview.action

import com.farhan.pixo.arch.mvi.IAction

sealed class PreviewActions: IAction {
    object LoadImage: PreviewActions()
    object NoInternet: PreviewActions()
    data class OnClickShare(val imageUrl:String): PreviewActions()
}