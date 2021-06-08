// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.RuntimeServices;

public class Literal extends Directive
{
    String literalText;
    
    public String getName() {
        return "literal";
    }
    
    public int getType() {
        return 1;
    }
    
    public void init(final RuntimeServices rs, final InternalContextAdapter context, final Node node) throws TemplateInitException {
        super.init(rs, context, node);
        this.literalText = node.jjtGetChild(0).literal();
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer, final Node node) throws IOException {
        writer.write(this.literalText);
        return true;
    }
}
