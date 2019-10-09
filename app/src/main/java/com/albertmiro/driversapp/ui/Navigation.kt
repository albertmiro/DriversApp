package com.albertmiro.driversapp.ui

import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.taximap.TaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.TaxiListFragment

fun MainActivity.loadTaxiListFragment() {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, TaxiListFragment.newInstance())
        .commit()
}

fun MainActivity.loadTaxiMapFragment() {
    val taxiMapFragment = TaxiMapFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .addToBackStack(taxiMapFragment.javaClass.name)
        .replace(R.id.fragmentContainer, taxiMapFragment)
        .commit()
}
