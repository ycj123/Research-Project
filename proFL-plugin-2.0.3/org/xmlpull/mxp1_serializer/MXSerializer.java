// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.mxp1_serializer;

import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Writer;
import org.xmlpull.v1.XmlSerializer;

public class MXSerializer implements XmlSerializer
{
    protected static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    protected static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    private static final boolean TRACE_SIZING = false;
    protected final String FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE = "http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe";
    protected final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    protected final String PROPERTY_SERIALIZER_INDENTATION = "http://xmlpull.org/v1/doc/properties.html#serializer-indentation";
    protected final String PROPERTY_SERIALIZER_LINE_SEPARATOR = "http://xmlpull.org/v1/doc/properties.html#serializer-line-separator";
    protected boolean namesInterned;
    protected boolean attributeUseApostrophe;
    protected String indentationString;
    protected String lineSeparator;
    protected Writer out;
    protected int autoDeclaredPrefixes;
    protected int depth;
    protected String[] elNamespace;
    protected String[] elName;
    protected int[] elNamespaceCount;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;
    protected boolean finished;
    protected boolean pastRoot;
    protected boolean setPrefixCalled;
    protected boolean startTagIncomplete;
    protected boolean doIndent;
    protected boolean seenTag;
    protected static final String[] precomputedPrefixes;
    private boolean checkNamesInterned;
    protected int offsetNewLine;
    protected int indentationJump;
    protected char[] indentationBuf;
    protected int maxIndentLevel;
    protected boolean writeLineSepartor;
    protected boolean writeIndentation;
    
    public MXSerializer() {
        this.indentationString = null;
        this.lineSeparator = "\n";
        this.depth = 0;
        this.elNamespace = new String[2];
        this.elName = new String[this.elNamespace.length];
        this.elNamespaceCount = new int[this.elNamespace.length];
        this.namespaceEnd = 0;
        this.namespacePrefix = new String[8];
        this.namespaceUri = new String[this.namespacePrefix.length];
        this.checkNamesInterned = false;
    }
    
    private void checkInterning(final String name) {
        if (this.namesInterned && name != name.intern()) {
            throw new IllegalArgumentException("all names passed as arguments must be internedwhen NAMES INTERNED feature is enabled");
        }
    }
    
    protected void reset() {
        this.out = null;
        this.autoDeclaredPrefixes = 0;
        this.depth = 0;
        for (int i = 0; i < this.elNamespaceCount.length; ++i) {
            this.elName[i] = null;
            this.elNamespace[i] = null;
            this.elNamespaceCount[i] = 2;
        }
        this.namespaceEnd = 0;
        this.namespacePrefix[this.namespaceEnd] = "xmlns";
        this.namespaceUri[this.namespaceEnd] = "http://www.w3.org/2000/xmlns/";
        ++this.namespaceEnd;
        this.namespacePrefix[this.namespaceEnd] = "xml";
        this.namespaceUri[this.namespaceEnd] = "http://www.w3.org/XML/1998/namespace";
        ++this.namespaceEnd;
        this.finished = false;
        this.pastRoot = false;
        this.setPrefixCalled = false;
        this.startTagIncomplete = false;
        this.seenTag = false;
    }
    
    protected void ensureElementsCapacity() {
        final int elStackSize = this.elName.length;
        final int newSize = ((this.depth >= 7) ? (2 * this.depth) : 8) + 2;
        final boolean needsCopying = elStackSize > 0;
        String[] arr = null;
        arr = new String[newSize];
        if (needsCopying) {
            System.arraycopy(this.elName, 0, arr, 0, elStackSize);
        }
        this.elName = arr;
        arr = new String[newSize];
        if (needsCopying) {
            System.arraycopy(this.elNamespace, 0, arr, 0, elStackSize);
        }
        this.elNamespace = arr;
        final int[] iarr = new int[newSize];
        if (needsCopying) {
            System.arraycopy(this.elNamespaceCount, 0, iarr, 0, elStackSize);
        }
        else {
            iarr[0] = 0;
        }
        this.elNamespaceCount = iarr;
    }
    
    protected void ensureNamespacesCapacity() {
        final int newSize = (this.namespaceEnd > 7) ? (2 * this.namespaceEnd) : 8;
        final String[] newNamespacePrefix = new String[newSize];
        final String[] newNamespaceUri = new String[newSize];
        if (this.namespacePrefix != null) {
            System.arraycopy(this.namespacePrefix, 0, newNamespacePrefix, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceUri, 0, newNamespaceUri, 0, this.namespaceEnd);
        }
        this.namespacePrefix = newNamespacePrefix;
        this.namespaceUri = newNamespaceUri;
    }
    
    public void setFeature(final String name, final boolean state) throws IllegalArgumentException, IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException("feature name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(name)) {
            this.namesInterned = state;
        }
        else {
            if (!"http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(name)) {
                throw new IllegalStateException("unsupported feature " + name);
            }
            this.attributeUseApostrophe = state;
        }
    }
    
    public boolean getFeature(final String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("feature name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(name)) {
            return this.namesInterned;
        }
        return "http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(name) && this.attributeUseApostrophe;
    }
    
    protected void rebuildIndentationBuf() {
        if (!this.doIndent) {
            return;
        }
        final int maxIndent = 65;
        int bufSize = 0;
        this.offsetNewLine = 0;
        if (this.writeLineSepartor) {
            this.offsetNewLine = this.lineSeparator.length();
            bufSize += this.offsetNewLine;
        }
        this.maxIndentLevel = 0;
        if (this.writeIndentation) {
            this.indentationJump = this.indentationString.length();
            this.maxIndentLevel = 65 / this.indentationJump;
            bufSize += this.maxIndentLevel * this.indentationJump;
        }
        if (this.indentationBuf == null || this.indentationBuf.length < bufSize) {
            this.indentationBuf = new char[bufSize + 8];
        }
        int bufPos = 0;
        if (this.writeLineSepartor) {
            for (int i = 0; i < this.lineSeparator.length(); ++i) {
                this.indentationBuf[bufPos++] = this.lineSeparator.charAt(i);
            }
        }
        if (this.writeIndentation) {
            for (int i = 0; i < this.maxIndentLevel; ++i) {
                for (int j = 0; j < this.indentationString.length(); ++j) {
                    this.indentationBuf[bufPos++] = this.indentationString.charAt(j);
                }
            }
        }
    }
    
    protected void writeIndent() throws IOException {
        final int start = this.writeLineSepartor ? 0 : this.offsetNewLine;
        final int level = (this.depth > this.maxIndentLevel) ? this.maxIndentLevel : this.depth;
        this.out.write(this.indentationBuf, start, level * this.indentationJump + this.offsetNewLine);
    }
    
    public void setProperty(final String name, final Object value) throws IllegalArgumentException, IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException("property name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(name)) {
            this.indentationString = (String)value;
        }
        else {
            if (!"http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(name)) {
                throw new IllegalStateException("unsupported property " + name);
            }
            this.lineSeparator = (String)value;
        }
        this.writeLineSepartor = (this.lineSeparator != null && this.lineSeparator.length() > 0);
        this.writeIndentation = (this.indentationString != null && this.indentationString.length() > 0);
        this.doIndent = (this.indentationString != null && (this.writeLineSepartor || this.writeIndentation));
        this.rebuildIndentationBuf();
        this.seenTag = false;
    }
    
    public Object getProperty(final String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("property name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(name)) {
            return this.indentationString;
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(name)) {
            return this.lineSeparator;
        }
        return null;
    }
    
    public void setOutput(final Writer writer) {
        this.reset();
        this.out = writer;
    }
    
    public void setOutput(final OutputStream os, final String encoding) throws IOException {
        if (os == null) {
            throw new IllegalArgumentException("output stream can not be null");
        }
        this.reset();
        if (encoding != null) {
            this.out = new OutputStreamWriter(os, encoding);
        }
        else {
            this.out = new OutputStreamWriter(os);
        }
    }
    
    public void startDocument(final String encoding, final Boolean standalone) throws IOException {
        this.out.write("<?xml version=\"1.0\"");
        if (encoding != null) {
            this.out.write(" encoding='");
            this.out.write(encoding);
            this.out.write(39);
        }
        if (standalone != null) {
            if (standalone) {
                this.out.write(" standalone='yes'");
            }
            else {
                this.out.write(" standalone='no'");
            }
        }
        this.out.write("?>");
    }
    
    public void endDocument() throws IOException {
        while (this.depth > 0) {
            this.endTag(this.elNamespace[this.depth], this.elName[this.depth]);
        }
        final boolean finished = true;
        this.startTagIncomplete = finished;
        this.pastRoot = finished;
        this.finished = finished;
        this.out.flush();
    }
    
    public void setPrefix(String prefix, String namespace) throws IOException {
        if (this.startTagIncomplete) {
            this.closeStartTag();
        }
        if (prefix == null) {
            prefix = "";
        }
        if (!this.namesInterned) {
            prefix = prefix.intern();
        }
        else if (this.checkNamesInterned) {
            this.checkInterning(prefix);
        }
        else if (prefix == null) {
            throw new IllegalArgumentException("prefix must be not null");
        }
        for (int i = this.elNamespaceCount[this.depth]; i < this.namespaceEnd; ++i) {
            if (prefix == this.namespacePrefix[i]) {
                throw new IllegalStateException("duplicated prefix " + printable(prefix));
            }
        }
        if (!this.namesInterned) {
            namespace = namespace.intern();
        }
        else if (this.checkNamesInterned) {
            this.checkInterning(namespace);
        }
        else if (namespace == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            this.ensureNamespacesCapacity();
        }
        this.namespacePrefix[this.namespaceEnd] = prefix;
        this.namespaceUri[this.namespaceEnd] = namespace;
        ++this.namespaceEnd;
        this.setPrefixCalled = true;
    }
    
    protected String lookupOrDeclarePrefix(final String namespace) {
        return this.getPrefix(namespace, true);
    }
    
    public String getPrefix(String namespace, final boolean generatePrefix) {
        if (!this.namesInterned) {
            namespace = namespace.intern();
        }
        else if (this.checkNamesInterned) {
            this.checkInterning(namespace);
        }
        else if (namespace == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        for (int i = this.namespaceEnd - 1; i >= 0; --i) {
            if (namespace == this.namespaceUri[i]) {
                final String prefix = this.namespacePrefix[i];
                for (int p = this.namespaceEnd - 1; p > i; --p) {
                    if (prefix == this.namespacePrefix[p]) {}
                }
                return prefix;
            }
        }
        if (!generatePrefix) {
            return null;
        }
        return this.generatePrefix(namespace);
    }
    
    private String generatePrefix(final String namespace) {
        ++this.autoDeclaredPrefixes;
        final String prefix = (this.autoDeclaredPrefixes < MXSerializer.precomputedPrefixes.length) ? MXSerializer.precomputedPrefixes[this.autoDeclaredPrefixes] : ("n" + this.autoDeclaredPrefixes).intern();
        for (int i = this.namespaceEnd - 1; i >= 0; --i) {
            if (prefix == this.namespacePrefix[i]) {}
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            this.ensureNamespacesCapacity();
        }
        this.namespacePrefix[this.namespaceEnd] = prefix;
        this.namespaceUri[this.namespaceEnd] = namespace;
        ++this.namespaceEnd;
        return prefix;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public String getNamespace() {
        return this.elNamespace[this.depth];
    }
    
    public String getName() {
        return this.elName[this.depth];
    }
    
    public XmlSerializer startTag(final String namespace, final String name) throws IOException {
        if (this.startTagIncomplete) {
            this.closeStartTag();
        }
        if (this.doIndent && this.depth > 0 && this.seenTag) {
            this.writeIndent();
        }
        this.seenTag = true;
        this.setPrefixCalled = false;
        this.startTagIncomplete = true;
        ++this.depth;
        if (this.depth + 1 >= this.elName.length) {
            this.ensureElementsCapacity();
        }
        if (this.checkNamesInterned && this.namesInterned) {
            this.checkInterning(namespace);
        }
        this.elNamespace[this.depth] = ((this.namesInterned || namespace == null) ? namespace : namespace.intern());
        if (this.checkNamesInterned && this.namesInterned) {
            this.checkInterning(name);
        }
        this.elName[this.depth] = ((this.namesInterned || name == null) ? name : name.intern());
        this.out.write(60);
        if (namespace != null) {
            if (namespace.length() > 0) {
                final String prefix = this.lookupOrDeclarePrefix(namespace);
                if (prefix.length() > 0) {
                    this.out.write(prefix);
                    this.out.write(58);
                }
            }
            else {
                int i = this.namespaceEnd - 1;
                while (i >= 0) {
                    if (this.namespacePrefix[i] == "") {
                        final String uri = this.namespaceUri[i];
                        if (uri == null) {
                            this.setPrefix("", "");
                            break;
                        }
                        if (uri.length() > 0) {
                            throw new IllegalStateException("start tag can not be written in empty default namespace as default namespace is currently bound to '" + uri + "'");
                        }
                        break;
                    }
                    else {
                        --i;
                    }
                }
            }
        }
        this.out.write(name);
        return this;
    }
    
    public XmlSerializer attribute(String namespace, final String name, final String value) throws IOException {
        if (!this.startTagIncomplete) {
            throw new IllegalArgumentException("startTag() must be called before attribute()");
        }
        this.out.write(32);
        if (namespace != null && namespace.length() > 0) {
            if (!this.namesInterned) {
                namespace = namespace.intern();
            }
            else if (this.checkNamesInterned) {
                this.checkInterning(namespace);
            }
            String prefix = this.lookupOrDeclarePrefix(namespace);
            if (prefix.length() == 0) {
                prefix = this.generatePrefix(namespace);
            }
            this.out.write(prefix);
            this.out.write(58);
        }
        this.out.write(name);
        this.out.write(61);
        this.out.write(this.attributeUseApostrophe ? 39 : 34);
        this.writeAttributeValue(value, this.out);
        this.out.write(this.attributeUseApostrophe ? 39 : 34);
        return this;
    }
    
    protected void closeStartTag() throws IOException {
        if (this.finished) {
            throw new IllegalArgumentException("trying to write past already finished output");
        }
        if (this.setPrefixCalled) {
            throw new IllegalArgumentException("startTag() must be called immediately after setPrefix()");
        }
        if (!this.startTagIncomplete) {
            throw new IllegalArgumentException("trying to close start tag that is not opened");
        }
        this.writeNamespaceDeclarations();
        this.out.write(62);
        this.elNamespaceCount[this.depth] = this.namespaceEnd;
        this.startTagIncomplete = false;
    }
    
    private void writeNamespaceDeclarations() throws IOException {
        for (int i = this.elNamespaceCount[this.depth - 1]; i < this.namespaceEnd; ++i) {
            if (this.namespacePrefix[i] != "") {
                this.out.write(" xmlns:");
                this.out.write(this.namespacePrefix[i]);
                this.out.write(61);
            }
            else {
                this.out.write(" xmlns=");
            }
            this.out.write(this.attributeUseApostrophe ? 39 : 34);
            this.writeAttributeValue(this.namespaceUri[i], this.out);
            this.out.write(this.attributeUseApostrophe ? 39 : 34);
        }
    }
    
    public XmlSerializer endTag(String namespace, final String name) throws IOException {
        if (namespace != null) {
            if (!this.namesInterned) {
                namespace = namespace.intern();
            }
            else if (this.checkNamesInterned) {
                this.checkInterning(namespace);
            }
        }
        if (namespace != this.elNamespace[this.depth]) {
            throw new IllegalArgumentException("expected namespace " + printable(this.elNamespace[this.depth]) + " and not " + printable(namespace));
        }
        if (name == null) {
            throw new IllegalArgumentException("end tag name can not be null");
        }
        if (this.checkNamesInterned && this.namesInterned) {
            this.checkInterning(name);
        }
        if ((!this.namesInterned && !name.equals(this.elName[this.depth])) || (this.namesInterned && name != this.elName[this.depth])) {
            throw new IllegalArgumentException("expected element name " + printable(this.elName[this.depth]) + " and not " + printable(name));
        }
        if (this.startTagIncomplete) {
            this.writeNamespaceDeclarations();
            this.out.write(" />");
            --this.depth;
        }
        else {
            --this.depth;
            if (this.doIndent && this.seenTag) {
                this.writeIndent();
            }
            this.out.write("</");
            if (namespace != null && namespace.length() > 0) {
                final String prefix = this.lookupOrDeclarePrefix(namespace);
                if (prefix.length() > 0) {
                    this.out.write(prefix);
                    this.out.write(58);
                }
            }
            this.out.write(name);
            this.out.write(62);
        }
        this.namespaceEnd = this.elNamespaceCount[this.depth];
        this.startTagIncomplete = false;
        this.seenTag = true;
        return this;
    }
    
    public XmlSerializer text(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.writeElementContent(text, this.out);
        return this;
    }
    
    public XmlSerializer text(final char[] buf, final int start, final int len) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.writeElementContent(buf, start, len, this.out);
        return this;
    }
    
    public void cdsect(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<![CDATA[");
        this.out.write(text);
        this.out.write("]]>");
    }
    
    public void entityRef(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(38);
        this.out.write(text);
        this.out.write(59);
    }
    
    public void processingInstruction(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<?");
        this.out.write(text);
        this.out.write("?>");
    }
    
    public void comment(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!--");
        this.out.write(text);
        this.out.write("-->");
    }
    
    public void docdecl(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!DOCTYPE");
        this.out.write(text);
        this.out.write(">");
    }
    
    public void ignorableWhitespace(final String text) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            this.closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(text);
    }
    
    public void flush() throws IOException {
        if (this.startTagIncomplete) {
            this.closeStartTag();
        }
        this.out.flush();
    }
    
    protected void writeAttributeValue(final String value, final Writer out) throws IOException {
        final char quot = this.attributeUseApostrophe ? '\'' : '\"';
        final String quotEntity = this.attributeUseApostrophe ? "&apos;" : "&quot;";
        int posLt = value.indexOf(60);
        int posAmp = value.indexOf(38);
        int posQuot = value.indexOf(quot);
        int pos = 0;
        while (posLt != -1 || posAmp != -1 || posQuot != -1) {
            if (posQuot != -1 && (posAmp == -1 || (posAmp != -1 && posQuot < posAmp)) && (posLt == -1 || (posLt != -1 && posQuot < posLt))) {
                if (pos < posQuot) {
                    out.write(value.substring(pos, posQuot));
                }
                out.write(quotEntity);
                pos = posQuot + 1;
                posQuot = value.indexOf(quot, pos);
            }
            else if (posAmp != -1 && (posQuot == -1 || (posQuot != -1 && posAmp < posQuot)) && (posLt == -1 || (posLt != -1 && posAmp < posLt))) {
                if (pos < posAmp) {
                    out.write(value.substring(pos, posAmp));
                }
                out.write("&amp;");
                pos = posAmp + 1;
                posAmp = value.indexOf(38, pos);
            }
            else {
                if (posLt == -1 || (posQuot != -1 && (posQuot == -1 || posLt >= posQuot)) || (posAmp != -1 && (posAmp == -1 || posLt >= posAmp))) {
                    throw new IllegalStateException("wrong state #1 posLt=" + posLt + " posAmp=" + posAmp + " posQuot=" + posQuot + " for " + value);
                }
                if (pos < posLt) {
                    out.write(value.substring(pos, posLt));
                }
                out.write("&lt;");
                pos = posLt + 1;
                posLt = value.indexOf(60, pos);
            }
        }
        if (pos > 0) {
            out.write(value.substring(pos));
        }
        else {
            out.write(value);
        }
    }
    
    protected void writeElementContent(final String text, final Writer out) throws IOException {
        int posLt = text.indexOf(60);
        int posAmp = text.indexOf(38);
        int pos = 0;
        while (posLt != -1 || posAmp != -1) {
            if (posLt == -1 || (posLt != -1 && posAmp != -1 && posAmp < posLt)) {
                if (pos < posAmp) {
                    out.write(text.substring(pos, posAmp));
                }
                out.write("&amp;");
                pos = posAmp + 1;
                posAmp = text.indexOf(38, pos);
            }
            else {
                if (posAmp != -1 && (posLt == -1 || posAmp == -1 || posLt >= posAmp)) {
                    throw new IllegalStateException("wrong state posLt=" + posLt + " posAmp=" + posAmp + " for " + text);
                }
                if (pos < posLt) {
                    out.write(text.substring(pos, posLt));
                }
                out.write("&lt;");
                pos = posLt + 1;
                posLt = text.indexOf(60, pos);
            }
        }
        out.write(text.substring(pos));
    }
    
    protected void writeElementContent(final char[] buf, final int off, final int len, final Writer out) throws IOException {
        final int end = off + len;
        int pos = off;
        for (int i = off; i < end; ++i) {
            final char ch = buf[i];
            if (ch == '&') {
                if (i > pos) {
                    out.write(buf, pos, i - pos);
                }
                out.write("&amp;");
                pos = i + 1;
            }
            else if (ch == '<') {
                if (i > pos) {
                    out.write(buf, pos, i - pos);
                }
                out.write("&lt;");
                pos = i + 1;
            }
        }
        if (end > pos) {
            out.write(buf, pos, end - pos);
        }
    }
    
    protected static final String printable(final String s) {
        if (s == null) {
            return "null";
        }
        final StringBuffer retval = new StringBuffer(s.length() + 16);
        retval.append("'");
        for (int i = 0; i < s.length(); ++i) {
            addPrintable(retval, s.charAt(i));
        }
        retval.append("'");
        return retval.toString();
    }
    
    protected static final String printable(final char ch) {
        final StringBuffer retval = new StringBuffer();
        addPrintable(retval, ch);
        return retval.toString();
    }
    
    private static void addPrintable(final StringBuffer retval, final char ch) {
        switch (ch) {
            case '\b': {
                retval.append("\\b");
                break;
            }
            case '\t': {
                retval.append("\\t");
                break;
            }
            case '\n': {
                retval.append("\\n");
                break;
            }
            case '\f': {
                retval.append("\\f");
                break;
            }
            case '\r': {
                retval.append("\\r");
                break;
            }
            case '\"': {
                retval.append("\\\"");
                break;
            }
            case '\'': {
                retval.append("\\'");
                break;
            }
            case '\\': {
                retval.append("\\\\");
                break;
            }
            default: {
                if (ch < ' ' || ch > '~') {
                    final String ss = "0000" + Integer.toString(ch, 16);
                    retval.append("\\u" + ss.substring(ss.length() - 4, ss.length()));
                    break;
                }
                retval.append(ch);
                break;
            }
        }
    }
    
    static {
        precomputedPrefixes = new String[32];
        for (int i = 0; i < MXSerializer.precomputedPrefixes.length; ++i) {
            MXSerializer.precomputedPrefixes[i] = ("n" + i).intern();
        }
    }
}
