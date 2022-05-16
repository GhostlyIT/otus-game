package org.sunsetcode.movement.exceptions;

public class UndefinedVelocityException extends MovableException
{
    @Override
    public String getMessage() {
        return "Movable object has undefined velocity";
    }
}
