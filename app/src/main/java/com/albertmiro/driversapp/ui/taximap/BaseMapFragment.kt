package com.albertmiro.driversapp.ui.taximap

import com.albertmiro.driversapp.ui.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_taxi_map.*

abstract class BaseMapFragment : BaseFragment(), OnMapReadyCallback {

    private var isMapReady: Boolean = false
    private var googleMap: GoogleMap? = null

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


}