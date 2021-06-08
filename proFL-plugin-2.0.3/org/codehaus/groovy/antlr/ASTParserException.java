// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.syntax.ParserException;

public class ASTParserException extends ParserException
{
    private final AST ast;
    
    public ASTParserException(final ASTRuntimeException e) {
        super(e.getMessage(), e, e.getLine(), e.getColumn());
        this.ast = e.getAst();
    }
    
    public ASTParserException(final String message, final ASTRuntimeException e) {
        super(message, e, e.getLine(), e.getColumn());
        this.ast = e.getAst();
    }
    
    public AST getAst() {
        return this.ast;
    }
}
