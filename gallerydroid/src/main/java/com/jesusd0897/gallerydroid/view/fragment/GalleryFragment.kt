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

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.jesusd0897.gallerydroid.model.GalleryDroid
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.util.navToPictureDetail
import com.jesusd0897.gallerydroid.view.adapter.PicturesAdapter
import com.jesusd0897.gallerydroid.view.adapter.PicturesBaseAdapter
import com.jesusd0897.gallerydroid.view.adapter.StaggeredPicturesAdapter
import com.jesusd0897.gallerydroid.view.decoration.GalleryRecyclerDecoration
import com.jesusd0897.gallerydroid.view.viewmodel.GalleryViewModel
import com.jesusd0897.kutil.log
import com.jesusd0897.kutil.model.StatusResource

interface OnPictureItemClickListener {
    fun onClick(picture: Picture, position: Int)
    fun onLongClick(picture: Picture, position: Int) = Unit
}

class GalleryFragment : RecyclerFragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    override val viewModel: GalleryViewModel by viewModels()

    var onItemClickListener: OnPictureItemClickListener? = null
    private var adapter: PicturesBaseAdapter? = null
    private var galleryDroid: GalleryDroid? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.statusResource.observe(viewLifecycleOwner, {
            when (it) {
                StatusResource.error(message = it.message) -> {
                    adapter?.submitList(null)
                }
            }
        })
        viewModel.galleryDroid.observe(viewLifecycleOwner, { adapter?.submitList(it.items) })
        viewModel.itemsCount.observe(viewLifecycleOwner, { updateEmptyPlaceholder(it) })
        refreshData()
        return view
    }

    override fun initAdapters() {
        val clickListener = object : OnPictureItemClickListener {
            override fun onClick(picture: Picture, position: Int) {
                onItemClickListener?.onClick(picture, position)
                if (galleryDroid?.autoClickHandler == true)
                    navToPictureDetail(
                        context = requireContext(),
                        pictures = ArrayList(galleryDroid?.items ?: listOf()),
                        position = position,
                        transformerId = galleryDroid?.transformer,
                        useLabels = galleryDroid?.useLabels,
                    )
            }

            override fun onLongClick(picture: Picture, position: Int) {
                onItemClickListener?.onLongClick(picture, position)
            }
        }
        log("GalleryFragment", "${galleryDroid?.layoutManager}")
        adapter =
            if (galleryDroid?.layoutManager == GalleryDroid.LAYOUT_STAGGERED_GRID)
                StaggeredPicturesAdapter(clickListener)
            else PicturesAdapter(clickListener)
    }

    override fun initRecyclerViews() {
        val columns =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                galleryDroid?.portraitColumns ?: 3
            else galleryDroid?.landscapeColumns ?: 4

        val layoutManager = when (galleryDroid?.layoutManager) {
            GalleryDroid.LAYOUT_LINEAR -> LinearLayoutManager(requireContext())
            GalleryDroid.LAYOUT_STAGGERED_GRID -> StaggeredGridLayoutManager(columns, VERTICAL)
            else -> GridLayoutManager(requireContext(), columns)
        }

        binding?.recycler?.layoutManager = layoutManager
        binding?.recycler?.addItemDecoration(
            GalleryRecyclerDecoration(
                spanCount = columns,
                spacing = galleryDroid?.spacing ?: 0
            )
        )
        binding?.recycler?.adapter = adapter
    }

    override fun refreshData() {
        galleryDroid?.let {
            adapter?.decoratorLayout = it.decoratorLayout
            adapter?.cornerRadius = it.pictureCornerRadius
            adapter?.cardElevation = it.pictureElevation
            adapter?.loadingPlaceholder = it.loadingPlaceholder
            adapter?.errorPlaceholder = it.errorPlaceholder
            adapter?.networkPolicies = it.networkPolicies
            emptyTitle = it.emptyTitle
            emptySubtitle = it.emptySubTitle
            emptyIcon = it.emptyIcon
            viewModel.submitGallery(it)
        }
        initPlaceholder()
    }

    fun injectGallery(galleryDroid: GalleryDroid) {
        this.galleryDroid = galleryDroid
        refreshData()
    }

}