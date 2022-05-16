package org.sunsetcode.movement;

public class Movement {
    private Movable movableObject;

    public Movement(Movable movableObject)
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
