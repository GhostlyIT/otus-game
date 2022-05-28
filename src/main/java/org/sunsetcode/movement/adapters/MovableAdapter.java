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
        return (Vector) o.getProperty("position");
    }

    @Override
    public Vector getVelocity() {
        double d = (double) o.getProperty("direction");
        int n = (int) o.getProperty("directionsNumber");
        int v = (int) o.getProperty("velocity");

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
