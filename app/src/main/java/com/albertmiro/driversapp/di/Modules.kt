package com.albertmiro.driversapp.di

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