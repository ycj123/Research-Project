// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import groovyjarjarantlr.collections.AST;

public class ASTRuntimeException extends RuntimeException
{
    private final AST ast;
    
    public ASTRuntimeException(final AST ast, final String message) {
        super(message + description(ast));
        this.ast = ast;
    }
    
    public ASTRuntimeException(final AST ast, final String message, final Throwable throwable) {
        super(message + description(ast), throwable);
        this.ast = null;
    }
    
    protected static String description(final AST node) {
        return (node != null) ? (" at line: " + node.getLine() + " column: " + node.getColumn()) : "";
    }
    
    public AST getAst() {
        return this.ast;
    }
    
    public int getLine() {
        return (this.ast != null) ? this.ast.getLine() : -1;
    }
    
    public int getColumn() {
        return (this.ast != null) ? this.ast.getColumn() : -1;
    }
}
