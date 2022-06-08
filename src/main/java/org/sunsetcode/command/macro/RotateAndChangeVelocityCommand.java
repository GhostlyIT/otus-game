package org.sunsetcode.command.macro;

import org.sunsetcode.command.ChangeVelocityCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.RotateCommand;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Rotable;

public class RotateAndChangeVelocityCommand extends AbstractMacroCommand
{
    private final Rotable rotable;
    private final UObject uObject;
    private final int newVelocity;

    public RotateAndChangeVelocityCommand(Rotable rotable, UObject uObject, int newVelocity)
    {
        this.rotable = rotable;
        this.uObject = uObject;
        this.newVelocity = newVelocity;
    }

    @Override
    public Command[] getCommands()
    {
        return new Command[]{
                new ChangeVelocityCommand(uObject, newVelocity),
                new RotateCommand(rotable)
        };
    }
}
