package org.sunsetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunsetcode.command.BurnFuelCommand;
import org.sunsetcode.gameobject.FuelTank;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurnFuelCommandTest
{
    @Test
    @DisplayName("Проверка уменьшения колличества топлива на скорость расхода топлива при сжигании")
    public void testBurnFuel()
    {
        FuelTank fuelTank = new FuelTank(90, 100, 10);
        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuelTank);

        burnFuelCommand.execute();

        assertEquals(80, fuelTank.getAmount());
    }
}
