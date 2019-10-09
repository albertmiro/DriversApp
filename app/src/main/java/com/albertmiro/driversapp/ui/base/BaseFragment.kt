package com.albertmiro.driversapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.albertmiro.driversapp.di.Injectable
import com.albertmiro.driversapp.ui.MainActivity
import com.albertmiro.driversapp.ui.viewmodel.Base
import com.albertmiro.driversapp.ui.viewmodel.BaseViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment(),
    Base.View, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: ViewModel
    protected abstract val layoutId: Int

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = provideViewModel()
        super.onCreate(savedInstanceState)
    }

    abstract fun provideViewModel(): ViewModel
}
