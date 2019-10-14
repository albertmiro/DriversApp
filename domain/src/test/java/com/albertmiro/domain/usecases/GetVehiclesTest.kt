package com.albertmiro.domain.usecases

import com.albertmiro.domain.VehiclesRepository
import com.albertmiro.domain.models.FleetType
import com.albertmiro.domain.models.Vehicle
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetVehiclesTest {

    @Mock
    lateinit var vehiclesRepository: VehiclesRepository

    lateinit var subject: GetVehicles

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        whenever(vehiclesRepository.getVehicles(Mockito.anyBoolean())).thenReturn(
            provideMockVehicles()
        )

        subject = GetVehicles(vehiclesRepository)
    }

    @Test
    fun `Test get vehicles`() {
        val response = subject.invoke(Mockito.anyBoolean())
        assert(response.blockingGet().size == 3)
    }

    private fun provideMockVehicles(): Single<List<Vehicle>> {
        val vehicle =
            Vehicle(id = 1, coordinates = 1.1 to 1.1, fleetType = FleetType.OTHER, heading = 2.2)
        return Single.just(listOf(vehicle, vehicle, vehicle))
    }
}