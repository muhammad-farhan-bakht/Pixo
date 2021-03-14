package com.farhan.pixo.arch.mvi

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface IModel<S: IState, I: IAction> {
    val actions: Channel<I>
    val state: LiveData<S>
}