package org.sunsetcode.command;

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
