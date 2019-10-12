package com.albertmiro.driversapp.ui.taxis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.ui.base.viewmodel.Base

interface VehiclesList {
    interface View : Base.View {
        fun showVehicles(vehicles: List<Vehicle>)
        fun hideRefreshIcon()
        fun changeProgressBarVisibility(isVisible: Boolean)
        fun onUnknownError(isUnknownError: Boolean)
        fun onNetworkError(isNetworkError: Boolean)
    }

    interface ViewModel {
        fun isDataLoading(): LiveData<Boolean>
        fun isNetworkError(): LiveData<Boolean>
        fun isUnknownError(): LiveData<Boolean>
        fun setCurrentTaxiId(taxiId: Int)
        fun getTaxis(): LiveData<List<Vehicle>>
        fun getCurrentVehicleId(): MutableLiveData<Int>
        fun loadTaxis(forceRefresh: Boolean)
        fun onError(error: Throwable?)
        fun onSuccess(result: List<Vehicle>?)
    }
}