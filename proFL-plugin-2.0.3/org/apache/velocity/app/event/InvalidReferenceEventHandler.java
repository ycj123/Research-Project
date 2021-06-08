// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.introspection.Info;
import org.apache.velocity.context.Context;

public interface InvalidReferenceEventHandler extends EventHandler
{
    Object invalidGetMethod(final Context p0, final String p1, final Object p2, final String p3, final Info p4);
    
    boolean invalidSetMethod(final Context p0, final String p1, final String p2, final Info p3);
    
    Object invalidMethod(final Context p0, final String p1, final Object p2, final String p3, final Info p4);
    
    public static class InvalidGetMethodExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String reference;
        private Object object;
        private String property;
        private Info info;
        private Object result;
        
        InvalidGetMethodExecutor(final Context context, final String reference, final Object object, final String property, final Info info) {
            this.context = context;
            this.reference = reference;
            this.object = object;
            this.property = property;
            this.info = info;
        }
        
        public void execute(final EventHandler handler) {
            this.result = ((InvalidReferenceEventHandler)handler).invalidGetMethod(this.context, this.reference, this.object, this.property, this.info);
        }
        
        public Object getReturnValue() {
            return this.result;
        }
        
        public boolean isDone() {
            return this.result != null;
        }
    }
    
    public static class InvalidSetMethodExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String leftreference;
        private String rightreference;
        private Info info;
        private boolean result;
        
        InvalidSetMethodExecutor(final Context context, final String leftreference, final String rightreference, final Info info) {
            this.context = context;
            this.leftreference = leftreference;
            this.rightreference = rightreference;
            this.info = info;
        }
        
        public void execute(final EventHandler handler) {
            this.result = ((InvalidReferenceEventHandler)handler).invalidSetMethod(this.context, this.leftreference, this.rightreference, this.info);
        }
        
        public Object getReturnValue() {
            return null;
        }
        
        public boolean isDone() {
            return this.result;
        }
    }
    
    public static class InvalidMethodExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String reference;
        private Object object;
        private String method;
        private Info info;
        private Object result;
        private boolean executed;
        
        InvalidMethodExecutor(final Context context, final String reference, final Object object, final String method, final Info info) {
            this.executed = false;
            this.context = context;
            this.reference = reference;
            this.object = object;
            this.method = method;
            this.info = info;
        }
        
        public void execute(final EventHandler handler) {
            this.executed = true;
            this.result = ((InvalidReferenceEventHandler)handler).invalidMethod(this.context, this.reference, this.object, this.method, this.info);
        }
        
        public Object getReturnValue() {
            return this.result;
        }
        
        public boolean isDone() {
            return this.executed && this.result != null;
        }
    }
}
