// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.GString;

public class GStringImpl extends GString
{
    private String[] strings;
    
    public GStringImpl(final Object[] values, final String[] strings) {
        super(values);
        this.strings = strings;
    }
    
    @Override
    public String[] getStrings() {
        return this.strings;
    }
}
