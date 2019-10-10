package com.albertmiro.driversapp.ui.vehiclemap

import android.os.Bundle
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.bindTaxi
import com.albertmiro.driversapp.ui.getTaxiCapacity
import com.albertmiro.driversapp.ui.taximap.BaseMapFragment
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.fragment_vehicle_map.*
import kotlinx.android.synthetic.main.item_vehicle.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VehiclesMapFragment : BaseMapFragment() {

    override val layoutId: Int = R.layout.fragment_vehicle_map

    private val vehiclesViewModel: VehiclesViewModel by sharedViewModel()

    private val vehicleId by lazy { vehiclesViewModel.getCurrentVehicleId().value ?: -1 }
    private val vehicles by lazy { vehiclesViewModel.getTaxis().value }

    companion object {
        fun newInstance(): VehiclesMapFragment {
            return VehiclesMapFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showActionBar()
        initMapView(savedInstanceState)
        showTaxis()
    }

    private fun showActionBar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showTaxis() {
        vehicles?.let {
            if (it.isEmpty()) {
                context?.showMessage(getString(R.string.no_vehicles))
            } else {
                if (isMapReady) {
                    showTaxisOnMap()
                } else {
                    waitingForMap = true
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        super.onMapReady(googleMap)
        if (waitingForMap) {
            showTaxisOnMap()
        }
    }

    private fun showTaxisOnMap() {
        lateinit var vehicle: Vehicle
        vehicles?.forEach {
            if (it.id == vehicleId) vehicle = it
            else addMarkerOnMap(it, false)
        }
        zoomOnSelectedTaxi(vehicle, ::addMarkerOnMap)
        bindTaxi(vehicle, vehicleHeader, vehicleDescription, vehicleImage)
    }

    private fun addMarkerOnMap(vehicle: Vehicle, showInfo: Boolean) {
        val capacity = getTaxiCapacity(vehicle.fleetType)
        addMarkerAndShowInfo(vehicle, capacity, showInfo)
        setOnMarkerClickListener(::setMarkerInfo)
        setOnInfoWindowCloseListener()
    }

    private fun setMarkerInfo(marker: Marker) {
        val vehicleWithId = vehicles?.firstOrNull { vehicle -> vehicle.id == marker.tag }
        vehicleWithId?.let {
            rootView.isVisible(true)
            bindTaxi(vehicleWithId, vehicleHeader, vehicleDescription, vehicleImage)
        }
    }

}
