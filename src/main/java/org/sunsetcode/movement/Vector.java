package org.sunsetcode.movement;

public class Vector
{
    private int x;
    private int y;

    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Vector plus(Vector position, Vector velocity)
    {
        return new Vector(position.getX() + velocity.getX(), position.getY() + velocity.getY());
    }
}
