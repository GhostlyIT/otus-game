package org.sunsetcode.movement.adapters;

import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Direction;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.Vector;
import org.sunsetcode.movement.exceptions.MovableException;
import org.sunsetcode.movement.exceptions.UndefinedPositionException;

public class MovableAdapter implements Movable
{
    UObject o;

    public MovableAdapter(UObject o)
    {
        this.o = o;
    }

    @Override
    public Vector getPosition() throws UndefinedPositionException {
        return o.<Vector>getProperty("position");
    }

    @Override
    public Vector getVelocity() {
        Direction d = o.<Direction>getProperty("direction");
        int n = Direction.DIRECTIONS_NUMBER;
        int v = o.<Integer>getProperty("velocity");

        return new Vector(
            (int) (v * Math.cos((double) d.getCurrentDirection() / 360 * n)),
            (int) (v * Math.sin((double) d.getCurrentDirection() / 360 * n))
        );
    }

    @Override
    public void setPosition(Vector newPosition) throws MovableException {
        o.setProperty("position", newPosition);
    }
}
