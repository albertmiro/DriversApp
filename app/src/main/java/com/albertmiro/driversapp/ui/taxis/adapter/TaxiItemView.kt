package com.albertmiro.driversapp.ui.taxis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.albertmiro.driversapp.R
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.ui.bindTaxi
import kotlinx.android.synthetic.main.item_taxi.view.*


class TaxiItemView constructor(context: Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_taxi, this, true)
    }

    fun bind(taxi: Vehicle) {
        bindTaxi(taxi, taxiHeader, taxiDescription, taxiImage)
    }

}