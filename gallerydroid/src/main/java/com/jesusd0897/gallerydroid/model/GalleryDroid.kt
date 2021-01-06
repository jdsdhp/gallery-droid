package com.jesusd0897.gallerydroid.model

import android.graphics.drawable.Drawable
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import com.jesusd0897.gallerydroid.R
import com.squareup.picasso.NetworkPolicy

object PageTransformer {
    const val DEPTH = 1
    const val ZOOM_IN = 2
    const val ZOOM_OUT = 3
    //const val CUBE_IN = 4
    const val CUBE_OUT = 5
    const val FLIP_HORIZONTAL = 6
    //const val FLIP_VERTICAL = 7
    const val FOREGROUND_TO_BACK = 8
    const val BACK_TO_FOREGROUND = 9
    //const val PARALLAX = 10
    const val ROTATE_DOWN = 11
    const val ROTATE_UP = 12
    const val TABLET = 13
}

class GalleryDroid(var items: List<Picture> = listOf()) {

    var tag: String? = null
    var transformer: Int? = PageTransformer.DEPTH
    var emptyTitle: String? = null
    var emptySubTitle: String? = null
    var networkPolicies: Array<out NetworkPolicy> = arrayOf()
    var emptyIcon: Drawable? = null
    var loadingPlaceholder: Drawable? = null
    var errorPlaceholder: Drawable? = null
    var portraitColumns: Int = 3
    var landscapeColumns: Int = 4
    var spacing: Int = 0
    var pictureCornerRadius: Float = 0f
    var pictureElevation: Float = 0f
    var autoClickHandler: Boolean = true
    var useLabels: Boolean = true

    @LayoutRes
    var decoratorLayout: Int = R.layout.default_decorator

    fun tag(tag: String): GalleryDroid {
        this.tag = tag
        return this
    }

    fun transformer(
        @IntRange(
            from = PageTransformer.DEPTH.toLong(),
            to = PageTransformer.ZOOM_OUT.toLong(),
        ) transformer: Int? = PageTransformer.DEPTH
    ): GalleryDroid {
        this.transformer = transformer
        return this
    }

    fun emptyTitle(emptyTitle: String?): GalleryDroid {
        this.emptyTitle = emptyTitle
        return this
    }

    fun emptySubTitle(emptySubTitle: String?): GalleryDroid {
        this.emptySubTitle = emptySubTitle
        return this
    }

    fun emptyIcon(emptyIcon: Drawable?): GalleryDroid {
        this.emptyIcon = emptyIcon
        return this
    }

    fun loadingPlaceholder(loadingPlaceholder: Drawable?): GalleryDroid {
        this.loadingPlaceholder = loadingPlaceholder
        return this
    }

    fun errorPlaceholder(errorPlaceholder: Drawable?): GalleryDroid {
        this.errorPlaceholder = errorPlaceholder
        return this
    }

    fun networkPolicies(vararg networkPolicies: NetworkPolicy): GalleryDroid {
        this.networkPolicies = networkPolicies
        return this
    }

    fun portraitColumns(portraitColumns: Int): GalleryDroid {
        this.portraitColumns = portraitColumns
        return this
    }

    fun landscapeColumns(landscapeColumns: Int): GalleryDroid {
        this.landscapeColumns = landscapeColumns
        return this
    }

    fun spacing(spacing: Int): GalleryDroid {
        this.spacing = spacing
        return this
    }

    fun pictureCornerRadius(pictureCornerRadius: Float): GalleryDroid {
        this.pictureCornerRadius = pictureCornerRadius
        return this
    }

    fun pictureElevation(pictureElevation: Float): GalleryDroid {
        this.pictureElevation = pictureElevation
        return this
    }

    fun decoratorLayout(@LayoutRes decoratorLayout: Int): GalleryDroid {
        this.decoratorLayout = decoratorLayout
        return this
    }

    fun autoClickHandler(autoClickHandler: Boolean): GalleryDroid {
        this.autoClickHandler = autoClickHandler
        return this
    }

    fun useLabels(useLabels: Boolean): GalleryDroid {
        this.useLabels = useLabels
        return this
    }

}