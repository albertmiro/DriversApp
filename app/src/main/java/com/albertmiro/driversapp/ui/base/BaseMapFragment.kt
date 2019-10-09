package com.albertmiro.driversapp.ui.base

import com.albertmiro.driversapp.ui.viewmodel.BaseViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_taxi_map.*

abstract class BaseMapFragment<T : BaseViewModel> : BaseFragment<T>(), OnMapReadyCallback {

    var isMapReady: Boolean = false
    var googleMap: GoogleMap? = null

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

    override fun onMapReady(googleMap: GoogleMap?) {
        isMapReady = true
        this.googleMap = googleMap
    }
}