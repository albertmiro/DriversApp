package com.albertmiro.driversapp.ui.taximap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.ui.base.viewmodel.Base

interface VehiclesMap {
    interface View : Base.View {
        fun showTaxis()
    }
    interface ViewModel {
        fun isDataLoading(): LiveData<Boolean>
        fun isNetworkError(): LiveData<Boolean>
        fun isUnknownError(): LiveData<Boolean>
        fun setCurrentTaxiId(taxiId: Int)
        fun getTaxis(): LiveData<List<Vehicle>>
        fun getCurrentVehicleId() : MutableLiveData<Int>
        fun loadTaxis(forceRefresh: Boolean)
        fun onError(error: Throwable?)
        fun onSuccess(result: List<Vehicle>?)
    }
}