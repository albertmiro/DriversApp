package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class VehiclesEntity(
    @SerializedName("poiList")
    val poiList: List<VehicleEntity>
)