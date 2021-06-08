// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Map;
import java.util.ArrayList;

public class NodeBuilder extends BuilderSupport
{
    public static NodeBuilder newInstance() {
        return new NodeBuilder();
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
    }
    
    @Override
    protected Object createNode(final Object name) {
        return new Node(this.getCurrentNode(), name, new ArrayList());
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        return new Node(this.getCurrentNode(), name, value);
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        return new Node(this.getCurrentNode(), name, attributes, new ArrayList());
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        return new Node(this.getCurrentNode(), name, attributes, value);
    }
    
    protected Node getCurrentNode() {
        return (Node)this.getCurrent();
    }
}
