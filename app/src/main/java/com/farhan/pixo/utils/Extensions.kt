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

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.farhan.pixo.R

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Fragment.waitForTransition(targetView: View) {
    postponeEnterTransition()
    targetView.doOnPreDraw { startPostponedEnterTransition() }
}

fun Fragment.setExitToFullScreenTransition(){
    exitTransition =
        TransitionInflater.from(context).inflateTransition(R.transition.list_exit_transition)
}

fun Fragment.setReturnFromFullScreenTransition(){
    reenterTransition =
        TransitionInflater.from(context).inflateTransition(R.transition.list_return_transition)
}