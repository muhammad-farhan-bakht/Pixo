package com.farhan.pixo.ui.preview.state

import com.farhan.pixo.arch.mvi.IState

sealed class PreviewState: IState {
     data class LoadImage(val imageUrl:String) : PreviewState()
}