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