package com.farhan.pixo.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.farhan.pixo.R
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private var mToolbarHeight = 0
    private var mAnimDuration = 400L
    private var mVaActionBar: ValueAnimator? = null

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        toolbar = findViewById(R.id.main_toolbar)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            // Hide Parent Toolbar for Preview Fragment
            if (nd.id == R.id.previewFragment) {
                supportActionBar?.isShowing?.let {
                    if (it)
                        hideActionBar()
                }

            } else {
                supportActionBar?.isShowing?.let {
                    if (!it)
                        showActionBar()
                }
            }
        }
    }


    private fun hideActionBar() {
        // initialize `mToolbarHeight`
        if (mToolbarHeight == 0) {
            mToolbarHeight = toolbar.height
        }
        if (mVaActionBar != null && mVaActionBar!!.isRunning) {
            // we are already animating a transition - block here
            return
        }

        // animate `Toolbar's` height to zero.
        mVaActionBar = ValueAnimator.ofInt(mToolbarHeight, 0)
        mVaActionBar?.addUpdateListener { animation -> // update LayoutParams
            (toolbar.layoutParams as AppBarLayout.LayoutParams).height =
                (animation.animatedValue as Int)
            toolbar.requestLayout()
        }
        mVaActionBar?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (supportActionBar != null) { // sanity check
                    supportActionBar!!.hide()
                }
            }
        })
        mVaActionBar?.duration = mAnimDuration
        mVaActionBar?.start()
    }

    private fun showActionBar() {
        if (mVaActionBar != null && mVaActionBar!!.isRunning) {
            // we are already animating a transition - block here
            return
        }

        // restore `Toolbar's` height
        mVaActionBar = ValueAnimator.ofInt(0, mToolbarHeight)
        mVaActionBar?.addUpdateListener { animation -> // update LayoutParams
            (toolbar.layoutParams as AppBarLayout.LayoutParams).height =
                (animation.animatedValue as Int)
            toolbar.requestLayout()
        }
        mVaActionBar?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                if (supportActionBar != null) { // sanity check
                    supportActionBar!!.show()
                }
            }
        })
        mVaActionBar?.duration = mAnimDuration
        mVaActionBar?.start()
    }

}