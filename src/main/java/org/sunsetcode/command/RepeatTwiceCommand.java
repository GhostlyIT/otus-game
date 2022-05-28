package org.sunsetcode.command;

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
