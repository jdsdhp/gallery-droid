package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager

class CubeOutPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        page.pivotX = if (pos < 0f) page.width.toFloat() else 0f
        page.pivotY = page.height * 0.5f
        page.rotationY = 90f * pos
    }
}