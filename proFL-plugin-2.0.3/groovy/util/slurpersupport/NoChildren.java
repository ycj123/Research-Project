// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import groovy.lang.GroovyObject;
import java.io.IOException;
import java.io.Writer;
import groovy.lang.Closure;
import java.util.Iterator;
import groovy.lang.GroovyRuntimeException;
import java.util.Map;

public class NoChildren extends GPathResult
{
    public NoChildren(final GPathResult parent, final String name, final Map<String, String> namespaceTagHints) {
        super(parent, name, "*", namespaceTagHints);
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public String text() {
        return "";
    }
    
    @Override
    public GPathResult parents() {
        throw new GroovyRuntimeException("parents() not implemented yet");
    }
    
    @Override
    public Iterator childNodes() {
        return this.iterator();
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() {
            public boolean hasNext() {
                return false;
            }
            
            public Object next() {
                return null;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public GPathResult find(final Closure closure) {
        return this;
    }
    
    @Override
    public GPathResult findAll(final Closure closure) {
        return this;
    }
    
    @Override
    public Iterator nodeIterator() {
        return this.iterator();
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        return out;
    }
    
    public void build(final GroovyObject builder) {
    }
    
    @Override
    protected void replaceNode(final Closure newValue) {
    }
    
    @Override
    protected void replaceBody(final Object newValue) {
    }
    
    @Override
    protected void appendNode(final Object newValue) {
    }
    
    public boolean asBoolean() {
        return false;
    }
}
