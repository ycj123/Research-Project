// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import groovy.lang.GroovyObject;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import java.util.Iterator;
import groovy.lang.GroovyRuntimeException;
import java.util.Map;

public class Attribute extends GPathResult
{
    private final String value;
    
    public Attribute(final String name, final String value, final GPathResult parent, final String namespacePrefix, final Map<String, String> namespaceTagHints) {
        super(parent, name, namespacePrefix, namespaceTagHints);
        this.value = value;
    }
    
    @Override
    public String name() {
        return this.name.substring(1);
    }
    
    @Override
    public int size() {
        return 1;
    }
    
    @Override
    public String text() {
        return this.value;
    }
    
    @Override
    public GPathResult parents() {
        throw new GroovyRuntimeException("parents() not implemented yet");
    }
    
    @Override
    public Iterator childNodes() {
        throw new GroovyRuntimeException("can't call childNodes() in the attribute " + this.name);
    }
    
    @Override
    public Iterator iterator() {
        return this.nodeIterator();
    }
    
    @Override
    public GPathResult find(final Closure closure) {
        if (DefaultTypeTransformation.castToBoolean(closure.call(new Object[] { this }))) {
            return this;
        }
        return new NoChildren(this, "", this.namespaceTagHints);
    }
    
    @Override
    public GPathResult findAll(final Closure closure) {
        return this.find(closure);
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
                    return this.hasNext ? Attribute.this : null;
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
    
    public Writer writeTo(final Writer out) throws IOException {
        out.write(this.value);
        return out;
    }
    
    public void build(final GroovyObject builder) {
        builder.getProperty("mkp");
        builder.invokeMethod("yield", new Object[] { this.value });
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
}
