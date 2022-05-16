package org.sunsetcode.movement;

import org.sunsetcode.movement.exceptions.MovableException;

public class Rotate implements Command
{
    Rotable rotableObject;

    public Rotate(Rotable rotableObject)
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
