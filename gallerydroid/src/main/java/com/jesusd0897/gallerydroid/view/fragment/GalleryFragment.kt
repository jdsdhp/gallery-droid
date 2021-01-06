package com.jesusd0897.gallerydroid.view.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jesusd0897.gallerydroid.model.GalleryDroid
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.util.navToPictureDetail
import com.jesusd0897.gallerydroid.view.adapter.PicturesAdapter
import com.jesusd0897.gallerydroid.view.decoration.GalleryRecyclerDecoration
import com.jesusd0897.gallerydroid.view.viewmodel.GalleryViewModel
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
    private var adapter: PicturesAdapter? = null
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
        adapter = PicturesAdapter(object : OnPictureItemClickListener {
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
        })
    }

    override fun initRecyclerViews() {
        val columns =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                galleryDroid?.portraitColumns ?: 3
            else galleryDroid?.landscapeColumns ?: 4
        val linearLayout = GridLayoutManager(context, columns)
        binding?.recycler?.layoutManager = linearLayout
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