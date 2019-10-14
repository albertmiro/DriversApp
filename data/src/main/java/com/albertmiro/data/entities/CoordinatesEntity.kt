package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class CoordinatesEntity(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)