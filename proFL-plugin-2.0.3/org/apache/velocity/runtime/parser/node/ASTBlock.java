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

public class ASTBlock extends SimpleNode
{
    public ASTBlock(final int id) {
        super(id);
    }
    
    public ASTBlock(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ResourceNotFoundException, ParseErrorException {
        for (int k = this.jjtGetNumChildren(), i = 0; i < k; ++i) {
            this.jjtGetChild(i).render(context, writer);
        }
        return true;
    }
}
