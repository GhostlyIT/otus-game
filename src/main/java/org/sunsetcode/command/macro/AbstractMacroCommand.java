package org.sunsetcode.command.macro;

import org.sunsetcode.command.Command;
import org.sunsetcode.command.exceptions.CommandException;

public abstract class AbstractMacroCommand implements Command
{
    @Override
    public void execute() throws CommandException
    {
        for (Command command : this.getCommands()) {
            try {
                command.execute();
            } catch (Exception e) {
                throw new CommandException(command.getClass().getName());
            }
        }
    }

    public abstract Command[] getCommands();
}
