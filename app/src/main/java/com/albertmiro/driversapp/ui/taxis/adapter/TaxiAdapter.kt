package com.albertmiro.driversapp.ui.taxis.adapter

import android.view.ViewGroup
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.ui.common.adapter.RecyclerViewAdapterBase
import com.albertmiro.driversapp.ui.common.adapter.ViewWrapper

class TaxiAdapter : RecyclerViewAdapterBase<Vehicle, TaxiItemView>() {

    var onClickAction: ((taxi: Vehicle) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int) =
        TaxiItemView(context = parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TaxiItemView>, position: Int) {
        val taxi = items[position]

        holder.view.apply {
            bind(taxi)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }
}