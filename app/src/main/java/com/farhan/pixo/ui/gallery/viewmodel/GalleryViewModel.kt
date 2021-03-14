package com.farhan.pixo.ui.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.pixo.arch.mvi.IModel
import com.farhan.pixo.ui.gallery.action.GalleryActions
import com.farhan.pixo.ui.gallery.repository.GalleryRepository
import com.farhan.pixo.ui.gallery.state.GalleryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val galleryRepository: GalleryRepository) : ViewModel(), IModel<GalleryState, GalleryActions> {

    override val actions: Channel<GalleryActions> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<GalleryState>()
    override val state: LiveData<GalleryState>
        get() = _state

    init {
        handlerAction()
    }

    private fun handlerAction() {
        viewModelScope.launch {
            actions.consumeAsFlow().collect { actions ->
                when(actions){
                    GalleryActions.GetImages -> loadImages()
                    GalleryActions.NoInternet -> updateState(GalleryState.NoInternet)
                    is GalleryActions.OnClickImage -> updateState (GalleryState.OnClickImage(imageUrl = actions.imageUrl))
                }
            }
        }
    }

    private suspend fun loadImages() {
        try {
            updateState(GalleryState.GetImages(isLoading = true))
            val image = galleryRepository.getImages()
            Timber.e("image size ${image.imageList.size}")
            updateState(GalleryState.GetImages(isLoading = false,imagesList = image.imageList))
        } catch (e:Exception){
            e.printStackTrace()
            updateState(GalleryState.GetImages(isLoading = false, errorMessage = e.message))
        }
    }

    private fun updateState(action: GalleryState) {
        _state.postValue(action)
    }

}