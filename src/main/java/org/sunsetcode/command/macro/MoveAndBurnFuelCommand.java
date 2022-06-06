package org.sunsetcode.command.macro;

import org.sunsetcode.command.BurnFuelCommand;
import org.sunsetcode.command.CheckFuelCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.MoveCommand;
import org.sunsetcode.gameobject.FuelTank;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.adapters.MovableAdapter;

public class MoveAndBurnFuelCommand extends AbstractMacroCommand
{
    private final UObject uObject;
    private final FuelTank fuelTank;

    public MoveAndBurnFuelCommand(UObject uObject, FuelTank fuelTank)
    {
        this.uObject = uObject;
        this.fuelTank = fuelTank;
    }

    @Override
    public Command[] getCommands()
    {
        return new Command[]{
            new CheckFuelCommand(fuelTank),
            new MoveCommand(new MovableAdapter(uObject)),
            new BurnFuelCommand(fuelTank)
        };
    }
}
