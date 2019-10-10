package com.albertmiro.driversapp.ui

import android.os.Bundle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.base.BaseActivity
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    // Using the Activity to create the ViewModel, would be shared between
    // the fragments due to the simplicity of the app.
    // The relation should be 1 Fragment <-> 1 ViewModel
    val vehiclesViewModel: VehiclesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loadVehiclesListFragment()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
