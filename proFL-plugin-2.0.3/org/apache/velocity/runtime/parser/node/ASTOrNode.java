// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTOrNode extends SimpleNode
{
    public ASTOrNode(final int id) {
        super(id);
    }
    
    public ASTOrNode(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        return new Boolean(this.evaluate(context));
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        final Node left = this.jjtGetChild(0);
        final Node right = this.jjtGetChild(1);
        return (left != null && left.evaluate(context)) || (right != null && right.evaluate(context));
    }
}
