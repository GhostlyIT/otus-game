package org.sunsetcode.command.exceptions;

import org.sunsetcode.command.Command;

public class CommandException extends Exception
{
    private final String commandName;

    private final String message;

    public CommandException(String commandName, String message)
    {
        this.commandName = commandName;
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return "Command " + commandName + " failed: " + message;
    }
}
