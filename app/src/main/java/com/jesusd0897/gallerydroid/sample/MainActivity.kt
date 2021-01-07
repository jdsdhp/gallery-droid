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

package com.jesusd0897.gallerydroid.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.jesusd0897.gallerydroid.model.GalleryDroid
import com.jesusd0897.gallerydroid.model.Picture
import com.jesusd0897.gallerydroid.sample.databinding.ActivityMainBinding
import com.jesusd0897.gallerydroid.view.fragment.GalleryFragment
import com.jesusd0897.gallerydroid.view.fragment.OnPictureItemClickListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.container, GalleryFragment.newInstance())
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is GalleryFragment) {
            fragment.injectGallery(
                GALLERY_DROID
                    .layoutManager(GalleryDroid.LAYOUT_STAGGERED_GRID)
                    .loadingPlaceholder(
                        ContextCompat.getDrawable(this, R.drawable.ic_custom_loading_placeholder)
                    )
                    .errorPlaceholder(
                        ContextCompat.getDrawable(this, R.drawable.ic_custom_error_placeholder)
                    )
                    .pictureCornerRadius(16f)
                    .pictureElevation(8f)
                    .transformer(GalleryDroid.TRANSFORMER_CUBE_OUT)
                    .spacing(12)
                    .portraitColumns(2)
                    .landscapeColumns(4)
                    .emptyTitle("Upps")
                    .emptySubTitle(getString(R.string.no_items_found))
                    .emptyIcon(ContextCompat.getDrawable(this, R.drawable.ic_round_find_in_page))
                    .decoratorLayout(R.layout.custom_decorator)
                    .autoClickHandler(true)
                    .useLabels(false)

            )

            fragment.onItemClickListener = object : OnPictureItemClickListener {
                override fun onClick(picture: Picture, position: Int) {
                    Toast.makeText(this@MainActivity, "onClick = $picture", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onLongClick(picture: Picture, position: Int) {
                    Toast.makeText(this@MainActivity, "onLongClick = $picture", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

}