// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTAndNode extends SimpleNode
{
    public ASTAndNode(final int id) {
        super(id);
    }
    
    public ASTAndNode(final Parser p, final int id) {
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
        if (left == null || right == null) {
            this.log.error(((left == null) ? "Left" : "Right") + " side of '&&' operation is null." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return false;
        }
        return left.evaluate(context) && right.evaluate(context);
    }
}
