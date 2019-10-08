package com.albertmiro.driversapp.ui.common

import androidx.fragment.app.Fragment
import com.albertmiro.driversapp.ui.MainActivity

open class BaseFragment : Fragment() {
    val mainActivity: MainActivity
        get() = activity as MainActivity
}
