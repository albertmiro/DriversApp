package com.albertmiro.driversapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.domain.usecases.GetVehicles
import com.albertmiro.driversapp.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class VehiclesViewModel(val getVehicles: GetVehicles) : BaseViewModel() {

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    private var vehicles: MutableLiveData<List<Vehicle>> = MutableLiveData()
    private var vehicleId: MutableLiveData<Int> = MutableLiveData()

    fun isDataLoading(): LiveData<Boolean> = isDataLoading

    fun isNetworkError(): LiveData<Boolean> = isNetworkError

    fun isUnknownError(): LiveData<Boolean> = isUnknownError

    fun setCurrentTaxiId(taxiId: Int) = this.vehicleId.postValue(taxiId)

    fun getVehicles(): LiveData<List<Vehicle>> = vehicles

    fun getCurrentVehicleId() = vehicleId

    fun loadVehicles(forceRefresh: Boolean) {
        compositeDisposable.add(getVehicles.invoke(forceRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isDataLoading.setValue(true) }
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) }
            ))
    }

    private fun onSuccess(result: List<Vehicle>?) {
        vehicles.postValue(result)
        isDataLoading.postValue(false)
        isNetworkError.postValue(false)
        isUnknownError.postValue(false)
    }

    private fun onError(error: Throwable?) {
        isDataLoading.postValue(false)
        when (error) {
            is UnknownHostException -> isNetworkError.postValue(true)
            else -> isUnknownError.postValue(true)
        }
    }
}
