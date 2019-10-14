package com.albertmiro.driversapp.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Vehicle
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
        fun getVehicles(): LiveData<List<Vehicle>>
        fun getCurrentVehicleId(): MutableLiveData<Int>
        fun loadVehicles(forceRefresh: Boolean)
        fun onError(error: Throwable?)
        fun onSuccess(result: List<Vehicle>?)
    }
}