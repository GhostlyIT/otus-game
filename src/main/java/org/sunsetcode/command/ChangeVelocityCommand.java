package org.sunsetcode.command;

import org.sunsetcode.gameobject.UObject;

public class ChangeVelocityCommand implements Command
{
    private final UObject uObject;
    private final int newVelocity;

    public ChangeVelocityCommand(UObject uObject, int newVelocity)
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
