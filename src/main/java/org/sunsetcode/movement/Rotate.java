package org.sunsetcode.movement;

public class Rotate
{
    Rotable rotableObject;

    public Rotate(Rotable rotableObject)
    {
        this.rotableObject = rotableObject;
    }

    public void execute()
    {
        rotableObject.setDirection(
            rotableObject.getDirection().next(rotableObject.getAngularVelocity())
        );
    }
}
