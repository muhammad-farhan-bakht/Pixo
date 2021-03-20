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
package com.farhan.pixo.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farhan.pixo.databinding.GalleryListViewBinding
import com.farhan.pixo.model.Images

class GalleryAdapter(private val onClickItem: (view: View, imageUrl:String) -> Unit) : PagingDataAdapter<Images, ItemViewHolder>(GalleryAdapter) {
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
            holder.bind(it,onClickItem)
        }
    }
}

class ItemViewHolder(private val binding: GalleryListViewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(image: Images, onClickItem: (view: View, imageUrl:String) -> Unit) {
        binding.image = image
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickItem(binding.imgGalleryImage,image.imageUrl)
        }
    }
}