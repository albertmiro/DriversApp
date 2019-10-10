package com.albertmiro.driversapp.ui.taxis.adapter

import android.view.ViewGroup
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.ui.base.adapter.RecyclerViewAdapterBase
import com.albertmiro.driversapp.ui.base.adapter.ViewWrapper

class VehiclesAdapter : RecyclerViewAdapterBase<Vehicle, VehicleItemView>() {

    var onClickAction: ((Vehicle) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int) =
        VehicleItemView(context = parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<VehicleItemView>, position: Int) {
        val taxi = items[position]

        holder.view.apply {
            bind(taxi)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }
}