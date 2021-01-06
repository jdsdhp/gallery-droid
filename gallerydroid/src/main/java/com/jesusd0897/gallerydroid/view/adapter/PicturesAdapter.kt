/*
 * Copyright (c) 2020 jesusd0897.
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

package com.jesusd0897.gallerydroid.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.jesusd0897.gallerydroid.R
import com.jesusd0897.gallerydroid.databinding.ItemImageBinding
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.view.fragment.OnPictureItemClickListener
import com.jesusd0897.kutil.extension.gone
import com.jesusd0897.kutil.extension.setSafeOnClickListener
import com.jesusd0897.kutil.extension.visible
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class PicturesAdapter(
    var itemClickListener: OnPictureItemClickListener? = null,
    var loadingPlaceholder: Drawable? = null,
    var errorPlaceholder: Drawable? = null,
    var cornerRadius: Float = 0f,
    var cardElevation: Float = 0f,
    var networkPolicies: Array<out NetworkPolicy> = arrayOf(),
    @LayoutRes var decoratorLayout: Int? = null,
) : RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {

    private var items: List<Picture> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PictureViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        items[position].let { holder.bind(it) }
    }

    override fun getItemCount() = items.size

    fun submitList(items: List<Picture>?) {
        this.items = items ?: listOf()
        notifyDataSetChanged()
    }

    inner class PictureViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var decoratorLabel: TextView? = null
        private var decoratorBackground: View? = null

        init {
            val view = LayoutInflater.from(itemView.context).inflate(decoratorLayout!!, null, false)

            decoratorLabel = view.findViewById(R.id.label_view)
            decoratorBackground = view.findViewById(R.id.background_view)

            if (decoratorLayout != null) binding.decoratorContainerView.addView(view)
            else binding.decoratorContainerView.removeView(view)

            itemView.setSafeOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener?.onClick(
                        items[bindingAdapterPosition],
                        bindingAdapterPosition
                    )
                }
            }
            itemView.setOnLongClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    items[bindingAdapterPosition].let { booking ->
                        itemClickListener?.onLongClick(booking, bindingAdapterPosition)
                    }
                }
                true
            }
        }

        fun bind(item: Picture) = binding.apply {
            root.radius = cornerRadius
            root.cardElevation = cardElevation
            val policy = if (!networkPolicies.isNullOrEmpty()) networkPolicies[0] else null
            val policies =
                if (networkPolicies.size > 1) networkPolicies.copyOfRange(1, networkPolicies.size)
                else arrayOf()
            val picasso = Picasso.get()
                .load(item.fileThumbURL)
                .apply {
                    loadingPlaceholder?.let { placeholder(it) }
                    errorPlaceholder?.let { error(it) }
                }
            if (policy != null) picasso.networkPolicy(policy, *policies)
            picasso.into(binding.imageViewDownloader)

            if (decoratorLayout != null && !item.fileName.isNullOrBlank()) {
                decoratorLabel?.text = item.fileName
                decoratorBackground?.visible()
            } else decoratorBackground?.gone()
        }

    }

}