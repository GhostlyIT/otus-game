package org.sunsetcode.command.macro;

import org.sunsetcode.command.BurnFuelCommand;
import org.sunsetcode.command.CheckFuelCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.MoveCommand;
import org.sunsetcode.gameobject.FuelTank;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.adapters.MovableAdapter;

public class MoveAndBurnFuelCommand extends AbstractMacroCommand
{
    private final FuelTank fuelTank;
    private Movable movable;

    public MoveAndBurnFuelCommand(Movable movable, FuelTank fuelTank)
    {
        this.movable = movable;
        this.fuelTank = fuelTank;
    }

    @Override
    public Command[] getCommands()
    {
        return new Command[]{
            new CheckFuelCommand(fuelTank),
            new MoveCommand(movable),
            new BurnFuelCommand(fuelTank)
        };
    }
}
