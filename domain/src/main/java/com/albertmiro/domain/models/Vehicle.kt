package com.albertmiro.domain.models

data class Vehicle(
    val id: Int,
    val coordinates: Pair<Double, Double>,
    val fleetType: FleetType,
    val heading: Double
)
