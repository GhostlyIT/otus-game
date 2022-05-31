package org.sunsetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunsetcode.command.CheckFuelCommand;
import org.sunsetcode.command.exceptions.CommandException;
import org.sunsetcode.gameobject.FuelTank;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFuelCommandTest
{
    @Test
    @DisplayName("Проверка выброса исключения CommandException при недостаточности топлива")
    public void testFuelCheckingThrowsCommandException()
    {
        FuelTank fuelTank = new FuelTank(10, 100, 20);
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuelTank);

        CommandException ex = assertThrows(CommandException.class, checkFuelCommand::execute);
        assertEquals("Command " + CheckFuelCommand.class.getName() + " failed: not enough fuel", ex.getMessage());
    }

    @Test
    @DisplayName("Проверка успешного выполнения команды")
    public void testSuccessfulFuelChecking()
    {
        FuelTank fuelTank = new FuelTank(40, 100, 20);
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuelTank);

        assertDoesNotThrow(checkFuelCommand::execute);
    }
}
