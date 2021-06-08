// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.Closure;

public class EventTriggerBinding implements TriggerBinding
{
    Object triggerBean;
    String eventName;
    
    public EventTriggerBinding(final Object triggerBean, final String eventName) {
        this.triggerBean = triggerBean;
        this.eventName = eventName;
    }
    
    public FullBinding createBinding(final SourceBinding sourceBinding, final TargetBinding targetBinding) {
        return new EventTriggerFullBinding(sourceBinding, targetBinding);
    }
    
    public Object getTriggerBean() {
        return this.triggerBean;
    }
    
    public void setTriggerBean(final Object triggerBean) {
        this.triggerBean = triggerBean;
    }
    
    public String getEventName() {
        return this.eventName;
    }
    
    public void setEventName(final String eventName) {
        this.eventName = eventName;
    }
    
    private class EventTriggerFullBinding extends AbstractFullBinding
    {
        Closure handler;
        
        public EventTriggerFullBinding(final SourceBinding sourceBinding, final TargetBinding targetBinding) {
            this.setSourceBinding(sourceBinding);
            this.setTargetBinding(targetBinding);
            this.handler = new Closure(EventTriggerBinding.this.triggerBean) {
                @Override
                public Object call(final Object[] params) {
                    if (sourceBinding instanceof ClosureSourceBinding) {
                        ((ClosureSourceBinding)sourceBinding).setClosureArguments(params);
                    }
                    EventTriggerFullBinding.this.update();
                    return null;
                }
            };
        }
        
        public void bind() {
            InvokerHelper.setProperty(EventTriggerBinding.this.triggerBean, EventTriggerBinding.this.eventName, this.handler);
        }
        
        public void unbind() {
            throw new UnsupportedOperationException();
        }
        
        public void rebind() {
            throw new UnsupportedOperationException();
        }
    }
}
