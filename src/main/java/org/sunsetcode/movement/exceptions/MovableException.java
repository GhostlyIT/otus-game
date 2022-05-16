package org.sunsetcode.movement.exceptions;

public class MovableException extends Exception
{
    @Override
    public String getMessage() {
        return "Impossible to move object";
    }
}
