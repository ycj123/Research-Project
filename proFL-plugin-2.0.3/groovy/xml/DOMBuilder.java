// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.util.Iterator;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.StringReader;
import org.xml.sax.InputSource;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import groovy.util.BuilderSupport;

public class DOMBuilder extends BuilderSupport
{
    Document document;
    DocumentBuilder documentBuilder;
    
    public static DOMBuilder newInstance() throws ParserConfigurationException {
        return newInstance(false, true);
    }
    
    public static DOMBuilder newInstance(final boolean validating, final boolean namespaceAware) throws ParserConfigurationException {
        final DocumentBuilderFactory factory = FactorySupport.createDocumentBuilderFactory();
        factory.setNamespaceAware(namespaceAware);
        factory.setValidating(validating);
        return new DOMBuilder(factory.newDocumentBuilder());
    }
    
    public static Document parse(final Reader reader) throws SAXException, IOException, ParserConfigurationException {
        return parse(reader, false, true);
    }
    
    public static Document parse(final Reader reader, final boolean validating, final boolean namespaceAware) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory factory = FactorySupport.createDocumentBuilderFactory();
        factory.setNamespaceAware(namespaceAware);
        factory.setValidating(validating);
        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(new InputSource(reader));
    }
    
    public Document parseText(final String text) throws SAXException, IOException, ParserConfigurationException {
        return parse(new StringReader(text));
    }
    
    public DOMBuilder(final Document document) {
        this.document = document;
    }
    
    public DOMBuilder(final DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
        final Node current = (Node)parent;
        final Node node = (Node)child;
        current.appendChild(node);
    }
    
    @Override
    protected Object createNode(final Object name) {
        if (this.document == null) {
            this.document = this.createDocument();
        }
        if (name instanceof QName) {
            final QName qname = (QName)name;
            return this.document.createElementNS(qname.getNamespaceURI(), qname.getQualifiedName());
        }
        return this.document.createElement(name.toString());
    }
    
    protected Document createDocument() {
        if (this.documentBuilder == null) {
            throw new IllegalArgumentException("No Document or DOMImplementation available so cannot create Document");
        }
        return this.documentBuilder.newDocument();
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        final Element element = (Element)this.createNode(name);
        element.appendChild(this.document.createTextNode(value.toString()));
        return element;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        final Element element = (Element)this.createNode(name, attributes);
        element.appendChild(this.document.createTextNode(value.toString()));
        return element;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        final Element element = (Element)this.createNode(name);
        for (final Map.Entry entry : attributes.entrySet()) {
            final String attrName = entry.getKey().toString();
            final Object value = entry.getValue();
            if ("xmlns".equals(attrName)) {
                if (value instanceof Map) {
                    this.appendNamespaceAttributes(element, (Map<Object, Object>)value);
                }
                else {
                    if (!(value instanceof String)) {
                        throw new IllegalArgumentException("The value of the xmlns attribute must be a Map of QNames to String URIs");
                    }
                    this.setStringNS(element, "", value);
                }
            }
            else if (attrName.startsWith("xmlns:") && value instanceof String) {
                this.setStringNS(element, attrName.substring(6), value);
            }
            else {
                final String valueText = (value != null) ? value.toString() : "";
                element.setAttribute(attrName, valueText);
            }
        }
        return element;
    }
    
    protected void appendNamespaceAttributes(final Element element, final Map<Object, Object> attributes) {
        for (final Map.Entry entry : attributes.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException("The value of key: " + key + " cannot be null");
            }
            if (key instanceof String) {
                this.setStringNS(element, key, value);
            }
            else {
                if (!(key instanceof QName)) {
                    throw new IllegalArgumentException("The key: " + key + " should be an instanceof of " + QName.class);
                }
                final QName qname = (QName)key;
                element.setAttributeNS(qname.getNamespaceURI(), qname.getQualifiedName(), value.toString());
            }
        }
    }
    
    private void setStringNS(final Element element, final Object key, final Object value) {
        final String prefix = (String)key;
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "".equals(prefix) ? "xmlns" : ("xmlns:" + prefix), value.toString());
    }
}
