// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTIfStatement extends SimpleNode
{
    public ASTIfStatement(final int id) {
        super(id);
    }
    
    public ASTIfStatement(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ResourceNotFoundException, ParseErrorException {
        if (this.jjtGetChild(0).evaluate(context)) {
            this.jjtGetChild(1).render(context, writer);
            return true;
        }
        for (int totalNodes = this.jjtGetNumChildren(), i = 2; i < totalNodes; ++i) {
            if (this.jjtGetChild(i).evaluate(context)) {
                this.jjtGetChild(i).render(context, writer);
                return true;
            }
        }
        return true;
    }
    
    public void process(final InternalContextAdapter context, final ParserVisitor visitor) {
    }
}
