// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.asm;

class Edge
{
    static final int NORMAL = 0;
    static final int EXCEPTION = Integer.MAX_VALUE;
    int info;
    Label successor;
    Edge next;
}
