package com.farhan.pixo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
    }
}