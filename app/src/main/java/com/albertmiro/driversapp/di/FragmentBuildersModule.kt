package com.albertmiro.driversapp.di

import com.albertmiro.driversapp.ui.taximap.TaxiMapFragment
import com.albertmiro.driversapp.ui.taxis.TaxiListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeTaxiListFragment(): TaxiListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTaxiMapFragment(): TaxiMapFragment

}
