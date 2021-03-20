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
package com.farhan.pixo.ui.gallery.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.farhan.pixo.arch.mvi.IModel
import com.farhan.pixo.ui.gallery.action.GalleryActions
import com.farhan.pixo.ui.gallery.repository.GalleryRepository
import com.farhan.pixo.ui.gallery.state.GalleryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val galleryRepository: GalleryRepository) : ViewModel(), IModel<GalleryState, GalleryActions> {

    override val actions: Channel<GalleryActions> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<GalleryState>()
    override val state: LiveData<GalleryState>
        get() = _state

    private val currentQuery = MutableLiveData<String>()
    private var fragmentState = true

    fun fragmentState() = fragmentState
    fun fragmentState(newState:Boolean){
        fragmentState = newState
    }

    val images = currentQuery.switchMap { queryString ->
        galleryRepository.getImages(queryString).cachedIn(viewModelScope)
    }

    init {
        handlerAction()
    }

    private fun handlerAction() {
        viewModelScope.launch {
            actions.consumeAsFlow().collect { actions ->
                when(actions){
                    is GalleryActions.GetImages -> {
                        loadImages(actions.query)
                    }
                }
            }
        }
    }

    private suspend fun loadImages(query:String) {
        try {
            updateState(GalleryState.GetImages(isLoading = true))
            delay(1000)
            currentQuery.value = query
        } catch (e:Exception){
            e.printStackTrace()
            updateState(GalleryState.GetImages(isLoading = false, errorMessage = e.message))
        }
    }

    private fun updateState(action: GalleryState) {
        _state.postValue(action)
    }
}