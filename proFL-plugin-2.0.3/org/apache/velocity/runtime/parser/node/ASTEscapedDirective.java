// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTEscapedDirective extends SimpleNode
{
    public ASTEscapedDirective(final int id) {
        super(id);
    }
    
    public ASTEscapedDirective(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException {
        if (context.getAllowRendering()) {
            writer.write(this.getFirstToken().image);
        }
        return true;
    }
}
