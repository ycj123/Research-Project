// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.visitor;

import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.node.ASTReference;
import java.util.Map;

public class VMReferenceMungeVisitor extends BaseVisitor
{
    private Map argmap;
    
    public VMReferenceMungeVisitor(final Map map) {
        this.argmap = null;
        this.argmap = map;
    }
    
    public Object visit(final ASTReference node, Object data) {
        final String override = this.argmap.get(node.literal().substring(1));
        if (override != null) {
            node.setLiteral(override);
        }
        data = node.childrenAccept(this, data);
        return data;
    }
}
