package org.sunsetcode.movement;

import org.sunsetcode.movement.exceptions.MovableException;
import org.sunsetcode.movement.exceptions.UndefinedPositionException;
import org.sunsetcode.movement.exceptions.UndefinedVelocityException;

public interface Movable
{
    Vector getPosition() throws UndefinedPositionException;
    Vector getVelocity() throws UndefinedVelocityException;
    void setPosition(Vector newPosition) throws MovableException;
}
