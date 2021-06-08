// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.TemplateInitException;
import java.math.BigInteger;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTIntegerLiteral extends SimpleNode
{
    private Number value;
    
    public ASTIntegerLiteral(final int id) {
        super(id);
        this.value = null;
    }
    
    public ASTIntegerLiteral(final Parser p, final int id) {
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
            this.value = new Integer(str);
        }
        catch (NumberFormatException E1) {
            try {
                this.value = new Long(str);
            }
            catch (NumberFormatException E2) {
                this.value = new BigInteger(str);
            }
        }
        return data;
    }
    
    public Object value(final InternalContextAdapter context) {
        return this.value;
    }
}
