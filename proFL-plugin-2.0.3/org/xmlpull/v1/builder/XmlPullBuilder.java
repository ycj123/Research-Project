// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

import java.io.StringWriter;
import java.io.Writer;
import java.io.OutputStream;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.builder.impl.XmlPullBuilderImpl;
import org.xmlpull.v1.XmlPullParserFactory;

public abstract class XmlPullBuilder
{
    protected XmlPullParserFactory factory;
    
    public static XmlPullBuilder newInstance() throws XmlBuilderException {
        final XmlPullBuilder impl = new XmlPullBuilderImpl();
        try {
            (impl.factory = XmlPullParserFactory.newInstance()).setNamespaceAware(true);
        }
        catch (XmlPullParserException ex) {
            throw new XmlBuilderException("could not create XmlPull factory:" + ex, ex);
        }
        return impl;
    }
    
    public XmlPullParserFactory getFactory() throws XmlBuilderException {
        return this.factory;
    }
    
    public XmlDocument newDocument() throws XmlBuilderException {
        return this.newDocument(null, null, null);
    }
    
    public abstract XmlDocument newDocument(final String p0, final Boolean p1, final String p2) throws XmlBuilderException;
    
    public abstract XmlElement newFragment(final String p0) throws XmlBuilderException;
    
    public abstract XmlElement newFragment(final String p0, final String p1) throws XmlBuilderException;
    
    public abstract XmlElement newFragment(final XmlNamespace p0, final String p1) throws XmlBuilderException;
    
    public abstract XmlNamespace newNamespace(final String p0) throws XmlBuilderException;
    
    public abstract XmlNamespace newNamespace(final String p0, final String p1) throws XmlBuilderException;
    
    public abstract XmlDocument parse(final XmlPullParser p0) throws XmlBuilderException;
    
    public abstract Object parseItem(final XmlPullParser p0) throws XmlBuilderException;
    
    public abstract XmlElement parseStartTag(final XmlPullParser p0) throws XmlBuilderException;
    
    public XmlDocument parseInputStream(final InputStream is) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(is, null);
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not start parsing input stream", e);
        }
        return this.parse(pp);
    }
    
    public XmlDocument parseInputStream(final InputStream is, final String encoding) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(is, encoding);
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not start parsing input stream (encoding=" + encoding + ")", e);
        }
        return this.parse(pp);
    }
    
    public XmlDocument parseReader(final Reader reader) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(reader);
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not start parsing input from reader", e);
        }
        return this.parse(pp);
    }
    
    public abstract XmlDocument parseLocation(final String p0) throws XmlBuilderException;
    
    public abstract XmlElement parseFragment(final XmlPullParser p0) throws XmlBuilderException;
    
    public XmlElement parseFragmentFromInputStream(final InputStream is) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(is, null);
            try {
                pp.nextTag();
            }
            catch (IOException e) {
                throw new XmlBuilderException("IO error when starting to parse input stream", e);
            }
        }
        catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not start parsing input stream", e2);
        }
        return this.parseFragment(pp);
    }
    
    public XmlElement parseFragementFromInputStream(final InputStream is, final String encoding) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(is, encoding);
            try {
                pp.nextTag();
            }
            catch (IOException e) {
                throw new XmlBuilderException("IO error when starting to parse input stream (encoding=" + encoding + ")", e);
            }
        }
        catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not start parsing input stream (encoding=" + encoding + ")", e2);
        }
        return this.parseFragment(pp);
    }
    
    public XmlElement parseFragmentFromReader(final Reader reader) throws XmlBuilderException {
        XmlPullParser pp = null;
        try {
            pp = this.factory.newPullParser();
            pp.setInput(reader);
            try {
                pp.nextTag();
            }
            catch (IOException e) {
                throw new XmlBuilderException("IO error when starting to parse from reader", e);
            }
        }
        catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not start parsing input from reader", e2);
        }
        return this.parseFragment(pp);
    }
    
    public void skipSubTree(final XmlPullParser pp) throws XmlBuilderException {
        try {
            pp.require(2, null, null);
            int level = 1;
            while (level > 0) {
                final int eventType = pp.next();
                if (eventType == 3) {
                    --level;
                }
                else {
                    if (eventType != 2) {
                        continue;
                    }
                    ++level;
                }
            }
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not skip subtree", e);
        }
        catch (IOException e2) {
            throw new XmlBuilderException("IO error when skipping subtree", e2);
        }
    }
    
    public abstract void serializeStartTag(final XmlElement p0, final XmlSerializer p1) throws XmlBuilderException;
    
    public abstract void serializeEndTag(final XmlElement p0, final XmlSerializer p1) throws XmlBuilderException;
    
    public abstract void serialize(final Object p0, final XmlSerializer p1) throws XmlBuilderException;
    
    public abstract void serializeItem(final Object p0, final XmlSerializer p1) throws XmlBuilderException;
    
    public void serializeToOutputStream(final Object item, final OutputStream os) throws XmlBuilderException {
        this.serializeToOutputStream(item, os, "UTF8");
    }
    
    public void serializeToOutputStream(final Object item, final OutputStream os, final String encoding) throws XmlBuilderException {
        XmlSerializer ser = null;
        try {
            ser = this.factory.newSerializer();
            ser.setOutput(os, encoding);
        }
        catch (Exception e) {
            throw new XmlBuilderException("could not serialize node to output stream (encoding=" + encoding + ")", e);
        }
        this.serialize(item, ser);
    }
    
    public void serializeToWriter(final Object item, final Writer writer) throws XmlBuilderException {
        XmlSerializer ser = null;
        try {
            ser = this.factory.newSerializer();
            ser.setOutput(writer);
        }
        catch (Exception e) {
            throw new XmlBuilderException("could not serialize node to writer", e);
        }
        this.serialize(item, ser);
    }
    
    public String serializeToString(final Object item) throws XmlBuilderException {
        final StringWriter sw = new StringWriter();
        this.serializeToWriter(item, sw);
        return sw.toString();
    }
}
