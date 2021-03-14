package com.farhan.pixo.arch.mvi

interface IView<S: IState> {
    fun render(state: S)
}