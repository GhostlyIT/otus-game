package org.sunsetcode;

public class Movement {
    private Movable movableObject;

    public Movement(Movable movableObject)
    {
        this.movableObject = movableObject;
    }

    public void execute()
    {
        movableObject.setPosition(
            Vector.plus(
                movableObject.getPosition(),
                movableObject.getVelocity()
            )
        );
    }
}
