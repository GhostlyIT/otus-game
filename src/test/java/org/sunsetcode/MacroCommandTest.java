package org.sunsetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.sunsetcode.command.BurnFuelCommand;
import org.sunsetcode.command.CheckFuelCommand;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.MoveCommand;
import org.sunsetcode.command.macro.AbstractMacroCommand;
import org.sunsetcode.gameobject.FuelTank;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MacroCommandTest
{
    @Test
    @DisplayName("Проверка макро комманды движения + сжигания топлива")
    public void testMoveAndBurnFuelMacroCommandExecution() throws Exception {
        Vector position = new Vector(12, 5);
        Vector velocity = new Vector(7, 3);
        Movable movable = mock(Movable.class);
        when(movable.getPosition()).thenReturn(position);
        when(movable.getVelocity()).thenReturn(velocity);

        ArgumentCaptor<Vector> vectorCapture = ArgumentCaptor.forClass(Vector.class);

        Mockito.doAnswer(
            invocation -> {
                Vector newVector = invocation.getArgumentAt(0, Vector.class);
                Mockito.when(movable.getPosition()).thenReturn(newVector);
                return null;
            }
        )
        .when(movable)
        .setPosition(vectorCapture.capture());

        FuelTank fuelTank = new FuelTank(50, 100, 10);
        AbstractMacroCommand macroCommand = mock(AbstractMacroCommand.class, Mockito.CALLS_REAL_METHODS);

        when(macroCommand.getCommands()).thenReturn(new Command[]{
                new CheckFuelCommand(fuelTank),
                new MoveCommand(movable),
                new BurnFuelCommand(fuelTank)
        });

        macroCommand.execute();

        assertEquals(19, movable.getPosition().getX());
        assertEquals(8, movable.getPosition().getY());
        assertEquals(40, fuelTank.getAmount());
    }
}
