package com.albertmiro.driversapp.di

import com.albertmiro.data.repository.TaxiVehiclesRepositoryImpl
import com.albertmiro.domain.VehiclesRepository
import com.albertmiro.domain.usecases.GetVehicles
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { VehiclesViewModel(getVehicles = get()) }
}

val useCaseModule: Module = module {
    factory { GetVehicles(vehiclesRepository = get()) }
}

val repositoryModule: Module = module {
    single { TaxiVehiclesRepositoryImpl(service = get()) as VehiclesRepository }
}
