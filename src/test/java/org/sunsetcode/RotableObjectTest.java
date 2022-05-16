package org.sunsetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.sunsetcode.movement.Direction;
import org.sunsetcode.movement.Rotable;
import org.sunsetcode.movement.Rotate;
import org.sunsetcode.movement.exceptions.MovableException;

public class RotableObjectTest
{
    private Rotable rotableObject;

    @Before
    public void setUp() throws MovableException {
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

        try {
            rotate.execute();
        } catch (MovableException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(4, rotableObject.getDirection().getCurrentDirection());
    }
}
