package com.albertmiro.data.repository

import com.albertmiro.data.VehiclesService
import com.albertmiro.data.mapper.toVehicleList
import com.albertmiro.data.entities.VehiclesEntity
import com.albertmiro.domain.VehiclesRepository
import com.albertmiro.domain.models.Vehicle
import io.reactivex.Single

/*
* Repository using a base Cache with the list of taxis retrieved in the last call,
* if we want to refresh and clean the values, sending the forceRefresh flag it will
* make the call to the service and update the results
*/

class VehiclesRepositoryImpl(val service: VehiclesService) : VehiclesRepository {

    var cachedTaxis: List<Vehicle> = emptyList()

    override fun getVehicles(forceRefresh: Boolean): Single<List<Vehicle>> {
        return if (cachedTaxis.isEmpty() || forceRefresh) {
            service.getVehicles()
                .map { response: VehiclesEntity ->
                    cachedTaxis = toVehicleList(response)
                    cachedTaxis
                }
        } else {
            Single.just(cachedTaxis)
        }
    }

}