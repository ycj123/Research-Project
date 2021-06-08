// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

public interface DynaClass
{
    String getName();
    
    DynaProperty getDynaProperty(final String p0);
    
    DynaProperty[] getDynaProperties();
    
    DynaBean newInstance() throws IllegalAccessException, InstantiationException;
}
