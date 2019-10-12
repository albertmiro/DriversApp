package com.albertmiro.domain

import com.albertmiro.domain.domain.Vehicle
import io.reactivex.Single

interface VehiclesRepository {
    fun getVehicles(forceRefresh: Boolean): Single<List<Vehicle>>
}