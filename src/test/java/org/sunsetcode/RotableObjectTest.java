package org.sunsetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.sunsetcode.movement.*;

public class RotableObjectTest
{
    private Rotable rotableObject;

    @Before
    public void setUp()
    {
        rotableObject = Mockito.mock(Rotable.class);
        Direction initDirection = new Direction(5);

        Mockito.when(rotableObject.getDirection()).thenReturn(initDirection);
        Mockito.when(rotableObject.getAngularVelocity()).thenReturn(15);

        ArgumentCaptor<Direction> directionCaptor = ArgumentCaptor.forClass(Direction.class);
        Mockito.doAnswer(
            invocation -> {
                Direction newDirection = invocation.getArgumentAt(0, Direction.class);
                Mockito.when(rotableObject.getDirection()).thenReturn(newDirection);
                return null;
            }
        )
        .when(rotableObject)
        .setDirection(directionCaptor.capture());
    }

    @Test
    public void directionChangedTest()
    {
        Rotate rotate = new Rotate(rotableObject);
        rotate.execute();

        Assert.assertEquals(4, rotableObject.getDirection().getCurrentDirection());
    }
}
