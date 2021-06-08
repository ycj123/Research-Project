// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.util.TemplateNumber;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTAddNode extends SimpleNode
{
    public ASTAddNode(final int id) {
        super(id);
    }
    
    public ASTAddNode(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        Object left = this.jjtGetChild(0).value(context);
        Object right = this.jjtGetChild(1).value(context);
        if (left == null || right == null) {
            this.log.error(((left == null) ? "Left" : "Right") + " side (" + this.jjtGetChild((left != null) ? 1 : 0).literal() + ") of addition operation has null value." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return null;
        }
        if (left instanceof TemplateNumber) {
            left = ((TemplateNumber)left).getAsNumber();
        }
        if (right instanceof TemplateNumber) {
            right = ((TemplateNumber)right).getAsNumber();
        }
        if (left instanceof Number && right instanceof Number) {
            return MathUtils.add((Number)left, (Number)right);
        }
        if (left instanceof String || right instanceof String) {
            return left.toString().concat(right.toString());
        }
        this.log.error(((!(left instanceof Number) && !(left instanceof String)) ? "Left" : "Right") + " side of addition operation is not a valid type. " + "Currently only Strings, numbers (1,2,3...) and Number type are supported. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
        return null;
    }
}
