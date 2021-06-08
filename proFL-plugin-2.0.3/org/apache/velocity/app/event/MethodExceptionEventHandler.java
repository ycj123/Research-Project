// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.ContextAware;
import org.apache.velocity.context.Context;

public interface MethodExceptionEventHandler extends EventHandler
{
    Object methodException(final Class p0, final String p1, final Exception p2) throws Exception;
    
    public static class MethodExceptionExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private Class claz;
        private String method;
        private Exception e;
        private Object result;
        private boolean executed;
        
        MethodExceptionExecutor(final Context context, final Class claz, final String method, final Exception e) {
            this.executed = false;
            this.context = context;
            this.claz = claz;
            this.method = method;
            this.e = e;
        }
        
        public void execute(final EventHandler handler) throws Exception {
            final MethodExceptionEventHandler eh = (MethodExceptionEventHandler)handler;
            if (eh instanceof ContextAware) {
                ((ContextAware)eh).setContext(this.context);
            }
            this.executed = true;
            this.result = ((MethodExceptionEventHandler)handler).methodException(this.claz, this.method, this.e);
        }
        
        public Object getReturnValue() {
            return this.result;
        }
        
        public boolean isDone() {
            return this.executed;
        }
    }
}
