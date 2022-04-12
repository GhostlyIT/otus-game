package org.sunsetcode.movement;

public class UndefinedVelocityException extends MovableException
{
    @Override
    public String getMessage() {
        return "Movable object has undefined velocity";
    }
}
