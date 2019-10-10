package com.albertmiro.driversapp.ui.taxis

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.base.BaseFragment
import com.albertmiro.driversapp.ui.loadTaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.adapter.TaxiAdapter
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import kotlinx.android.synthetic.main.fragment_taxi_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TaxiListFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_taxi_list

    private val vehiclesViewModel: VehiclesViewModel by sharedViewModel()

    private lateinit var taxiAdapter: TaxiAdapter

    companion object {
        fun newInstance(): TaxiListFragment {
            return TaxiListFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideBackOnToolbar()
        iniTaxiList()
        initSwipeRefresh()
        initObservers()

        vehiclesViewModel.loadTaxis(false)
    }

    private fun hideBackOnToolbar() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun iniTaxiList() {
        initAdapter()
        initList()
    }

    private fun initAdapter() {
        taxiAdapter = TaxiAdapter().apply {
            onClickAction = {
                vehiclesViewModel.setCurrentTaxiId(it.id)
                mainActivity.loadTaxiMapFragment()
            }
        }
    }

    private fun initList() {
        taxiList.apply {
            adapter = taxiAdapter
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
        taxiAdapter.setItems(vehicles)

        if (vehicles.isEmpty()) {
            context?.showMessage(getString(R.string.no_taxis))
        }
    }

    private fun hideRefreshIcon() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}