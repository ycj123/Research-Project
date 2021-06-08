// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.util;

public final class TimeoutController
{
    private TimeoutController() {
    }
    
    public static void execute(final Thread task, final long timeout) throws TimeoutException {
        task.start();
        try {
            task.join(timeout);
        }
        catch (InterruptedException ex) {}
        if (task.isAlive()) {
            task.interrupt();
            throw new TimeoutException();
        }
    }
    
    public static void execute(final Runnable task, final long timeout) throws TimeoutException {
        final Thread t = new Thread(task, "Timeout guard");
        t.setDaemon(true);
        execute(t, timeout);
    }
    
    public static class TimeoutException extends Exception
    {
    }
}
