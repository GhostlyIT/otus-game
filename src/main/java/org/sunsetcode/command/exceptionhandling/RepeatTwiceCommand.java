package org.sunsetcode.command.exceptionhandling;

import org.sunsetcode.command.Command;

public class RepeatTwiceCommand implements Command
{
    private final Command command;

    public RepeatTwiceCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() throws Exception {
        command.execute();
    }
}
