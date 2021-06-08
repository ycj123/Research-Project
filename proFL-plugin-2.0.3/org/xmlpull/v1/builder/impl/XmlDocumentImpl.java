// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder.impl;

import org.xmlpull.v1.builder.XmlNamespace;
import org.xmlpull.v1.builder.XmlNotation;
import org.xmlpull.v1.builder.XmlDoctype;
import org.xmlpull.v1.builder.XmlComment;
import org.xmlpull.v1.builder.XmlContainer;
import java.util.Iterator;
import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.builder.XmlProcessingInstruction;
import java.util.ArrayList;
import org.xmlpull.v1.builder.XmlElement;
import java.util.List;
import org.xmlpull.v1.builder.XmlDocument;

public class XmlDocumentImpl implements XmlDocument
{
    private List children;
    private XmlElement root;
    private String version;
    private Boolean standalone;
    private String characterEncoding;
    
    public XmlDocumentImpl(final String version, final Boolean standalone, final String characterEncoding) {
        this.children = new ArrayList();
        this.version = version;
        this.standalone = standalone;
        this.characterEncoding = characterEncoding;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public Boolean isStandalone() {
        return this.standalone;
    }
    
    public String getCharacterEncodingScheme() {
        return this.characterEncoding;
    }
    
    public void setCharacterEncodingScheme(final String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }
    
    public XmlProcessingInstruction newProcessingInstruction(final String target, final String content) {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlProcessingInstruction addProcessingInstruction(final String target, final String content) {
        throw new XmlBuilderException("not implemented");
    }
    
    public Iterator children() {
        return this.children.iterator();
    }
    
    public void remocveAllUnparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }
    
    public void setDocumentElement(final XmlElement rootElement) {
        boolean replaced = false;
        for (int i = 0; i < this.children.size(); ++i) {
            final Object element = this.children.get(i);
            if (element == this.root) {
                this.children.set(i, rootElement);
                replaced = true;
            }
        }
        if (!replaced) {
            this.children.add(rootElement);
        }
        (this.root = rootElement).setParent(this);
    }
    
    public void insertChild(final int pos, final Object child) {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlComment addComment(final String content) {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlDoctype newDoctype(final String systemIdentifier, final String publicIdentifier) {
        throw new XmlBuilderException("not implemented");
    }
    
    public Iterator unparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }
    
    public void removeAllChildren() {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlComment newComment(final String content) {
        throw new XmlBuilderException("not implemented");
    }
    
    public void removeAllNotations() {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlDoctype addDoctype(final String systemIdentifier, final String publicIdentifier) {
        throw new XmlBuilderException("not implemented");
    }
    
    public void addChild(final Object child) {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlNotation addNotation(final String name, final String systemIdentifier, final String publicIdentifier, final String declarationBaseUri) {
        throw new XmlBuilderException("not implemented");
    }
    
    public String getBaseUri() {
        throw new XmlBuilderException("not implemented");
    }
    
    public Iterator notations() {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlElement addDocumentElement(final String name) {
        return this.addDocumentElement(null, name);
    }
    
    public XmlElement addDocumentElement(final XmlNamespace namespace, final String name) {
        final XmlElement el = new XmlElementImpl(namespace, name);
        if (this.getDocumentElement() != null) {
            throw new XmlBuilderException("document already has root element");
        }
        this.setDocumentElement(el);
        return el;
    }
    
    public boolean isAllDeclarationsProcessed() {
        throw new XmlBuilderException("not implemented");
    }
    
    public XmlElement getDocumentElement() {
        return this.root;
    }
}
