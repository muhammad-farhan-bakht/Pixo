package com.farhan.pixo.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farhan.pixo.R
import com.farhan.pixo.arch.mvi.IView
import com.farhan.pixo.databinding.GalleryFragmentBinding
import com.farhan.pixo.ui.gallery.action.GalleryActions
import com.farhan.pixo.ui.gallery.adapter.GalleryAdapter
import com.farhan.pixo.ui.gallery.state.GalleryState
import com.farhan.pixo.ui.gallery.viewmodel.GalleryViewModel
import com.farhan.pixo.utils.toast
import com.farhan.pixo.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.gallery_fragment), IView<GalleryState> {

    private val viewModel by viewModels<GalleryViewModel>()
    private val binding by viewBinding(GalleryFragmentBinding::bind)
    private val galleryAdapter by lazy {
        GalleryAdapter()
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        initUi()
        subscribeObservers()
        //sendAction(GalleryActions.GetImages)
    }

    private fun initUi() {
        // initialize staggered grid layout manager and RecyclerView
        /*val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }*/

        binding.rvGallery.apply {
            setHasFixedSize(true)
            adapter = galleryAdapter
            //binding.rvGallery.layoutManager = staggeredGridLayoutManager

            binding.rvGallery.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

            /*addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE)
                        recyclerView.invalidateItemDecorations()
                }
            })*/
        }
    }

    private fun subscribeObservers() {
        // Observing the state
        viewModel.state.observe(viewLifecycleOwner, {
            render(it)
        })

        viewModel.images.observe(viewLifecycleOwner){
            Timber.e("subscribeObservers images.observe Calls")
            galleryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun sendAction(galleryActions: GalleryActions){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.send(galleryActions)
        }
    }

    override fun render(state: GalleryState) {
        when(state){
            is GalleryState.GetImages -> {
                binding.galleryProgressBar.isVisible = state.isLoading
                //galleryAdapter.submitList(state.imagesList?.toArrayList())
                //galleryAdapter.submitData(viewLifecycleOwner.lifecycle, state.imagesList?.toArrayList())
                if (state.errorMessage != null) {
                   requireContext().toast("Error: ${state.errorMessage}")
                }
            }
            GalleryState.NoInternet -> {}
            is GalleryState.OnClickImage -> {}
        }
    }

}