package com.albertmiro.data

import com.albertmiro.data.model.MyTaxiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MyTaxiService {

    @GET("vehicles")
    fun getVehicles(): Single<MyTaxiResponse>

}