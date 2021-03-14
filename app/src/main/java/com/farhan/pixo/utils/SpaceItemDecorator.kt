package com.farhan.pixo.utils

import android.graphics.Rect
import android.view.View
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SpaceItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        if (spanIndex == 0) {
            val marginParentEnd = parent.marginEnd
            layoutParams.marginEnd = marginParentEnd / 2
        } else {
            val marginParentStart = parent.marginStart
            layoutParams.marginStart = marginParentStart / 2
        }

        view.layoutParams = layoutParams
    }
}