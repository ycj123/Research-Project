// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.runtime.MethodKey;

public class TemporaryMethodKey extends MethodKey
{
    private final Object[] parameterValues;
    
    public TemporaryMethodKey(final Class sender, final String name, Object[] parameterValues, final boolean isCallToSuper) {
        super(sender, name, isCallToSuper);
        if (parameterValues == null) {
            parameterValues = MetaClassHelper.EMPTY_ARRAY;
        }
        this.parameterValues = parameterValues;
    }
    
    @Override
    public int getParameterCount() {
        return this.parameterValues.length;
    }
    
    @Override
    public Class getParameterType(final int index) {
        final Object value = this.parameterValues[index];
        if (value != null) {
            final Class type = (Class)((value.getClass() == Class.class) ? value : value.getClass());
            return type;
        }
        return Object.class;
    }
}
