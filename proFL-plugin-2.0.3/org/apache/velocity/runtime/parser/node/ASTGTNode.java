// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.util.TemplateNumber;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTGTNode extends SimpleNode
{
    public ASTGTNode(final int id) {
        super(id);
    }
    
    public ASTGTNode(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        Object left = this.jjtGetChild(0).value(context);
        Object right = this.jjtGetChild(1).value(context);
        if (left == null || right == null) {
            this.log.error(((left == null) ? "Left" : "Right") + " side (" + this.jjtGetChild((left != null) ? 1 : 0).literal() + ") of '>' operation has null value." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return false;
        }
        if (left instanceof TemplateNumber) {
            left = ((TemplateNumber)left).getAsNumber();
        }
        if (right instanceof TemplateNumber) {
            right = ((TemplateNumber)right).getAsNumber();
        }
        if (!(left instanceof Number) || !(right instanceof Number)) {
            this.log.error(((left instanceof Number) ? "Right" : "Left") + " side of '>=' operation is not a Numbere. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return false;
        }
        return MathUtils.compare((Number)left, (Number)right) == 1;
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        final boolean val = this.evaluate(context);
        return val ? Boolean.TRUE : Boolean.FALSE;
    }
}
