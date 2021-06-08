// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import groovy.lang.Buildable;
import java.util.HashMap;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import groovy.lang.GroovyObject;
import groovy.lang.Closure;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.util.Map;
import groovy.lang.Writable;

public class Node implements Writable
{
    private final String name;
    private final Map attributes;
    private final Map attributeNamespaces;
    private final String namespaceURI;
    private final List children;
    private final Stack replacementNodeStack;
    
    public Node(final Node parent, final String name, final Map attributes, final Map attributeNamespaces, final String namespaceURI) {
        this.children = new LinkedList();
        this.replacementNodeStack = new Stack();
        this.name = name;
        this.attributes = attributes;
        this.attributeNamespaces = attributeNamespaces;
        this.namespaceURI = namespaceURI;
    }
    
    public String name() {
        return this.name;
    }
    
    public String namespaceURI() {
        return this.namespaceURI;
    }
    
    public Map attributes() {
        return this.attributes;
    }
    
    public List children() {
        return this.children;
    }
    
    public void addChild(final Object child) {
        this.children.add(child);
    }
    
    public void replaceNode(final Closure replacementClosure, final GPathResult result) {
        this.replacementNodeStack.push(new ReplacementNode() {
            @Override
            public void build(final GroovyObject builder, final Map namespaceMap, final Map<String, String> namespaceTagHints) {
                final Closure c = (Closure)replacementClosure.clone();
                Node.this.replacementNodeStack.pop();
                c.setDelegate(builder);
                c.call(new Object[] { result });
                Node.this.replacementNodeStack.push(this);
            }
        });
    }
    
    protected void replaceBody(final Object newValue) {
        this.children.clear();
        this.children.add(newValue);
    }
    
    protected void appendNode(final Object newValue, final GPathResult result) {
        if (newValue instanceof Closure) {
            this.children.add(new ReplacementNode() {
                @Override
                public void build(final GroovyObject builder, final Map namespaceMap, final Map<String, String> namespaceTagHints) {
                    final Closure c = (Closure)((Closure)newValue).clone();
                    c.setDelegate(builder);
                    c.call(new Object[] { result });
                }
            });
        }
        else {
            this.children.add(newValue);
        }
    }
    
    public String text() {
        final StringBuffer buff = new StringBuffer();
        for (final Object child : this.children) {
            if (child instanceof Node) {
                buff.append(((Node)child).text());
            }
            else {
                buff.append(child);
            }
        }
        return buff.toString();
    }
    
    public Iterator childNodes() {
        return new Iterator() {
            private final Iterator iter = Node.this.children.iterator();
            private Object nextElementNodes = this.getNextElementNodes();
            
            public boolean hasNext() {
                return this.nextElementNodes != null;
            }
            
            public Object next() {
                try {
                    return this.nextElementNodes;
                }
                finally {
                    this.nextElementNodes = this.getNextElementNodes();
                }
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            private Object getNextElementNodes() {
                while (this.iter.hasNext()) {
                    final Object node = this.iter.next();
                    if (node instanceof Node) {
                        return node;
                    }
                }
                return null;
            }
        };
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        if (this.replacementNodeStack.empty()) {
            for (final Object child : this.children) {
                if (child instanceof Writable) {
                    ((Writable)child).writeTo(out);
                }
                else {
                    out.write(child.toString());
                }
            }
            return out;
        }
        return this.replacementNodeStack.peek().writeTo(out);
    }
    
    public void build(final GroovyObject builder, final Map namespaceMap, final Map<String, String> namespaceTagHints) {
        if (this.replacementNodeStack.empty()) {
            final Closure rest = new Closure(null) {
                public Object doCall(final Object o) {
                    Node.this.buildChildren(builder, namespaceMap, namespaceTagHints);
                    return null;
                }
            };
            if (this.namespaceURI.length() == 0 && this.attributeNamespaces.isEmpty()) {
                builder.invokeMethod(this.name, new Object[] { this.attributes, rest });
            }
            else {
                final List newTags = new LinkedList();
                builder.getProperty("mkp");
                final List namespaces = (List)builder.invokeMethod("getNamespaces", new Object[0]);
                final Map current = namespaces.get(0);
                final Map pending = namespaces.get(1);
                if (this.attributeNamespaces.isEmpty()) {
                    builder.getProperty(getTagFor(this.namespaceURI, current, pending, namespaceMap, namespaceTagHints, newTags, builder));
                    builder.invokeMethod(this.name, new Object[] { this.attributes, rest });
                }
                else {
                    final Map attributesWithNamespaces = new HashMap(this.attributes);
                    for (final Object key : this.attributes.keySet()) {
                        final Object attributeNamespaceURI = this.attributeNamespaces.get(key);
                        if (attributeNamespaceURI != null) {
                            attributesWithNamespaces.put(getTagFor(attributeNamespaceURI, current, pending, namespaceMap, namespaceTagHints, newTags, builder) + "$" + key, attributesWithNamespaces.remove(key));
                        }
                    }
                    builder.getProperty(getTagFor(this.namespaceURI, current, pending, namespaceMap, namespaceTagHints, newTags, builder));
                    builder.invokeMethod(this.name, new Object[] { attributesWithNamespaces, rest });
                }
                if (!newTags.isEmpty()) {
                    final Iterator iter = newTags.iterator();
                    do {
                        pending.remove(iter.next());
                    } while (iter.hasNext());
                }
            }
        }
        else {
            this.replacementNodeStack.peek().build(builder, namespaceMap, namespaceTagHints);
        }
    }
    
    private static String getTagFor(final Object namespaceURI, final Map current, final Map pending, final Map local, final Map tagHints, final List newTags, final GroovyObject builder) {
        String tag = findNamespaceTag(pending, namespaceURI);
        if (tag == null) {
            tag = findNamespaceTag(current, namespaceURI);
            if (tag == null) {
                tag = findNamespaceTag(local, namespaceURI);
                if (tag == null || tag.length() == 0) {
                    tag = findNamespaceTag(tagHints, namespaceURI);
                }
                if (tag == null || tag.length() == 0) {
                    int suffix = 0;
                    do {
                        final String posibleTag = "tag" + suffix++;
                        if (!pending.containsKey(posibleTag) && !current.containsKey(posibleTag) && !local.containsKey(posibleTag)) {
                            tag = posibleTag;
                        }
                    } while (tag == null);
                }
                final Map newNamespace = new HashMap();
                newNamespace.put(tag, namespaceURI);
                builder.getProperty("mkp");
                builder.invokeMethod("declareNamespace", new Object[] { newNamespace });
                newTags.add(tag);
            }
        }
        return tag;
    }
    
    private static String findNamespaceTag(final Map tagMap, final Object namespaceURI) {
        if (tagMap.containsValue(namespaceURI)) {
            for (final Map.Entry entry : tagMap.entrySet()) {
                if (namespaceURI.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
    
    private void buildChildren(final GroovyObject builder, final Map namespaceMap, final Map<String, String> namespaceTagHints) {
        for (final Object child : this.children) {
            if (child instanceof Node) {
                ((Node)child).build(builder, namespaceMap, namespaceTagHints);
            }
            else if (child instanceof Buildable) {
                ((Buildable)child).build(builder);
            }
            else {
                builder.getProperty("mkp");
                builder.invokeMethod("yield", new Object[] { child });
            }
        }
    }
}
