// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import groovy.lang.GroovyObject;
import java.io.IOException;
import java.io.Writer;
import groovy.lang.Closure;
import java.util.ArrayList;
import java.util.List;
import groovy.lang.GroovyRuntimeException;
import java.util.Iterator;
import java.util.Map;

class Attributes extends NodeChildren
{
    final String attributeName;
    
    public Attributes(final GPathResult parent, final String name, final String namespacePrefix, final Map<String, String> namespaceTagHints) {
        super(parent, name, namespacePrefix, namespaceTagHints);
        this.attributeName = this.name.substring(1);
    }
    
    public Attributes(final GPathResult parent, final String name, final Map<String, String> namespaceTagHints) {
        this(parent, name, "*", namespaceTagHints);
    }
    
    @Override
    public String name() {
        return this.name.substring(1);
    }
    
    @Override
    public Iterator childNodes() {
        throw new GroovyRuntimeException("Can't get the child nodes on a GPath expression selecting attributes: ...." + this.parent.name() + "." + this.name() + ".childNodes()");
    }
    
    @Override
    public Iterator iterator() {
        return new NodeIterator(this.nodeIterator()) {
            @Override
            protected Object getNextNode(final Iterator iter) {
                while (iter.hasNext()) {
                    final Object next = iter.next();
                    if (next instanceof Attribute) {
                        return next;
                    }
                    final String value = ((Node)next).attributes().get(Attributes.this.attributeName);
                    if (value != null) {
                        return new Attribute(Attributes.this.name, value, new NodeChild((Node)next, Attributes.this.parent.parent, "", Attributes.this.namespaceTagHints), "", Attributes.this.namespaceTagHints);
                    }
                }
                return null;
            }
        };
    }
    
    @Override
    public Iterator nodeIterator() {
        return this.parent.nodeIterator();
    }
    
    @Override
    public GPathResult parents() {
        return super.parents();
    }
    
    @Override
    public String text() {
        final StringBuffer buf = new StringBuffer();
        final Iterator iter = this.iterator();
        while (iter.hasNext()) {
            buf.append(iter.next());
        }
        return buf.toString();
    }
    
    @Override
    public List list() {
        final Iterator iter = this.iterator();
        final List result = new ArrayList();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    
    @Override
    public GPathResult findAll(final Closure closure) {
        return new FilteredAttributes(this, closure, this.namespaceTagHints);
    }
    
    @Override
    public Writer writeTo(final Writer out) throws IOException {
        out.write(this.text());
        return out;
    }
    
    @Override
    public void build(final GroovyObject builder) {
        builder.getProperty("mkp");
        builder.invokeMethod("yield", new Object[] { this.text() });
    }
}
