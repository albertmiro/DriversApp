package com.albertmiro.driversapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albertmiro.driversapp.ui.MainActivity
import com.albertmiro.driversapp.ui.base.viewmodel.Base

abstract class BaseFragment : Fragment(), Base.View {

    protected abstract val layoutId: Int

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

}
