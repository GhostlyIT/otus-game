package org.sunsetcode.movement;

public class Vector
{
    private double x;
    private double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Vector plus(Vector position, Vector velocity)
    {
        return new Vector(position.getX() + velocity.getX(), position.getY() + velocity.getY());
    }
}
