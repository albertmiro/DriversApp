package com.albertmiro.driversapp.ui.common

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_taxi_map.*

open class BaseMapFragment : BaseFragment(), OnMapReadyCallback {

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