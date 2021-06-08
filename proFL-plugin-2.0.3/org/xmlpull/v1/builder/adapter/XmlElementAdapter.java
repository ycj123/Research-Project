// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder.adapter;

import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlNamespace;
import java.util.Iterator;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlContainer;
import org.xmlpull.v1.builder.XmlElement;

public class XmlElementAdapter implements XmlElement
{
    private XmlElement target;
    private XmlContainer parent;
    
    public XmlElementAdapter(final XmlElement target) {
        this.target = target;
        if (target.getParent() != null) {
            final XmlContainer parent = target.getParent();
            if (parent instanceof XmlDocument) {
                final XmlDocument doc = (XmlDocument)parent;
                doc.setDocumentElement(this);
            }
            if (parent instanceof XmlElement) {
                final XmlElement parentEl = (XmlElement)parent;
                parentEl.replaceChild(this, target);
            }
        }
        final Iterator iter = target.children();
        while (iter.hasNext()) {
            final Object child = iter.next();
            this.fixParent(child);
        }
        target.setParent(null);
    }
    
    private void fixParent(final Object child) {
        if (child instanceof XmlElement) {
            final XmlElement childEl = (XmlElement)child;
            this.fixElementParent(childEl);
        }
    }
    
    private XmlElement fixElementParent(final XmlElement el) {
        el.setParent(this);
        return el;
    }
    
    public XmlContainer getParent() {
        return this.parent;
    }
    
    public void setParent(final XmlContainer parent) {
        this.parent = parent;
    }
    
    public XmlNamespace newNamespace(final String prefix, final String namespaceName) {
        return this.target.newNamespace(prefix, namespaceName);
    }
    
    public XmlAttribute findAttribute(final String attributeNamespaceName, final String attributeName) {
        return this.target.findAttribute(attributeNamespaceName, attributeName);
    }
    
    public Iterator attributes() {
        return this.target.attributes();
    }
    
    public void removeAllChildren() {
        this.target.removeAllChildren();
    }
    
    public XmlAttribute addAttribute(final String attributeType, final String attributePrefix, final String attributeNamespace, final String attributeName, final String attributeValue, final boolean specified) {
        return this.target.addAttribute(attributeType, attributePrefix, attributeNamespace, attributeName, attributeValue, specified);
    }
    
    public XmlNamespace lookupNamespaceByPrefix(final String namespacePrefix) {
        return this.target.lookupNamespaceByPrefix(namespacePrefix);
    }
    
    public XmlAttribute addAttribute(final XmlNamespace namespace, final String name, final String value) {
        return this.target.addAttribute(namespace, name, value);
    }
    
    public String getNamespaceName() {
        return this.target.getNamespaceName();
    }
    
    public void ensureChildrenCapacity(final int minCapacity) {
        this.target.ensureChildrenCapacity(minCapacity);
    }
    
    public Iterator namespaces() {
        return this.target.namespaces();
    }
    
    public void removeAllAttributes() {
        this.target.removeAllAttributes();
    }
    
    public XmlNamespace getNamespace() {
        return this.target.getNamespace();
    }
    
    public String getBaseUri() {
        return this.target.getBaseUri();
    }
    
    public void removeAttribute(final XmlAttribute attr) {
        this.target.removeAttribute(attr);
    }
    
    public XmlNamespace declareNamespace(final String prefix, final String namespaceName) {
        return this.target.declareNamespace(prefix, namespaceName);
    }
    
    public void removeAllNamespaceDeclarations() {
        this.target.removeAllNamespaceDeclarations();
    }
    
    public boolean hasAttributes() {
        return this.target.hasAttributes();
    }
    
    public XmlAttribute addAttribute(final String type, final XmlNamespace namespace, final String name, final String value, final boolean specified) {
        return this.target.addAttribute(type, namespace, name, value, specified);
    }
    
    public XmlNamespace declareNamespace(final XmlNamespace namespace) {
        return this.target.declareNamespace(namespace);
    }
    
    public XmlAttribute addAttribute(final String name, final String value) {
        return this.target.addAttribute(name, value);
    }
    
    public boolean hasNamespaceDeclarations() {
        return this.target.hasNamespaceDeclarations();
    }
    
    public XmlNamespace lookupNamespaceByName(final String namespaceName) {
        return this.target.lookupNamespaceByName(namespaceName);
    }
    
    public XmlNamespace newNamespace(final String namespaceName) {
        return this.target.newNamespace(namespaceName);
    }
    
    public void setBaseUri(final String baseUri) {
        this.target.setBaseUri(baseUri);
    }
    
    public void setNamespace(final XmlNamespace namespace) {
        this.target.setNamespace(namespace);
    }
    
    public void ensureNamespaceDeclarationsCapacity(final int minCapacity) {
        this.target.ensureNamespaceDeclarationsCapacity(minCapacity);
    }
    
    public String getName() {
        return this.target.getName();
    }
    
    public void setName(final String name) {
        this.target.setName(name);
    }
    
    public XmlAttribute addAttribute(final String type, final XmlNamespace namespace, final String name, final String value) {
        return this.target.addAttribute(type, namespace, name, value);
    }
    
    public void ensureAttributeCapacity(final int minCapacity) {
        this.target.ensureAttributeCapacity(minCapacity);
    }
    
    public XmlAttribute addAttribute(final XmlAttribute attributeValueToAdd) {
        return this.target.addAttribute(attributeValueToAdd);
    }
    
    public XmlElement findElementByName(final String name, final XmlElement elementToStartLooking) {
        return this.target.findElementByName(name, elementToStartLooking);
    }
    
    public XmlElement newElement(final XmlNamespace namespace, final String name) {
        return this.target.newElement(namespace, name);
    }
    
    public XmlElement addElement(final XmlElement element) {
        return this.fixElementParent(this.target.addElement(element));
    }
    
    public XmlElement addElement(final String name) {
        return this.fixElementParent(this.target.addElement(name));
    }
    
    public XmlElement findElementByName(final String namespaceName, final String name) {
        return this.target.findElementByName(namespaceName, name);
    }
    
    public void addChild(final Object child) {
        this.target.addChild(child);
        this.fixParent(child);
    }
    
    public void insertChild(final int pos, final Object childToInsert) {
        this.target.insertChild(pos, childToInsert);
        this.fixParent(childToInsert);
    }
    
    public XmlElement findElementByName(final String name) {
        return this.target.findElementByName(name);
    }
    
    public XmlElement findElementByName(final String namespaceName, final String name, final XmlElement elementToStartLooking) {
        return this.target.findElementByName(namespaceName, name, elementToStartLooking);
    }
    
    public void removeChild(final Object child) {
        this.target.removeChild(child);
    }
    
    public Iterator children() {
        return this.target.children();
    }
    
    public boolean hasChild(final Object child) {
        return this.target.hasChild(child);
    }
    
    public XmlElement newElement(final String namespaceName, final String name) {
        return this.target.newElement(namespaceName, name);
    }
    
    public XmlElement addElement(final XmlNamespace namespace, final String name) {
        return this.fixElementParent(this.target.addElement(namespace, name));
    }
    
    public boolean hasChildren() {
        return this.target.hasChildren();
    }
    
    public void addChild(final int pos, final Object child) {
        this.target.addChild(pos, child);
        this.fixParent(child);
    }
    
    public void replaceChild(final Object newChild, final Object oldChild) {
        this.target.replaceChild(newChild, oldChild);
        this.fixParent(newChild);
    }
    
    public XmlElement newElement(final String name) {
        return this.target.newElement(name);
    }
}
