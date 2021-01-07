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

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jesusd0897.gallerydroid.R
import com.jesusd0897.gallerydroid.databinding.FragmentRecyclerBinding
import com.jesusd0897.kutil.extension.gone
import com.jesusd0897.kutil.extension.visible

abstract class BasicFragment : Fragment() {

    protected abstract val viewModel: ViewModel

}

abstract class RecyclerFragment : BasicFragment() {

    private var _binding: FragmentRecyclerBinding? = null
    protected val binding get() = _binding

    protected open var emptyTitle: String? = null
    protected open var emptySubtitle: String? = null
    protected open var emptyIcon: Drawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        emptyTitle = getString(R.string.no_items_found_title)
        emptySubtitle = getString(R.string.no_items_found)

        binding?.apply {
            initPlaceholder()
            initAdapters()
            initRecyclerViews()
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun initPlaceholder() {
        binding?.emptyPlaceholderImage?.setImageDrawable(emptyIcon)
        binding?.emptyPlaceholderTile?.text = emptyTitle
        binding?.emptyPlaceholderSubtitle?.text = emptySubtitle
    }

    protected open fun updateEmptyPlaceholder(size: Int?) {
        if (size ?: 0 > 0) binding?.emptyPlaceholder?.gone()
        else binding?.emptyPlaceholder?.visible()
    }

    protected abstract fun initAdapters()
    protected abstract fun initRecyclerViews()
    abstract fun refreshData()

}