package com.albertmiro.domain

import com.albertmiro.domain.domain.Vehicle
import io.reactivex.Single

interface TaxiVehiclesRepository {
    fun getHamburgTaxis(forceRefresh: Boolean): Single<List<Vehicle>>
}