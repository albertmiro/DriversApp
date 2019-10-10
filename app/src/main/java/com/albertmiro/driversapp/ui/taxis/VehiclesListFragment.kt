package com.albertmiro.driversapp.ui.taxis

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.base.BaseFragment
import com.albertmiro.driversapp.ui.loadVehiclesMapFragment
import com.albertmiro.driversapp.ui.taxis.adapter.VehiclesAdapter
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import kotlinx.android.synthetic.main.fragment_vehicles_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VehiclesListFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_vehicles_list

    private val vehiclesViewModel: VehiclesViewModel by sharedViewModel()

    private lateinit var vehiclesAdapter: VehiclesAdapter

    companion object {
        fun newInstance(): VehiclesListFragment {
            return VehiclesListFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideBackOnToolbar()
        initAdapter()
        initList()
        initSwipeRefresh()
        initObservers()

        vehiclesViewModel.loadTaxis(false)
    }

    private fun hideBackOnToolbar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initAdapter() {
        vehiclesAdapter = VehiclesAdapter().apply {
            onClickAction = {
                vehiclesViewModel.setCurrentTaxiId(it.id)
                mainActivity.loadVehiclesMapFragment()
            }
        }
    }

    private fun initList() {
        taxiList.apply {
            adapter = vehiclesAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            vehiclesViewModel.loadTaxis(true)
        }
    }

    private fun initObservers() {
        vehiclesViewModel.isDataLoading()
            .observe(this, Observer { updateProgressBarVisibility(it) })
        vehiclesViewModel.getTaxis().observe(this, Observer { showVehicles(it) })
        vehiclesViewModel.isNetworkError().observe(this, Observer { onNetworkError(it) })
        vehiclesViewModel.isUnknownError().observe(this, Observer { onUnknownError(it) })
    }

    private fun onUnknownError(isUnknownError: Boolean) {
        if (isUnknownError) {
            context?.showMessage(getString(R.string.unexpected_error))
            hideRefreshIcon()
        }
    }

    private fun onNetworkError(isNetworkError: Boolean) {
        if (isNetworkError) {
            context?.showMessage(getString(R.string.lost_connection))
            hideRefreshIcon()
        }
    }

    private fun updateProgressBarVisibility(dataLoaded: Boolean) {
        if (!swipeRefresh.isRefreshing) {
            progressBar.isVisible(dataLoaded)
        }
    }

    private fun showVehicles(vehicles: List<Vehicle>) {
        hideRefreshIcon()
        vehiclesAdapter.setItems(vehicles)
        if (vehicles.isEmpty()) {
            context?.showMessage(getString(R.string.no_vehicles))
        }
    }

    private fun hideRefreshIcon() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}