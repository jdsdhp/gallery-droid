package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.min

class BackgroundToForegroundPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val height = page.height.toFloat()
        val width = page.width.toFloat()
        val scale: Float = min(if (pos < 0) 1f else abs(1f - pos), 1f)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = width * 0.5f
        page.pivotY = height * 0.5f
        page.translationX = if (pos < 0) width * pos else -width * pos * 0.25f
    }
}