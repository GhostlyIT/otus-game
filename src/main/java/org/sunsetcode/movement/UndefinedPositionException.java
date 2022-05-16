package org.sunsetcode.movement;

public class UndefinedPositionException extends MovableException
{
    @Override
    public String getMessage() {
        return "Movable object has undefined position";
    }
}
