// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.ContextAware;
import org.apache.velocity.context.Context;

public interface NullSetEventHandler extends EventHandler
{
    boolean shouldLogOnNullSet(final String p0, final String p1);
    
    public static class ShouldLogOnNullSetExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String lhs;
        private String rhs;
        private boolean result;
        private boolean executed;
        
        ShouldLogOnNullSetExecutor(final Context context, final String lhs, final String rhs) {
            this.result = true;
            this.executed = false;
            this.context = context;
            this.lhs = lhs;
            this.rhs = rhs;
        }
        
        public void execute(final EventHandler handler) {
            final NullSetEventHandler eh = (NullSetEventHandler)handler;
            if (eh instanceof ContextAware) {
                ((ContextAware)eh).setContext(this.context);
            }
            this.executed = true;
            this.result = ((NullSetEventHandler)handler).shouldLogOnNullSet(this.lhs, this.rhs);
        }
        
        public Object getReturnValue() {
            return new Boolean(this.result);
        }
        
        public boolean isDone() {
            return this.executed && !this.result;
        }
    }
}
