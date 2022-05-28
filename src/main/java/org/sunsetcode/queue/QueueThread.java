package org.sunsetcode.queue;

import org.sunsetcode.IoC.IoC;
import org.sunsetcode.command.Command;

import java.util.concurrent.BlockingQueue;

public class QueueThread
{
    private final Thread thread;
    private final BlockingQueue<Command> queue;
    private boolean stop;

    public QueueThread(BlockingQueue<Command> queue)
    {
        this.queue = queue;
        this.thread = new Thread(() -> {
            try {
                while (!stop) {
                    var cmd = this.queue.take();
                    try {
                        cmd.execute();
                    } catch (Exception e) {
                        IoC.<Command>resolve(cmd.getClass().getSimpleName() + "." + e.getClass().getSimpleName(), cmd, e).execute();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void start()
    {
        stop = false;
        thread.start();
    }

    public void stop()
    {
        stop = true;
    }

    public void hardStop()
    {
        stop = true;
        thread.interrupt();
    }

    public void join(long ms) throws InterruptedException
    {
        thread.join(ms);
    }

    public Thread.State getState()
    {
        return thread.getState();
    }
}
