package org.sunsetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sunsetcode.IoC.IoC;
import org.sunsetcode.command.*;
import org.sunsetcode.command.exceptionhandling.LogCommand;
import org.sunsetcode.command.exceptionhandling.RepeatCommand;
import org.sunsetcode.command.exceptionhandling.RepeatTwiceCommand;
import org.sunsetcode.exception.handler.LogHandler;
import org.sunsetcode.exception.handler.RepeatCommandHandler;
import org.sunsetcode.movement.Movable;
import org.sunsetcode.movement.adapters.MovableAdapter;
import org.sunsetcode.movement.exceptions.UndefinedPositionException;
import org.sunsetcode.queue.QueueControl;
import org.sunsetcode.queue.QueueThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExceptionsHandlingTest
{
    private BlockingQueue<Command> commandQueue;
    private QueueControl queueControl;
    private QueueThread queueThread;
    private Logger logger;
    private Movable movable;
    private final List<Class<? extends Command>> processedCommands = new ArrayList<>();

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception
    {
        commandQueue = (BlockingQueue<Command>) spy(LinkedBlockingQueue.class);
        doAnswer(
                i -> {
                    Command c = (Command) i.callRealMethod();
                    processedCommands.add(c.getClass());
                    return c;
                }
        ).when(commandQueue).take();
        queueThread = new QueueThread(commandQueue);
        queueControl = new QueueControl(queueThread, commandQueue);
        logger = mock(Logger.class);
        movable = mock(MovableAdapter.class);
        when(movable.getPosition()).thenThrow(new UndefinedPositionException());

        IoC.<Command>resolve("IoC.Registry", "Command.Queue",
                (Function<Object[], BlockingQueue<Command>>) (args) -> commandQueue).execute();
        IoC.<Command>resolve("IoC.Registry", "Application.Logger",
                (Function<Object[], Logger>) (args) -> logger).execute();
        queueControl.start();
    }

    @Test
    @DisplayName("Обработчик исключения, который ставит Команду, пишущую в лог, в очередь Команд")
    public void exceptionHandlerLogCommandTest() throws Exception
    {
        IoC.<Command>resolve("IoC.Registry", "MoveCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new LogHandler((Command) args[0], (Exception) args[1])).execute();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        doAnswer(i -> {
            countDownLatch.countDown();
            return null;
        }).when(logger).log(any(), anyString());

        MoveCommand cmd = new MoveCommand(movable);
        commandQueue.add(cmd);
        var i = countDownLatch.await(30, TimeUnit.SECONDS);

        verify(commandQueue, atLeast(2)).take();
        assertTrue(processedCommands.containsAll(List.of(MoveCommand.class, LogCommand.class)));
        verify(movable, times(1)).getPosition();
        verify(logger, times(1)).log(Level.SEVERE, "MoveCommand error. Reason: Movable object has undefined position");
    }

    @Test
    @DisplayName("Повторить команду, выбросившую исключение")
    public void exceptionHandlerRepeatAndLogCommandTest() throws Exception
    {
        IoC.<Command>resolve("IoC.Registry", "MoveCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new RepeatCommandHandler((Command) args[0])).execute();

        IoC.<Command>resolve("IoC.Registry", "RepeatCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new LogHandler((Command) args[0], (Exception) args[1])).execute();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        doAnswer(i -> {
            countDownLatch.countDown();
            return null;
        }).when(logger).log(any(), anyString());

        MoveCommand cmd = new MoveCommand(movable);
        commandQueue.add(cmd);
        var i = countDownLatch.await(30, TimeUnit.SECONDS);

        verify(commandQueue, atLeast(3)).take();
        assertTrue(processedCommands.containsAll(List.of(MoveCommand.class, LogCommand.class, RepeatCommand.class)));
        verify(movable, times(2)).getPosition();
        verify(logger, times(1)).log(Level.SEVERE, "RepeatCommand error. Reason: Movable object has undefined position");
    }

    @Test
    @DisplayName("Повторить дважды команду, выбросивушю исключение, и поставить команду, пишущую в лог, в очередь команд.")
    public void exceptionHandlerTwoRepeatAndLogCommandTest() throws Exception
    {
        IoC.<Command>resolve("IoC.Registry", "MoveCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new RepeatCommandHandler((Command) args[0])).execute();

        IoC.<Command>resolve("IoC.Registry", "RepeatCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new RepeatCommandHandler((Command) args[0])).execute();

        IoC.<Command>resolve("IoC.Registry", "RepeatTwiceCommand.UndefinedPositionException",
                (Function<Object[], Command>) (args) -> new LogHandler((Command) args[0], (Exception) args[1])).execute();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        doAnswer(i -> {
            countDownLatch.countDown();
            return null;
        }).when(logger).log(any(), anyString());
        MoveCommand cmd = new MoveCommand(movable);
        commandQueue.add(cmd);
        var i = countDownLatch.await(30, TimeUnit.SECONDS);
        verify(commandQueue, atLeast(3)).take();
        assertTrue(processedCommands.containsAll(List.of(MoveCommand.class, RepeatCommand.class, RepeatTwiceCommand.class)));
        verify(movable, times(3)).getPosition();
        verify(logger, times(1)).log(Level.SEVERE, "RepeatTwiceCommand error. Reason: Movable object has undefined position");
    }

    @AfterEach
    public void tearDown() throws InterruptedException
    {
        queueControl.softStop();
        queueThread.join(30000);
        processedCommands.clear();
    }
}
