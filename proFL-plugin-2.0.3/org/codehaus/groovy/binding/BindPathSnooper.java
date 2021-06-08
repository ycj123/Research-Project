// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.util.LinkedHashMap;
import java.util.Map;
import groovy.lang.GroovyObjectSupport;

class BindPathSnooper extends GroovyObjectSupport
{
    static final DeadEndObject DEAD_END;
    Map<String, BindPathSnooper> fields;
    
    BindPathSnooper() {
        this.fields = new LinkedHashMap<String, BindPathSnooper>();
    }
    
    @Override
    public Object getProperty(final String property) {
        if (this.fields.containsKey(property)) {
            return this.fields.get(property);
        }
        final BindPathSnooper snooper = new BindPathSnooper();
        this.fields.put(property, snooper);
        return snooper;
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        return BindPathSnooper.DEAD_END;
    }
    
    static {
        DEAD_END = new DeadEndObject();
    }
}
