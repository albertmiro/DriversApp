package com.albertmiro.data.repository

import com.albertmiro.data.MyTaxiService
import com.albertmiro.data.mapper.toVehicleList
import com.albertmiro.data.model.MyTaxiResponse
import com.albertmiro.domain.TaxiVehiclesRepository
import com.albertmiro.domain.domain.Vehicle
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/*
* Repository using a base Cache with the list of taxis retrieved in the last call,
* if we want to refresh and clean the values, sending the forceRefresh flag it will
* make the call to the service and update the results
*/

@Singleton
class TaxiVehiclesRepositoryImpl @Inject constructor(
    private val myTaxiService: MyTaxiService
) : TaxiVehiclesRepository {

    var cachedTaxis: List<Vehicle> = emptyList()

    override fun getHamburgTaxis(forceRefresh: Boolean): Single<List<Vehicle>> {
        return if (cachedTaxis.isEmpty() || forceRefresh) {
            myTaxiService.getVehicles()
                .map { response: MyTaxiResponse ->
                    cachedTaxis = toVehicleList(response)
                    cachedTaxis
                }
        } else {
            Single.just(cachedTaxis)
        }
    }

}