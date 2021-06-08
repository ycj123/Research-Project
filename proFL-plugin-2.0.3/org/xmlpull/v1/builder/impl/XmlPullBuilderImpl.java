// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder.impl;

import java.util.Iterator;
import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlSerializable;
import org.xmlpull.v1.XmlSerializer;
import java.net.MalformedURLException;
import java.net.URL;
import org.xmlpull.v1.builder.XmlContainer;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.builder.XmlNamespace;
import org.xmlpull.v1.builder.XmlElement;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlPullBuilder;

public class XmlPullBuilderImpl extends XmlPullBuilder
{
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
    
    public XmlDocument newDocument(final String version, final Boolean standalone, final String characterEncoding) {
        return new XmlDocumentImpl(version, standalone, characterEncoding);
    }
    
    public XmlElement newFragment(final String elementName) {
        return new XmlElementImpl((XmlNamespace)null, elementName);
    }
    
    public XmlElement newFragment(final String elementNamespaceName, final String elementName) {
        return new XmlElementImpl(elementNamespaceName, elementName);
    }
    
    public XmlElement newFragment(final XmlNamespace elementNamespace, final String elementName) {
        return new XmlElementImpl(elementNamespace, elementName);
    }
    
    public XmlNamespace newNamespace(final String namespaceName) {
        return new XmlNamespaceImpl(null, namespaceName);
    }
    
    public XmlNamespace newNamespace(final String prefix, final String namespaceName) {
        return new XmlNamespaceImpl(prefix, namespaceName);
    }
    
    public XmlDocument parse(final XmlPullParser pp) {
        final XmlDocument doc = this.parseDocumentStart(pp);
        final XmlElement root = this.parseFragment(pp);
        doc.setDocumentElement(root);
        return doc;
    }
    
    public Object parseItem(final XmlPullParser pp) {
        try {
            final int eventType = pp.getEventType();
            if (eventType == 2) {
                return this.parseStartTag(pp);
            }
            if (eventType == 4) {
                return pp.getText();
            }
            if (eventType == 0) {
                return this.parseDocumentStart(pp);
            }
            throw new XmlBuilderException("currently unsupported event type " + XmlPullParser.TYPES[eventType] + pp.getPositionDescription());
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML item", e);
        }
    }
    
    private XmlDocument parseDocumentStart(final XmlPullParser pp) {
        XmlDocument doc = null;
        try {
            if (pp.getEventType() != 0) {
                throw new XmlBuilderException("parser must be positioned on beginning of document and not " + pp.getPositionDescription());
            }
            pp.next();
            final String xmlDeclVersion = (String)pp.getProperty("http://xmlpull.org/v1/doc/properties.html#xmldecl-version");
            final Boolean xmlDeclStandalone = (Boolean)pp.getProperty("http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone");
            final String characterEncoding = pp.getInputEncoding();
            doc = new XmlDocumentImpl(xmlDeclVersion, xmlDeclStandalone, characterEncoding);
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML document prolog", e);
        }
        catch (IOException e2) {
            throw new XmlBuilderException("could not read XML document prolog", e2);
        }
        return doc;
    }
    
    public XmlElement parseFragment(final XmlPullParser pp) {
        try {
            int eventType = pp.getEventType();
            if (eventType != 2) {
                throw new XmlBuilderException("expected parser to be on start tag and not " + XmlPullParser.TYPES[eventType] + pp.getPositionDescription());
            }
            XmlElement curElem = this.parseStartTag(pp);
            while (true) {
                eventType = pp.next();
                if (eventType == 2) {
                    final XmlElement child = this.parseStartTag(pp);
                    curElem.addChild(child);
                    curElem = child;
                }
                else if (eventType == 3) {
                    final XmlContainer parent = curElem.getParent();
                    if (parent == null) {
                        break;
                    }
                    curElem = (XmlElement)parent;
                }
                else {
                    if (eventType != 4) {
                        continue;
                    }
                    curElem.addChild(pp.getText());
                }
            }
            return curElem;
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not build tree from XML", e);
        }
        catch (IOException e2) {
            throw new XmlBuilderException("could not read XML tree content", e2);
        }
    }
    
    public XmlElement parseStartTag(final XmlPullParser pp) {
        try {
            if (pp.getEventType() != 2) {
                throw new XmlBuilderException("parser must be on START_TAG and not " + pp.getPositionDescription());
            }
            final XmlElement el = new XmlElementImpl(pp.getNamespace(), pp.getName());
            for (int i = pp.getNamespaceCount(pp.getDepth() - 1); i < pp.getNamespaceCount(pp.getDepth()); ++i) {
                final String prefix = pp.getNamespacePrefix(i);
                el.declareNamespace((prefix == null) ? "" : prefix, pp.getNamespaceUri(i));
            }
            for (int j = 0; j < pp.getAttributeCount(); ++j) {
                el.addAttribute(pp.getAttributeType(j), pp.getAttributePrefix(j), pp.getAttributeNamespace(j), pp.getAttributeName(j), pp.getAttributeValue(j), !pp.isAttributeDefault(j));
            }
            return el;
        }
        catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML start tag", e);
        }
    }
    
    public XmlDocument parseLocation(final String locationUrl) {
        URL url = null;
        try {
            url = new URL(locationUrl);
        }
        catch (MalformedURLException e) {
            throw new XmlBuilderException("could not parse URL " + locationUrl, e);
        }
        try {
            return this.parseInputStream(url.openStream());
        }
        catch (IOException e2) {
            throw new XmlBuilderException("could not open connection to URL " + locationUrl, e2);
        }
    }
    
    public void serialize(final Object item, final XmlSerializer serializer) {
        if (item instanceof XmlContainer) {
            this.serializeContainer((XmlContainer)item, serializer);
        }
        else {
            this.serializeItem(item, serializer);
        }
    }
    
    private void serializeContainer(final XmlContainer node, final XmlSerializer serializer) {
        if (node instanceof XmlSerializable) {
            ((XmlSerializable)node).serialize(serializer);
        }
        else if (node instanceof XmlDocument) {
            this.serializeDocument((XmlDocument)node, serializer);
        }
        else {
            if (!(node instanceof XmlElement)) {
                throw new IllegalArgumentException("could not serialzie unknown XML container " + node.getClass());
            }
            this.serializeFragment((XmlElement)node, serializer);
        }
    }
    
    public void serializeItem(final Object item, final XmlSerializer ser) {
        try {
            if (item instanceof XmlSerializable) {
                ((XmlSerializable)item).serialize(ser);
            }
            else {
                if (!(item instanceof String)) {
                    throw new IllegalArgumentException("could not serialize " + item.getClass());
                }
                ser.text(item.toString());
            }
        }
        catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }
    
    public void serializeStartTag(final XmlElement el, final XmlSerializer ser) {
        try {
            if (el.hasNamespaceDeclarations()) {
                final Iterator iter = el.namespaces();
                while (iter.hasNext()) {
                    final XmlNamespace n = iter.next();
                    ser.setPrefix(n.getPrefix(), n.getNamespaceName());
                }
            }
            ser.startTag(el.getNamespaceName(), el.getName());
            if (el.hasAttributes()) {
                final Iterator iter = el.attributes();
                while (iter.hasNext()) {
                    final XmlAttribute a = iter.next();
                    ser.attribute(a.getNamespaceName(), a.getName(), a.getValue());
                }
            }
        }
        catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }
    
    public void serializeEndTag(final XmlElement el, final XmlSerializer ser) {
        try {
            ser.endTag(el.getNamespaceName(), el.getName());
        }
        catch (IOException e) {
            throw new XmlBuilderException("serializing XML end tag failed", e);
        }
    }
    
    private void serializeDocument(final XmlDocument doc, final XmlSerializer ser) {
        try {
            ser.startDocument(doc.getCharacterEncodingScheme(), doc.isStandalone());
        }
        catch (IOException e) {
            throw new XmlBuilderException("serialziing XML document start failed", e);
        }
        this.serializeFragment(doc.getDocumentElement(), ser);
        try {
            ser.endDocument();
        }
        catch (IOException e) {
            throw new XmlBuilderException("serializing XML document end failed", e);
        }
    }
    
    private void serializeFragment(final XmlElement el, final XmlSerializer ser) {
        this.serializeStartTag(el, ser);
        if (el.hasChildren()) {
            final Iterator iter = el.children();
            while (iter.hasNext()) {
                final Object child = iter.next();
                if (child instanceof XmlSerializable) {
                    ((XmlSerializable)child).serialize(ser);
                }
                else if (child instanceof XmlElement) {
                    this.serializeFragment((XmlElement)child, ser);
                }
                else {
                    this.serializeItem(child, ser);
                }
            }
        }
        this.serializeEndTag(el, ser);
    }
}
