package com.albertmiro.driversapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.albertmiro.data.MyTaxiService
import com.albertmiro.data.TaxiVehiclesRepositoryImpl
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyTaxiViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MyTaxiViewModel
    lateinit var mytaxiRepository: TaxiVehiclesRepositoryImpl
    val service: MyTaxiService = mock()

    @Before
    fun setUp() {
        mytaxiRepository = mock()
        viewModel = MyTaxiViewModel(mytaxiRepository)
    }

    @Test
    fun test() {
        viewModel.loadTaxis(true)
        viewModel.isDataLoading().observeOnce {
            assert(it)
        }
    }


}