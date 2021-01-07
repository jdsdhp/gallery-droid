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

package com.jesusd0897.gallerydroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jesusd0897.gallerydroid.databinding.FragmentPictureBinding
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.view.activity.PictureDetailActivity
import com.jesusd0897.kutil.EXTRA_ITEM_TAG
import com.jesusd0897.kutil.EXTRA_POSITION_TAG
import com.squareup.picasso.Picasso

class PictureDetailFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(sectionNumber: Int, picture: Picture): PictureDetailFragment {
            val fragment = PictureDetailFragment()
            val args = Bundle()
            args.putInt(EXTRA_POSITION_TAG, sectionNumber)
            args.putParcelable(EXTRA_ITEM_TAG, picture)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var picture: Picture
    private var position = 0

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        position = args!!.getInt(EXTRA_POSITION_TAG)
        picture = args.getParcelable(EXTRA_ITEM_TAG)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        binding?.photoView?.let {
            it.setOnClickListener { (activity as PictureDetailActivity).handleDecorVisibility() }
            Picasso.get()
                .load(picture.fileURL)
                .into(it)
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}