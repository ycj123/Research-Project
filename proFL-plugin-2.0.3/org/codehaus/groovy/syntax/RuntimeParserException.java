// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import org.codehaus.groovy.ast.ASTNode;
import groovy.lang.GroovyRuntimeException;

public class RuntimeParserException extends GroovyRuntimeException
{
    public RuntimeParserException(final String message, final ASTNode node) {
        super(message + "\n", node);
    }
    
    public void throwParserException() throws SyntaxException {
        throw new SyntaxException(this.getMessage(), this.getNode().getLineNumber(), this.getNode().getColumnNumber());
    }
}
