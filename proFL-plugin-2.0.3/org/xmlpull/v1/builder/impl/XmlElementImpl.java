// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder.impl;

import java.util.ArrayList;
import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlBuilderException;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.builder.XmlNamespace;
import org.xmlpull.v1.builder.XmlContainer;
import org.xmlpull.v1.builder.XmlElement;

public class XmlElementImpl implements XmlElement
{
    private XmlContainer parent;
    private XmlNamespace namespace;
    private String name;
    private List attrs;
    private List nsList;
    private List children;
    private static final Iterator EMPTY_ITERATOR;
    
    XmlElementImpl(final XmlNamespace namespace, final String name) {
        this.namespace = namespace;
        this.name = name;
    }
    
    XmlElementImpl(final String namespaceName, final String name) {
        if (namespaceName != null) {
            this.namespace = new XmlNamespaceImpl(null, namespaceName);
        }
        this.name = name;
    }
    
    public XmlContainer getParent() {
        return this.parent;
    }
    
    public void setParent(final XmlContainer parent) {
        if (parent != null) {
            if (parent instanceof XmlElement) {
                final Iterator iter = ((XmlElement)parent).children();
                boolean found = false;
                while (iter.hasNext()) {
                    final Object element = iter.next();
                    if (element == this) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new XmlBuilderException("this element must be child of parent to set its parent");
                }
            }
            else if (parent instanceof XmlDocument) {
                final XmlDocument doc = (XmlDocument)parent;
                if (doc.getDocumentElement() != this) {
                    throw new XmlBuilderException("this element must be root docuemnt element to have document set as parent but already different element is set as root document element");
                }
            }
        }
        this.parent = parent;
    }
    
    public XmlNamespace getNamespace() {
        return this.namespace;
    }
    
    public String getNamespaceName() {
        return (this.namespace != null) ? this.namespace.getNamespaceName() : null;
    }
    
    public void setNamespace(final XmlNamespace namespace) {
        this.namespace = namespace;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return "name[" + this.name + "] namespace[" + this.namespace.getNamespaceName() + "]";
    }
    
    public String getBaseUri() {
        throw new XmlBuilderException("not implemented");
    }
    
    public void setBaseUri(final String baseUri) {
        throw new XmlBuilderException("not implemented");
    }
    
    public Iterator attributes() {
        if (this.attrs == null) {
            return XmlElementImpl.EMPTY_ITERATOR;
        }
        return this.attrs.iterator();
    }
    
    public XmlAttribute addAttribute(final XmlAttribute attributeValueToAdd) {
        if (this.attrs == null) {
            this.ensureAttributeCapacity(5);
        }
        this.attrs.add(attributeValueToAdd);
        return attributeValueToAdd;
    }
    
    public XmlAttribute addAttribute(final XmlNamespace namespace, final String name, final String value) {
        return this.addAttribute("CDATA", namespace, name, value, false);
    }
    
    public XmlAttribute addAttribute(final String name, final String value) {
        return this.addAttribute("CDATA", null, name, value, false);
    }
    
    public XmlAttribute addAttribute(final String attributeType, final XmlNamespace namespace, final String name, final String value) {
        return this.addAttribute(attributeType, namespace, name, value, false);
    }
    
    public XmlAttribute addAttribute(final String attributeType, final XmlNamespace namespace, final String name, final String value, final boolean specified) {
        final XmlAttribute a = new XmlAttributeImpl(this, attributeType, namespace, name, value, specified);
        return this.addAttribute(a);
    }
    
    public XmlAttribute addAttribute(final String attributeType, final String attributePrefix, final String attributeNamespace, final String attributeName, final String attributeValue, final boolean specified) {
        final XmlNamespace n = this.newNamespace(attributePrefix, attributeNamespace);
        return this.addAttribute(attributeType, n, attributeName, attributeValue, specified);
    }
    
    public void ensureAttributeCapacity(final int minCapacity) {
        if (this.attrs == null) {
            this.attrs = new ArrayList(minCapacity);
        }
        else {
            ((ArrayList)this.attrs).ensureCapacity(minCapacity);
        }
    }
    
    public boolean hasAttributes() {
        return this.attrs != null && this.attrs.size() > 0;
    }
    
    public XmlAttribute findAttribute(final String attributeNamespace, final String attributeName) {
        if (attributeName == null) {
            throw new IllegalArgumentException("attribute name ca not ber null");
        }
        if (this.attrs == null) {
            return null;
        }
        for (int length = this.attrs.size(), i = 0; i < length; ++i) {
            final XmlAttribute a = this.attrs.get(i);
            final String aName = a.getName();
            if (aName == attributeName || attributeName.equals(aName)) {
                if (attributeNamespace != null) {
                    final String aNamespace = a.getNamespaceName();
                    if (attributeNamespace.equals(aNamespace)) {
                        return a;
                    }
                    if (attributeNamespace == "" && aNamespace == null) {
                        return a;
                    }
                }
                else {
                    if (a.getNamespace() == null) {
                        return a;
                    }
                    if (a.getNamespace().getNamespaceName() == "") {
                        return a;
                    }
                }
            }
        }
        return null;
    }
    
    public void removeAllAttributes() {
        this.attrs = null;
    }
    
    public void removeAttribute(final XmlAttribute attr) {
        for (int i = 0; i < this.attrs.size(); ++i) {
            if (this.attrs.get(i).equals(attr)) {
                this.attrs.remove(i);
                break;
            }
        }
    }
    
    public XmlNamespace declareNamespace(final String prefix, final String namespaceName) {
        if (prefix == null) {
            throw new XmlBuilderException("namespace added to element must have not null prefix");
        }
        final XmlNamespace n = this.newNamespace(prefix, namespaceName);
        return this.declareNamespace(n);
    }
    
    public XmlNamespace declareNamespace(final XmlNamespace n) {
        if (n.getPrefix() == null) {
            throw new XmlBuilderException("namespace added to element must have not null prefix");
        }
        if (this.nsList == null) {
            this.ensureNamespaceDeclarationsCapacity(5);
        }
        this.nsList.add(n);
        return n;
    }
    
    public boolean hasNamespaceDeclarations() {
        return this.nsList != null && this.nsList.size() > 0;
    }
    
    public XmlNamespace lookupNamespaceByPrefix(final String namespacePrefix) {
        if (namespacePrefix == null) {
            throw new IllegalArgumentException("namespace prefix can not ber null");
        }
        if (this.hasNamespaceDeclarations()) {
            for (int length = this.nsList.size(), i = 0; i < length; ++i) {
                final XmlNamespace n = this.nsList.get(i);
                if (namespacePrefix.equals(n.getPrefix())) {
                    return n;
                }
            }
        }
        return null;
    }
    
    public XmlNamespace lookupNamespaceByName(final String namespaceName) {
        if (namespaceName == null) {
            throw new IllegalArgumentException("namespace name can not ber null");
        }
        if (this.hasNamespaceDeclarations()) {
            for (int length = this.nsList.size(), i = 0; i < length; ++i) {
                final XmlNamespace n = this.nsList.get(i);
                if (namespaceName.equals(n.getNamespaceName())) {
                    return n;
                }
            }
        }
        return null;
    }
    
    public Iterator namespaces() {
        if (this.nsList == null) {
            return XmlElementImpl.EMPTY_ITERATOR;
        }
        return this.nsList.iterator();
    }
    
    public XmlNamespace newNamespace(final String namespaceName) {
        return this.newNamespace(null, namespaceName);
    }
    
    public XmlNamespace newNamespace(final String prefix, final String namespaceName) {
        return new XmlNamespaceImpl(prefix, namespaceName);
    }
    
    public void ensureNamespaceDeclarationsCapacity(final int minCapacity) {
        if (this.nsList == null) {
            this.nsList = new ArrayList(minCapacity);
        }
        else {
            ((ArrayList)this.nsList).ensureCapacity(minCapacity);
        }
    }
    
    public void removeAllNamespaceDeclarations() {
        this.nsList = null;
    }
    
    public void addChild(final Object child) {
        if (this.children == null) {
            this.ensureChildrenCapacity(1);
        }
        this.checkChildParent(child);
        this.children.add(child);
        this.setChildParent(child);
    }
    
    public void addChild(final int index, final Object child) {
        if (this.children == null) {
            this.ensureChildrenCapacity(1);
        }
        this.checkChildParent(child);
        this.children.add(index, child);
        this.setChildParent(child);
    }
    
    private void checkChildParent(final Object child) {
        if (child instanceof XmlContainer) {
            if (child instanceof XmlElement) {
                final XmlElement elChild = (XmlElement)child;
                final XmlContainer p = elChild.getParent();
                if (p != null && p != this.parent) {
                    throw new XmlBuilderException("child must have no parent to be added to this node");
                }
            }
            else if (child instanceof XmlDocument) {
                throw new XmlBuilderException("docuemet can not be stored as element child");
            }
        }
    }
    
    private void setChildParent(final Object child) {
        if (child instanceof XmlElement) {
            final XmlElement elChild = (XmlElement)child;
            elChild.setParent(this);
        }
    }
    
    public XmlElement addElement(final XmlElement element) {
        this.addChild(element);
        return element;
    }
    
    public XmlElement addElement(final XmlNamespace namespace, final String name) {
        final XmlElement el = this.newElement(namespace, name);
        this.addChild(el);
        return el;
    }
    
    public XmlElement addElement(final String name) {
        return this.addElement(null, name);
    }
    
    public Iterator children() {
        if (this.children == null) {
            return XmlElementImpl.EMPTY_ITERATOR;
        }
        return this.children.iterator();
    }
    
    public void ensureChildrenCapacity(final int minCapacity) {
        if (this.children == null) {
            this.children = new ArrayList(minCapacity);
        }
        else {
            ((ArrayList)this.children).ensureCapacity(minCapacity);
        }
    }
    
    public XmlElement findElementByName(final String name) {
        if (this.children == null) {
            return null;
        }
        for (int length = this.children.size(), i = 0; i < length; ++i) {
            final Object child = this.children.get(i);
            if (child instanceof XmlElement) {
                final XmlElement childEl = (XmlElement)child;
                if (name.equals(childEl.getName())) {
                    return childEl;
                }
            }
        }
        return null;
    }
    
    public XmlElement findElementByName(final String namespaceName, final String name, final XmlElement elementToStartLooking) {
        throw new UnsupportedOperationException();
    }
    
    public XmlElement findElementByName(final String name, final XmlElement elementToStartLooking) {
        throw new UnsupportedOperationException();
    }
    
    public XmlElement findElementByName(final String namespaceName, final String name) {
        throw new UnsupportedOperationException();
    }
    
    public boolean hasChild(final Object child) {
        if (this.children == null) {
            return false;
        }
        for (int i = 0; i < this.children.size(); ++i) {
            if (this.children.get(i) == child) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasChildren() {
        return this.children != null && this.children.size() > 0;
    }
    
    public void insertChild(final int pos, final Object childToInsert) {
        this.children.set(pos, childToInsert);
    }
    
    public XmlElement newElement(final String name) {
        return this.newElement((XmlNamespace)null, name);
    }
    
    public XmlElement newElement(final String namespace, final String name) {
        return new XmlElementImpl(namespace, name);
    }
    
    public XmlElement newElement(final XmlNamespace namespace, final String name) {
        return new XmlElementImpl(namespace, name);
    }
    
    public void replaceChild(final Object newChild, final Object oldChild) {
        if (newChild == null) {
            throw new IllegalArgumentException("new child to replace can not be null");
        }
        if (oldChild == null) {
            throw new IllegalArgumentException("old child to replace can not be null");
        }
        if (!this.hasChildren()) {
            throw new XmlBuilderException("no children available for replacement");
        }
        final int pos = this.children.indexOf(oldChild);
        if (pos == -1) {
            throw new XmlBuilderException("could not find child to replace");
        }
        this.children.set(pos, newChild);
    }
    
    public void removeAllChildren() {
        this.children = null;
    }
    
    public void removeChild(final Object child) {
        if (child == null) {
            throw new IllegalArgumentException("child to remove can not be null");
        }
        if (!this.hasChildren()) {
            throw new XmlBuilderException("no children to remove");
        }
        final int pos = this.children.indexOf(child);
        if (pos != -1) {
            this.children.remove(pos);
        }
    }
    
    static {
        EMPTY_ITERATOR = new EmptyIterator();
    }
    
    private static class EmptyIterator implements Iterator
    {
        public boolean hasNext() {
            return false;
        }
        
        public Object next() {
            throw new RuntimeException("this iterator has no content and next() is not allowed");
        }
        
        public void remove() {
            throw new RuntimeException("this iterator has no content and remove() is not allowed");
        }
    }
}
