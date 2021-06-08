// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.ContextAware;
import org.apache.velocity.context.Context;

public interface ReferenceInsertionEventHandler extends EventHandler
{
    Object referenceInsert(final String p0, final Object p1);
    
    public static class referenceInsertExecutor implements EventHandlerMethodExecutor
    {
        private Context context;
        private String reference;
        private Object value;
        
        referenceInsertExecutor(final Context context, final String reference, final Object value) {
            this.context = context;
            this.reference = reference;
            this.value = value;
        }
        
        public void execute(final EventHandler handler) {
            final ReferenceInsertionEventHandler eh = (ReferenceInsertionEventHandler)handler;
            if (eh instanceof ContextAware) {
                ((ContextAware)eh).setContext(this.context);
            }
            this.value = ((ReferenceInsertionEventHandler)handler).referenceInsert(this.reference, this.value);
        }
        
        public Object getReturnValue() {
            return this.value;
        }
        
        public boolean isDone() {
            return false;
        }
    }
}
