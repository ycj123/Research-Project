// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.util.Iterator;

public abstract class NodeIterator implements Iterator
{
    private static final Object DELAYED_INIT;
    private final Iterator iter;
    private Object nextNode;
    
    public NodeIterator(final Iterator iter) {
        this.iter = iter;
        this.nextNode = NodeIterator.DELAYED_INIT;
    }
    
    private void initNextNode() {
        if (this.nextNode == NodeIterator.DELAYED_INIT) {
            this.nextNode = this.getNextNode(this.iter);
        }
    }
    
    public boolean hasNext() {
        this.initNextNode();
        return this.nextNode != null;
    }
    
    public Object next() {
        this.initNextNode();
        try {
            return this.nextNode;
        }
        finally {
            this.nextNode = this.getNextNode(this.iter);
        }
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    protected abstract Object getNextNode(final Iterator p0);
    
    static {
        DELAYED_INIT = new Object();
    }
}
