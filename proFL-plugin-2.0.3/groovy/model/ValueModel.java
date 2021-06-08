// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

public interface ValueModel
{
    Object getValue();
    
    void setValue(final Object p0);
    
    Class getType();
    
    boolean isEditable();
}
