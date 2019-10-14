package com.albertmiro.data

import com.albertmiro.data.entities.VehiclesEntity
import io.reactivex.Single
import retrofit2.http.GET

interface VehiclesService {

    @GET("vehicles")
    fun getVehicles(): Single<VehiclesEntity>

}