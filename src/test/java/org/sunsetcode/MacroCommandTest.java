package org.sunsetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.sunsetcode.command.macro.MoveAndBurnFuelCommand;
import org.sunsetcode.command.macro.RotateAndChangeVelocityCommand;
import org.sunsetcode.gameobject.FuelTank;
import org.sunsetcode.gameobject.UObject;
import org.sunsetcode.movement.Direction;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.Rotable;
import org.sunsetcode.movement.Vector;
import org.sunsetcode.movement.adapters.MovableAdapter;
import org.sunsetcode.movement.adapters.RotableAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MacroCommandTest
{
    private UObject uObject;

    @BeforeEach
    public void setUp()
    {
        uObject = mock(UObject.class);
        when(uObject.<Vector>getProperty("position")).thenReturn(new Vector(12, 5));
        when(uObject.<Direction>getProperty("direction")).thenReturn(new Direction(5));
        when(uObject.<Integer>getProperty("velocity")).thenReturn(50);
        when(uObject.<Integer>getProperty("angularVelocity")).thenReturn(50);

        ArgumentCaptor<Vector> positionCapture = ArgumentCaptor.forClass(Vector.class);

        Mockito.doAnswer(
                invocation -> {
                    Vector newPosition = invocation.getArgumentAt(1, Vector.class);
                    when(uObject.<Vector>getProperty("position")).thenReturn(newPosition);
                    return null;
                }
        )
        .when(uObject)
        .setProperty(eq("position"), positionCapture.capture());

        ArgumentCaptor<Integer> velocityCapture = ArgumentCaptor.forClass(Integer.class);

        Mockito.doAnswer(
                invocation -> {
                    int newVelocity = invocation.getArgumentAt(1, Integer.class);
                    when(uObject.<Integer>getProperty("velocity")).thenReturn(newVelocity);
                    return null;
                }
        )
        .when(uObject)
        .setProperty(eq("velocity"), velocityCapture.capture());

        ArgumentCaptor<Direction> directionCapture = ArgumentCaptor.forClass(Direction.class);

        Mockito.doAnswer(
                invocation -> {
                    Direction newDirection = invocation.getArgumentAt(1, Direction.class);
                    when(uObject.<Direction>getProperty("direction")).thenReturn(newDirection);
                    return null;
                }
        )
        .when(uObject)
        .setProperty(eq("direction"), directionCapture.capture());
    }

    @Test
    @DisplayName("Движение + сжигание топлива")
    public void testMoveAndBurnFuelMacroCommandExecution() throws Exception
    {
        Movable movable = new MovableAdapter(uObject);
        FuelTank fuelTank = new FuelTank(50, 100, 10);
        MoveAndBurnFuelCommand command = new MoveAndBurnFuelCommand(movable, fuelTank);

        command.execute();

        assertEquals(61, movable.getPosition().getX());
        assertEquals(10, movable.getPosition().getY());
        assertEquals(40, fuelTank.getAmount());
    }

    @Test
    @DisplayName("Поворот + изменение вектора мгновенной скорости")
    public void testRotateAndChangeVelocityCommand() throws Exception
    {
        Rotable rotable = new RotableAdapter(uObject);
        RotateAndChangeVelocityCommand command = new RotateAndChangeVelocityCommand(rotable, uObject, 30);
        command.execute();

        assertEquals(30, uObject.<Integer>getProperty("velocity"));
        assertEquals(7, rotable.getDirection().getCurrentDirection());
    }
}
