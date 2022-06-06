package org.sunsetcode;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.sunsetcode.command.ChangeVelocityCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChangeVelocityCommandTest
{
    @Test
    public void testChangeVelocity() throws Exception
    {
        UObject uObject = mock(UObject.class);
        Vector initialVelocity = new Vector(3 ,3);
        when(uObject.<Vector>getProperty("velocity")).thenReturn(initialVelocity);

        ArgumentCaptor<Vector> vectorCapture = ArgumentCaptor.forClass(Vector.class);
        doAnswer(
                invocation -> {
                    Vector newVector = invocation.getArgumentAt(1, Vector.class);
                    when(uObject.<Vector>getProperty("velocity")).thenReturn(newVector);
                    return null;
                }
        ).when(uObject).setProperty(eq("velocity"), vectorCapture.capture());

        assertEquals(uObject.<Vector>getProperty("velocity").getX(), initialVelocity.getX());
        assertEquals(uObject.<Vector>getProperty("velocity").getY(), initialVelocity.getY());

        Vector newVelocity = new Vector(5, 6);
        Command changeVelocityCommand = new ChangeVelocityCommand(uObject, newVelocity);
        changeVelocityCommand.execute();

        assertEquals(uObject.<Vector>getProperty("velocity").getX(), newVelocity.getX());
        assertEquals(uObject.<Vector>getProperty("velocity").getY(), newVelocity.getY());
    }
}
