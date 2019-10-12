package com.albertmiro.driversapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.domain.usecases.GetVehicles
import com.albertmiro.driversapp.ui.base.viewmodel.BaseViewModel
import com.albertmiro.driversapp.ui.taximap.VehiclesMap
import com.albertmiro.driversapp.ui.taxis.VehiclesList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class VehiclesViewModel(val getVehicles: GetVehicles) : BaseViewModel(),
    VehiclesList.ViewModel,
    VehiclesMap.ViewModel {

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    private var vehicles: MutableLiveData<List<Vehicle>> = MutableLiveData()
    private var vehicleId: MutableLiveData<Int> = MutableLiveData()

    override fun isDataLoading(): LiveData<Boolean> = isDataLoading

    override fun isNetworkError(): LiveData<Boolean> = isNetworkError

    override fun isUnknownError(): LiveData<Boolean> = isUnknownError

    override fun setCurrentTaxiId(taxiId: Int) = this.vehicleId.postValue(taxiId)

    override fun getTaxis(): LiveData<List<Vehicle>> = vehicles

    override fun getCurrentVehicleId() = vehicleId

    override fun loadTaxis(forceRefresh: Boolean) {
        compositeDisposable.add(getVehicles.invoke(forceRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isDataLoading.setValue(true) }
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) }
            ))
    }

    override fun onSuccess(result: List<Vehicle>?) {
        vehicles.postValue(result)
        isDataLoading.postValue(false)
        isNetworkError.postValue(false)
        isUnknownError.postValue(false)
    }

    override fun onError(error: Throwable?) {
        isDataLoading.postValue(false)
        when (error) {
            is UnknownHostException -> isNetworkError.postValue(true)
            else -> isUnknownError.postValue(true)
        }
    }
}
