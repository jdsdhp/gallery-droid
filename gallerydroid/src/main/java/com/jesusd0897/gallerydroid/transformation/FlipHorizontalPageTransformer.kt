package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager

class FlipHorizontalPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val rotation = 180f * pos
        page.alpha = if (rotation > 90f || rotation < -90f) 0f else 1f
        page.pivotX = page.width * 0.5f
        page.pivotY = page.height * 0.5f
        page.rotationY = rotation
    }
}