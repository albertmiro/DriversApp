package com.albertmiro.driversapp.di

import com.albertmiro.data.di.networkModule
import org.koin.core.context.loadKoinModules

object Modules {
    fun init() {
        loadKoinModules(
            listOf(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                networkModule
            )
        )
    }
}