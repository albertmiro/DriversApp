package com.albertmiro.domain.usecases

import com.albertmiro.domain.VehiclesRepository

class GetVehicles(private val vehiclesRepository: VehiclesRepository) {

    operator fun invoke(forceRefresh: Boolean) =
        vehiclesRepository.getHamburgTaxis(forceRefresh)
}