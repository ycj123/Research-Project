// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import groovy.lang.MissingMethodException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import java.beans.PropertyVetoException;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class PropertyBinding implements SourceBinding, TargetBinding, TriggerBinding
{
    Object bean;
    String propertyName;
    boolean nonChangeCheck;
    
    public PropertyBinding(final Object bean, final String propertyName) {
        this.bean = bean;
        this.propertyName = propertyName;
    }
    
    public void updateTargetValue(final Object newValue) {
        if (this.nonChangeCheck && DefaultTypeTransformation.compareEqual(this.getSourceValue(), newValue)) {
            return;
        }
        try {
            InvokerHelper.setProperty(this.bean, this.propertyName, newValue);
        }
        catch (InvokerInvocationException iie) {
            if (!(iie.getCause() instanceof PropertyVetoException)) {
                throw iie;
            }
        }
    }
    
    public boolean isNonChangeCheck() {
        return this.nonChangeCheck;
    }
    
    public void setNonChangeCheck(final boolean nonChangeCheck) {
        this.nonChangeCheck = nonChangeCheck;
    }
    
    public Object getSourceValue() {
        return InvokerHelper.getPropertySafe(this.bean, this.propertyName);
    }
    
    public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
        return new PropertyFullBinding(source, target);
    }
    
    public Object getBean() {
        return this.bean;
    }
    
    public void setBean(final Object bean) {
        this.bean = bean;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }
    
    class PropertyFullBinding extends AbstractFullBinding implements PropertyChangeListener
    {
        Object boundBean;
        Object boundProperty;
        boolean bound;
        boolean boundToProperty;
        
        PropertyFullBinding(final SourceBinding source, final TargetBinding target) {
            this.setSourceBinding(source);
            this.setTargetBinding(target);
        }
        
        public void propertyChange(final PropertyChangeEvent event) {
            if (this.boundToProperty || event.getPropertyName().equals(this.boundProperty)) {
                this.update();
            }
        }
        
        public void bind() {
            if (!this.bound) {
                this.bound = true;
                this.boundBean = PropertyBinding.this.bean;
                this.boundProperty = PropertyBinding.this.propertyName;
                try {
                    InvokerHelper.invokeMethodSafe(this.boundBean, "addPropertyChangeListener", new Object[] { this.boundProperty, this });
                    this.boundToProperty = true;
                }
                catch (MissingMethodException mme) {
                    try {
                        this.boundToProperty = false;
                        InvokerHelper.invokeMethodSafe(this.boundBean, "addPropertyChangeListener", new Object[] { this });
                    }
                    catch (MissingMethodException mme2) {
                        throw new RuntimeException("Properties in beans of type " + PropertyBinding.this.bean.getClass().getName() + " are not observable in any capacity (no PropertyChangeListener support).");
                    }
                }
            }
        }
        
        public void unbind() {
            if (this.bound) {
                if (this.boundToProperty) {
                    try {
                        InvokerHelper.invokeMethodSafe(this.boundBean, "removePropertyChangeListener", new Object[] { this.boundProperty, this });
                    }
                    catch (MissingMethodException mme) {}
                }
                else {
                    try {
                        InvokerHelper.invokeMethodSafe(this.boundBean, "removePropertyChangeListener", new Object[] { this });
                    }
                    catch (MissingMethodException ex) {}
                }
                this.boundBean = null;
                this.boundProperty = null;
                this.bound = false;
            }
        }
        
        public void rebind() {
            if (this.bound) {
                this.unbind();
                this.bind();
            }
        }
    }
}
