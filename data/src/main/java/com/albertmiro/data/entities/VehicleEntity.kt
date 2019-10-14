package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class VehicleEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("coordinate")
    val coordinate: CoordinatesEntity,
    @SerializedName("fleetType")
    val fleetType: String,
    @SerializedName("heading")
    val heading: Double
)