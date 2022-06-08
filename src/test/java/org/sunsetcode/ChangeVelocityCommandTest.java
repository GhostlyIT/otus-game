package org.sunsetcode;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.sunsetcode.command.ChangeVelocityCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.gameobject.UObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChangeVelocityCommandTest
{
    @Test
    public void testChangeVelocity() throws Exception
    {
        UObject uObject = mock(UObject.class);
        when(uObject.<Integer>getProperty("velocity")).thenReturn(10);

        ArgumentCaptor<Integer> velocityCapture = ArgumentCaptor.forClass(Integer.class);
        doAnswer(
                invocation -> {
                    Integer newVelocity = invocation.getArgumentAt(1, Integer.class);
                    when(uObject.<Integer>getProperty("velocity")).thenReturn(newVelocity);
                    return null;
                }
        ).when(uObject).setProperty(eq("velocity"), velocityCapture.capture());

        assertEquals(uObject.<Integer>getProperty("velocity"), 10);

        Command changeVelocityCommand = new ChangeVelocityCommand(uObject, 5);
        changeVelocityCommand.execute();

        assertEquals(uObject.<Integer>getProperty("velocity"), 5);
    }
}
