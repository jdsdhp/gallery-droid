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