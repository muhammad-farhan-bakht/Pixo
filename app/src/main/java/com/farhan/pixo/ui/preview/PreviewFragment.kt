package com.farhan.pixo.ui.preview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.farhan.pixo.R
import com.farhan.pixo.arch.mvi.IView
import com.farhan.pixo.databinding.PreviewFragmentBinding
import com.farhan.pixo.ui.preview.action.PreviewActions
import com.farhan.pixo.ui.preview.state.PreviewState
import com.farhan.pixo.ui.preview.viewmodel.PreviewViewModel
import com.farhan.pixo.utils.viewBinding
import kotlinx.coroutines.launch

class PreviewFragment : Fragment(R.layout.preview_fragment), IView<PreviewState> {

    private val viewModel by viewModels<PreviewViewModel>()
    private val binding by viewBinding(PreviewFragmentBinding::bind)
    private val args: PreviewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        sendAction(PreviewActions.LoadImage(args.extraImageUrl))
    }

    private fun subscribeObservers() {
        // Observing the state
        viewModel.state.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun sendAction(previewActions: PreviewActions){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.send(previewActions)
        }
    }

    override fun render(state: PreviewState) {
        when(state){
            is PreviewState.LoadImage -> {
                binding.run {
                    imageUrl = state.imageUrl
                }
                binding.executePendingBindings()
            }
        }
    }
}