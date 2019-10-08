package com.albertmiro.data

import com.albertmiro.data.model.MyTaxiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MyTaxiService {

    @GET(".")
    fun getTaxisByCoordinates(@Query("p1Lat") p1Lat: Double,
                              @Query("p1Lon") p1Lon: Double,
                              @Query("p2Lat") p2Lat: Double,
                              @Query("p2Lon") p2Lon: Double): Single<MyTaxiResponse>

}