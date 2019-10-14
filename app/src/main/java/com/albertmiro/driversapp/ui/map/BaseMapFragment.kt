package com.albertmiro.driversapp.ui.map

import android.os.Bundle
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.domain.models.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_vehicle_map.*

abstract class BaseMapFragment : BaseFragment(), OnMapReadyCallback {

    var isMapReady: Boolean = false
    var googleMap: GoogleMap? = null
    var waitingForMap: Boolean = false

    fun initMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
        super.onLowMemory()
    }

    fun setOnInfoWindowCloseListener() {
        googleMap?.setOnInfoWindowCloseListener {
            rootView.isVisible(false)
        }
    }

    fun setOnMarkerClickListener(setMarkerInfo: (Marker) -> Unit) {
        googleMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            setMarkerInfo(it)
            true
        }
    }

    fun addMarkerAndShowInfo(
        vehicle: Vehicle,
        capacity: Int,
        showInfo: Boolean
    ) {
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(vehicle.coordinates.first, vehicle.coordinates.second))
                .title(String.format(getString(R.string.title_info_poi), vehicle.id, capacity))
        )
        marker?.tag = vehicle.id
        if (showInfo) marker?.showInfoWindow()
    }

    fun zoomOnSelectedTaxi(vehicle: Vehicle, addMarkerOnMap: (Vehicle, Boolean) -> Unit) {
        val zoomLevel = 17f
        val cameraUpdate = CameraUpdateFactory
            .newLatLngZoom(LatLng(vehicle.coordinates.first, vehicle.coordinates.second), zoomLevel)
        googleMap?.animateCamera(cameraUpdate)
        addMarkerOnMap(vehicle, true)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        isMapReady = true
        this.googleMap = googleMap
    }
}