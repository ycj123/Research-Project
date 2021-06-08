// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTComment extends SimpleNode
{
    private static final char[] ZILCH;
    private char[] carr;
    
    public ASTComment(final int id) {
        super(id);
    }
    
    public ASTComment(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) {
        final Token t = this.getFirstToken();
        final int loc1 = t.image.indexOf("##");
        final int loc2 = t.image.indexOf("#*");
        if (loc1 == -1 && loc2 == -1) {
            this.carr = ASTComment.ZILCH;
        }
        else {
            this.carr = t.image.substring(0, (loc1 == -1) ? loc2 : loc1).toCharArray();
        }
        return data;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
        if (context.getAllowRendering()) {
            writer.write(this.carr);
        }
        return true;
    }
    
    static {
        ZILCH = "".toCharArray();
    }
}
