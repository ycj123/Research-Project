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

public class ASTObjectArray extends SimpleNode
{
    public ASTObjectArray(final int id) {
        super(id);
    }
    
    public ASTObjectArray(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        final int size = this.jjtGetNumChildren();
        final List objectArray = new ArrayList();
        for (int i = 0; i < size; ++i) {
            objectArray.add(this.jjtGetChild(i).value(context));
        }
        return objectArray;
    }
}
