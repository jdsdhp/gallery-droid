/*
 * Copyright (c) 2021 jesusd0897.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jesusd0897.gallerydroid.model

import android.graphics.drawable.Drawable
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import com.jesusd0897.gallerydroid.R
import com.squareup.picasso.NetworkPolicy

class GalleryDroid(var items: List<Picture> = listOf()) {

    companion object {
        @IntDef(LAYOUT_LINEAR, LAYOUT_GRID, LAYOUT_STAGGERED_GRID)
        @Retention(AnnotationRetention.SOURCE)
        annotation class GalleryLayout

        const val LAYOUT_LINEAR = 1
        const val LAYOUT_GRID = 2
        const val LAYOUT_STAGGERED_GRID = 3

        @IntDef(
            TRANSFORMER_DEPTH,
            TRANSFORMER_ZOOM_IN,
            TRANSFORMER_ZOOM_OUT,
            TRANSFORMER_CUBE_OUT,
            TRANSFORMER_FLIP_HORIZONTAL,
            TRANSFORMER_FOREGROUND_TO_BACK,
            TRANSFORMER_BACK_TO_FOREGROUND,
            TRANSFORMER_ROTATE_DOWN,
            TRANSFORMER_ROTATE_UP,
            TRANSFORMER_TABLET,
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class PageTransformer

        const val TRANSFORMER_DEPTH = 1
        const val TRANSFORMER_ZOOM_IN = 2
        const val TRANSFORMER_ZOOM_OUT = 3
        const val TRANSFORMER_CUBE_OUT = 4
        const val TRANSFORMER_FLIP_HORIZONTAL = 5
        const val TRANSFORMER_FOREGROUND_TO_BACK = 6
        const val TRANSFORMER_BACK_TO_FOREGROUND = 7
        const val TRANSFORMER_ROTATE_DOWN = 8
        const val TRANSFORMER_ROTATE_UP = 9
        const val TRANSFORMER_TABLET = 10
        //const val CUBE_IN = 11
        //const val FLIP_VERTICAL = 12
        //const val PARALLAX = 13
    }

    var tag: String? = null
    var transformer: Int? = TRANSFORMER_DEPTH
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

    @GalleryLayout
    var layoutManager: Int? = LAYOUT_GRID

    @LayoutRes
    var decoratorLayout: Int? = R.layout.gallery_droid_default_decorator

    fun tag(tag: String): GalleryDroid {
        this.tag = tag
        return this
    }

    fun transformer(@PageTransformer transformer: Int? = TRANSFORMER_DEPTH): GalleryDroid {
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

    fun decoratorLayout(@LayoutRes decoratorLayout: Int?): GalleryDroid {
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

    fun layoutManager(@GalleryLayout layoutManager: Int?): GalleryDroid {
        this.layoutManager = layoutManager
        return this
    }

}