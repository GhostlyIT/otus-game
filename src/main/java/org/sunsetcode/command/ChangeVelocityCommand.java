package org.sunsetcode.command;

import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Vector;

public class ChangeVelocityCommand implements Command
{
    private final UObject uObject;
    private final Vector newVelocity;

    public ChangeVelocityCommand(UObject uObject, Vector newVelocity)
    {
        this.uObject = uObject;
        this.newVelocity = newVelocity;
    }

    @Override
    public void execute() throws Exception
    {
        uObject.setProperty("velocity", newVelocity);
    }
}
