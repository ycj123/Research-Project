// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import groovy.lang.GroovyObject;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Iterator;
import java.util.Map;
import groovy.lang.Closure;

public class FilteredNodeChildren extends NodeChildren
{
    private final Closure closure;
    
    public FilteredNodeChildren(final GPathResult parent, final Closure closure, final Map<String, String> namespaceTagHints) {
        super(parent, parent.name, namespaceTagHints);
        this.closure = closure;
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() {
            final Iterator iter = FilteredNodeChildren.this.parent.iterator();
            Object next = null;
            
            public boolean hasNext() {
                while (this.iter.hasNext()) {
                    final Object childNode = this.iter.next();
                    if (FilteredNodeChildren.this.closureYieldsTrueForNode(childNode)) {
                        this.next = childNode;
                        return true;
                    }
                }
                return false;
            }
            
            public Object next() {
                return this.next;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public Iterator nodeIterator() {
        return new NodeIterator(this.parent.nodeIterator()) {
            @Override
            protected Object getNextNode(final Iterator iter) {
                while (iter.hasNext()) {
                    final Object node = iter.next();
                    if (FilteredNodeChildren.this.closureYieldsTrueForNode(new NodeChild((Node)node, FilteredNodeChildren.this.parent, FilteredNodeChildren.this.namespaceTagHints))) {
                        return node;
                    }
                }
                return null;
            }
        };
    }
    
    private boolean closureYieldsTrueForNode(final Object childNode) {
        return DefaultTypeTransformation.castToBoolean(this.closure.call(new Object[] { childNode }));
    }
}
