// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml;

import org.xml.sax.Attributes;
import java.util.ArrayList;
import org.xml.sax.SAXParseException;
import java.util.List;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.IOUtil;
import org.xml.sax.InputSource;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.pull.XmlPullParserException;
import javax.annotation.Nonnull;
import javax.annotation.WillClose;
import java.io.Reader;

public class Xpp3DomBuilder
{
    private static final boolean DEFAULT_TRIM = true;
    
    public static Xpp3Dom build(@WillClose @Nonnull final Reader reader) throws XmlPullParserException {
        return build(reader, true);
    }
    
    public static Xpp3Dom build(@WillClose final InputStream is, @Nonnull final String encoding) throws XmlPullParserException {
        return build(is, encoding, true);
    }
    
    public static Xpp3Dom build(@WillClose final InputStream is, @Nonnull final String encoding, final boolean trim) throws XmlPullParserException {
        try {
            final Reader reader = new InputStreamReader(is, encoding);
            return build(reader, trim);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Xpp3Dom build(@WillClose final Reader reader, final boolean trim) throws XmlPullParserException {
        try {
            final DocHandler docHandler = parseSax(new InputSource(reader), trim);
            return docHandler.result;
        }
        finally {
            IOUtil.close(reader);
        }
    }
    
    private static DocHandler parseSax(@Nonnull final InputSource inputSource, final boolean trim) throws XmlPullParserException {
        try {
            final DocHandler ch = new DocHandler(trim);
            final XMLReader parser = createXmlReader();
            parser.setContentHandler(ch);
            parser.parse(inputSource);
            return ch;
        }
        catch (IOException e) {
            throw new XmlPullParserException(e);
        }
        catch (SAXException e2) {
            throw new XmlPullParserException(e2);
        }
    }
    
    private static XMLReader createXmlReader() throws SAXException {
        final XMLReader comSunXmlReader = instantiate("com.sun.org.apache.xerces.internal.parsers.SAXParser");
        if (comSunXmlReader != null) {
            return comSunXmlReader;
        }
        final String key = "org.xml.sax.driver";
        final String oldParser = System.getProperty(key);
        System.clearProperty(key);
        try {
            return XMLReaderFactory.createXMLReader();
        }
        finally {
            if (oldParser != null) {
                System.setProperty(key, oldParser);
            }
        }
    }
    
    private static XMLReader instantiate(final String s) {
        try {
            final Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(s);
            return (XMLReader)aClass.newInstance();
        }
        catch (ClassNotFoundException e) {
            return null;
        }
        catch (InstantiationException e2) {
            return null;
        }
        catch (IllegalAccessException e3) {
            return null;
        }
    }
    
    private static class DocHandler extends DefaultHandler
    {
        private final List<Xpp3Dom> elemStack;
        private final List<StringBuilder> values;
        private final List<SAXParseException> warnings;
        private final List<SAXParseException> errors;
        private final List<SAXParseException> fatals;
        Xpp3Dom result;
        private final boolean trim;
        private boolean spacePreserve;
        
        DocHandler(final boolean trim) {
            this.elemStack = new ArrayList<Xpp3Dom>();
            this.values = new ArrayList<StringBuilder>();
            this.warnings = new ArrayList<SAXParseException>();
            this.errors = new ArrayList<SAXParseException>();
            this.fatals = new ArrayList<SAXParseException>();
            this.result = null;
            this.spacePreserve = false;
            this.trim = trim;
        }
        
        @Override
        public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
            this.spacePreserve = false;
            final Xpp3Dom child = new Xpp3Dom(localName);
            this.attachToParent(child);
            this.pushOnStack(child);
            this.values.add(new StringBuilder());
            for (int size = attributes.getLength(), i = 0; i < size; ++i) {
                final String name = attributes.getQName(i);
                final String value = attributes.getValue(i);
                child.setAttribute(name, value);
                this.spacePreserve = (this.spacePreserve || ("xml:space".equals(name) && "preserve".equals(value)));
            }
        }
        
        private boolean pushOnStack(final Xpp3Dom child) {
            return this.elemStack.add(child);
        }
        
        private void attachToParent(final Xpp3Dom child) {
            final int depth = this.elemStack.size();
            if (depth > 0) {
                this.elemStack.get(depth - 1).addChild(child);
            }
        }
        
        @Override
        public void warning(final SAXParseException e) throws SAXException {
            this.warnings.add(e);
        }
        
        @Override
        public void error(final SAXParseException e) throws SAXException {
            this.errors.add(e);
        }
        
        @Override
        public void fatalError(final SAXParseException e) throws SAXException {
            this.fatals.add(e);
        }
        
        private Xpp3Dom pop() {
            final int depth = this.elemStack.size() - 1;
            return this.elemStack.remove(depth);
        }
        
        @Override
        public void endElement(final String uri, final String localName, final String qName) throws SAXException {
            final int depth = this.elemStack.size() - 1;
            final Xpp3Dom element = this.pop();
            final Object accumulatedValue = this.values.remove(depth);
            if (element.getChildCount() == 0) {
                if (accumulatedValue == null) {
                    element.setValue("");
                }
                else {
                    element.setValue(accumulatedValue.toString());
                }
            }
            if (depth == 0) {
                this.result = element;
            }
        }
        
        @Override
        public void characters(final char[] ch, final int start, final int length) throws SAXException {
            final String text = new String(ch, start, length);
            this.appendToTopValue((this.trim && !this.spacePreserve) ? text.trim() : text);
        }
        
        private void appendToTopValue(final String toAppend) {
            final StringBuilder stringBuilder = this.values.get(this.values.size() - 1);
            stringBuilder.append(toAppend);
        }
    }
}
