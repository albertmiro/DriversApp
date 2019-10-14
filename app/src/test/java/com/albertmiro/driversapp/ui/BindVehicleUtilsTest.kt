package com.albertmiro.driversapp.ui

import com.albertmiro.domain.models.FleetType
import com.albertmiro.driversapp.ui.BindVehicleUtils.getVehicleCapacity
import org.junit.Assert
import org.junit.Test

class BindVehicleUtilsTest {

    @Test
    fun checkVehicleCapacity() {
        val smallCapacity = 2
        val mediumCapacity = 4
        val bigCapacity = 6

        Assert.assertEquals(getVehicleCapacity(FleetType.POOLING), smallCapacity)
        Assert.assertEquals(getVehicleCapacity(FleetType.TAXI), mediumCapacity)
        Assert.assertEquals(getVehicleCapacity(FleetType.OTHER), bigCapacity)
    }

}