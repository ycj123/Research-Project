// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTStop extends SimpleNode
{
    public ASTStop(final int id) {
        super(id);
    }
    
    public ASTStop(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
        context.setAllowRendering(false);
        return true;
    }
}
