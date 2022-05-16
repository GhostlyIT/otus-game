package org.sunsetcode.movement.exceptions;

public class UndefinedPositionException extends MovableException
{
    @Override
    public String getMessage() {
        return "Movable object has undefined position";
    }
}
