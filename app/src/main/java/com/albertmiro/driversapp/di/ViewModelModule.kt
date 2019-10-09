package com.albertmiro.driversapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertmiro.driversapp.ui.base.AppViewModelFactory
import com.albertmiro.driversapp.ui.viewmodel.MyTaxiViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyTaxiViewModel::class)
    abstract fun bindMyTaxiViewModel(myTaxiViewModel: MyTaxiViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
