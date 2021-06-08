// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

public abstract class ArrayPutAtMetaMethod extends ArrayMetaMethod
{
    @Override
    public String getName() {
        return "putAt";
    }
    
    @Override
    public Class getReturnType() {
        return Void.class;
    }
}
