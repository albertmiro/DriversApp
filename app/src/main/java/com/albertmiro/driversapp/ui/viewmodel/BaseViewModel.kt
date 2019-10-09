package com.albertmiro.driversapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(protected val app: Application) : AndroidViewModel(app), Base.ViewModel {

    override fun onNetworkError(exception: Throwable) {}

    override fun onUnknownError(exception: Throwable) {}

}