// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.xml.QName;
import java.util.Map;
import java.util.LinkedHashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.InputSource;
import java.io.FileInputStream;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import groovy.xml.FactorySupport;
import java.util.ArrayList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.XMLReader;
import org.xml.sax.Locator;
import java.util.List;
import org.xml.sax.ContentHandler;

public class XmlParser implements ContentHandler
{
    private StringBuffer bodyText;
    private List<Node> stack;
    private Locator locator;
    private XMLReader reader;
    private Node parent;
    private boolean trimWhitespace;
    private boolean namespaceAware;
    
    public XmlParser() throws ParserConfigurationException, SAXException {
        this(false, true);
    }
    
    public XmlParser(final boolean validating, final boolean namespaceAware) throws ParserConfigurationException, SAXException {
        this.bodyText = new StringBuffer();
        this.stack = new ArrayList<Node>();
        this.trimWhitespace = true;
        final SAXParserFactory factory = FactorySupport.createSaxParserFactory();
        factory.setNamespaceAware(namespaceAware);
        this.namespaceAware = namespaceAware;
        factory.setValidating(validating);
        this.reader = factory.newSAXParser().getXMLReader();
    }
    
    public XmlParser(final XMLReader reader) {
        this.bodyText = new StringBuffer();
        this.stack = new ArrayList<Node>();
        this.trimWhitespace = true;
        this.reader = reader;
    }
    
    public XmlParser(final SAXParser parser) throws SAXException {
        this.bodyText = new StringBuffer();
        this.stack = new ArrayList<Node>();
        this.trimWhitespace = true;
        this.reader = parser.getXMLReader();
    }
    
    public boolean isTrimWhitespace() {
        return this.trimWhitespace;
    }
    
    public void setTrimWhitespace(final boolean trimWhitespace) {
        this.trimWhitespace = trimWhitespace;
    }
    
    public Node parse(final File file) throws IOException, SAXException {
        final InputSource input = new InputSource(new FileInputStream(file));
        input.setSystemId("file://" + file.getAbsolutePath());
        this.getXMLReader().parse(input);
        return this.parent;
    }
    
    public Node parse(final InputSource input) throws IOException, SAXException {
        this.getXMLReader().parse(input);
        return this.parent;
    }
    
    public Node parse(final InputStream input) throws IOException, SAXException {
        final InputSource is = new InputSource(input);
        this.getXMLReader().parse(is);
        return this.parent;
    }
    
    public Node parse(final Reader in) throws IOException, SAXException {
        final InputSource is = new InputSource(in);
        this.getXMLReader().parse(is);
        return this.parent;
    }
    
    public Node parse(final String uri) throws IOException, SAXException {
        final InputSource is = new InputSource(uri);
        this.getXMLReader().parse(is);
        return this.parent;
    }
    
    public Node parseText(final String text) throws IOException, SAXException {
        return this.parse(new StringReader(text));
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public void setNamespaceAware(final boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
    }
    
    public DTDHandler getDTDHandler() {
        return this.reader.getDTDHandler();
    }
    
    public EntityResolver getEntityResolver() {
        return this.reader.getEntityResolver();
    }
    
    public ErrorHandler getErrorHandler() {
        return this.reader.getErrorHandler();
    }
    
    public boolean getFeature(final String uri) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.reader.getFeature(uri);
    }
    
    public Object getProperty(final String uri) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.reader.getProperty(uri);
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.reader.setDTDHandler(dtdHandler);
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.reader.setEntityResolver(entityResolver);
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.reader.setErrorHandler(errorHandler);
    }
    
    public void setFeature(final String uri, final boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.reader.setFeature(uri, value);
    }
    
    public void setProperty(final String uri, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.reader.setProperty(uri, value);
    }
    
    public void startDocument() throws SAXException {
        this.parent = null;
    }
    
    public void endDocument() throws SAXException {
        this.stack.clear();
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes list) throws SAXException {
        this.addTextToNode();
        final Object nodeName = this.getElementName(namespaceURI, localName, qName);
        final int size = list.getLength();
        final Map<Object, String> attributes = new LinkedHashMap<Object, String>(size);
        for (int i = 0; i < size; ++i) {
            final Object attributeName = this.getElementName(list.getURI(i), list.getLocalName(i), list.getQName(i));
            final String value = list.getValue(i);
            attributes.put(attributeName, value);
        }
        this.parent = this.createNode(this.parent, nodeName, attributes);
        this.stack.add(this.parent);
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qName) throws SAXException {
        this.addTextToNode();
        if (!this.stack.isEmpty()) {
            this.stack.remove(this.stack.size() - 1);
            if (!this.stack.isEmpty()) {
                this.parent = this.stack.get(this.stack.size() - 1);
            }
        }
    }
    
    public void characters(final char[] buffer, final int start, final int length) throws SAXException {
        this.bodyText.append(buffer, start, length);
    }
    
    public void startPrefixMapping(final String prefix, final String namespaceURI) throws SAXException {
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] buffer, final int start, final int len) throws SAXException {
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
    }
    
    public Locator getDocumentLocator() {
        return this.locator;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    protected XMLReader getXMLReader() {
        this.reader.setContentHandler(this);
        return this.reader;
    }
    
    protected void addTextToNode() {
        String text = this.bodyText.toString();
        if (this.trimWhitespace) {
            text = text.trim();
        }
        if (text.length() > 0) {
            this.parent.children().add(text);
        }
        this.bodyText = new StringBuffer();
    }
    
    protected Node createNode(final Node parent, final Object name, final Map attributes) {
        return new Node(parent, name, attributes);
    }
    
    protected Object getElementName(final String namespaceURI, final String localName, final String qName) {
        String name = localName;
        String prefix = "";
        if (name == null || name.length() < 1) {
            name = qName;
        }
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return name;
        }
        if (qName != null && qName.length() > 0 && this.namespaceAware) {
            final int index = qName.lastIndexOf(":");
            if (index > 0) {
                prefix = qName.substring(0, index);
            }
        }
        return new QName(namespaceURI, name, prefix);
    }
}
