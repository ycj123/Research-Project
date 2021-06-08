// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.HashMap;
import org.xml.sax.Attributes;
import java.net.URL;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import java.io.StringReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import groovy.util.slurpersupport.NodeChild;
import groovy.util.slurpersupport.GPathResult;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import groovy.xml.FactorySupport;
import java.util.Hashtable;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;
import java.util.Stack;
import groovy.util.slurpersupport.Node;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSlurper extends DefaultHandler
{
    private final XMLReader reader;
    private Node currentNode;
    private final Stack stack;
    private final StringBuffer charBuffer;
    private final Map<String, String> namespaceTagHints;
    private boolean keepWhitespace;
    
    public XmlSlurper() throws ParserConfigurationException, SAXException {
        this(false, true);
    }
    
    public XmlSlurper(final boolean validating, final boolean namespaceAware) throws ParserConfigurationException, SAXException {
        this.currentNode = null;
        this.stack = new Stack();
        this.charBuffer = new StringBuffer();
        this.namespaceTagHints = new Hashtable<String, String>();
        this.keepWhitespace = false;
        final SAXParserFactory factory = FactorySupport.createSaxParserFactory();
        factory.setNamespaceAware(namespaceAware);
        factory.setValidating(validating);
        this.reader = factory.newSAXParser().getXMLReader();
    }
    
    public XmlSlurper(final XMLReader reader) {
        this.currentNode = null;
        this.stack = new Stack();
        this.charBuffer = new StringBuffer();
        this.namespaceTagHints = new Hashtable<String, String>();
        this.keepWhitespace = false;
        this.reader = reader;
    }
    
    public XmlSlurper(final SAXParser parser) throws SAXException {
        this(parser.getXMLReader());
    }
    
    public void setKeepWhitespace(final boolean keepWhitespace) {
        this.keepWhitespace = keepWhitespace;
    }
    
    public GPathResult getDocument() {
        try {
            return new NodeChild(this.currentNode, null, this.namespaceTagHints);
        }
        finally {
            this.currentNode = null;
        }
    }
    
    public GPathResult parse(final InputSource input) throws IOException, SAXException {
        this.reader.setContentHandler(this);
        this.reader.parse(input);
        return this.getDocument();
    }
    
    public GPathResult parse(final File file) throws IOException, SAXException {
        final InputSource input = new InputSource(new FileInputStream(file));
        input.setSystemId("file://" + file.getAbsolutePath());
        return this.parse(input);
    }
    
    public GPathResult parse(final InputStream input) throws IOException, SAXException {
        return this.parse(new InputSource(input));
    }
    
    public GPathResult parse(final Reader in) throws IOException, SAXException {
        return this.parse(new InputSource(in));
    }
    
    public GPathResult parse(final String uri) throws IOException, SAXException {
        return this.parse(new InputSource(uri));
    }
    
    public GPathResult parseText(final String text) throws IOException, SAXException {
        return this.parse(new StringReader(text));
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
    
    public void setEntityBaseUrl(final URL base) {
        this.reader.setEntityResolver(new EntityResolver() {
            public InputSource resolveEntity(final String publicId, final String systemId) throws IOException {
                return new InputSource(new URL(base, systemId).openStream());
            }
        });
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
    
    @Override
    public void startDocument() throws SAXException {
        this.currentNode = null;
        this.charBuffer.setLength(0);
    }
    
    @Override
    public void startPrefixMapping(final String tag, final String uri) throws SAXException {
        this.namespaceTagHints.put(tag, uri);
    }
    
    @Override
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        this.addCdata();
        final Map attributes = new HashMap();
        final Map attributeNamespaces = new HashMap();
        for (int i = atts.getLength() - 1; i != -1; --i) {
            if (atts.getURI(i).length() == 0) {
                attributes.put(atts.getQName(i), atts.getValue(i));
            }
            else {
                attributes.put(atts.getLocalName(i), atts.getValue(i));
                attributeNamespaces.put(atts.getLocalName(i), atts.getURI(i));
            }
        }
        Node newElement;
        if (namespaceURI.length() == 0) {
            newElement = new Node(this.currentNode, qName, attributes, attributeNamespaces, namespaceURI);
        }
        else {
            newElement = new Node(this.currentNode, localName, attributes, attributeNamespaces, namespaceURI);
        }
        if (this.currentNode != null) {
            this.currentNode.addChild(newElement);
        }
        this.stack.push(this.currentNode);
        this.currentNode = newElement;
    }
    
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.charBuffer.append(ch, start, length);
    }
    
    @Override
    public void endElement(final String namespaceURI, final String localName, final String qName) throws SAXException {
        this.addCdata();
        final Object oldCurrentNode = this.stack.pop();
        if (oldCurrentNode != null) {
            this.currentNode = (Node)oldCurrentNode;
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
    }
    
    private void addCdata() {
        if (this.charBuffer.length() != 0) {
            final String cdata = this.charBuffer.toString();
            this.charBuffer.setLength(0);
            if (this.keepWhitespace || cdata.trim().length() != 0) {
                this.currentNode.addChild(cdata);
            }
        }
    }
}
