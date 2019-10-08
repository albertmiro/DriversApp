package com.albertmiro.data.model

import com.google.gson.annotations.SerializedName

/*
* Service response classes
*/

data class MyTaxiResponse(
    @SerializedName("poiList")
    val poiList: List<VehicleResponse>
)

data class VehicleResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("coordinate")
    val coordinate: CoordinateResponse,
    @SerializedName("fleetType")
    val fleetType: String,
    @SerializedName("heading")
    val heading: Double
)

data class CoordinateResponse(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

