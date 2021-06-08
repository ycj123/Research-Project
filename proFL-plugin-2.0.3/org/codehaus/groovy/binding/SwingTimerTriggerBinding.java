// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

public class SwingTimerTriggerBinding implements TriggerBinding
{
    public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
        return new SwingTimerFullBinding((ClosureSourceBinding)source, target);
    }
}
