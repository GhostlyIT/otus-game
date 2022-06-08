package org.sunsetcode.command.exceptions;

public class CommandException extends Exception
{
    private final String commandName;

    private String message = "";

    public CommandException(String commandName)
    {
        this.commandName = commandName;
    }

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
