package com.albertmiro.driversapp.ui.taxis

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertmiro.common.extensions.isVisible
import com.albertmiro.common.extensions.showMessage
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.ui.base.BaseFragment
import com.albertmiro.driversapp.ui.loadTaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.adapter.TaxiAdapter
import com.albertmiro.driversapp.ui.viewmodel.MyTaxiViewModel
import kotlinx.android.synthetic.main.fragment_taxi_list.*

class TaxiListFragment : BaseFragment<MyTaxiViewModel>() {

    override val layoutId: Int = R.layout.fragment_taxi_list

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

        viewModel.loadTaxis(false)
    }

    override fun provideViewModel(): MyTaxiViewModel =
        ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MyTaxiViewModel::class.java)

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
                viewModel.setCurrentTaxiId(it.id)
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
            viewModel.loadTaxis(true)
        }
    }

    private fun initObservers() {
        viewModel.isDataLoading().observe(this, Observer { dataLoaded ->
            dataLoaded?.let {
                if (!swipeRefresh.isRefreshing) {
                    progressBar.isVisible(it)
                }
            }
        })

        viewModel.getTaxis().observe(this, Observer {
            it?.let {
                showTaxis(it)
            }
        })

        viewModel.isNetworkError().observe(this, Observer {
            it?.let {
                if (it) {
                    context?.showMessage(getString(R.string.lost_connection))
                    hideRefreshIcon()
                }
            }
        })

        viewModel.isUnknownError().observe(this, Observer {
            it?.let {
                if (it) {
                    context?.showMessage(getString(R.string.unexpected_error))
                    hideRefreshIcon()
                }
            }
        })
    }

    private fun showTaxis(taxis: List<Vehicle>) {
        hideRefreshIcon()
        taxiAdapter.setItems(taxis)

        if (taxis.isEmpty()) {
            context?.showMessage(getString(R.string.no_taxis))
        }
    }

    private fun hideRefreshIcon() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}