package com.albertmiro.driversapp.ui.taximap

import android.os.Bundle
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.bindTaxi
import com.albertmiro.driversapp.ui.getTaxiCapacity
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_taxi_map.*
import kotlinx.android.synthetic.main.item_taxi.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TaxiMapFragment : BaseMapFragment() {

    override val layoutId: Int = R.layout.fragment_taxi_map
    private val vehiclesViewModel: VehiclesViewModel by sharedViewModel()

    private var vehicleId: Int = 0

    private var vehicles: List<Vehicle>? = null
    private var waitingForMap: Boolean = false
    private var isMapReady: Boolean = false
    private var googleMap: GoogleMap? = null

    companion object {
        fun newInstance(): TaxiMapFragment {
            return TaxiMapFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showActionBar()
        initMapView(savedInstanceState)
        initLocalVariables()
        showTaxis()
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        isMapReady = true
        this.googleMap = googleMap
        if (waitingForMap) {
            showTaxisOnMap()
        }
    }

    private fun showActionBar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initLocalVariables() {
        this.vehicleId = vehiclesViewModel.getCurrentVehicleId().value ?: -1
        this.vehicles = vehiclesViewModel.getTaxis().value
    }

    private fun showTaxis() {
        vehicles?.let {
            if (it.isEmpty()) {
                context?.showMessage(getString(R.string.no_taxis))
            } else {
                if (isMapReady) {
                    showTaxisOnMap()
                } else {
                    waitingForMap = true
                }
            }
        }
    }

    private fun showTaxisOnMap() {
        lateinit var taxi: Vehicle
        vehicles?.forEach {
            if (it.id == vehicleId) taxi = it
            else addMarkerOnMap(it, false)
        }
        zoomOnSelectedTaxi(taxi)
        bindTaxi(taxi, taxiHeader, taxiDescription, taxiImage)
    }

    private fun zoomOnSelectedTaxi(taxi: Vehicle) {
        val cameraUpdate = CameraUpdateFactory
            .newLatLngZoom(LatLng(taxi.coordinates.first, taxi.coordinates.second), 17f)
        googleMap?.animateCamera(cameraUpdate)
        addMarkerOnMap(taxi, true)
    }

    private fun addMarkerOnMap(vehicle: Vehicle, showInfo: Boolean) {
        val capacity = getTaxiCapacity(vehicle.fleetType)

        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(vehicle.coordinates.first, vehicle.coordinates.second))
                .title(String.format(getString(R.string.title_info_poi), vehicle.id, capacity))
        )

        marker?.tag = vehicle.id

        if (showInfo) marker?.showInfoWindow()

        googleMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            val vehicleWithId = vehicles?.firstOrNull { vehicle -> vehicle.id == it.tag }
            vehicleWithId?.let {
                rootView.isVisible(true)
                bindTaxi(vehicleWithId, taxiHeader, taxiDescription, taxiImage)
            }
            true
        }

        googleMap?.setOnInfoWindowCloseListener {
            rootView.isVisible(false)
        }
    }


}
