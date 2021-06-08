// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements Iterator
{
    private Enumeration enumeration;
    
    public EnumerationIterator(final Enumeration enumeration) {
        this.enumeration = null;
        this.enumeration = enumeration;
    }
    
    public Object next() {
        return this.enumeration.nextElement();
    }
    
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }
    
    public void remove() {
    }
}
