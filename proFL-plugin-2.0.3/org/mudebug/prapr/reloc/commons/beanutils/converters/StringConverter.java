// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class StringConverter implements Converter
{
    public Object convert(final Class type, final Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
