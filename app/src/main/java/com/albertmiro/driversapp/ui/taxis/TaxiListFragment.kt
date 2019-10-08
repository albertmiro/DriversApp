package com.albertmiro.driversapp.ui.taxis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertmiro.driversapp.R
import com.albertmiro.driversapp.di.Injectable
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.driversapp.extensions.isVisible
import com.albertmiro.driversapp.extensions.showMessage
import com.albertmiro.driversapp.ui.common.BaseFragment
import com.albertmiro.driversapp.ui.loadTaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.adapter.TaxiAdapter
import com.albertmiro.driversapp.ui.viewmodel.MyTaxiViewModel
import kotlinx.android.synthetic.main.fragment_taxi_list.*
import javax.inject.Inject

class TaxiListFragment : BaseFragment(),
    Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var myTaxiViewModel: MyTaxiViewModel
    private lateinit var taxiAdapter: TaxiAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_taxi_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getViewModel()
        hideBackOnToolbar()
        initTaxiList()
        initSwipeRefresh()
        initObservers()

        myTaxiViewModel.loadTaxis(false)
    }

    private fun getViewModel() {
        myTaxiViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(MyTaxiViewModel::class.java)
    }

    private fun hideBackOnToolbar() =
            mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

    private fun initTaxiList() {
        taxiAdapter = TaxiAdapter().apply {
            onClickAction = {
                myTaxiViewModel.setCurrentTaxiId(it.id)
                mainActivity.loadTaxiMapFragment()
            }
        }

        taxiList.apply {
            adapter = taxiAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            myTaxiViewModel.loadTaxis(true)
        }
    }

    private fun initObservers() {
        myTaxiViewModel.isDataLoading().observe(this, Observer {
            it?.let {
                if (!swipeRefresh.isRefreshing) {
                    progressBar.isVisible(it)
                }
            }
        })

        myTaxiViewModel.getTaxis().observe(this, Observer {
            it?.let {
                showTaxis(it)
            }
        })

        myTaxiViewModel.isNetworkError().observe(this, Observer {
            it?.let {
                if (it) {
                    mainActivity.showMessage(getString(R.string.lost_connection))
                    hideRefreshIcon()
                }
            }
        })

        myTaxiViewModel.isUnknownError().observe(this, Observer {
            it?.let {
                if (it) {
                    mainActivity.showMessage(getString(R.string.unexpected_error))
                    hideRefreshIcon()
                }
            }
        })
    }

    private fun showTaxis(taxis: List<Vehicle>) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }

        taxiAdapter.setItems(taxis)

        if (taxis.isEmpty()) {
            mainActivity.showMessage(getString(R.string.no_taxis))
        }
    }

    private fun hideRefreshIcon() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}