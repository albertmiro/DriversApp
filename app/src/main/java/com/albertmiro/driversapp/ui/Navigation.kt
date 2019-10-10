package com.albertmiro.driversapp.ui

import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.taxis.VehiclesListFragment
import com.albertmiro.driversapp.ui.vehiclemap.VehiclesMapFragment

fun MainActivity.loadVehiclesListFragment() {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, VehiclesListFragment.newInstance())
        .commit()
}

fun MainActivity.loadVehiclesMapFragment() {
    val vehiclesMapFragment = VehiclesMapFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .addToBackStack(vehiclesMapFragment.javaClass.name)
        .replace(R.id.fragmentContainer, vehiclesMapFragment)
        .commit()
}
