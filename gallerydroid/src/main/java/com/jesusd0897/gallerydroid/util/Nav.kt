package com.jesusd0897.gallerydroid.util

import android.content.Context
import android.content.Intent
import com.jesusd0897.gallerydroid.model.PageTransformer
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.view.activity.PictureDetailActivity
import com.jesusd0897.kutil.EXTRA_ITEMS_TAG
import com.jesusd0897.kutil.EXTRA_ITEM_TAG
import com.jesusd0897.kutil.EXTRA_POSITION_TAG

fun navToPictureDetail(
    context: Context,
    pictures: ArrayList<Picture>,
    position: Int = 0,
    transformerId: Int? = PageTransformer.DEPTH,
    useLabels: Boolean? = null,
) = context.startActivity(
    Intent(context, PictureDetailActivity::class.java)
        .putParcelableArrayListExtra(EXTRA_ITEMS_TAG, pictures)
        .putExtra(EXTRA_POSITION_TAG, position)
        .putExtra(EXTRA_ITEM_TAG, transformerId)
        .putExtra(EXTRA_USE_LABEL_TAG, useLabels)
)