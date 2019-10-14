package com.albertmiro.driversapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.driversapp.ui.base.viewmodel.Base

interface VehiclesMap {
    interface View : Base.View {
        fun showVehicles()
    }
    interface ViewModel {
        fun isDataLoading(): LiveData<Boolean>
        fun isNetworkError(): LiveData<Boolean>
        fun isUnknownError(): LiveData<Boolean>
        fun setCurrentTaxiId(taxiId: Int)
        fun getVehicles(): LiveData<List<Vehicle>>
        fun getCurrentVehicleId() : MutableLiveData<Int>
        fun loadVehicles(forceRefresh: Boolean)
        fun onError(error: Throwable?)
        fun onSuccess(result: List<Vehicle>?)
    }
}