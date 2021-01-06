package com.jesusd0897.gallerydroid.view.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jesusd0897.kutil.model.StatusResource
import kotlinx.coroutines.Dispatchers

abstract class LiveCoroutinesViewModel(app: Application) : AndroidViewModel(app) {

    val statusResource: MutableLiveData<StatusResource> = MutableLiveData(StatusResource.idle())

    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }
}
