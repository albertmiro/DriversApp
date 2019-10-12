package com.albertmiro.driversapp.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), Base.ViewModel {

    val compositeDisposable = CompositeDisposable()

    override fun onNetworkError(exception: Throwable) {}

    override fun onUnknownError(exception: Throwable) {}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}