// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl.arrays;

public abstract class ArrayGetAtMetaMethod extends ArrayMetaMethod
{
    protected ArrayGetAtMetaMethod() {
        this.parameterTypes = ArrayGetAtMetaMethod.INTEGER_CLASS_ARR;
    }
    
    @Override
    public String getName() {
        return "getAt";
    }
}
