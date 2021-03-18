package com.farhan.pixo.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farhan.pixo.databinding.GalleryListViewBinding
import com.farhan.pixo.model.Images
import com.farhan.pixo.ui.gallery.GalleryFragmentDirections
import com.farhan.pixo.utils.toTransitionGroup

class GalleryAdapter : PagingDataAdapter<Images, ItemViewHolder>(GalleryAdapter) {
    companion object : DiffUtil.ItemCallback<Images>() {
        override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryListViewBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentImage = getItem(position)
        currentImage?.let {
            holder.bind(it)
        }
    }
}

class ItemViewHolder(private val binding: GalleryListViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Images) {
        binding.image = image
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                    binding.imgGalleryImage.toTransitionGroup()
            )
            val direction = GalleryFragmentDirections.actionGalleryFragmentToPreviewFragment(image.imageUrl)
            it.findNavController().navigate(direction, extras)
        }
    }
}