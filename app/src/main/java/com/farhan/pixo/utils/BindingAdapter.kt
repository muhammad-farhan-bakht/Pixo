package com.farhan.pixo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.farhan.pixo.R

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    transitionName = imageUrl
    Glide.with(this)
        .load(imageUrl)
        .apply(RequestOptions().transform(CenterCrop(),RoundedCorners(this.context.resources.getDimensionPixelSize(R.dimen.corner_radius))))
        .transition(withCrossFade())
        .into(this)
}