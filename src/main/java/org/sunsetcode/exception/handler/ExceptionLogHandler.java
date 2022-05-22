package org.sunsetcode.exception.handler;

import org.sunsetcode.IoC.IoC;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.LogCommand;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;

public class ExceptionLogHandler implements Command
{
    private final Command command;
    private final Exception ex;

    public ExceptionLogHandler(Command command, Exception ex)
    {
        this.command = command;
        this.ex = ex;
    }

    @Override
    public void execute() throws Exception
    {
        Command logCommand = new LogCommand(Level.SEVERE, command.getClass().getSimpleName() + " error. Reason: " + ex.getMessage());
        var queue = IoC.<BlockingQueue<Command>>resolve("Command.Queue");
        queue.add(logCommand);
    }
}
