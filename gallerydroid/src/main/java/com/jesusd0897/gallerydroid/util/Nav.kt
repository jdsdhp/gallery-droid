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

package com.jesusd0897.gallerydroid.util

import android.content.Context
import android.content.Intent
import com.jesusd0897.gallerydroid.model.GalleryDroid.Companion.TRANSFORMER_DEPTH
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.view.activity.PictureDetailActivity
import com.jesusd0897.kutil.EXTRA_ITEMS_TAG
import com.jesusd0897.kutil.EXTRA_ITEM_TAG
import com.jesusd0897.kutil.EXTRA_POSITION_TAG

const val EXTRA_USE_LABEL_TAG = "use_label_extra"

fun navToPictureDetail(
    context: Context,
    pictures: ArrayList<Picture>,
    position: Int = 0,
    transformerId: Int? = TRANSFORMER_DEPTH,
    useLabels: Boolean? = null,
) = context.startActivity(
    Intent(context, PictureDetailActivity::class.java)
        .putParcelableArrayListExtra(EXTRA_ITEMS_TAG, pictures)
        .putExtra(EXTRA_POSITION_TAG, position)
        .putExtra(EXTRA_ITEM_TAG, transformerId)
        .putExtra(EXTRA_USE_LABEL_TAG, useLabels)
)