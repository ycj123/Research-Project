// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.collections.impl;

import java.util.NoSuchElementException;
import org.pitest.reloc.antlr.common.collections.AST;
import org.pitest.reloc.antlr.common.collections.ASTEnumeration;

public class ASTEnumerator implements ASTEnumeration
{
    VectorEnumerator nodes;
    int i;
    
    public ASTEnumerator(final Vector vector) {
        this.i = 0;
        this.nodes = new VectorEnumerator(vector);
    }
    
    public boolean hasMoreNodes() {
        synchronized (this.nodes) {
            return this.i <= this.nodes.vector.lastElement;
        }
    }
    
    public AST nextNode() {
        synchronized (this.nodes) {
            if (this.i <= this.nodes.vector.lastElement) {
                return (AST)this.nodes.vector.data[this.i++];
            }
            throw new NoSuchElementException("ASTEnumerator");
        }
    }
}
