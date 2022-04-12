package org.sunsetcode.movement;

public interface Movable
{
    Vector getPosition() throws UndefinedPositionException;
    Vector getVelocity() throws UndefinedVelocityException;
    void setPosition(Vector newPosition) throws MovableException;
}
