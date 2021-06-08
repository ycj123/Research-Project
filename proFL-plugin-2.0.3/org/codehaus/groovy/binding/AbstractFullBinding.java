// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import groovy.lang.Closure;

public abstract class AbstractFullBinding implements FullBinding
{
    protected SourceBinding sourceBinding;
    protected TargetBinding targetBinding;
    protected Closure validator;
    protected Closure converter;
    protected Closure reverseConverter;
    
    private void fireBinding() {
        if (this.sourceBinding == null || this.targetBinding == null) {
            return;
        }
        Object result = this.sourceBinding.getSourceValue();
        if (this.getValidator() != null) {
            final Object validation = this.getValidator().call(result);
            if (validation == null || (validation instanceof Boolean && !(boolean)validation)) {
                return;
            }
        }
        if (this.getConverter() != null) {
            result = this.getConverter().call(result);
        }
        this.targetBinding.updateTargetValue(result);
    }
    
    public void update() {
        this.fireBinding();
    }
    
    private void fireReverseBinding() {
        if (!(this.sourceBinding instanceof TargetBinding) || !(this.targetBinding instanceof SourceBinding)) {
            throw new RuntimeException("Binding Instance is not reversable");
        }
        Object result = ((SourceBinding)this.targetBinding).getSourceValue();
        if (this.getReverseConverter() != null) {
            result = this.getReverseConverter().call(result);
        }
        ((TargetBinding)this.sourceBinding).updateTargetValue(result);
    }
    
    public void reverseUpdate() {
        this.fireReverseBinding();
    }
    
    public SourceBinding getSourceBinding() {
        return this.sourceBinding;
    }
    
    public void setSourceBinding(final SourceBinding sourceBinding) {
        this.sourceBinding = sourceBinding;
    }
    
    public TargetBinding getTargetBinding() {
        return this.targetBinding;
    }
    
    public void setTargetBinding(final TargetBinding targetBinding) {
        this.targetBinding = targetBinding;
    }
    
    public Closure getValidator() {
        return this.validator;
    }
    
    public void setValidator(final Closure validator) {
        this.validator = validator;
    }
    
    public Closure getConverter() {
        return this.converter;
    }
    
    public void setConverter(final Closure converter) {
        this.converter = converter;
    }
    
    public Closure getReverseConverter() {
        return this.reverseConverter;
    }
    
    public void setReverseConverter(final Closure reverseConverter) {
        this.reverseConverter = reverseConverter;
    }
}
