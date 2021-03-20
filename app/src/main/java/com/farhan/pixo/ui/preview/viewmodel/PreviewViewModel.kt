/**Designed and developed by 2021 Muhammad Farhan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package com.farhan.pixo.ui.preview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.pixo.arch.mvi.IModel
import com.farhan.pixo.ui.gallery.state.GalleryState
import com.farhan.pixo.ui.preview.action.PreviewActions
import com.farhan.pixo.ui.preview.state.PreviewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class PreviewViewModel : ViewModel(), IModel<PreviewState, PreviewActions> {

    override val actions: Channel<PreviewActions> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<PreviewState>()
    override val state: LiveData<PreviewState>
        get() = _state

    init {
        handlerAction()
    }

    private fun handlerAction() {
        viewModelScope.launch {
            actions.consumeAsFlow().collect {
                when(it){
                    is PreviewActions.LoadImage -> {
                        updateState(PreviewState.LoadImage(it.imageUrl))
                    }
                }
            }
        }
    }

    private fun updateState(action: PreviewState) {
        _state.postValue(action)
    }

}