package org.sunsetcode.command;

import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.Vector;
import org.sunsetcode.movement.exceptions.MovableException;

public class MoveCommand implements Command
{
    private Movable movableObject;

    public MoveCommand(Movable movableObject)
    {
        this.movableObject = movableObject;
    }

    public void execute() throws MovableException
    {
        movableObject.setPosition(
            Vector.plus(
                movableObject.getPosition(),
                movableObject.getVelocity()
            )
        );
    }
}
