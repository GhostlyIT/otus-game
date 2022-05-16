package org.sunsetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.sunsetcode.movement.exceptions.MovableException;
import org.sunsetcode.movement.exceptions.UndefinedPositionException;
import org.sunsetcode.movement.exceptions.UndefinedVelocityException;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.Movement;
import org.sunsetcode.movement.Vector;

public class MovableObjectTest
{
    private Movable movableObject;

    @Before
    public void setUp() throws MovableException
    {
        movableObject = Mockito.mock(Movable.class);
        Vector position = new Vector(12, 5);
        Vector velocity = new Vector(-7, 3);
        try {
            Mockito.when(movableObject.getPosition()).thenReturn(position);
            Mockito.when(movableObject.getVelocity()).thenReturn(velocity);
        } catch (MovableException e) {
            e.printStackTrace();
        }

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
    public void testPositionChanged() throws MovableException {
        Movement movement = new Movement(movableObject);
        movement.execute();

        Assert.assertEquals(5, movableObject.getPosition().getX());
        Assert.assertEquals(8, movableObject.getPosition().getY());
    }

    @Test(expected = UndefinedPositionException.class)
    public void testUndefinedPosition() throws MovableException
    {
        Mockito.when(movableObject.getPosition()).thenThrow(UndefinedPositionException.class);
        Movement movement = new Movement(movableObject);
        movement.execute();
    }

    @Test(expected = UndefinedVelocityException.class)
    public void testUndefinedVelocity() throws MovableException
    {
        Mockito.when(movableObject.getVelocity()).thenThrow(UndefinedVelocityException.class);
        Movement movement = new Movement(movableObject);
        movement.execute();
    }

    @Test(expected = MovableException.class)
    public void testImpossibleToSetPosition() throws MovableException
    {
        ArgumentCaptor<Vector> vectorCapture = ArgumentCaptor.forClass(Vector.class);
        Mockito.doThrow(MovableException.class).when(movableObject).setPosition(vectorCapture.capture());

        Movement movement = new Movement(movableObject);
        movement.execute();
    }
}
