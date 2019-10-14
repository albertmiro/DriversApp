package com.albertmiro.driversapp.ui

import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.map.VehiclesMapFragment
import com.albertmiro.driversapp.ui.vehicles.VehiclesListFragment

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
