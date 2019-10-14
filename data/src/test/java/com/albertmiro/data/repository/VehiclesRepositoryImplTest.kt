package com.albertmiro.data.repository

class VehiclesRepositoryImplTest {

/*
This test is not working right now

I have an issue when trying to mock the VehiclesService, I tried to create a VehiclesService
not mock, for that I should create and include a mock web server as made in VehiclesServiceTest, but
this approach has been discarted due that the service is being tested there.
 */

//    private lateinit var spyRepository: VehiclesRepository
//    private lateinit var service: VehiclesService
//
//    @Before
//    fun setUp() {
//        service = mock()
//        val vehicle = VehiclesEntity(emptyList())
//        whenever(service.getVehicles()).thenReturn(Single.just(vehicle))
//
//        spyRepository = spy(VehiclesRepositoryImpl(service))
//    }
//
//    @Test
//    fun `check empty response`() {
//
//        doReturn(VehiclesEntity(emptyList())).whenever(spyRepository).getVehicles(true)
//
//        spyRepository.getVehicles(true)
//
//        verify(service).getVehicles()
//    }
}