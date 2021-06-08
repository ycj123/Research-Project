// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.TemplateInitException;
import java.math.BigDecimal;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTFloatingPointLiteral extends SimpleNode
{
    private Number value;
    
    public ASTFloatingPointLiteral(final int id) {
        super(id);
        this.value = null;
    }
    
    public ASTFloatingPointLiteral(final Parser p, final int id) {
        super(p, id);
        this.value = null;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        final String str = this.getFirstToken().image;
        try {
            this.value = new Double(str);
        }
        catch (NumberFormatException E1) {
            this.value = new BigDecimal(str);
        }
        return data;
    }
    
    public Object value(final InternalContextAdapter context) {
        return this.value;
    }
}
