package com.albertmiro.data.repository

import com.albertmiro.data.VehiclesService
import com.albertmiro.data.mapper.toVehicleList
import com.albertmiro.data.model.MyTaxiResponse
import com.albertmiro.domain.VehiclesRepository
import com.albertmiro.domain.domain.Vehicle
import io.reactivex.Single
import javax.inject.Singleton

/*
* Repository using a base Cache with the list of taxis retrieved in the last call,
* if we want to refresh and clean the values, sending the forceRefresh flag it will
* make the call to the service and update the results
*/

@Singleton
class TaxiVehiclesRepositoryImpl(val service: VehiclesService) : VehiclesRepository {

    var cachedTaxis: List<Vehicle> = emptyList()

    override fun getHamburgTaxis(forceRefresh: Boolean): Single<List<Vehicle>> {
        return if (cachedTaxis.isEmpty() || forceRefresh) {
            service.getVehicles()
                .map { response: MyTaxiResponse ->
                    cachedTaxis = toVehicleList(response)
                    cachedTaxis
                }
        } else {
            Single.just(cachedTaxis)
        }
    }

}