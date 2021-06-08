// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.util.TemplateNumber;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTEQNode extends SimpleNode
{
    public ASTEQNode(final int id) {
        super(id);
    }
    
    public ASTEQNode(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        Object left = this.jjtGetChild(0).value(context);
        Object right = this.jjtGetChild(1).value(context);
        if (left == null || right == null) {
            this.log.error(((left == null) ? "Left" : "Right") + " side (" + this.jjtGetChild((left != null) ? 1 : 0).literal() + ") of '==' operation " + "has null value. " + "If a reference, it may not be in the context." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return false;
        }
        if (left instanceof TemplateNumber) {
            left = ((TemplateNumber)left).getAsNumber();
        }
        if (right instanceof TemplateNumber) {
            right = ((TemplateNumber)right).getAsNumber();
        }
        if (left instanceof Number && right instanceof Number) {
            return MathUtils.compare((Number)left, (Number)right) == 0;
        }
        if (left.getClass().isAssignableFrom(right.getClass()) || right.getClass().isAssignableFrom(left.getClass())) {
            return left.equals(right);
        }
        if (left.toString() == null || right.toString() == null) {
            final boolean culprit = left.toString() == null;
            this.log.error((culprit ? "Left" : "Right") + " string side " + "String representation (" + this.jjtGetChild(culprit ? 0 : 1).literal() + ") of '!=' operation has null value." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return false;
        }
        return left.toString().equals(right.toString());
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        return this.evaluate(context) ? Boolean.TRUE : Boolean.FALSE;
    }
}
