// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTElseIfStatement extends SimpleNode
{
    public ASTElseIfStatement(final int id) {
        super(id);
    }
    
    public ASTElseIfStatement(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        return this.jjtGetChild(0).evaluate(context);
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ResourceNotFoundException, ParseErrorException {
        return this.jjtGetChild(1).render(context, writer);
    }
}
