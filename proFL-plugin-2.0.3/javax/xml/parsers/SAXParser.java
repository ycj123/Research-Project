// 
// Decompiled by Procyon v0.5.36
// 

package javax.xml.parsers;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.Parser;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DocumentHandler;
import java.io.File;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.HandlerBase;
import java.io.InputStream;

public abstract class SAXParser
{
    protected SAXParser() {
    }
    
    public void parse(final InputStream inputStream, final HandlerBase handlerBase) throws SAXException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        this.parse(new InputSource(inputStream), handlerBase);
    }
    
    public void parse(final InputStream inputStream, final HandlerBase handlerBase, final String systemId) throws SAXException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource inputSource = new InputSource(inputStream);
        inputSource.setSystemId(systemId);
        this.parse(inputSource, handlerBase);
    }
    
    public void parse(final InputStream inputStream, final DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        this.parse(new InputSource(inputStream), defaultHandler);
    }
    
    public void parse(final InputStream inputStream, final DefaultHandler defaultHandler, final String systemId) throws SAXException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource inputSource = new InputSource(inputStream);
        inputSource.setSystemId(systemId);
        this.parse(inputSource, defaultHandler);
    }
    
    public void parse(final String s, final HandlerBase handlerBase) throws SAXException, IOException {
        if (s == null) {
            throw new IllegalArgumentException("uri cannot be null");
        }
        this.parse(new InputSource(s), handlerBase);
    }
    
    public void parse(final String s, final DefaultHandler defaultHandler) throws SAXException, IOException {
        if (s == null) {
            throw new IllegalArgumentException("uri cannot be null");
        }
        this.parse(new InputSource(s), defaultHandler);
    }
    
    public void parse(final File file, final HandlerBase handlerBase) throws SAXException, IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String s = "file:" + file.getAbsolutePath();
        if (File.separatorChar == '\\') {
            s = s.replace('\\', '/');
        }
        this.parse(new InputSource(s), handlerBase);
    }
    
    public void parse(final File file, final DefaultHandler defaultHandler) throws SAXException, IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String s = "file:" + file.getAbsolutePath();
        if (File.separatorChar == '\\') {
            s = s.replace('\\', '/');
        }
        this.parse(new InputSource(s), defaultHandler);
    }
    
    public void parse(final InputSource inputSource, final HandlerBase handlerBase) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        final Parser parser = this.getParser();
        if (handlerBase != null) {
            parser.setDocumentHandler(handlerBase);
            parser.setEntityResolver(handlerBase);
            parser.setErrorHandler(handlerBase);
            parser.setDTDHandler(handlerBase);
        }
        parser.parse(inputSource);
    }
    
    public void parse(final InputSource inputSource, final DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        final XMLReader xmlReader = this.getXMLReader();
        if (defaultHandler != null) {
            xmlReader.setContentHandler(defaultHandler);
            xmlReader.setEntityResolver(defaultHandler);
            xmlReader.setErrorHandler(defaultHandler);
            xmlReader.setDTDHandler(defaultHandler);
        }
        xmlReader.parse(inputSource);
    }
    
    public abstract Parser getParser() throws SAXException;
    
    public abstract XMLReader getXMLReader() throws SAXException;
    
    public abstract boolean isNamespaceAware();
    
    public abstract boolean isValidating();
    
    public abstract void setProperty(final String p0, final Object p1) throws SAXNotRecognizedException, SAXNotSupportedException;
    
    public abstract Object getProperty(final String p0) throws SAXNotRecognizedException, SAXNotSupportedException;
}
