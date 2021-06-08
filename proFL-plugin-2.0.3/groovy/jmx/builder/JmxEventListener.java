// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.HashMap;
import groovy.lang.Closure;
import java.util.Map;
import javax.management.Notification;
import javax.management.NotificationListener;

public class JmxEventListener implements NotificationListener
{
    private static JmxEventListener listener;
    
    public static synchronized JmxEventListener getListner() {
        if (JmxEventListener.listener == null) {
            JmxEventListener.listener = new JmxEventListener();
        }
        return JmxEventListener.listener;
    }
    
    public void handleNotification(final Notification notification, final Object handback) {
        final Map event = (Map)handback;
        if (event != null) {
            final Object del = event.get("managedObject");
            final Object callback = event.get("callback");
            if (callback != null && callback instanceof Closure) {
                final Closure closure = (Closure)callback;
                closure.setDelegate(del);
                if (closure.getMaximumNumberOfParameters() == 1) {
                    closure.call(buildOperationNotificationPacket(notification));
                }
                else {
                    closure.call();
                }
            }
        }
    }
    
    private static Map buildOperationNotificationPacket(final Notification note) {
        final Map<String, Object> result = new HashMap<String, Object>();
        result.put("event", note.getType());
        result.put("source", note.getSource());
        result.put("sequenceNumber", note.getSequenceNumber());
        result.put("timeStamp", note.getTimeStamp());
        result.put("data", note.getUserData());
        return result;
    }
}
