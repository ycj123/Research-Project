// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import org.jdom.output.XMLOutputter;
import java.util.ListIterator;
import java.util.Iterator;
import java.io.IOException;
import org.jdom.EntityRef;
import org.jdom.DocType;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.Attribute;
import java.io.Writer;
import java.io.StringWriter;
import java.util.Collection;
import org.jdom.Element;
import org.jdom.Document;
import java.util.ArrayList;
import java.util.List;

public class NodeList implements List, Cloneable
{
    private static final AttributeXMLOutputter DEFAULT_OUTPUTTER;
    private List nodes;
    
    public NodeList() {
        this.nodes = new ArrayList();
    }
    
    public NodeList(final Document document) {
        this((Object)document);
    }
    
    public NodeList(final Element element) {
        this((Object)element);
    }
    
    private NodeList(final Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Cannot construct NodeList with null.");
        }
        (this.nodes = new ArrayList(1)).add(object);
    }
    
    public NodeList(final List nodes) {
        this(nodes, true);
    }
    
    public NodeList(final List nodes, final boolean copy) {
        if (nodes == null) {
            throw new IllegalArgumentException("Cannot initialize NodeList with null list");
        }
        this.nodes = (copy ? new ArrayList(nodes) : nodes);
    }
    
    public List getList() {
        return this.nodes;
    }
    
    public String toString() {
        if (this.nodes.isEmpty()) {
            return "";
        }
        final StringWriter sw = new StringWriter(this.nodes.size() * 128);
        try {
            for (final Object node : this.nodes) {
                if (node instanceof Element) {
                    NodeList.DEFAULT_OUTPUTTER.output((Element)node, (Writer)sw);
                }
                else if (node instanceof Attribute) {
                    NodeList.DEFAULT_OUTPUTTER.output((Attribute)node, sw);
                }
                else if (node instanceof Text) {
                    NodeList.DEFAULT_OUTPUTTER.output((Text)node, (Writer)sw);
                }
                else if (node instanceof Document) {
                    NodeList.DEFAULT_OUTPUTTER.output((Document)node, (Writer)sw);
                }
                else if (node instanceof ProcessingInstruction) {
                    NodeList.DEFAULT_OUTPUTTER.output((ProcessingInstruction)node, (Writer)sw);
                }
                else if (node instanceof Comment) {
                    NodeList.DEFAULT_OUTPUTTER.output((Comment)node, (Writer)sw);
                }
                else if (node instanceof CDATA) {
                    NodeList.DEFAULT_OUTPUTTER.output((CDATA)node, (Writer)sw);
                }
                else if (node instanceof DocType) {
                    NodeList.DEFAULT_OUTPUTTER.output((DocType)node, (Writer)sw);
                }
                else {
                    if (!(node instanceof EntityRef)) {
                        throw new IllegalArgumentException("Cannot process a " + ((node == null) ? "null node" : ("node of class " + node.getClass().getName())));
                    }
                    NodeList.DEFAULT_OUTPUTTER.output((EntityRef)node, (Writer)sw);
                }
            }
        }
        catch (IOException e) {
            throw new Error();
        }
        return sw.toString();
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NodeList clonedList = (NodeList)super.clone();
        clonedList.cloneNodes();
        return clonedList;
    }
    
    private void cloneNodes() throws CloneNotSupportedException {
        final Class listClass = this.nodes.getClass();
        try {
            final List clonedNodes = listClass.newInstance();
            clonedNodes.addAll(this.nodes);
            this.nodes = clonedNodes;
        }
        catch (IllegalAccessException e) {
            throw new CloneNotSupportedException("Cannot clone NodeList since there is no accessible no-arg constructor on class " + listClass.getName());
        }
        catch (InstantiationException e2) {
            throw new Error();
        }
    }
    
    public int hashCode() {
        return this.nodes.hashCode();
    }
    
    public boolean equals(final Object o) {
        return o instanceof NodeList && ((NodeList)o).nodes.equals(this.nodes);
    }
    
    public NodeList selectNodes(final String xpathString) {
        return new NodeList(XPathCache.getXPath(xpathString).applyTo(this.nodes), false);
    }
    
    public boolean add(final Object o) {
        return this.nodes.add(o);
    }
    
    public void add(final int index, final Object o) {
        this.nodes.add(index, o);
    }
    
    public boolean addAll(final Collection c) {
        return this.nodes.addAll(c);
    }
    
    public boolean addAll(final int index, final Collection c) {
        return this.nodes.addAll(index, c);
    }
    
    public void clear() {
        this.nodes.clear();
    }
    
    public boolean contains(final Object o) {
        return this.nodes.contains(o);
    }
    
    public boolean containsAll(final Collection c) {
        return this.nodes.containsAll(c);
    }
    
    public Object get(final int index) {
        return this.nodes.get(index);
    }
    
    public int indexOf(final Object o) {
        return this.nodes.indexOf(o);
    }
    
    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }
    
    public Iterator iterator() {
        return this.nodes.iterator();
    }
    
    public int lastIndexOf(final Object o) {
        return this.nodes.lastIndexOf(o);
    }
    
    public ListIterator listIterator() {
        return this.nodes.listIterator();
    }
    
    public ListIterator listIterator(final int index) {
        return this.nodes.listIterator(index);
    }
    
    public Object remove(final int index) {
        return this.nodes.remove(index);
    }
    
    public boolean remove(final Object o) {
        return this.nodes.remove(o);
    }
    
    public boolean removeAll(final Collection c) {
        return this.nodes.removeAll(c);
    }
    
    public boolean retainAll(final Collection c) {
        return this.nodes.retainAll(c);
    }
    
    public Object set(final int index, final Object o) {
        return this.nodes.set(index, o);
    }
    
    public int size() {
        return this.nodes.size();
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        return new NodeList(this.nodes.subList(fromIndex, toIndex));
    }
    
    public Object[] toArray() {
        return this.nodes.toArray();
    }
    
    public Object[] toArray(final Object[] a) {
        return this.nodes.toArray(a);
    }
    
    static {
        DEFAULT_OUTPUTTER = new AttributeXMLOutputter();
    }
    
    private static final class AttributeXMLOutputter extends XMLOutputter
    {
        public void output(final Attribute attribute, final Writer out) throws IOException {
            out.write(" ");
            out.write(attribute.getQualifiedName());
            out.write("=");
            out.write("\"");
            out.write(this.escapeAttributeEntities(attribute.getValue()));
            out.write("\"");
        }
    }
}
