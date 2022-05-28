package org.sunsetcode.exception.handler;

import org.sunsetcode.IoC.IoC;
import org.sunsetcode.command.Command;
import org.sunsetcode.command.RepeatCommand;
import org.sunsetcode.command.RepeatTwiceCommand;

import java.util.concurrent.BlockingQueue;

public class RepeatCommandHandler implements Command
{
    private final Command command;

    public RepeatCommandHandler(Command command)
    {
        this.command = command;
    }

    @Override
    public void execute()
    {
        var queue = IoC.<BlockingQueue<Command>>resolve("Command.Queue");

        Command repeatCommand = new RepeatCommand(command);
        if (command instanceof RepeatCommand) {
            repeatCommand = new RepeatTwiceCommand(command);
        }

        queue.add(repeatCommand);
    }
}
