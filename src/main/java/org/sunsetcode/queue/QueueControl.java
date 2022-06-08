package org.sunsetcode.queue;

import org.sunsetcode.command.Command;
import org.sunsetcode.command.queue.StopSoftCommand;

import java.util.concurrent.BlockingQueue;

public class QueueControl
{
    private final QueueThread processingThread;
    private final BlockingQueue<Command> commandQueue;

    public QueueControl(QueueThread queueThread, BlockingQueue<Command> commandQueue)
    {
        this.processingThread = queueThread;
        this.commandQueue = commandQueue;
    }

    public void start()
    {
        processingThread.start();
    }

    public void softStop()
    {
        if (commandQueue.isEmpty()) {
            processingThread.stop();
            commandQueue.add(new StopSoftCommand(this));
        } else {
            if (commandQueue.stream().noneMatch(x -> x instanceof StopSoftCommand))
                commandQueue.add(new StopSoftCommand(this));
        }
    }

    public void hardStop()
    {
        processingThread.hardStop();
    }
}
