package com.jesusd0897.gallerydroid.model

import android.graphics.drawable.Drawable
import androidx.viewpager.widget.ViewPager
import com.jesusd0897.gallerydroid.R
import com.jesusd0897.gallerydroid.transformation.DepthPageTransformer
import com.squareup.picasso.NetworkPolicy

class Decorator(var label: String) {

    var labelColor: Int = R.color.white
    var backgroundColor: Int = R.color.black
    var maxLines: Int = 2


    var tag: String? = null
    var transformer: ViewPager.PageTransformer = DepthPageTransformer()
    var emptyTitle: String? = null
    var emptySubTitle: String? = null
    var networkPolicies: Array<out NetworkPolicy> = arrayOf()
    var emptyIcon: Drawable? = null
    var placeholder: Drawable? = null

    var landscapeColumnsCount: Int = 4
    var spacing: Int = 0
    var pictureCornerRadius: Float = 0f

    fun tag(tag: String): Decorator {
        this.tag = tag
        return this
    }

    fun transformer(transformer: ViewPager.PageTransformer): Decorator {
        this.transformer = transformer
        return this
    }

    fun emptyTitle(emptyTitle: String?): Decorator {
        this.emptyTitle = emptyTitle
        return this
    }

    fun emptySubTitle(emptySubTitle: String?): Decorator {
        this.emptySubTitle = emptySubTitle
        return this
    }

    fun emptyIcon(emptyIcon: Drawable?): Decorator {
        this.emptyIcon = emptyIcon
        return this
    }


    fun placeholder(placeholder: Drawable?): Decorator {
        this.placeholder = placeholder
        return this
    }

    fun networkPolicies(vararg networkPolicies: NetworkPolicy): Decorator {
        this.networkPolicies = networkPolicies
        return this
    }

    fun portraitColumnsCount(portraitColumnsCount: Int): Decorator {
        this.backgroundColor = portraitColumnsCount
        return this
    }

    fun landscapeColumnsCount(landscapeColumnsCount: Int): Decorator {
        this.landscapeColumnsCount = landscapeColumnsCount
        return this
    }

    fun spacing(spacing: Int): Decorator {
        this.spacing = spacing
        return this
    }

    fun pictureCornerRadius(pictureCornerRadius: Float): Decorator {
        this.pictureCornerRadius = pictureCornerRadius
        return this
    }

}