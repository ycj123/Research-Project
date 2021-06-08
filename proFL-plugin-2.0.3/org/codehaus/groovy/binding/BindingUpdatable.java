// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

public interface BindingUpdatable
{
    void bind();
    
    void unbind();
    
    void rebind();
    
    void update();
    
    void reverseUpdate();
}
