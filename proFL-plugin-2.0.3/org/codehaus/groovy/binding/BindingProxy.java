// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import groovy.lang.ReadOnlyPropertyException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import groovy.lang.GroovyObjectSupport;

public class BindingProxy extends GroovyObjectSupport implements BindingUpdatable
{
    Object model;
    boolean bound;
    final Map<String, PropertyBinding> propertyBindings;
    final List<FullBinding> generatedBindings;
    
    public BindingProxy(final Object model) {
        this.propertyBindings = new HashMap<String, PropertyBinding>();
        this.generatedBindings = new ArrayList<FullBinding>();
        this.model = model;
    }
    
    public Object getModel() {
        return this.model;
    }
    
    public synchronized void setModel(final Object model) {
        final boolean bindAgain = this.bound;
        this.model = model;
        this.unbind();
        for (final PropertyBinding propertyBinding : this.propertyBindings.values()) {
            propertyBinding.setBean(model);
        }
        if (bindAgain) {
            this.bind();
            this.update();
        }
    }
    
    @Override
    public Object getProperty(final String property) {
        PropertyBinding pb;
        synchronized (this.propertyBindings) {
            pb = this.propertyBindings.get(property);
            if (pb == null) {
                pb = new ModelBindingPropertyBinding(this.model, property);
                this.propertyBindings.put(property, pb);
            }
        }
        final FullBinding fb = pb.createBinding(pb, null);
        if (this.bound) {
            fb.bind();
        }
        return fb;
    }
    
    @Override
    public void setProperty(final String property, final Object value) {
        throw new ReadOnlyPropertyException(property, this.model.getClass());
    }
    
    public void bind() {
        synchronized (this.generatedBindings) {
            if (!this.bound) {
                this.bound = true;
                for (final FullBinding generatedBinding : this.generatedBindings) {
                    generatedBinding.bind();
                }
            }
        }
    }
    
    public void unbind() {
        synchronized (this.generatedBindings) {
            if (this.bound) {
                this.bound = false;
                for (final FullBinding generatedBinding : this.generatedBindings) {
                    generatedBinding.unbind();
                }
            }
        }
    }
    
    public void rebind() {
        synchronized (this.generatedBindings) {
            if (this.bound) {
                for (final FullBinding generatedBinding : this.generatedBindings) {
                    generatedBinding.rebind();
                }
            }
        }
    }
    
    public void update() {
        synchronized (this.generatedBindings) {
            for (final FullBinding generatedBinding : this.generatedBindings) {
                generatedBinding.update();
            }
        }
    }
    
    public void reverseUpdate() {
        synchronized (this.generatedBindings) {
            for (final FullBinding generatedBinding : this.generatedBindings) {
                generatedBinding.reverseUpdate();
            }
        }
    }
    
    class ModelBindingPropertyBinding extends PropertyBinding
    {
        public ModelBindingPropertyBinding(final Object bean, final String propertyName) {
            super(bean, propertyName);
        }
        
        @Override
        public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
            final FullBinding fb = super.createBinding(source, target);
            BindingProxy.this.generatedBindings.add(fb);
            return fb;
        }
    }
}
