package org.sunsetcode.movement;

public interface Rotable
{
    Direction getDirection();
    int getAngularVelocity();
    void setDirection(Direction newDirection);
}
