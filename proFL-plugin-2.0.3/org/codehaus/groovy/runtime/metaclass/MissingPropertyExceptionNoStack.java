// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import groovy.lang.MissingPropertyException;

public class MissingPropertyExceptionNoStack extends MissingPropertyException
{
    public MissingPropertyExceptionNoStack(final String propertyName, final Class theClass) {
        super(propertyName, theClass);
    }
    
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
