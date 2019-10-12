package com.albertmiro.driversapp.ui.taxis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.BindVehicleUtils
import kotlinx.android.synthetic.main.item_vehicle.view.*


class VehicleItemView constructor(context: Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_vehicle, this, true)
    }

    fun bind(vehicle: Vehicle) {
        BindVehicleUtils.bindVehicle(vehicle, vehicleHeader, vehicleDescription, vehicleImage)
    }

}