// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTText extends SimpleNode
{
    private char[] ctext;
    
    public ASTText(final int id) {
        super(id);
    }
    
    public ASTText(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        final Token t = this.getFirstToken();
        final String text = NodeUtils.tokenLiteral(t);
        this.ctext = text.toCharArray();
        return data;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException {
        if (context.getAllowRendering()) {
            writer.write(this.ctext);
        }
        return true;
    }
}
