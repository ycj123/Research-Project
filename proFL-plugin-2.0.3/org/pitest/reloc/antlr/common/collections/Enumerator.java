// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.collections;

public interface Enumerator
{
    Object cursor();
    
    Object next();
    
    boolean valid();
}
