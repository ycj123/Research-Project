// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.ContextAware;
import org.apache.velocity.context.Context;

public interface IncludeEventHandler extends EventHandler
{
    String includeEvent(final String p0, final String p1, final String p2);
    
    public static class IncludeEventExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String includeResourcePath;
        private String currentResourcePath;
        private String directiveName;
        private boolean executed;
        
        IncludeEventExecutor(final Context context, final String includeResourcePath, final String currentResourcePath, final String directiveName) {
            this.executed = false;
            this.context = context;
            this.includeResourcePath = includeResourcePath;
            this.currentResourcePath = currentResourcePath;
            this.directiveName = directiveName;
        }
        
        public void execute(final EventHandler handler) {
            final IncludeEventHandler eh = (IncludeEventHandler)handler;
            if (eh instanceof ContextAware) {
                ((ContextAware)eh).setContext(this.context);
            }
            this.executed = true;
            this.includeResourcePath = ((IncludeEventHandler)handler).includeEvent(this.includeResourcePath, this.currentResourcePath, this.directiveName);
        }
        
        public Object getReturnValue() {
            return this.includeResourcePath;
        }
        
        public boolean isDone() {
            return this.executed && this.includeResourcePath == null;
        }
    }
}
