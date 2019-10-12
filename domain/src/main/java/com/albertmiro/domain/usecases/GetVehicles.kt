package com.albertmiro.domain.usecases

import com.albertmiro.domain.VehiclesRepository

class GetVehicles(val vehiclesRepository: VehiclesRepository) {

    operator fun invoke(forceRefresh: Boolean) =
        vehiclesRepository.getVehicles(forceRefresh)
}