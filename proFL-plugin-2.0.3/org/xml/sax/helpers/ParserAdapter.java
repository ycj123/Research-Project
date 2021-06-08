// 
// Decompiled by Procyon v0.5.36
// 

package org.xml.sax.helpers;

import java.util.Enumeration;
import org.xml.sax.SAXParseException;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.AttributeList;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.DocumentHandler;
import org.xml.sax.XMLReader;

public class ParserAdapter implements XMLReader, DocumentHandler
{
    private static final String FEATURES = "http://xml.org/sax/features/";
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    private NamespaceSupport nsSupport;
    private AttributeListAdapter attAdapter;
    private boolean parsing;
    private String[] nameParts;
    private Parser parser;
    private AttributesImpl atts;
    private boolean namespaces;
    private boolean prefixes;
    Locator locator;
    EntityResolver entityResolver;
    DTDHandler dtdHandler;
    ContentHandler contentHandler;
    ErrorHandler errorHandler;
    
    public ParserAdapter() throws SAXException {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        final String property = System.getProperty("org.xml.sax.parser");
        try {
            this.setup(ParserFactory.makeParser());
        }
        catch (ClassNotFoundException ex) {
            throw new SAXException("Cannot find SAX1 driver class " + property, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new SAXException("SAX1 driver class " + property + " found but cannot be loaded", ex2);
        }
        catch (InstantiationException ex3) {
            throw new SAXException("SAX1 driver class " + property + " loaded but cannot be instantiated", ex3);
        }
        catch (ClassCastException ex4) {
            throw new SAXException("SAX1 driver class " + property + " does not implement org.xml.sax.Parser");
        }
        catch (NullPointerException ex5) {
            throw new SAXException("System property org.xml.sax.parser not specified");
        }
    }
    
    public ParserAdapter(final Parser parser) {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        this.setup(parser);
    }
    
    private void setup(final Parser parser) {
        if (parser == null) {
            throw new NullPointerException("Parser argument must not be null");
        }
        this.parser = parser;
        this.atts = new AttributesImpl();
        this.nsSupport = new NamespaceSupport();
        this.attAdapter = new AttributeListAdapter();
    }
    
    public void setFeature(final String str, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://xml.org/sax/features/namespaces")) {
            this.checkNotParsing("feature", str);
            this.namespaces = b;
            if (!this.namespaces && !this.prefixes) {
                this.prefixes = true;
            }
        }
        else {
            if (!str.equals("http://xml.org/sax/features/namespace-prefixes")) {
                throw new SAXNotRecognizedException("Feature: " + str);
            }
            this.checkNotParsing("feature", str);
            this.prefixes = b;
            if (!this.prefixes && !this.namespaces) {
                this.namespaces = true;
            }
        }
    }
    
    public boolean getFeature(final String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://xml.org/sax/features/namespaces")) {
            return this.namespaces;
        }
        if (str.equals("http://xml.org/sax/features/namespace-prefixes")) {
            return this.prefixes;
        }
        throw new SAXNotRecognizedException("Feature: " + str);
    }
    
    public void setProperty(final String str, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + str);
    }
    
    public Object getProperty(final String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + str);
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.dtdHandler = dtdHandler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setContentHandler(final ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void parse(final String s) throws IOException, SAXException {
        this.parse(new InputSource(s));
    }
    
    public void parse(final InputSource inputSource) throws IOException, SAXException {
        if (this.parsing) {
            throw new SAXException("Parser is already in use");
        }
        this.setupParser();
        this.parsing = true;
        try {
            this.parser.parse(inputSource);
        }
        finally {
            this.parsing = false;
        }
        this.parsing = false;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
        if (this.contentHandler != null) {
            this.contentHandler.setDocumentLocator(locator);
        }
    }
    
    public void startDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startDocument();
        }
    }
    
    public void endDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endDocument();
        }
    }
    
    public void startElement(final String s, final AttributeList attributeList) throws SAXException {
        Vector vector = null;
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.attAdapter.setAttributeList(attributeList);
                this.contentHandler.startElement("", "", s.intern(), this.attAdapter);
            }
            return;
        }
        this.nsSupport.pushContext();
        final int length = attributeList.getLength();
        for (int i = 0; i < length; ++i) {
            final String name = attributeList.getName(i);
            if (name.startsWith("xmlns")) {
                final int index = name.indexOf(58);
                String substring;
                if (index == -1 && name.length() == 5) {
                    substring = "";
                }
                else {
                    if (index != 5) {
                        continue;
                    }
                    substring = name.substring(index + 1);
                }
                final String value = attributeList.getValue(i);
                if (!this.nsSupport.declarePrefix(substring, value)) {
                    this.reportError("Illegal Namespace prefix: " + substring);
                }
                else if (this.contentHandler != null) {
                    this.contentHandler.startPrefixMapping(substring, value);
                }
            }
        }
        this.atts.clear();
        for (int j = 0; j < length; ++j) {
            final String name2 = attributeList.getName(j);
            final String type = attributeList.getType(j);
            final String value2 = attributeList.getValue(j);
            if (name2.startsWith("xmlns")) {
                final int index2 = name2.indexOf(58);
                String substring2;
                if (index2 == -1 && name2.length() == 5) {
                    substring2 = "";
                }
                else if (index2 != 5) {
                    substring2 = null;
                }
                else {
                    substring2 = name2.substring(index2 + 1);
                }
                if (substring2 != null) {
                    if (this.prefixes) {
                        this.atts.addAttribute("", "", name2.intern(), type, value2);
                    }
                    continue;
                }
            }
            try {
                final String[] processName = this.processName(name2, true, true);
                this.atts.addAttribute(processName[0], processName[1], processName[2], type, value2);
            }
            catch (SAXException obj) {
                if (vector == null) {
                    vector = new Vector<SAXParseException>();
                }
                vector.addElement(obj);
                this.atts.addAttribute("", name2, name2, type, value2);
            }
        }
        if (vector != null && this.errorHandler != null) {
            for (int k = 0; k < vector.size(); ++k) {
                this.errorHandler.error(vector.elementAt(k));
            }
        }
        if (this.contentHandler != null) {
            final String[] processName2 = this.processName(s, false, false);
            this.contentHandler.startElement(processName2[0], processName2[1], processName2[2], this.atts);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.contentHandler.endElement("", "", s.intern());
            }
            return;
        }
        final String[] processName = this.processName(s, false, false);
        if (this.contentHandler != null) {
            this.contentHandler.endElement(processName[0], processName[1], processName[2]);
            final Enumeration declaredPrefixes = this.nsSupport.getDeclaredPrefixes();
            while (declaredPrefixes.hasMoreElements()) {
                this.contentHandler.endPrefixMapping(declaredPrefixes.nextElement());
            }
        }
        this.nsSupport.popContext();
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.characters(array, n, n2);
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.ignorableWhitespace(array, n, n2);
        }
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.processingInstruction(s, s2);
        }
    }
    
    private void setupParser() {
        this.nsSupport.reset();
        if (this.entityResolver != null) {
            this.parser.setEntityResolver(this.entityResolver);
        }
        if (this.dtdHandler != null) {
            this.parser.setDTDHandler(this.dtdHandler);
        }
        if (this.errorHandler != null) {
            this.parser.setErrorHandler(this.errorHandler);
        }
        this.parser.setDocumentHandler(this);
        this.locator = null;
    }
    
    private String[] processName(final String s, final boolean b, final boolean b2) throws SAXException {
        String[] processName = this.nsSupport.processName(s, this.nameParts, b);
        if (processName == null) {
            if (b2) {
                throw this.makeException("Undeclared prefix: " + s);
            }
            this.reportError("Undeclared prefix: " + s);
            processName = new String[3];
            processName[0] = (processName[1] = "");
            processName[2] = s.intern();
        }
        return processName;
    }
    
    void reportError(final String s) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.error(this.makeException(s));
        }
    }
    
    private SAXParseException makeException(final String s) {
        if (this.locator != null) {
            return new SAXParseException(s, this.locator);
        }
        return new SAXParseException(s, null, null, -1, -1);
    }
    
    private void checkNotParsing(final String str, final String str2) throws SAXNotSupportedException {
        if (this.parsing) {
            throw new SAXNotSupportedException("Cannot change " + str + ' ' + str2 + " while parsing");
        }
    }
    
    final class AttributeListAdapter implements Attributes
    {
        private AttributeList qAtts;
        
        void setAttributeList(final AttributeList qAtts) {
            this.qAtts = qAtts;
        }
        
        public int getLength() {
            return this.qAtts.getLength();
        }
        
        public String getURI(final int n) {
            return "";
        }
        
        public String getLocalName(final int n) {
            return "";
        }
        
        public String getQName(final int n) {
            return this.qAtts.getName(n).intern();
        }
        
        public String getType(final int n) {
            return this.qAtts.getType(n).intern();
        }
        
        public String getValue(final int n) {
            return this.qAtts.getValue(n);
        }
        
        public int getIndex(final String s, final String s2) {
            return -1;
        }
        
        public int getIndex(final String anObject) {
            for (int length = ParserAdapter.this.atts.getLength(), i = 0; i < length; ++i) {
                if (this.qAtts.getName(i).equals(anObject)) {
                    return i;
                }
            }
            return -1;
        }
        
        public String getType(final String s, final String s2) {
            return null;
        }
        
        public String getType(final String s) {
            return this.qAtts.getType(s).intern();
        }
        
        public String getValue(final String s, final String s2) {
            return null;
        }
        
        public String getValue(final String s) {
            return this.qAtts.getValue(s);
        }
    }
}
