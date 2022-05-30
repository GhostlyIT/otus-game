package org.sunsetcode.command;

import org.sunsetcode.command.exceptions.CommandException;
import org.sunsetcode.gameobject.FuelTank;

public class CheckFuelCommand implements Command
{
    private final FuelTank fuelTank;

    public CheckFuelCommand(FuelTank fuelTank)
    {
        this.fuelTank = fuelTank;
    }

    @Override
    public void execute() throws Exception
    {
        if (fuelTank.getAmount() < fuelTank.getConsumption()) {
            throw new CommandException(CheckFuelCommand.class.getName(), "not enough fuel");
        }
    }
}
