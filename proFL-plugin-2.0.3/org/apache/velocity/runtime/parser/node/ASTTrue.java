// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTTrue extends SimpleNode
{
    private static Boolean value;
    
    public ASTTrue(final int id) {
        super(id);
    }
    
    public ASTTrue(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean evaluate(final InternalContextAdapter context) {
        return true;
    }
    
    public Object value(final InternalContextAdapter context) {
        return ASTTrue.value;
    }
    
    static {
        ASTTrue.value = Boolean.TRUE;
    }
}
