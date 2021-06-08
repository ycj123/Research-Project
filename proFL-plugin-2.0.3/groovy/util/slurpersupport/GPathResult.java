// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import groovy.lang.Range;
import groovy.lang.IntRange;
import java.util.List;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URL;
import java.math.BigInteger;
import java.math.BigDecimal;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.GroovyObject;
import java.util.Iterator;
import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;
import java.util.HashMap;
import java.util.Map;
import groovy.lang.Buildable;
import groovy.lang.Writable;
import groovy.lang.GroovyObjectSupport;

public abstract class GPathResult extends GroovyObjectSupport implements Writable, Buildable
{
    protected final GPathResult parent;
    protected final String name;
    protected final String namespacePrefix;
    protected final Map namespaceMap;
    protected final Map<String, String> namespaceTagHints;
    
    public GPathResult(final GPathResult parent, final String name, final String namespacePrefix, final Map<String, String> namespaceTagHints) {
        this.namespaceMap = new HashMap();
        if (parent == null) {
            this.parent = this;
            this.namespaceMap.put("xml", "http://www.w3.org/XML/1998/namespace");
        }
        else {
            this.parent = parent;
            this.namespaceMap.putAll(parent.namespaceMap);
        }
        this.name = name;
        this.namespacePrefix = namespacePrefix;
        this.namespaceTagHints = namespaceTagHints;
        this.setMetaClass(this.getMetaClass());
    }
    
    @Override
    public void setMetaClass(final MetaClass metaClass) {
        final MetaClass newMetaClass = new DelegatingMetaClass(metaClass) {
            @Override
            public Object getAttribute(final Object object, final String attribute) {
                return GPathResult.this.getProperty("@" + attribute);
            }
            
            @Override
            public void setAttribute(final Object object, final String attribute, final Object newValue) {
                GPathResult.this.setProperty("@" + attribute, newValue);
            }
        };
        super.setMetaClass(newMetaClass);
    }
    
    @Override
    public Object getProperty(final String property) {
        if ("..".equals(property)) {
            return this.parent();
        }
        if ("*".equals(property)) {
            return this.children();
        }
        if ("**".equals(property)) {
            return this.depthFirst();
        }
        if (property.startsWith("@")) {
            if (property.indexOf(":") != -1) {
                final int i = property.indexOf(":");
                return new Attributes(this, "@" + property.substring(i + 1), property.substring(1, i), this.namespaceTagHints);
            }
            return new Attributes(this, property, this.namespaceTagHints);
        }
        else {
            if (property.indexOf(":") != -1) {
                final int i = property.indexOf(":");
                return new NodeChildren(this, property.substring(i + 1), property.substring(0, i), this.namespaceTagHints);
            }
            return new NodeChildren(this, property, this.namespaceTagHints);
        }
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        if (property.startsWith("@")) {
            if (newValue instanceof String || newValue instanceof GString) {
                for (final NodeChild child : this) {
                    child.attributes().put(property.substring(1), newValue);
                }
            }
        }
        else {
            final GPathResult result = new NodeChildren(this, property, this.namespaceTagHints);
            if (newValue instanceof Map) {
                for (final Object o : ((Map)newValue).entrySet()) {
                    final Map.Entry entry = (Map.Entry)o;
                    result.setProperty("@" + entry.getKey(), entry.getValue());
                }
            }
            else if (newValue instanceof Closure) {
                result.replaceNode((Closure)newValue);
            }
            else {
                result.replaceBody(newValue);
            }
        }
    }
    
    public Object leftShift(final Object newValue) {
        this.appendNode(newValue);
        return this;
    }
    
    public Object plus(final Object newValue) {
        this.replaceNode(new Closure(this) {
            public void doCall(final Object[] args) {
                final GroovyObject delegate = (GroovyObject)this.getDelegate();
                delegate.getProperty("mkp");
                delegate.invokeMethod("yield", args);
                delegate.getProperty("mkp");
                delegate.invokeMethod("yield", new Object[] { newValue });
            }
        });
        return this;
    }
    
    protected abstract void replaceNode(final Closure p0);
    
    protected abstract void replaceBody(final Object p0);
    
    protected abstract void appendNode(final Object p0);
    
    public String name() {
        return this.name;
    }
    
    public GPathResult parent() {
        return this.parent;
    }
    
    public GPathResult children() {
        return new NodeChildren(this, this.namespaceTagHints);
    }
    
    public String lookupNamespace(final String prefix) {
        return this.namespaceTagHints.get(prefix);
    }
    
    @Override
    public String toString() {
        return this.text();
    }
    
    public Integer toInteger() {
        return DefaultGroovyMethods.toInteger(this.text());
    }
    
    public Long toLong() {
        return DefaultGroovyMethods.toLong(this.text());
    }
    
    public Float toFloat() {
        return DefaultGroovyMethods.toFloat(this.text());
    }
    
    public Double toDouble() {
        return DefaultGroovyMethods.toDouble(this.text());
    }
    
    public BigDecimal toBigDecimal() {
        return DefaultGroovyMethods.toBigDecimal(this.text());
    }
    
    public BigInteger toBigInteger() {
        return DefaultGroovyMethods.toBigInteger(this.text());
    }
    
    public URL toURL() throws MalformedURLException {
        return DefaultGroovyMethods.toURL(this.text());
    }
    
    public URI toURI() throws URISyntaxException {
        return DefaultGroovyMethods.toURI(this.text());
    }
    
    public Boolean toBoolean() {
        return DefaultGroovyMethods.toBoolean(this.text());
    }
    
    public GPathResult declareNamespace(final Map newNamespaceMapping) {
        this.namespaceMap.putAll(newNamespaceMapping);
        return this;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.text().equals(obj.toString());
    }
    
    public Object getAt(final int index) {
        if (index < 0) {
            final List list = this.list();
            final int adjustedIndex = index + list.size();
            if (adjustedIndex >= 0 && adjustedIndex < list.size()) {
                return list.get(adjustedIndex);
            }
        }
        else {
            final Iterator iter = this.iterator();
            int count = 0;
            while (iter.hasNext()) {
                if (count++ == index) {
                    return iter.next();
                }
                iter.next();
            }
        }
        return new NoChildren(this, this.name, this.namespaceTagHints);
    }
    
    public Object getAt(final IntRange range) {
        return DefaultGroovyMethods.getAt((List<Object>)this.list(), range);
    }
    
    public void putAt(final int index, final Object newValue) {
        final GPathResult result = (GPathResult)this.getAt(index);
        if (newValue instanceof Closure) {
            result.replaceNode((Closure)newValue);
        }
        else {
            result.replaceBody(newValue);
        }
    }
    
    public Iterator depthFirst() {
        return new Iterator() {
            private final List list = new LinkedList();
            private final Stack stack = new Stack();
            private Iterator iter = GPathResult.this.iterator();
            private GPathResult next = this.getNextByDepth();
            
            public boolean hasNext() {
                return this.next != null;
            }
            
            public Object next() {
                try {
                    return this.next;
                }
                finally {
                    this.next = this.getNextByDepth();
                }
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            private GPathResult getNextByDepth() {
                while (this.iter.hasNext()) {
                    final GPathResult node = this.iter.next();
                    this.list.add(node);
                    this.stack.push(this.iter);
                    this.iter = node.children().iterator();
                }
                if (this.list.isEmpty()) {
                    return null;
                }
                final GPathResult result = this.list.get(0);
                this.list.remove(0);
                this.iter = this.stack.pop();
                return result;
            }
        };
    }
    
    public Iterator breadthFirst() {
        return new Iterator() {
            private final List list = new LinkedList();
            private Iterator iter = GPathResult.this.iterator();
            private GPathResult next = this.getNextByBreadth();
            
            public boolean hasNext() {
                return this.next != null;
            }
            
            public Object next() {
                try {
                    return this.next;
                }
                finally {
                    this.next = this.getNextByBreadth();
                }
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            private GPathResult getNextByBreadth() {
                List children = new ArrayList();
                while (this.iter.hasNext() || !children.isEmpty()) {
                    if (this.iter.hasNext()) {
                        final GPathResult node = this.iter.next();
                        this.list.add(node);
                        this.list.add(this.iter);
                        children.add(node.children());
                    }
                    else {
                        final List nextLevel = new ArrayList();
                        for (final Object child : children) {
                            final GPathResult next = (GPathResult)child;
                            final Iterator iterator = next.iterator();
                            while (iterator.hasNext()) {
                                nextLevel.add(iterator.next());
                            }
                        }
                        this.iter = nextLevel.iterator();
                        children = new ArrayList();
                    }
                }
                if (this.list.isEmpty()) {
                    return null;
                }
                final GPathResult result = this.list.get(0);
                this.list.remove(0);
                this.iter = this.list.get(0);
                this.list.remove(0);
                return result;
            }
        };
    }
    
    public List list() {
        final Iterator iter = this.nodeIterator();
        final List result = new LinkedList();
        while (iter.hasNext()) {
            result.add(new NodeChild(iter.next(), this.parent, this.namespacePrefix, this.namespaceTagHints));
        }
        return result;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public Closure getBody() {
        return new Closure(this.parent(), this) {
            public void doCall(final Object[] args) {
                final GroovyObject delegate = (GroovyObject)this.getDelegate();
                final GPathResult thisObject = (GPathResult)this.getThisObject();
                final Node node = (Node)thisObject.getAt(0);
                final List children = node.children();
                for (final Object child : children) {
                    delegate.getProperty("mkp");
                    if (child instanceof Node) {
                        delegate.invokeMethod("yield", new Object[] { new NodeChild((Node)child, thisObject, "*", null) });
                    }
                    else {
                        delegate.invokeMethod("yield", new Object[] { child });
                    }
                }
            }
        };
    }
    
    public abstract int size();
    
    public abstract String text();
    
    public abstract GPathResult parents();
    
    public abstract Iterator childNodes();
    
    public abstract Iterator iterator();
    
    public abstract GPathResult find(final Closure p0);
    
    public abstract GPathResult findAll(final Closure p0);
    
    public abstract Iterator nodeIterator();
}
