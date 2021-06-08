// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.beans.PropertyChangeListener;

public class PropertyChangeProxyTargetBinding implements TargetBinding
{
    Object proxyObject;
    String propertyName;
    PropertyChangeListener listener;
    
    public PropertyChangeProxyTargetBinding(final Object proxyObject, final String propertyName, final PropertyChangeListener listener) {
        this.proxyObject = proxyObject;
        this.propertyName = propertyName;
        this.listener = listener;
    }
    
    public void updateTargetValue(final Object value) {
        this.listener.propertyChange(new PropertyChangeEvent(this.proxyObject, this.propertyName, InvokerHelper.getProperty(this.proxyObject, this.propertyName), value));
    }
}
