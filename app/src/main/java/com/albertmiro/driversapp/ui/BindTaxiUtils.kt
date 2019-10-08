package com.albertmiro.driversapp.ui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.albertmiro.driversapp.R
import com.albertmiro.domain.domain.FleetType
import com.albertmiro.domain.domain.Vehicle

fun bindTaxi(taxi: Vehicle, taxiHeader: TextView, taxiDescription: TextView, taxiImage: ImageView) {
    val taxiCapacity = getTaxiCapacity(taxi.fleetType)
    val drawableId = getTaxiImage(taxi.fleetType, taxi.id)

    taxiHeader.text =
        getString(taxiHeader.context, R.string.taxi_license, taxi.id)
    taxiDescription.text = getString(
        taxiDescription.context,
        R.string.passengers_capacity,
        taxiCapacity
    )
    taxiImage.setImageResource(drawableId)
}

fun getTaxiCapacity(fleetType: FleetType): Int {
    return when (fleetType) {
        FleetType.TAXI -> 4
        FleetType.POOLING -> 2
        else -> 6
    }
}

fun getTaxiImage(fleetType: FleetType, taxiId: Int): Int {
    return when (fleetType) {
        FleetType.TAXI -> {
            when {
                taxiId % 2 == 1 -> R.drawable.taxi1
                else -> R.drawable.taxi2
            }
        }
        FleetType.POOLING -> {
            when {
                taxiId % 2 == 1 -> R.drawable.taxi3
                else -> R.drawable.taxi4
            }
        }
        else -> R.drawable.taxi1
    }
}

fun getString(context: Context, stringId: Int, value: Int): String =
        String.format(context.getString(stringId), value)
