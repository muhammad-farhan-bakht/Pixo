package com.farhan.pixo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    transitionName = imageUrl
    Glide.with(this)
        .load(imageUrl)
        .transition(withCrossFade())
        .into(this)
}