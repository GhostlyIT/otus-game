package org.sunsetcode.movement.adapters;

import org.sunsetcode.gameobject.UObject;
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
        double d = o.<Double>getProperty("direction");
        int n = o.<Integer>getProperty("directionsNumber");
        int v = o.<Integer>getProperty("velocity");

        return new Vector(
            (int) (v * Math.cos(d / 360 * n)),
            (int) (v * Math.sin(d / 360 * n))
        );
    }

    @Override
    public void setPosition(Vector newPosition) throws MovableException {
        o.setProperty("position", newPosition);
    }
}
