package com.albertmiro.driversapp.ui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.albertmiro.domain.domain.FleetType
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R

fun bindTaxi(
    vehicle: Vehicle,
    vehicleHeader: TextView,
    vehicleDescription: TextView,
    vehicleImage: ImageView
) {
    val vehicleCapacity = getTaxiCapacity(vehicle.fleetType)
    val drawableId = getTaxiImage(vehicle.fleetType, vehicle.id)

    vehicleHeader.text =
        getString(vehicleHeader.context, R.string.vehicle_license, vehicle.id)
    vehicleDescription.text = getString(
        vehicleDescription.context,
        R.string.passengers_capacity,
        vehicleCapacity
    )
    vehicleImage.setImageResource(drawableId)
}

fun getTaxiCapacity(fleetType: FleetType): Int {
    val smallCapacity = 2
    val mediumCapacity = 4
    val bigCapacity = 6
    return when (fleetType) {
        FleetType.TAXI -> mediumCapacity
        FleetType.POOLING -> smallCapacity
        else -> bigCapacity
    }
}

fun getTaxiImage(fleetType: FleetType, vehicleId: Int): Int {
    return when (fleetType) {
        FleetType.TAXI -> {
            when {
                isMod2(vehicleId) -> R.drawable.vehicle1
                else -> R.drawable.vehicle2
            }
        }
        FleetType.POOLING -> {
            when {
                isMod2(vehicleId) -> R.drawable.vehicle3
                else -> R.drawable.vehicle4
            }
        }
        else -> R.drawable.vehicle1
    }
}

private fun isMod2(num: Int) = num % 2 == 1

fun getString(context: Context, stringId: Int, value: Int): String =
    String.format(context.getString(stringId), value)
