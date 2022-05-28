package org.sunsetcode.command;

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
