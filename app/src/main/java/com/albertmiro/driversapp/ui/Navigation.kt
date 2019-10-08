package com.albertmiro.driversapp.ui

import com.albertmiro.driversapp.ui.taximap.TaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.TaxiListFragment
import com.albertmiro.driversapp.R

fun MainActivity.loadTaxiListFragment() {
    supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TaxiListFragment())
            .commit()
}

fun MainActivity.loadTaxiMapFragment() {
    val taxiMapFragment = TaxiMapFragment()
    supportFragmentManager.beginTransaction()
            .addToBackStack(taxiMapFragment.javaClass.name)
            .replace(R.id.fragmentContainer, taxiMapFragment)
            .commit()
}
