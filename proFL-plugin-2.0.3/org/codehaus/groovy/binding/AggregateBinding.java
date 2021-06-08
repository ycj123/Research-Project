// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class AggregateBinding implements BindingUpdatable
{
    protected boolean bound;
    protected Set<BindingUpdatable> bindings;
    
    public AggregateBinding() {
        this.bindings = new LinkedHashSet<BindingUpdatable>();
    }
    
    public void addBinding(final BindingUpdatable binding) {
        if (this.bound) {
            binding.bind();
        }
        this.bindings.add(binding);
    }
    
    public void removeBinding(final BindingUpdatable binding) {
        this.bindings.remove(binding);
    }
    
    public void bind() {
        if (!this.bound) {
            this.bound = true;
            for (final BindingUpdatable binding : this.bindings) {
                binding.bind();
            }
        }
    }
    
    public void unbind() {
        if (this.bound) {
            for (final BindingUpdatable binding : this.bindings) {
                binding.unbind();
            }
            this.bound = false;
        }
    }
    
    public void rebind() {
        if (this.bound) {
            this.unbind();
            this.bind();
        }
    }
    
    public void update() {
        for (final BindingUpdatable binding : this.bindings) {
            binding.update();
        }
    }
    
    public void reverseUpdate() {
        for (final BindingUpdatable binding : this.bindings) {
            binding.reverseUpdate();
        }
    }
}
