// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarantlr.collections.impl;

import groovyjarjarantlr.collections.AST;

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
