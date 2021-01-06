package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

private const val MAX_ROTATION = 90.0f

class ZoomInPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val scale = if (pos < 0) pos + 1f else abs(1f - pos)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = page.width * 0.5f
        page.pivotY = page.height * 0.5f
        page.alpha = if (pos < -1f || pos > 1f) 0f else 1f - (scale - 1f)
    }

}