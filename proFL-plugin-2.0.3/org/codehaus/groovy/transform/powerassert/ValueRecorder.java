// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import java.util.ArrayList;
import java.util.List;

public class ValueRecorder
{
    public static final String RECORD_METHOD_NAME = "record";
    public static final String CLEAR_METHOD_NAME = "clear";
    private final List<Value> values;
    
    public ValueRecorder() {
        this.values = new ArrayList<Value>();
    }
    
    public void clear() {
        this.values.clear();
    }
    
    public Object record(final Object value, final int anchor) {
        this.values.add(new Value(value, anchor));
        return value;
    }
    
    public List<Value> getValues() {
        return this.values;
    }
}
