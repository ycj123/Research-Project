// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.io.IOException;
import java.io.Writer;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import java.util.Iterator;
import groovy.lang.GroovyRuntimeException;
import java.util.Map;

public class NodeChild extends GPathResult
{
    private final Node node;
    
    public NodeChild(final Node node, final GPathResult parent, final String namespacePrefix, final Map<String, String> namespaceTagHints) {
        super(parent, node.name(), namespacePrefix, namespaceTagHints);
        this.node = node;
    }
    
    public NodeChild(final Node node, final GPathResult parent, final Map<String, String> namespaceTagHints) {
        this(node, parent, "*", namespaceTagHints);
    }
    
    @Override
    public int size() {
        return 1;
    }
    
    @Override
    public String text() {
        return this.node.text();
    }
    
    public String namespaceURI() {
        return this.node.namespaceURI();
    }
    
    @Override
    public GPathResult parents() {
        throw new GroovyRuntimeException("parents() not implemented yet");
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private boolean hasNext = true;
            
            public boolean hasNext() {
                return this.hasNext;
            }
            
            public Object next() {
                try {
                    return this.hasNext ? NodeChild.this : null;
                }
                finally {
                    this.hasNext = false;
                }
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public Iterator nodeIterator() {
        return new Iterator() {
            private boolean hasNext = true;
            
            public boolean hasNext() {
                return this.hasNext;
            }
            
            public Object next() {
                try {
                    return this.hasNext ? NodeChild.this.node : null;
                }
                finally {
                    this.hasNext = false;
                }
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public Object getAt(final int index) {
        if (index == 0) {
            return this.node;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }
    
    public Map attributes() {
        return this.node.attributes();
    }
    
    @Override
    public Iterator childNodes() {
        return this.node.childNodes();
    }
    
    @Override
    public GPathResult find(final Closure closure) {
        if (DefaultTypeTransformation.castToBoolean(closure.call(new Object[] { this.node }))) {
            return this;
        }
        return new NoChildren(this, "", this.namespaceTagHints);
    }
    
    @Override
    public GPathResult findAll(final Closure closure) {
        return this.find(closure);
    }
    
    public void build(final GroovyObject builder) {
        this.node.build(builder, this.namespaceMap, this.namespaceTagHints);
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        return this.node.writeTo(out);
    }
    
    @Override
    protected void replaceNode(final Closure newValue) {
        this.node.replaceNode(newValue, this);
    }
    
    @Override
    protected void replaceBody(final Object newValue) {
        this.node.replaceBody(newValue);
    }
    
    @Override
    protected void appendNode(final Object newValue) {
        this.node.appendNode(newValue, this);
    }
}
