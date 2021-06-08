// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.util.Iterator;
import java.util.Map;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import groovy.util.BuilderSupport;

public class SAXBuilder extends BuilderSupport
{
    private ContentHandler handler;
    private Attributes emptyAttributes;
    
    public SAXBuilder(final ContentHandler handler) {
        this.emptyAttributes = new AttributesImpl();
        this.handler = handler;
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
    }
    
    @Override
    protected Object createNode(final Object name) {
        this.doStartElement(name, this.emptyAttributes);
        return name;
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        this.doStartElement(name, this.emptyAttributes);
        this.doText(value);
        return name;
    }
    
    private void doText(final Object value) {
        try {
            final char[] text = value.toString().toCharArray();
            this.handler.characters(text, 0, text.length);
        }
        catch (SAXException e) {
            this.handleException(e);
        }
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributeMap, final Object text) {
        final AttributesImpl attributes = new AttributesImpl();
        for (final Map.Entry entry : attributeMap.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            String uri = "";
            String localName = null;
            String qualifiedName = "";
            final String valueText = (value != null) ? value.toString() : "";
            if (key instanceof QName) {
                final QName qname = (QName)key;
                uri = qname.getNamespaceURI();
                localName = qname.getLocalPart();
                qualifiedName = qname.getQualifiedName();
            }
            else {
                localName = (qualifiedName = key.toString());
            }
            attributes.addAttribute(uri, localName, qualifiedName, "CDATA", valueText);
        }
        this.doStartElement(name, attributes);
        if (text != null) {
            this.doText(text);
        }
        return name;
    }
    
    protected void doStartElement(final Object name, final Attributes attributes) {
        String uri = "";
        String localName = null;
        String qualifiedName = "";
        if (name instanceof QName) {
            final QName qname = (QName)name;
            uri = qname.getNamespaceURI();
            localName = qname.getLocalPart();
            qualifiedName = qname.getQualifiedName();
        }
        else {
            localName = (qualifiedName = name.toString());
        }
        try {
            this.handler.startElement(uri, localName, qualifiedName, attributes);
        }
        catch (SAXException e) {
            this.handleException(e);
        }
    }
    
    @Override
    protected void nodeCompleted(final Object parent, final Object name) {
        String uri = "";
        String localName = null;
        String qualifiedName = "";
        if (name instanceof QName) {
            final QName qname = (QName)name;
            uri = qname.getNamespaceURI();
            localName = qname.getLocalPart();
            qualifiedName = qname.getQualifiedName();
        }
        else {
            localName = (qualifiedName = name.toString());
        }
        try {
            this.handler.endElement(uri, localName, qualifiedName);
        }
        catch (SAXException e) {
            this.handleException(e);
        }
    }
    
    protected void handleException(final SAXException e) {
        throw new RuntimeException(e);
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        return this.createNode(name, attributes, null);
    }
}
