// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.reflection.CachedClass;

public class NewMetaMethod extends ReflectionMetaMethod
{
    protected static final CachedClass[] EMPTY_TYPE_ARRAY;
    protected CachedClass[] bytecodeParameterTypes;
    
    public NewMetaMethod(final CachedMethod method) {
        super(method);
        this.bytecodeParameterTypes = method.getParameterTypes();
        int size = this.bytecodeParameterTypes.length;
        CachedClass[] logicalParameterTypes;
        if (size <= 1) {
            logicalParameterTypes = NewMetaMethod.EMPTY_TYPE_ARRAY;
        }
        else {
            logicalParameterTypes = new CachedClass[--size];
            System.arraycopy(this.bytecodeParameterTypes, 1, logicalParameterTypes, 0, size);
        }
        this.setParametersTypes(logicalParameterTypes);
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.getBytecodeParameterTypes()[0];
    }
    
    public CachedClass[] getBytecodeParameterTypes() {
        return this.bytecodeParameterTypes;
    }
    
    public CachedClass getOwnerClass() {
        return this.getBytecodeParameterTypes()[0];
    }
    
    static {
        EMPTY_TYPE_ARRAY = new CachedClass[0];
    }
}
