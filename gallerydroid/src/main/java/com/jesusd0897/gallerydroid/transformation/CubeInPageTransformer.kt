package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager

class CubeInPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // Rotate the fragment on the left or right edge
        page.pivotX = if (position > 0) 0f else page.width.toFloat()
        page.pivotY = 0f
        page.rotationY = -90f * position
    }
}