package com.albertmiro.driversapp.ui.vehicles.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.driversapp.ui.base.adapter.ViewWrapper

class VehiclesAdapter : ListAdapter<Vehicle, ViewWrapper<VehicleItemView>>(DiffCallback()) {

    var onClickAction: ((Vehicle) -> Unit)? = null

    private val items = emptyList<Vehicle>().toMutableList()

    fun setItems(newItems: List<Vehicle>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewWrapper<VehicleItemView> {
        return ViewWrapper(
            onCreateItemView(
                parent,
                viewType
            )
        )
    }

    override fun onBindViewHolder(holder: ViewWrapper<VehicleItemView>, position: Int) {
        val vehicle = items[position]

        holder.view.apply {
            bind(vehicle)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }

    override fun getItemCount() = items.size

    private fun onCreateItemView(parent: ViewGroup, viewType: Int) =
        VehicleItemView(context = parent.context)

    class DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }

    }
}