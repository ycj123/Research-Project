// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.wrappers;

public class BooleanWrapper extends PojoWrapper
{
    public BooleanWrapper(final boolean wrapped) {
        super(wrapped ? Boolean.TRUE : Boolean.FALSE, Boolean.TYPE);
    }
}
