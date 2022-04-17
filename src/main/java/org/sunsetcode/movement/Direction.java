package org.sunsetcode.movement;

public class Direction
{
    private int currentDirection;
    private final static int DIRECTIONS_NUMBER = 8;

    public Direction(int currentDirection)
    {
        this.currentDirection = currentDirection;
    }

    public Direction next(int angularVelocity)
    {
        return new Direction((currentDirection + angularVelocity) % DIRECTIONS_NUMBER);
    }

    public int getCurrentDirection()
    {
        return currentDirection;
    }
}
