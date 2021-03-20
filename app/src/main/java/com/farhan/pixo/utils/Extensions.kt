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