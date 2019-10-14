package com.albertmiro.data.mapper

import com.albertmiro.data.entities.VehicleEntity
import com.albertmiro.data.entities.VehiclesEntity
import com.albertmiro.domain.models.FleetType
import com.albertmiro.domain.models.Vehicle

/*
* Mapper methods to transform the service response to our model objects
*/

const val TAXI_TYPE = "TAXI"
const val POOLING_TYPE = "POOLING"

fun toVehicleList(response: VehiclesEntity): List<Vehicle> {
    return response.poiList.map { toVehicle(it) }.toList()
}

fun toVehicle(vehicle: VehicleEntity): Vehicle {
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
