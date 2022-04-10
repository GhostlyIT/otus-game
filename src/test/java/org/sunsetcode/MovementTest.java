package org.sunsetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class MovementTest
{
    private Movable movableObject;

    @Before
    public void setUp()
    {
        movableObject = Mockito.mock(Movable.class);
        Vector position = new Vector(12, 5);
        Vector velocity = new Vector(-7, 3);
        Mockito.when(movableObject.getPosition()).thenReturn(position);
        Mockito.when(movableObject.getVelocity()).thenReturn(velocity);

        ArgumentCaptor<Vector> vectorCapture = ArgumentCaptor.forClass(Vector.class);

        Mockito.doAnswer(
                invocation -> {
                    Vector newVector = invocation.getArgumentAt(0, Vector.class);
                    Mockito.when(movableObject.getPosition()).thenReturn(newVector);
                    return null;
                }
        )
        .when(movableObject)
        .setPosition(vectorCapture.capture());
    }

    @Test
    public void positionChangedTest()
    {
        Movement movement = new Movement(movableObject);
        movement.execute();

        Assert.assertEquals(5, movableObject.getPosition().getX(), 0.0);
        Assert.assertEquals(8, movableObject.getPosition().getY(), 0.0);
    }
}
