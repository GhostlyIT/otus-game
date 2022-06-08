package org.sunsetcode.command.queue;

import org.sunsetcode.command.Command;
import org.sunsetcode.queue.QueueControl;

public class StopSoftCommand implements Command
{
    private final QueueControl QueueControl;

    public StopSoftCommand(QueueControl QueueControl)
    {
        this.QueueControl = QueueControl;
    }

    @Override
    public void execute()
    {
        QueueControl.softStop();
    }
}
