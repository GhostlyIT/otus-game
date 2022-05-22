package org.sunsetcode.command;

import org.sunsetcode.movement.Rotable;
import org.sunsetcode.movement.exceptions.MovableException;

public class RotateCommand implements Command
{
    Rotable rotableObject;

    public RotateCommand(Rotable rotableObject)
    {
        this.rotableObject = rotableObject;
    }

    public void execute() throws MovableException
    {
        rotableObject.setDirection(
            rotableObject.getDirection().next(rotableObject.getAngularVelocity())
        );
    }
}
