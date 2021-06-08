// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

public class GroovyClass
{
    public static final GroovyClass[] EMPTY_ARRAY;
    private String name;
    private byte[] bytes;
    
    public GroovyClass(final String name, final byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }
    
    public String getName() {
        return this.name;
    }
    
    public byte[] getBytes() {
        return this.bytes;
    }
    
    static {
        EMPTY_ARRAY = new GroovyClass[0];
    }
}
