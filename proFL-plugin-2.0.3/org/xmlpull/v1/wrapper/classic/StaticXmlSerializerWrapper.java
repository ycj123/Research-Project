// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlPullParser;
import java.io.Reader;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;
import org.xmlpull.v1.wrapper.XmlPullWrapperFactory;
import org.xmlpull.v1.wrapper.XmlSerializerWrapper;

public class StaticXmlSerializerWrapper extends XmlSerializerDelegate implements XmlSerializerWrapper
{
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/features.html#xmldecl-standalone";
    private static final boolean TRACE_SIZING = false;
    protected String currentNs;
    protected XmlPullWrapperFactory wf;
    protected XmlPullParserWrapper fragmentParser;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;
    protected int[] namespaceDepth;
    
    public StaticXmlSerializerWrapper(final XmlSerializer xs, final XmlPullWrapperFactory wf) {
        super(xs);
        this.namespaceEnd = 0;
        this.namespacePrefix = new String[8];
        this.namespaceUri = new String[this.namespacePrefix.length];
        this.namespaceDepth = new int[this.namespacePrefix.length];
        this.wf = wf;
    }
    
    public String getCurrentNamespaceForElements() {
        return this.currentNs;
    }
    
    public String setCurrentNamespaceForElements(final String value) {
        final String old = this.currentNs;
        this.currentNs = value;
        return old;
    }
    
    public XmlSerializerWrapper attribute(final String name, final String value) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.attribute(null, name, value);
        return this;
    }
    
    public XmlSerializerWrapper startTag(final String name) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.startTag(this.currentNs, name);
        return this;
    }
    
    public XmlSerializerWrapper endTag(final String name) throws IOException, IllegalArgumentException, IllegalStateException {
        this.endTag(this.currentNs, name);
        return this;
    }
    
    public XmlSerializerWrapper element(final String elementName, final String elementText) throws IOException, XmlPullParserException {
        return this.element(this.currentNs, elementName, elementText);
    }
    
    public XmlSerializerWrapper element(final String namespace, final String elementName, final String elementText) throws IOException, XmlPullParserException {
        if (elementName == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        this.xs.startTag(namespace, elementName);
        if (elementText == null) {
            this.xs.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
        }
        else {
            this.xs.text(elementText);
        }
        this.xs.endTag(namespace, elementName);
        return this;
    }
    
    private void ensureNamespacesCapacity() {
        final int newSize = (this.namespaceEnd > 7) ? (2 * this.namespaceEnd) : 8;
        final String[] newNamespacePrefix = new String[newSize];
        final String[] newNamespaceUri = new String[newSize];
        final int[] newNamespaceDepth = new int[newSize];
        if (this.namespacePrefix != null) {
            System.arraycopy(this.namespacePrefix, 0, newNamespacePrefix, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceUri, 0, newNamespaceUri, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceDepth, 0, newNamespaceDepth, 0, this.namespaceEnd);
        }
        this.namespacePrefix = newNamespacePrefix;
        this.namespaceUri = newNamespaceUri;
        this.namespaceDepth = newNamespaceDepth;
    }
    
    public void setPrefix(final String prefix, final String namespace) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.setPrefix(prefix, namespace);
        for (int depth = this.getDepth(), pos = this.namespaceEnd - 1; pos >= 0 && this.namespaceDepth[pos] > depth; --pos) {
            --this.namespaceEnd;
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            this.ensureNamespacesCapacity();
        }
        this.namespacePrefix[this.namespaceEnd] = prefix;
        this.namespaceUri[this.namespaceEnd] = namespace;
        ++this.namespaceEnd;
    }
    
    public void fragment(final String xmlFragment) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException {
        final StringBuffer buf = new StringBuffer(xmlFragment.length() + this.namespaceEnd * 30);
        buf.append("<fragment");
        int pos = this.namespaceEnd - 1;
    Label_0142:
        while (pos >= 0) {
            final String prefix = this.namespacePrefix[pos];
            while (true) {
                for (int i = this.namespaceEnd - 1; i > pos; --i) {
                    if (prefix.equals(this.namespacePrefix[i])) {
                        --pos;
                        continue Label_0142;
                    }
                }
                buf.append(" xmlns");
                if (prefix.length() > 0) {
                    buf.append(':').append(prefix);
                }
                buf.append("='");
                buf.append(this.escapeAttributeValue(this.namespaceUri[pos]));
                buf.append("'");
                continue;
            }
        }
        buf.append(">");
        buf.append(xmlFragment);
        buf.append("</fragment>");
        if (this.fragmentParser == null) {
            this.fragmentParser = this.wf.newPullParserWrapper();
        }
        final String s = buf.toString();
        this.fragmentParser.setInput(new StringReader(s));
        this.fragmentParser.nextTag();
        this.fragmentParser.require(2, null, "fragment");
        while (true) {
            this.fragmentParser.nextToken();
            if (this.fragmentParser.getDepth() == 1 && this.fragmentParser.getEventType() == 3) {
                break;
            }
            this.event(this.fragmentParser);
        }
        this.fragmentParser.require(3, null, "fragment");
    }
    
    public void event(final XmlPullParser pp) throws XmlPullParserException, IOException {
        final int eventType = pp.getEventType();
        switch (eventType) {
            case 0: {
                final Boolean standalone = (Boolean)pp.getProperty("http://xmlpull.org/v1/doc/features.html#xmldecl-standalone");
                this.startDocument(pp.getInputEncoding(), standalone);
                break;
            }
            case 1: {
                this.endDocument();
                break;
            }
            case 2: {
                this.writeStartTag(pp);
                break;
            }
            case 3: {
                this.endTag(pp.getNamespace(), pp.getName());
                break;
            }
            case 7: {
                final String s = pp.getText();
                this.ignorableWhitespace(s);
                break;
            }
            case 4: {
                if (pp.getDepth() > 0) {
                    this.text(pp.getText());
                    break;
                }
                this.ignorableWhitespace(pp.getText());
                break;
            }
            case 6: {
                this.entityRef(pp.getName());
                break;
            }
            case 5: {
                this.cdsect(pp.getText());
                break;
            }
            case 8: {
                this.processingInstruction(pp.getText());
                break;
            }
            case 9: {
                this.comment(pp.getText());
                break;
            }
            case 10: {
                this.docdecl(pp.getText());
                break;
            }
        }
    }
    
    private void writeStartTag(final XmlPullParser pp) throws XmlPullParserException, IOException {
        if (!pp.getFeature("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes")) {
            for (int i = pp.getNamespaceCount(pp.getDepth() - 1); i < pp.getNamespaceCount(pp.getDepth()); ++i) {
                final String prefix = pp.getNamespacePrefix(i);
                final String ns = pp.getNamespaceUri(i);
                this.setPrefix(prefix, ns);
            }
        }
        this.startTag(pp.getNamespace(), pp.getName());
        for (int i = 0; i < pp.getAttributeCount(); ++i) {
            this.attribute(pp.getAttributeNamespace(i), pp.getAttributeName(i), pp.getAttributeValue(i));
        }
    }
    
    public String escapeAttributeValue(final String value) {
        final int posLt = value.indexOf(60);
        final int posAmp = value.indexOf(38);
        final int posQuot = value.indexOf(34);
        final int posApos = value.indexOf(39);
        if (posLt == -1 && posAmp == -1 && posQuot == -1 && posApos == -1) {
            return value;
        }
        final StringBuffer buf = new StringBuffer(value.length() + 10);
        for (int pos = 0, len = value.length(); pos < len; ++pos) {
            final char ch = value.charAt(pos);
            switch (ch) {
                case '<': {
                    buf.append("&lt;");
                    break;
                }
                case '&': {
                    buf.append("&amp;");
                    break;
                }
                case '\'': {
                    buf.append("&apos;");
                    break;
                }
                case '\"': {
                    buf.append("&quot;");
                    break;
                }
                default: {
                    buf.append(ch);
                    break;
                }
            }
        }
        return buf.toString();
    }
    
    public String escapeText(final String text) {
        int posLt = text.indexOf(60);
        int posAmp = text.indexOf(38);
        if (posLt == -1 && posAmp == -1) {
            return text;
        }
        final StringBuffer buf = new StringBuffer(text.length() + 10);
        int pos = 0;
        while (posLt != -1 || posAmp != -1) {
            if (posLt == -1 || (posLt != -1 && posAmp != -1 && posAmp < posLt)) {
                if (pos < posAmp) {
                    buf.append(text.substring(pos, posAmp));
                }
                buf.append("&amp;");
                pos = posAmp + 1;
                posAmp = text.indexOf(38, pos);
            }
            else {
                if (posAmp != -1 && (posLt == -1 || posAmp == -1 || posLt >= posAmp)) {
                    throw new IllegalStateException("wrong state posLt=" + posLt + " posAmp=" + posAmp + " for " + text);
                }
                if (pos < posLt) {
                    buf.append(text.substring(pos, posLt));
                }
                buf.append("&lt;");
                pos = posLt + 1;
                posLt = text.indexOf(60, pos);
            }
        }
        buf.append(text.substring(pos));
        return buf.toString();
    }
}
