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