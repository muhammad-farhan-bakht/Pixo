package com.farhan.pixo.ui.preview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.farhan.pixo.R
import com.farhan.pixo.arch.mvi.IView
import com.farhan.pixo.databinding.PreviewFragmentBinding
import com.farhan.pixo.ui.preview.action.PreviewActions
import com.farhan.pixo.ui.preview.state.PreviewState
import com.farhan.pixo.ui.preview.viewmodel.PreviewViewModel
import kotlinx.coroutines.launch

class PreviewFragment : Fragment(R.layout.preview_fragment), IView<PreviewState> {

    private val viewModel by viewModels<PreviewViewModel>()
    private var _binding: PreviewFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: PreviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PreviewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedElementTransitionOnEnter()
        postponeEnterTransition()

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
                setUpImageInView(state.imageUrl)
            }
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }

    private fun setUpImageInView(imageUrl:String){
        binding.imgPreviewImage.apply {
            transitionName = imageUrl
            startEnterTransitionAfterLoadingImage(imageUrl, this)
        }
    }

    private fun startEnterTransitionAfterLoadingImage(imageAddress: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageAddress)
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}