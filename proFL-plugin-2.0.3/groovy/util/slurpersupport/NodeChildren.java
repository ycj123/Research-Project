// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.io.IOException;
import java.io.Writer;
import groovy.lang.Buildable;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import groovy.lang.GroovyRuntimeException;
import java.util.Iterator;
import java.util.Map;

class NodeChildren extends GPathResult
{
    private int size;
    
    public NodeChildren(final GPathResult parent, final String name, final String namespacePrefix, final Map<String, String> namespaceTagHints) {
        super(parent, name, namespacePrefix, namespaceTagHints);
        this.size = -1;
    }
    
    public NodeChildren(final GPathResult parent, final String name, final Map<String, String> namespaceTagHints) {
        this(parent, name, "*", namespaceTagHints);
    }
    
    public NodeChildren(final GPathResult parent, final Map<String, String> namespaceTagHints) {
        this(parent, "*", namespaceTagHints);
    }
    
    @Override
    public Iterator childNodes() {
        return new Iterator() {
            private final Iterator iter = NodeChildren.this.parent.childNodes();
            private Iterator childIter = this.nextChildIter();
            
            public boolean hasNext() {
                return this.childIter != null;
            }
            
            public Object next() {
                while (this.childIter != null) {
                    try {
                        if (this.childIter.hasNext()) {
                            return this.childIter.next();
                        }
                        continue;
                    }
                    finally {
                        if (!this.childIter.hasNext()) {
                            this.childIter = this.nextChildIter();
                        }
                    }
                }
                return null;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            private Iterator nextChildIter() {
                while (this.iter.hasNext()) {
                    final Node node = this.iter.next();
                    if (NodeChildren.this.name.equals(node.name()) || NodeChildren.this.name.equals("*")) {
                        final Iterator result = node.childNodes();
                        if (result.hasNext() && ("*".equals(NodeChildren.this.namespacePrefix) || ("".equals(NodeChildren.this.namespacePrefix) && "".equals(node.namespaceURI())) || node.namespaceURI().equals(NodeChildren.this.namespaceMap.get(NodeChildren.this.namespacePrefix)))) {
                            return result;
                        }
                        continue;
                    }
                }
                return null;
            }
        };
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() {
            final Iterator iter = NodeChildren.this.nodeIterator();
            
            public boolean hasNext() {
                return this.iter.hasNext();
            }
            
            public Object next() {
                return new NodeChild(this.iter.next(), NodeChildren.this.parent, NodeChildren.this.namespaceTagHints);
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public Iterator nodeIterator() {
        if ("*".equals(this.name)) {
            return this.parent.childNodes();
        }
        return new NodeIterator(this.parent.childNodes()) {
            @Override
            protected Object getNextNode(final Iterator iter) {
                while (iter.hasNext()) {
                    final Node node = iter.next();
                    if (NodeChildren.this.name.equals(node.name()) && ("*".equals(NodeChildren.this.namespacePrefix) || ("".equals(NodeChildren.this.namespacePrefix) && "".equals(node.namespaceURI())) || node.namespaceURI().equals(NodeChildren.this.namespaceMap.get(NodeChildren.this.namespacePrefix)))) {
                        return node;
                    }
                }
                return null;
            }
        };
    }
    
    @Override
    public GPathResult parents() {
        throw new GroovyRuntimeException("parents() not implemented yet");
    }
    
    @Override
    public synchronized int size() {
        if (this.size == -1) {
            final Iterator iter = this.iterator();
            this.size = 0;
            while (iter.hasNext()) {
                iter.next();
                ++this.size;
            }
        }
        return this.size;
    }
    
    @Override
    public String text() {
        final StringBuffer buf = new StringBuffer();
        final Iterator iter = this.nodeIterator();
        while (iter.hasNext()) {
            buf.append(iter.next().text());
        }
        return buf.toString();
    }
    
    @Override
    public GPathResult find(final Closure closure) {
        for (final Object node : this) {
            if (DefaultTypeTransformation.castToBoolean(closure.call(new Object[] { node }))) {
                return (GPathResult)node;
            }
        }
        return new NoChildren(this, this.name, this.namespaceTagHints);
    }
    
    @Override
    public GPathResult findAll(final Closure closure) {
        return new FilteredNodeChildren(this, closure, this.namespaceTagHints);
    }
    
    public void build(final GroovyObject builder) {
        final Iterator iter = this.nodeIterator();
        while (iter.hasNext()) {
            final Object next = iter.next();
            if (next instanceof Buildable) {
                ((Buildable)next).build(builder);
            }
            else {
                ((Node)next).build(builder, this.namespaceMap, this.namespaceTagHints);
            }
        }
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        final Iterator iter = this.nodeIterator();
        while (iter.hasNext()) {
            iter.next().writeTo(out);
        }
        return out;
    }
    
    @Override
    protected void replaceNode(final Closure newValue) {
        for (final NodeChild result : this) {
            result.replaceNode(newValue);
        }
    }
    
    @Override
    protected void replaceBody(final Object newValue) {
        for (final NodeChild result : this) {
            result.replaceBody(newValue);
        }
    }
    
    @Override
    protected void appendNode(final Object newValue) {
        for (final NodeChild result : this) {
            result.appendNode(newValue);
        }
    }
}
