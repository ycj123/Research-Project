// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.io.PrintWriter;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.xml.QName;
import groovy.lang.GroovySystem;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import groovy.lang.Closure;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Node implements Serializable
{
    private static final long serialVersionUID = 4121134753270542643L;
    private Node parent;
    private Object name;
    private Map attributes;
    private Object value;
    
    public Node(final Node parent, final Object name) {
        this(parent, name, new NodeList());
    }
    
    public Node(final Node parent, final Object name, final Object value) {
        this(parent, name, new HashMap(), value);
    }
    
    public Node(final Node parent, final Object name, final Map attributes) {
        this(parent, name, attributes, new NodeList());
    }
    
    public Node(final Node parent, final Object name, final Map attributes, final Object value) {
        this.parent = parent;
        this.name = name;
        this.attributes = attributes;
        this.value = value;
        if (parent != null) {
            this.getParentList(parent).add(this);
        }
    }
    
    private List getParentList(final Node parent) {
        final Object parentValue = parent.value();
        List parentList;
        if (parentValue instanceof List) {
            parentList = (List)parentValue;
        }
        else {
            parentList = new NodeList();
            parentList.add(parentValue);
            parent.setValue(parentList);
        }
        return parentList;
    }
    
    public boolean append(final Node child) {
        child.parent = this;
        return this.getParentList(this).add(child);
    }
    
    public boolean remove(final Node child) {
        child.parent = null;
        return this.getParentList(this).remove(child);
    }
    
    public Node appendNode(final Object name, final Map attributes) {
        return new Node(this, name, attributes);
    }
    
    public Node appendNode(final Object name) {
        return new Node(this, name);
    }
    
    public Node appendNode(final Object name, final Object value) {
        return new Node(this, name, value);
    }
    
    public Node appendNode(final Object name, final Map attributes, final Object value) {
        return new Node(this, name, attributes, value);
    }
    
    public Node replaceNode(final Closure c) {
        this.getParentList(this.parent).remove(this);
        final NodeBuilder b = new NodeBuilder();
        final Node newNode = (Node)b.invokeMethod("dummyNode", c);
        final List<Node> children = (List<Node>)newNode.children();
        Node result = this;
        for (final Node child : children) {
            result = this.parent.appendNode(child.name(), child.attributes(), child.value());
        }
        return result;
    }
    
    public void plus(final Closure c) {
        final List<Node> list = (List<Node>)this.parent().children();
        final int afterIndex = list.indexOf(this);
        final List<Node> leftOvers = new ArrayList<Node>(list.subList(afterIndex + 1, list.size()));
        list.subList(afterIndex + 1, list.size()).clear();
        final NodeBuilder b = new NodeBuilder();
        final Node newNode = (Node)b.invokeMethod("dummyNode", c);
        final List<Node> children = (List<Node>)newNode.children();
        for (final Node child : children) {
            this.parent.appendNode(child.name(), child.attributes(), child.value());
        }
        for (final Node child : leftOvers) {
            this.parent().children().add(child);
        }
    }
    
    protected static void setMetaClass(final MetaClass metaClass, final Class nodeClass) {
        final MetaClass newMetaClass = new DelegatingMetaClass(metaClass) {
            @Override
            public Object getAttribute(final Object object, final String attribute) {
                final Node n = (Node)object;
                return n.get("@" + attribute);
            }
            
            @Override
            public void setAttribute(final Object object, final String attribute, final Object newValue) {
                final Node n = (Node)object;
                n.attributes().put(attribute, newValue);
            }
            
            @Override
            public Object getProperty(final Object object, final String property) {
                if (object instanceof Node) {
                    final Node n = (Node)object;
                    return n.get(property);
                }
                return super.getProperty(object, property);
            }
            
            @Override
            public void setProperty(final Object object, final String property, final Object newValue) {
                if (property.startsWith("@")) {
                    final String attribute = property.substring(1);
                    final Node n = (Node)object;
                    n.attributes().put(attribute, newValue);
                    return;
                }
                this.delegate.setProperty(object, property, newValue);
            }
        };
        GroovySystem.getMetaClassRegistry().setMetaClass(nodeClass, newMetaClass);
    }
    
    public String text() {
        if (this.value instanceof String) {
            return (String)this.value;
        }
        if (this.value instanceof Collection) {
            final Collection coll = (Collection)this.value;
            String previousText = null;
            StringBuffer buffer = null;
            for (final Object child : coll) {
                if (child instanceof String) {
                    final String childText = (String)child;
                    if (previousText == null) {
                        previousText = childText;
                    }
                    else {
                        if (buffer == null) {
                            buffer = new StringBuffer();
                            buffer.append(previousText);
                        }
                        buffer.append(childText);
                    }
                }
            }
            if (buffer != null) {
                return buffer.toString();
            }
            if (previousText != null) {
                return previousText;
            }
        }
        return "";
    }
    
    public Iterator iterator() {
        return this.children().iterator();
    }
    
    public List children() {
        if (this.value == null) {
            return new NodeList();
        }
        if (this.value instanceof List) {
            return (List)this.value;
        }
        final List result = new NodeList();
        result.add(this.value);
        return result;
    }
    
    public Map attributes() {
        return this.attributes;
    }
    
    public Object attribute(final Object key) {
        return (this.attributes != null) ? this.attributes.get(key) : null;
    }
    
    public Object name() {
        return this.name;
    }
    
    public Object value() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public Node parent() {
        return this.parent;
    }
    
    public Object get(final String key) {
        if (key != null && key.charAt(0) == '@') {
            final String attributeName = key.substring(1);
            return this.attributes().get(attributeName);
        }
        if ("..".equals(key)) {
            return this.parent();
        }
        if ("*".equals(key)) {
            return this.children();
        }
        if ("**".equals(key)) {
            return this.depthFirst();
        }
        return this.getByName(key);
    }
    
    public NodeList getAt(final QName name) {
        final NodeList answer = new NodeList();
        for (final Object child : this.children()) {
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                final Object childNodeName = childNode.name();
                if (!name.matches(childNodeName)) {
                    continue;
                }
                answer.add(childNode);
            }
        }
        return answer;
    }
    
    private NodeList getByName(final String name) {
        final NodeList answer = new NodeList();
        for (final Object child : this.children()) {
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                final Object childNodeName = childNode.name();
                if (childNodeName instanceof QName) {
                    final QName qn = (QName)childNodeName;
                    if (!qn.matches(name)) {
                        continue;
                    }
                    answer.add(childNode);
                }
                else {
                    if (!name.equals(childNodeName)) {
                        continue;
                    }
                    answer.add(childNode);
                }
            }
        }
        return answer;
    }
    
    public List depthFirst() {
        final List answer = new NodeList();
        answer.add(this);
        answer.addAll(this.depthFirstRest());
        return answer;
    }
    
    private List depthFirstRest() {
        final List answer = new NodeList();
        final Iterator iter = InvokerHelper.asIterator(this.value);
        while (iter.hasNext()) {
            final Object child = iter.next();
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                final List children = childNode.depthFirstRest();
                answer.add(childNode);
                answer.addAll(children);
            }
        }
        return answer;
    }
    
    public List breadthFirst() {
        final List answer = new NodeList();
        answer.add(this);
        answer.addAll(this.breadthFirstRest());
        return answer;
    }
    
    private List breadthFirstRest() {
        final List answer = new NodeList();
        List nextLevelChildren = this.getDirectChildren();
        while (!nextLevelChildren.isEmpty()) {
            final List working = new NodeList(nextLevelChildren);
            nextLevelChildren = new NodeList();
            for (final Node childNode : working) {
                answer.add(childNode);
                final List children = childNode.getDirectChildren();
                nextLevelChildren.addAll(children);
            }
        }
        return answer;
    }
    
    private List getDirectChildren() {
        final List answer = new NodeList();
        final Iterator iter = InvokerHelper.asIterator(this.value);
        while (iter.hasNext()) {
            final Object child = iter.next();
            if (child instanceof Node) {
                final Node childNode = (Node)child;
                answer.add(childNode);
            }
        }
        return answer;
    }
    
    @Override
    public String toString() {
        return this.name + "[attributes=" + this.attributes + "; value=" + this.value + "]";
    }
    
    public void print(final PrintWriter out) {
        new NodePrinter(out).print(this);
    }
    
    static {
        setMetaClass(GroovySystem.getMetaClassRegistry().getMetaClass(Node.class), Node.class);
    }
}
