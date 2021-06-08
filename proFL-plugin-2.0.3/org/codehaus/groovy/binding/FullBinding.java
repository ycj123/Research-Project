// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import groovy.lang.Closure;

public interface FullBinding extends BindingUpdatable
{
    SourceBinding getSourceBinding();
    
    TargetBinding getTargetBinding();
    
    void setSourceBinding(final SourceBinding p0);
    
    void setTargetBinding(final TargetBinding p0);
    
    void setValidator(final Closure p0);
    
    Closure getValidator();
    
    void setConverter(final Closure p0);
    
    Closure getConverter();
    
    void setReverseConverter(final Closure p0);
    
    Closure getReverseConverter();
}
