package org.sunsetcode.movement.adapters;

import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Direction;
import org.sunsetcode.movement.Rotable;
import org.sunsetcode.movement.exceptions.MovableException;

public class RotableAdapter implements Rotable
{
    UObject o;

    public RotableAdapter(UObject o)
    {
        this.o = o;
    }

    @Override
    public Direction getDirection() {
        return (Direction) o.getProperty("direction");
    }

    @Override
    public int getAngularVelocity() {
        return (int) o.getProperty("angularVelocity");
    }

    @Override
    public void setDirection(Direction newDirection) throws MovableException {
        o.setProperty("direction", newDirection);
    }
}
