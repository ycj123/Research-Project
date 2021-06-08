// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.collections.impl;

import org.pitest.reloc.antlr.common.collections.AST;

public class ASTArray
{
    public int size;
    public AST[] array;
    
    public ASTArray(final int n) {
        this.size = 0;
        this.array = new AST[n];
    }
    
    public ASTArray add(final AST ast) {
        this.array[this.size++] = ast;
        return this;
    }
}
