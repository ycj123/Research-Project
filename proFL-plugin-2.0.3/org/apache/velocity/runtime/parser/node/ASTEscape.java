// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTEscape extends SimpleNode
{
    public String val;
    private char[] ctext;
    
    public ASTEscape(final int id) {
        super(id);
    }
    
    public ASTEscape(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) {
        this.ctext = this.val.toCharArray();
        return data;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException {
        if (context.getAllowRendering()) {
            writer.write(this.ctext);
        }
        return true;
    }
}
