package com.farhan.pixo.ui.preview.action

import com.farhan.pixo.arch.mvi.IAction

sealed class PreviewActions: IAction {
    data class LoadImage(val imageUrl:String): PreviewActions()
}