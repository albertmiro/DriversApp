package com.albertmiro.driversapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albertmiro.driversapp.repository.TaxiVehiclesRepositoryImpl
import com.albertmiro.domain.domain.Vehicle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class MyTaxiViewModel @Inject constructor(
    private val repository: TaxiVehiclesRepositoryImpl
) : ViewModel() {

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    private var taxis: MutableLiveData<List<Vehicle>> = MutableLiveData()
    private var taxiId: MutableLiveData<Int> = MutableLiveData()

    fun isDataLoading(): LiveData<Boolean> = isDataLoading

    fun isNetworkError(): LiveData<Boolean> = isNetworkError

    fun isUnknownError(): LiveData<Boolean> = isUnknownError

    fun setCurrentTaxiId(taxiId: Int) = this.taxiId.postValue(taxiId)

    fun getTaxis(): LiveData<List<Vehicle>> = taxis

    fun getCurrentTaxiId() = taxiId

    fun loadTaxis(forceRefresh: Boolean) {
        repository.getHamburgTaxis(forceRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe({ isDataLoading.setValue(true) })
            .subscribe(
                { result ->
                    taxis.postValue(result)
                    isDataLoading.postValue(false)
                    isNetworkError.postValue(false)
                    isUnknownError.postValue(false)
                },
                { error ->
                    isDataLoading.postValue(false)
                    when (error) {
                        is UnknownHostException -> isNetworkError.postValue(true)
                        else -> isUnknownError.postValue(true)
                    }
                }
            )
    }
}
