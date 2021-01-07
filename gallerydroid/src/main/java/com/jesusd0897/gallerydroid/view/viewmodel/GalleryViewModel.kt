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

package com.jesusd0897.gallerydroid.view.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusd0897.gallerydroid.model.GalleryDroid
import com.jesusd0897.kutil.model.StatusResource
import kotlinx.coroutines.launch

class GalleryViewModel(app: Application) : LiveCoroutinesViewModel(app) {

    val galleryDroid: MutableLiveData<GalleryDroid> = MutableLiveData()
    val itemsCount = MutableLiveData(0)

    fun submitGallery(galleryDroid: GalleryDroid) = viewModelScope.launch {
        try {
            statusResource.postValue(StatusResource.loading())
            itemsCount.postValue(galleryDroid.items.size)
            this@GalleryViewModel.galleryDroid.postValue(galleryDroid)
            statusResource.postValue(StatusResource.success())
        } catch (e: Exception) {
            statusResource.postValue(StatusResource.error(e.message))
        }
    }

}