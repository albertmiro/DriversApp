package com.albertmiro.driversapp.ui.taximap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.di.Injectable
import com.albertmiro.driversapp.ui.bindTaxi
import com.albertmiro.driversapp.ui.common.BaseMapFragment
import com.albertmiro.driversapp.ui.getTaxiCapacity
import com.albertmiro.driversapp.ui.viewmodel.MyTaxiViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_taxi_map.*
import kotlinx.android.synthetic.main.item_taxi.*
import javax.inject.Inject

class TaxiMapFragment : BaseMapFragment(),
    Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var myTaxiViewModel: MyTaxiViewModel
    private var taxiId: Int = 0
    private var taxis: List<Vehicle>? = null
    private var waitingForMap: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_taxi_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showActionBar()
        initMapView(savedInstanceState)
        getViewModel()
        initLocalVariables()
        showTaxis()
    }

    private fun showActionBar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getViewModel() {
        myTaxiViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MyTaxiViewModel::class.java)
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    private fun initLocalVariables() {
        this.taxiId = myTaxiViewModel.getCurrentTaxiId().value!!
        this.taxis = myTaxiViewModel.getTaxis().value
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        super.onMapReady(googleMap)
        if (waitingForMap) {
            showTaxisOnMap()
        }
    }

    private fun showTaxis() {
        taxis?.let {
            if (it.isEmpty()) {
                mainActivity.showMessage(getString(R.string.no_taxis))
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
        taxis?.forEach {
            if (it.id == taxiId) taxi = it
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

    private fun addMarkerOnMap(taxi: Vehicle, showInfo: Boolean) {
        val capacity = getTaxiCapacity(taxi.fleetType)

        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(taxi.coordinates.first, taxi.coordinates.second))
                .title(String.format(getString(R.string.title_info_poi), taxi.id, capacity))
        )

        marker?.tag = taxi.id

        if (showInfo) marker?.showInfoWindow()

        googleMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            val vehicle = taxis?.first { vehicle -> vehicle.id == it.tag }
            rootView.isVisible(true)
            bindTaxi(vehicle!!, taxiHeader, taxiDescription, taxiImage)
            true
        }

        googleMap?.setOnInfoWindowCloseListener {
            rootView.isVisible(false)
        }
    }


}
