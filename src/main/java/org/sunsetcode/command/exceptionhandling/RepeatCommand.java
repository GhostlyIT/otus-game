package org.sunsetcode.command.exceptionhandling;

import org.sunsetcode.command.Command;

public class RepeatCommand implements Command
{
    private final Command command;

    public RepeatCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() throws Exception {
        command.execute();
    }
}
