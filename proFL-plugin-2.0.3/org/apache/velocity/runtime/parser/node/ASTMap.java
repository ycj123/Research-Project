// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import java.util.Map;
import java.util.HashMap;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;

public class ASTMap extends SimpleNode
{
    public ASTMap(final int id) {
        super(id);
    }
    
    public ASTMap(final Parser p, final int id) {
        super(p, id);
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        final int size = this.jjtGetNumChildren();
        final Map objectMap = new HashMap();
        for (int i = 0; i < size; i += 2) {
            final SimpleNode keyNode = (SimpleNode)this.jjtGetChild(i);
            final SimpleNode valueNode = (SimpleNode)this.jjtGetChild(i + 1);
            final Object key = (keyNode == null) ? null : keyNode.value(context);
            final Object value = (valueNode == null) ? null : valueNode.value(context);
            objectMap.put(key, value);
        }
        return objectMap;
    }
}
