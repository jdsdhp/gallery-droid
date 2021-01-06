package com.jesusd0897.gallerydroid.transformation

import android.view.View
import androidx.viewpager.widget.ViewPager

class ParallaxPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val pageWidth: Int = view.width
        when {
            position < -1 -> { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 1f
            }
            position <= 1 -> { // [-1,1]
                view.translationX = -position * (pageWidth / 2) //Half the normal speed
            }
            else -> { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 1f
            }
        }
    }
}