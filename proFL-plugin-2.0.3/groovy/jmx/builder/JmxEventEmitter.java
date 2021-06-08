// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.concurrent.atomic.AtomicLong;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class JmxEventEmitter extends NotificationBroadcasterSupport implements JmxEventEmitterMBean
{
    private String event;
    private String message;
    
    public String getEvent() {
        return this.event;
    }
    
    public void setEvent(final String event) {
        this.event = event;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public long send(final Object data) {
        final long seq = NumberSequencer.getNextSequence();
        final Notification note = new Notification(this.getEvent(), this, seq, System.currentTimeMillis(), "Event notification " + this.getEvent());
        note.setUserData(data);
        super.sendNotification(note);
        return seq;
    }
    
    private static class NumberSequencer
    {
        private static AtomicLong num;
        
        public static long getNextSequence() {
            return NumberSequencer.num.incrementAndGet();
        }
        
        static {
            NumberSequencer.num = new AtomicLong(0L);
        }
    }
}
