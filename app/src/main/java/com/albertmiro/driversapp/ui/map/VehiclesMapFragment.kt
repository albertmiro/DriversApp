package com.albertmiro.driversapp.ui.map

import android.os.Bundle
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.BindVehicleUtils
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
    private val vehicles by lazy { vehiclesViewModel.getVehicles().value }

    companion object {
        fun newInstance(): VehiclesMapFragment {
            return VehiclesMapFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showActionBar()
        initMapView(savedInstanceState)
        showVehicles()
    }

    private fun showActionBar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showVehicles() {
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
        var vehicle: Vehicle? = null
        vehicles?.forEach {
            if (it.id == vehicleId) vehicle = it
            else addMarkerOnMap(it, false)
        }
        vehicle?.let {
            zoomOnSelectedTaxi(it, ::addMarkerOnMap)
            BindVehicleUtils.bindVehicle(it, vehicleHeader, vehicleDescription, vehicleImage)
        }
    }

    private fun addMarkerOnMap(vehicle: Vehicle, showInfo: Boolean) {
        val capacity = BindVehicleUtils.getVehicleCapacity(vehicle.fleetType)
        addMarkerAndShowInfo(vehicle, capacity, showInfo)
        setOnMarkerClickListener(::setMarkerInfo)
        setOnInfoWindowCloseListener()
    }

    private fun setMarkerInfo(marker: Marker) {
        val vehicleWithId = vehicles?.firstOrNull { vehicle -> vehicle.id == marker.tag }
        vehicleWithId?.let {
            rootView.isVisible(true)
            BindVehicleUtils.bindVehicle(
                vehicleWithId,
                vehicleHeader,
                vehicleDescription,
                vehicleImage
            )
        }
    }

}
