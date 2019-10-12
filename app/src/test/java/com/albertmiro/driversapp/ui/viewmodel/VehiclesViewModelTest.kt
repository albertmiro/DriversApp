package com.albertmiro.driversapp.ui.viewmodel

import com.albertmiro.domain.domain.FleetType
import com.albertmiro.domain.domain.Vehicle
import com.albertmiro.domain.usecases.GetVehicles
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class VehiclesViewModelTest {

    @Mock
    private lateinit var mockUseCase: GetVehicles

    private lateinit var viewModel: VehiclesViewModel

    @Mock
    lateinit var mockException: Exception

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = VehiclesViewModel(mockUseCase)
    }

    @Test
    fun `get vehicles returning empty list`() {
        `when`(mockUseCase.invoke(Mockito.anyBoolean()))
            .thenReturn(Single.just(emptyList()))

        val testObserver = TestObserver<List<Vehicle>>()

        viewModel.getVehicles(Mockito.anyBoolean())
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { list -> list.isEmpty() }
    }

    @Test
    fun `get vehicles returning list with one item`() {
        val vehicle =
            Vehicle(id = 1, coordinates = 1.1 to 1.1, fleetType = FleetType.OTHER, heading = 2.2)

        `when`(mockUseCase.invoke(Mockito.anyBoolean()))
            .thenReturn(Single.just(listOf(vehicle)))

        val testObserver = TestObserver<List<Vehicle>>()

        viewModel.getVehicles(Mockito.anyBoolean())
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { list -> list.size == 1 }
        testObserver.assertValue { list -> list[0].id == vehicle.id }
        testObserver.assertValue { list -> list[0].coordinates == vehicle.coordinates }
        testObserver.assertValue { list -> list[0].fleetType == vehicle.fleetType }
        testObserver.assertValue { list -> list[0].heading == vehicle.heading }

    }

    @Test
    fun `get vehicles returning list with more items`() {
        val vehicle =
            Vehicle(id = 1, coordinates = 1.1 to 1.1, fleetType = FleetType.OTHER, heading = 2.2)

        `when`(mockUseCase.invoke(Mockito.anyBoolean()))
            .thenReturn(Single.just(listOf(vehicle, vehicle, vehicle)))

        val testObserver = TestObserver<List<Vehicle>>()

        viewModel.getVehicles(Mockito.anyBoolean())
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { list -> list.size == 3 }
        testObserver.assertValue { list -> list[0].id == vehicle.id }
        testObserver.assertValue { list -> list[0].coordinates == vehicle.coordinates }
        testObserver.assertValue { list -> list[0].fleetType == vehicle.fleetType }
        testObserver.assertValue { list -> list[0].heading == vehicle.heading }

    }

    @Test
    fun `get vehicles with error`() {

        `when`(mockUseCase.invoke(Mockito.anyBoolean()))
            .thenReturn(Single.just(emptyList()))

        val testObserver = TestObserver<List<Vehicle>>()

        viewModel.getVehicles(Mockito.anyBoolean())
            .subscribe(testObserver)

        testObserver.onError(mockException)
        testObserver.assertError(mockException)
    }
}
