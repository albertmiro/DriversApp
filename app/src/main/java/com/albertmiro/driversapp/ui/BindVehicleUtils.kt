package com.albertmiro.driversapp.ui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.albertmiro.domain.models.FleetType
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.driversapp.R

object BindVehicleUtils {

    fun bindVehicle(
        vehicle: Vehicle,
        vehicleHeader: TextView,
        vehicleDescription: TextView,
        vehicleImage: ImageView
    ) {
        val vehicleCapacity = getVehicleCapacity(vehicle.fleetType)
        val drawableId = getVehicleImage(vehicle.fleetType, vehicle.id)

        vehicleHeader.text = getFormattedString(
            vehicleHeader.context,
            R.string.vehicle_license,
            vehicle.id
        )

        vehicleDescription.text = getFormattedString(
            vehicleDescription.context,
            R.string.passengers_capacity,
            vehicleCapacity
        )
        vehicleImage.setImageResource(drawableId)
    }

    fun getVehicleCapacity(fleetType: FleetType): Int {
        val smallCapacity = 2
        val mediumCapacity = 4
        val bigCapacity = 6
        return when (fleetType) {
            FleetType.TAXI -> mediumCapacity
            FleetType.POOLING -> smallCapacity
            else -> bigCapacity
        }
    }

    private fun getVehicleImage(fleetType: FleetType, vehicleId: Int): Int {
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
            else -> R.drawable.vehicle5
        }
    }

    private fun isMod2(num: Int) = num % 2 == 0

    private fun getFormattedString(context: Context, stringId: Int, value: Int): String =
        String.format(context.getString(stringId), value)
}