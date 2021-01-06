package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager

private const val ROTATION = -15f

class RotateDownPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width = page.width.toFloat()
        val rotation = ROTATION * position
        page.pivotX = width * 0.5f
        page.pivotY = 0f
        page.translationX = 0f
        page.rotation = rotation
    }

}