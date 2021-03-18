package com.farhan.pixo.ui.gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.farhan.pixo.R
import com.farhan.pixo.arch.mvi.IView
import com.farhan.pixo.databinding.GalleryFragmentBinding
import com.farhan.pixo.ui.gallery.action.GalleryActions
import com.farhan.pixo.ui.gallery.adapter.GalleryAdapter
import com.farhan.pixo.ui.gallery.state.GalleryState
import com.farhan.pixo.ui.gallery.viewmodel.GalleryViewModel
import com.farhan.pixo.utils.gone
import com.farhan.pixo.utils.toast
import com.farhan.pixo.utils.viewBinding
import com.farhan.pixo.utils.waitForTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.gallery_fragment), IView<GalleryState> {

    private val viewModel by viewModels<GalleryViewModel>()

    private val binding by viewBinding(GalleryFragmentBinding::bind)
    private var fragmentStateRefresh = true
    private val galleryAdapter by lazy {
        GalleryAdapter()
    }

    companion object {
        private const val DEFAULT_QUERY = "nature"
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        setHasOptionsMenu(true)
        initUi()
        subscribeObservers()
        if (fragmentStateRefresh)
            sendAction(GalleryActions.GetImages(DEFAULT_QUERY))
        fragmentStateRefresh = false
    }

    private fun initUi() {
        binding.rvGallery.apply {
            setHasFixedSize(true)
            adapter = galleryAdapter
            binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun subscribeObservers() {
        // Observing the state
        viewModel.state.observe(viewLifecycleOwner, {
            render(it)
        })

        viewModel.images.observe(viewLifecycleOwner) {
            galleryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            waitForTransition(binding.rvGallery)
            binding.galleryProgressBar.gone()
        }
    }

    private fun sendAction(galleryActions: GalleryActions) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.send(galleryActions)
        }
    }

    override fun render(state: GalleryState) {
        when (state) {
            is GalleryState.GetImages -> {
                binding.galleryProgressBar.isVisible = state.isLoading
                if (state.errorMessage != null) {
                    requireContext().toast("Error: ${state.errorMessage}")
                }
            }
            is GalleryState.OnClickImage -> {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvGallery.scrollToPosition(0)
                    sendAction(GalleryActions.GetImages(query))
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}