package com.albertmiro.driversapp.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}