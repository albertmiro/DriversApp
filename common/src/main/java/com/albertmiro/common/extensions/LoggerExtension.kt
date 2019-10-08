package com.albertmiro.common.extensions

import android.util.Log

fun Any.d(message: String) =
    Log.d(javaClass.name, message)

fun Any.w(message: String) =
    Log.w(javaClass.name, message)

fun Any.e(message: String) =
    Log.e(javaClass.name, message)

fun Any.e(message: String?, throwable: Throwable?) =
    Log.e(javaClass.name, message, throwable)

fun Any.v(message: String) =
    Log.v(javaClass.name, message)

fun Any.i(message: String) =
    Log.i(javaClass.name, message)

fun Any.wtf(message: String) =
    Log.wtf(javaClass.name, message)




