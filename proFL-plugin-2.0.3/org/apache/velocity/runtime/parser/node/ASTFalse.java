// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTFalse extends SimpleNode
{
    private static Boolean value;
    
    public ASTFalse(final int id) {
        super(id);
    }
    
    public ASTFalse(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean evaluate(final InternalContextAdapter context) {
        return false;
    }
    
    public Object value(final InternalContextAdapter context) {
        return ASTFalse.value;
    }
    
    static {
        ASTFalse.value = Boolean.FALSE;
    }
}
