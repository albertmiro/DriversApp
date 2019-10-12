package com.albertmiro.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

class VehiclesServiceTest {

    lateinit var service: VehiclesService
    lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(VehiclesService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldGetNotEmptyListOfTaxis() {
        enqueueResponse("mytaxi_response.json")
        service.getVehicles()
            .test()
            .assertValue({ response -> response.poiList.isNotEmpty() })
    }

    @Test
    fun shouldGetCorrectNumberOfTaxis() {
        enqueueResponse("mytaxi_response.json")
        service.getVehicles()
            .test()
            .assertValue({ response -> response.poiList.size == 30 })
    }

    @Test
    fun shouldGetTaxiWithCorrectValues() {
        enqueueResponse("mytaxi_response.json")
        service.getVehicles()
            .test()
            .assertValue { response -> response.poiList.isNotEmpty() }
            .assertValue { response -> response.poiList[0].id == 555331 }
            .assertValue { response -> response.poiList[0].coordinate.latitude == 53.50108189038939 }
            .assertValue { response -> response.poiList[0].coordinate.longitude == 9.903256768459475 }
            .assertValue { response -> response.poiList[0].fleetType == "POOLING" }
            .assertValue { response -> response.poiList[0].heading == 347.0020573828715 }
    }

    @Test
    fun shouldGetEmptyResponse() {
        enqueueResponse("mytaxi_response_empty.json")
        service.getVehicles()
            .test()
            .assertValue { response -> response.poiList.isNullOrEmpty() }
    }

    @Test
    fun shouldGetEmptyPOISResponse() {
        enqueueResponse("mytaxi_response_empty_pois.json")
        service.getVehicles()
            .test()
            .assertValue { response -> response.poiList.isEmpty() }
    }

    @Test
    fun error400() {
        mockWebServer.enqueue(MockResponse().setBody("{error:\"bad request\"").setResponseCode(400))
        service.getVehicles()
            .test()
            .assertError(HttpException::class.java)
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("service-response/$fileName")
        val source = inputStream.source()
        val buffer = source.buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(buffer.readString(StandardCharsets.UTF_8)))
    }

}