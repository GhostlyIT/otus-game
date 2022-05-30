package org.sunsetcode.command;

import org.sunsetcode.gameobject.FuelTank;

public class BurnFuelCommand implements Command
{
    private final FuelTank fuelTank;

    public BurnFuelCommand(FuelTank fuelTank)
    {
        this.fuelTank = fuelTank;
    }

    @Override
    public void execute() throws Exception
    {
        fuelTank.burn();
    }
}
