// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.component.configurator.converters.basic;

public class BooleanConverter extends AbstractBasicConverter
{
    public boolean canConvert(final Class type) {
        return type.equals(Boolean.TYPE) || type.equals(Boolean.class);
    }
    
    public Object fromString(final String str) {
        return str.equals("true") ? Boolean.TRUE : Boolean.FALSE;
    }
}
