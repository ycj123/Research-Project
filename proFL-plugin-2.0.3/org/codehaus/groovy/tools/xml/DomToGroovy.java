// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.xml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Element;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import org.w3c.dom.Node;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.codehaus.groovy.syntax.Types;
import java.io.PrintWriter;
import java.util.Collection;
import groovy.util.IndentPrinter;

public class DomToGroovy
{
    protected IndentPrinter out;
    protected boolean inMixed;
    protected String qt;
    protected Collection<String> keywords;
    
    public DomToGroovy(final PrintWriter out) {
        this(new IndentPrinter(out));
    }
    
    public DomToGroovy(final IndentPrinter out) {
        this.inMixed = false;
        this.qt = "'";
        this.keywords = Types.getKeywords();
        this.out = out;
    }
    
    public void print(final Document document) {
        this.printChildren(document, new HashMap());
    }
    
    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: DomToGroovy infilename [outfilename]");
            System.exit(1);
        }
        Document document = null;
        try {
            document = parse(args[0]);
        }
        catch (Exception e) {
            System.out.println("Unable to parse input file '" + args[0] + "': " + e.getMessage());
            System.exit(1);
        }
        PrintWriter writer = null;
        if (args.length < 2) {
            writer = new PrintWriter(System.out);
        }
        else {
            try {
                writer = new PrintWriter(new FileWriter(new File(args[1])));
            }
            catch (IOException e2) {
                System.out.println("Unable to create output file '" + args[1] + "': " + e2.getMessage());
                System.exit(1);
            }
        }
        final DomToGroovy converter = new DomToGroovy(writer);
        converter.out.incrementIndent();
        writer.println("#!/bin/groovy");
        writer.println();
        writer.println("// generated from " + args[0]);
        writer.println("System.out << new groovy.xml.StreamingMarkupBuilder().bind {");
        converter.print(document);
        writer.println("}");
        writer.close();
    }
    
    protected static Document parse(final String fileName) throws Exception {
        return parse(new File(fileName));
    }
    
    public static Document parse(final File file) throws Exception {
        return parse(new BufferedReader(new FileReader(file)));
    }
    
    public static Document parse(final Reader input) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(input));
    }
    
    public static Document parse(final InputStream input) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(input));
    }
    
    protected void print(final Node node, final Map namespaces, final boolean endWithComma) {
        switch (node.getNodeType()) {
            case 1: {
                this.printElement((Element)node, namespaces, endWithComma);
                break;
            }
            case 7: {
                this.printPI((ProcessingInstruction)node, endWithComma);
                break;
            }
            case 3: {
                this.printText((Text)node, endWithComma);
                break;
            }
            case 8: {
                this.printComment((Comment)node, endWithComma);
                break;
            }
        }
    }
    
    protected void printElement(final Element element, Map namespaces, final boolean endWithComma) {
        namespaces = this.defineNamespaces(element, namespaces);
        element.normalize();
        this.printIndent();
        final String prefix = element.getPrefix();
        final boolean hasPrefix = prefix != null && prefix.length() > 0;
        final String localName = this.getLocalName(element);
        final boolean isKeyword = this.checkEscaping(localName);
        if (isKeyword || hasPrefix) {
            this.print(this.qt);
        }
        if (hasPrefix) {
            this.print(prefix);
            this.print(".");
        }
        this.print(localName);
        if (isKeyword || hasPrefix) {
            this.print(this.qt);
        }
        this.print("(");
        final boolean hasAttributes = this.printAttributes(element);
        final NodeList list = element.getChildNodes();
        final int length = list.getLength();
        if (length == 0) {
            this.printEnd(")", endWithComma);
        }
        else {
            Node node = list.item(0);
            if (length == 1 && node instanceof Text) {
                final Text textNode = (Text)node;
                final String text = this.getTextNodeData(textNode);
                if (hasAttributes) {
                    this.print(", ");
                }
                this.printQuoted(text);
                this.printEnd(")", endWithComma);
            }
            else if (this.mixedContent(list)) {
                this.println(") {");
                this.out.incrementIndent();
                final boolean oldInMixed = this.inMixed;
                this.inMixed = true;
                for (node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
                    this.print(node, namespaces, false);
                }
                this.inMixed = oldInMixed;
                this.out.decrementIndent();
                this.printIndent();
                this.printEnd("}", endWithComma);
            }
            else {
                this.println(") {");
                this.out.incrementIndent();
                this.printChildren(element, namespaces);
                this.out.decrementIndent();
                this.printIndent();
                this.printEnd("}", endWithComma);
            }
        }
    }
    
    protected void printQuoted(final String text) {
        if (text.indexOf("\n") != -1) {
            this.print("'''");
            this.print(text);
            this.print("'''");
        }
        else {
            this.print(this.qt);
            this.print(this.escapeQuote(text));
            this.print(this.qt);
        }
    }
    
    protected void printPI(final ProcessingInstruction instruction, final boolean endWithComma) {
        this.printIndent();
        this.print("mkp.pi(" + this.qt);
        this.print(instruction.getTarget());
        this.print(this.qt + ", " + this.qt);
        this.print(instruction.getData());
        this.printEnd(this.qt + ");", endWithComma);
    }
    
    protected void printComment(final Comment comment, final boolean endWithComma) {
        final String text = comment.getData().trim();
        if (text.length() > 0) {
            this.printIndent();
            this.print("/* ");
            this.print(text);
            this.printEnd(" */", endWithComma);
        }
    }
    
    protected void printText(final Text node, final boolean endWithComma) {
        final String text = this.getTextNodeData(node);
        if (text.length() > 0) {
            this.printIndent();
            if (this.inMixed) {
                this.print("mkp.yield ");
            }
            this.printQuoted(text);
            this.printEnd("", endWithComma);
        }
    }
    
    protected String escapeQuote(final String text) {
        return text.replaceAll("\\\\", "\\\\\\\\").replaceAll(this.qt, "\\\\" + this.qt);
    }
    
    protected Map defineNamespaces(final Element element, final Map namespaces) {
        Map answer = null;
        String prefix = element.getPrefix();
        if (prefix != null && prefix.length() > 0 && !namespaces.containsKey(prefix)) {
            answer = new HashMap(namespaces);
            this.defineNamespace(answer, prefix, element.getNamespaceURI());
        }
        final NamedNodeMap attributes = element.getAttributes();
        for (int length = attributes.getLength(), i = 0; i < length; ++i) {
            final Attr attribute = (Attr)attributes.item(i);
            prefix = attribute.getPrefix();
            if (prefix != null && prefix.length() > 0 && !namespaces.containsKey(prefix)) {
                if (answer == null) {
                    answer = new HashMap(namespaces);
                }
                this.defineNamespace(answer, prefix, attribute.getNamespaceURI());
            }
        }
        return (answer != null) ? answer : namespaces;
    }
    
    protected void defineNamespace(final Map namespaces, final String prefix, final String uri) {
        namespaces.put(prefix, uri);
        if (!prefix.equals("xmlns") && !prefix.equals("xml")) {
            this.printIndent();
            this.print("mkp.declareNamespace(");
            this.print(prefix);
            this.print(":" + this.qt);
            this.print(uri);
            this.println(this.qt + ")");
        }
    }
    
    protected boolean printAttributes(final Element element) {
        boolean hasAttribute = false;
        final NamedNodeMap attributes = element.getAttributes();
        final int length = attributes.getLength();
        if (length > 0) {
            final StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < length; ++i) {
                this.printAttributeWithPrefix((Attr)attributes.item(i), buffer);
            }
            for (int i = 0; i < length; ++i) {
                hasAttribute = this.printAttributeWithoutPrefix((Attr)attributes.item(i), hasAttribute);
            }
            if (buffer.length() > 0) {
                if (hasAttribute) {
                    this.print(", ");
                }
                this.print(buffer.toString());
                hasAttribute = true;
            }
        }
        return hasAttribute;
    }
    
    protected void printAttributeWithPrefix(final Attr attribute, final StringBuffer buffer) {
        final String prefix = attribute.getPrefix();
        if (prefix != null && prefix.length() > 0 && !prefix.equals("xmlns")) {
            if (buffer.length() > 0) {
                buffer.append(", ");
            }
            buffer.append(this.qt);
            buffer.append(prefix);
            buffer.append(":");
            buffer.append(this.getLocalName(attribute));
            buffer.append(this.qt + ":" + this.qt);
            buffer.append(this.escapeQuote(this.getAttributeValue(attribute)));
            buffer.append(this.qt);
        }
    }
    
    protected String getAttributeValue(final Attr attribute) {
        return attribute.getValue();
    }
    
    protected boolean printAttributeWithoutPrefix(final Attr attribute, boolean hasAttribute) {
        final String prefix = attribute.getPrefix();
        if (prefix == null || prefix.length() == 0) {
            if (!hasAttribute) {
                hasAttribute = true;
            }
            else {
                this.print(", ");
            }
            final String localName = this.getLocalName(attribute);
            final boolean needsEscaping = this.checkEscaping(localName);
            if (needsEscaping) {
                this.print(this.qt);
            }
            this.print(localName);
            if (needsEscaping) {
                this.print(this.qt);
            }
            this.print(":");
            this.printQuoted(this.getAttributeValue(attribute));
        }
        return hasAttribute;
    }
    
    protected boolean checkEscaping(final String localName) {
        return this.keywords.contains(localName) || localName.contains("-") || localName.contains(":") || localName.contains(".");
    }
    
    protected String getTextNodeData(final Text node) {
        return node.getData().trim();
    }
    
    protected boolean mixedContent(final NodeList list) {
        boolean hasText = false;
        boolean hasElement = false;
        for (int i = 0, size = list.getLength(); i < size; ++i) {
            final Node node = list.item(i);
            if (node instanceof Element) {
                hasElement = true;
            }
            else if (node instanceof Text) {
                final String text = this.getTextNodeData((Text)node);
                if (text.length() > 0) {
                    hasText = true;
                }
            }
        }
        return hasText && hasElement;
    }
    
    protected void printChildren(final Node parent, final Map namespaces) {
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            this.print(node, namespaces, false);
        }
    }
    
    protected String getLocalName(final Node node) {
        String answer = node.getLocalName();
        if (answer == null) {
            answer = node.getNodeName();
        }
        return answer.trim();
    }
    
    protected void printEnd(final String text, final boolean endWithComma) {
        if (endWithComma) {
            this.print(text);
            this.println(",");
        }
        else {
            this.println(text);
        }
    }
    
    protected void println(final String text) {
        this.out.println(text);
    }
    
    protected void print(final String text) {
        this.out.print(text);
    }
    
    protected void printIndent() {
        this.out.printIndent();
    }
}
