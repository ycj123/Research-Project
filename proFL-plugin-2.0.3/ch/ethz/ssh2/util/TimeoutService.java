// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.util;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import ch.ethz.ssh2.log.Logger;

public class TimeoutService
{
    private static final Logger log;
    private static final LinkedList todolist;
    private static Thread timeoutThread;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = TimeoutService.class$0) == null) {
            try {
                class$0 = (TimeoutService.class$0 = Class.forName("ch.ethz.ssh2.util.TimeoutService"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
        todolist = new LinkedList();
        TimeoutService.timeoutThread = null;
    }
    
    public static final TimeoutToken addTimeoutHandler(final long runTime, final Runnable handler) {
        final TimeoutToken token = new TimeoutToken(runTime, handler, null);
        synchronized (TimeoutService.todolist) {
            TimeoutService.todolist.add(token);
            Collections.sort((List<Comparable>)TimeoutService.todolist);
            if (TimeoutService.timeoutThread != null) {
                TimeoutService.timeoutThread.interrupt();
            }
            else {
                (TimeoutService.timeoutThread = new TimeoutThread((TimeoutThread)null)).setDaemon(true);
                TimeoutService.timeoutThread.start();
            }
        }
        // monitorexit(TimeoutService.todolist)
        return token;
    }
    
    public static final void cancelTimeoutHandler(final TimeoutToken token) {
        synchronized (TimeoutService.todolist) {
            TimeoutService.todolist.remove(token);
            if (TimeoutService.timeoutThread != null) {
                TimeoutService.timeoutThread.interrupt();
            }
        }
        // monitorexit(TimeoutService.todolist)
    }
    
    static /* synthetic */ void access$1(final Thread timeoutThread) {
        TimeoutService.timeoutThread = timeoutThread;
    }
    
    public static class TimeoutToken implements Comparable
    {
        private long runTime;
        private Runnable handler;
        
        private TimeoutToken(final long runTime, final Runnable handler) {
            this.runTime = runTime;
            this.handler = handler;
        }
        
        public int compareTo(final Object o) {
            final TimeoutToken t = (TimeoutToken)o;
            if (this.runTime > t.runTime) {
                return 1;
            }
            if (this.runTime == t.runTime) {
                return 0;
            }
            return -1;
        }
    }
    
    private static class TimeoutThread extends Thread
    {
        public void run() {
            synchronized (TimeoutService.todolist) {
                while (TimeoutService.todolist.size() != 0) {
                    final long now = System.currentTimeMillis();
                    final TimeoutToken tt = TimeoutService.todolist.getFirst();
                    if (tt.runTime > now) {
                        try {
                            TimeoutService.todolist.wait(tt.runTime - now);
                        }
                        catch (InterruptedException ex) {}
                    }
                    else {
                        TimeoutService.todolist.removeFirst();
                        try {
                            tt.handler.run();
                        }
                        catch (Exception e) {
                            final StringWriter sw = new StringWriter();
                            e.printStackTrace(new PrintWriter(sw));
                            TimeoutService.log.log(20, "Exeception in Timeout handler:" + e.getMessage() + "(" + sw.toString() + ")");
                        }
                    }
                }
                TimeoutService.access$1(null);
            }
            // monitorexit(TimeoutService.access$0())
        }
    }
}
