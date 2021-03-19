package com.farhan.pixo.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.toTransitionGroup() = this to transitionName

fun Fragment.waitForTransition(targetView: View) {
    postponeEnterTransition()
    targetView.doOnPreDraw { startPostponedEnterTransition() }
}
