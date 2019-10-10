package com.albertmiro.driversapp.di

import com.albertmiro.data.VehiclesService
import com.albertmiro.data.repository.TaxiVehiclesRepositoryImpl
import com.albertmiro.domain.VehiclesRepository
import com.albertmiro.domain.usecases.GetVehicles
import com.albertmiro.driversapp.BuildConfig
import com.albertmiro.driversapp.ui.viewmodel.VehiclesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val httpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(httpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val vehiclesApi = retrofit.create(VehiclesService::class.java)

val viewModelModule: Module = module {
    viewModel { VehiclesViewModel(getVehicles = get()) }
}

val useCaseModule: Module = module {
    factory { GetVehicles(vehiclesRepository = get()) }
}

val repositoryModule: Module = module {
    single { TaxiVehiclesRepositoryImpl(service = get()) as VehiclesRepository }
}

val networkModule: Module = module {
    single { vehiclesApi }
}