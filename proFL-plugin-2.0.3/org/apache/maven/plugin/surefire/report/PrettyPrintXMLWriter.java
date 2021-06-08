// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.util.LinkedList;
import java.io.PrintWriter;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.XMLWriter;

public class PrettyPrintXMLWriter implements XMLWriter
{
    private final PrintWriter writer;
    private final LinkedList<String> elementStack;
    private boolean tagInProgress;
    private int depth;
    private final String lineIndenter;
    private final String encoding;
    private final String docType;
    private boolean readyForNewLine;
    private boolean tagIsEmpty;
    
    public PrettyPrintXMLWriter(final PrintWriter writer) {
        this(writer, null, null);
    }
    
    private PrettyPrintXMLWriter(final PrintWriter writer, final String lineIndenter, final String encoding, final String doctype) {
        this.elementStack = new LinkedList<String>();
        this.writer = writer;
        this.lineIndenter = lineIndenter;
        this.encoding = encoding;
        this.docType = doctype;
        if (this.docType != null || encoding != null) {
            this.writeDocumentHeaders();
        }
    }
    
    public void setEncoding(final String encoding) {
        throw new RuntimeException("Not Implemented");
    }
    
    public void setDocType(final String docType) {
        throw new RuntimeException("Not Implemented");
    }
    
    private PrettyPrintXMLWriter(final PrintWriter writer, final String encoding, final String doctype) {
        this(writer, "  ", encoding, doctype);
    }
    
    public void startElement(final String name) {
        this.tagIsEmpty = false;
        this.finishTag();
        this.write("<");
        this.write(name);
        this.elementStack.addLast(name);
        this.tagInProgress = true;
        ++this.depth;
        this.readyForNewLine = true;
        this.tagIsEmpty = true;
    }
    
    public void writeText(final String text) {
        this.writeText(text, true);
    }
    
    public void writeMarkup(final String text) {
        this.writeText(text, false);
    }
    
    private void writeText(String text, final boolean escapeXml) {
        this.readyForNewLine = false;
        this.tagIsEmpty = false;
        this.finishTag();
        if (escapeXml) {
            text = escapeXml(text);
        }
        this.write(text);
    }
    
    private static String escapeXml(final String text) {
        final StringBuffer sb = new StringBuffer(text.length() * 2);
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            if (c < ' ') {
                if (c == '\n' || c == '\r' || c == '\t') {
                    sb.append(c);
                }
                else {
                    sb.append("&amp;#").append((int)c).append(';');
                }
            }
            else if (c == '<') {
                sb.append("&lt;");
            }
            else if (c == '>') {
                sb.append("&gt;");
            }
            else if (c == '&') {
                sb.append("&amp;");
            }
            else if (c == '\"') {
                sb.append("&quot;");
            }
            else if (c == '\'') {
                sb.append("&apos;");
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public void addAttribute(final String key, final String value) {
        this.write(" ");
        this.write(key);
        this.write("=\"");
        this.write(escapeXml(value));
        this.write("\"");
    }
    
    public void endElement() {
        --this.depth;
        if (this.tagIsEmpty) {
            this.write("/");
            this.readyForNewLine = false;
            this.finishTag();
            this.elementStack.removeLast();
        }
        else {
            this.finishTag();
            this.write("</" + this.elementStack.removeLast() + ">");
        }
        this.readyForNewLine = true;
    }
    
    private void write(final String str) {
        this.writer.write(str);
    }
    
    private void finishTag() {
        if (this.tagInProgress) {
            this.write(">");
        }
        this.tagInProgress = false;
        if (this.readyForNewLine) {
            this.endOfLine();
        }
        this.readyForNewLine = false;
        this.tagIsEmpty = false;
    }
    
    protected void endOfLine() {
        this.write("\n");
        for (int i = 0; i < this.depth; ++i) {
            this.write(this.lineIndenter);
        }
    }
    
    private void writeDocumentHeaders() {
        this.write("<?xml version=\"1.0\"");
        if (this.encoding != null) {
            this.write(" encoding=\"" + this.encoding + "\"");
        }
        this.write("?>");
        this.endOfLine();
        if (this.docType != null) {
            this.write("<!DOCTYPE ");
            this.write(this.docType);
            this.write(">");
            this.endOfLine();
        }
    }
}
