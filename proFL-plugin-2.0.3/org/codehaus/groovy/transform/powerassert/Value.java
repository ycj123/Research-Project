// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

public class Value
{
    private final Object value;
    private final int column;
    
    public Value(final Object value, final int column) {
        this.value = value;
        this.column = column;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public int getColumn() {
        return this.column;
    }
}
