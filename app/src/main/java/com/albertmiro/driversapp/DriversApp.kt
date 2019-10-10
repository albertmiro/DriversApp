package com.albertmiro.driversapp

import android.app.Application
import com.albertmiro.driversapp.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DriversApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@DriversApp)
            Modules.init()
        }

    }
}