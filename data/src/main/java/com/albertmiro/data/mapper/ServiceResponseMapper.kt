package com.albertmiro.data.mapper

import com.albertmiro.data.model.MyTaxiResponse
import com.albertmiro.data.model.VehicleResponse
import com.albertmiro.domain.domain.FleetType
import com.albertmiro.domain.domain.Vehicle

/*
* Mapper methods to transform the service response to our model objects
*/

const val TAXI_TYPE = "TAXI"
const val POOLING_TYPE = "POOLING"

fun toVehicleList(response: MyTaxiResponse): List<Vehicle> {
    return response.poiList.map { toVehicle(it) }.toList()
}

fun toVehicle(vehicle: VehicleResponse): Vehicle {
    val fleetType: FleetType = when (vehicle.fleetType) {
        TAXI_TYPE -> FleetType.TAXI
        POOLING_TYPE -> FleetType.POOLING
        else -> FleetType.OTHER
    }

    return Vehicle(
        vehicle.id,
        vehicle.coordinate.latitude to vehicle.coordinate.longitude,
        fleetType,
        vehicle.heading
    )
}
