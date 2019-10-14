package com.albertmiro.driversapp.ui.base.viewmodel

interface Base {

    interface View

    interface ViewModel {
        fun onNetworkError(exception: Throwable)
        fun onUnknownError(exception: Throwable)
    }
}