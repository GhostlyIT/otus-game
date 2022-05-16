package org.sunsetcode.movement;

import org.sunsetcode.movement.exceptions.MovableException;

public interface Rotable
{
    Direction getDirection();
    int getAngularVelocity();
    void setDirection(Direction newDirection) throws MovableException;
}
