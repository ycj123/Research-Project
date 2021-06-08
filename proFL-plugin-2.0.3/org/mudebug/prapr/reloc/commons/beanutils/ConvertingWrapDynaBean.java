// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

public class ConvertingWrapDynaBean extends WrapDynaBean
{
    public ConvertingWrapDynaBean(final Object instance) {
        super(instance);
    }
    
    public void set(final String name, final Object value) {
        try {
            BeanUtils.copyProperty(this.instance, name, value);
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no write method");
        }
    }
}
