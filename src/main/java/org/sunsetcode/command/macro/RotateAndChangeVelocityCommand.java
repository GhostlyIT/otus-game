package org.sunsetcode.command.macro;

import org.sunsetcode.command.ChangeVelocityCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.RotateCommand;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.adapters.RotableAdapter;

public class RotateAndChangeVelocityCommand extends AbstractMacroCommand
{
    private final UObject uObject;
    private final int newVelocity;

    public RotateAndChangeVelocityCommand(UObject uObject, int newVelocity)
    {
        this.uObject = uObject;
        this.newVelocity = newVelocity;
    }

    @Override
    public Command[] getCommands()
    {
        return new Command[]{
                new ChangeVelocityCommand(uObject, newVelocity),
                new RotateCommand(new RotableAdapter(uObject))
        };
    }
}
