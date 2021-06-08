// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import java.util.List;
import java.util.ArrayList;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTIntegerRange extends SimpleNode
{
    public ASTIntegerRange(final int id) {
        super(id);
    }
    
    public ASTIntegerRange(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        final Object left = this.jjtGetChild(0).value(context);
        final Object right = this.jjtGetChild(1).value(context);
        if (left == null || right == null) {
            this.log.error(((left == null) ? "Left" : "Right") + " side of range operator [n..m] has null value." + " Operation not possible. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return null;
        }
        if (!(left instanceof Integer) || !(right instanceof Integer)) {
            this.log.error(((left instanceof Integer) ? "Right" : "Left") + " side of range operator is not a valid type. " + "Currently only integers (1,2,3...) and Integer type is supported. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
            return null;
        }
        final int l = (int)left;
        final int r = (int)right;
        int nbrElements = Math.abs(l - r);
        ++nbrElements;
        final int delta = (l >= r) ? -1 : 1;
        final List elements = new ArrayList(nbrElements);
        int value = l;
        for (int i = 0; i < nbrElements; ++i) {
            elements.add(new Integer(value));
            value += delta;
        }
        return elements;
    }
}
