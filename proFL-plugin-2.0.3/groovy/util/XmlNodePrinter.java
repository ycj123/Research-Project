// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.HashMap;
import java.util.Map;
import groovy.xml.QName;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Iterator;
import java.util.List;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class XmlNodePrinter
{
    protected final IndentPrinter out;
    private String quote;
    private boolean namespaceAware;
    private boolean preserveWhitespace;
    
    public XmlNodePrinter(final PrintWriter out) {
        this(out, "  ");
    }
    
    public XmlNodePrinter(final PrintWriter out, final String indent) {
        this(out, indent, "\"");
    }
    
    public XmlNodePrinter(final PrintWriter out, final String indent, final String quote) {
        this(new IndentPrinter(out, indent), quote);
    }
    
    public XmlNodePrinter(final IndentPrinter out) {
        this(out, "\"");
    }
    
    public XmlNodePrinter(final IndentPrinter out, final String quote) {
        this.namespaceAware = true;
        this.preserveWhitespace = false;
        if (out == null) {
            throw new IllegalArgumentException("Argument 'IndentPrinter out' must not be null!");
        }
        this.out = out;
        this.quote = quote;
    }
    
    public XmlNodePrinter() {
        this(new PrintWriter(new OutputStreamWriter(System.out)));
    }
    
    public void print(final Node node) {
        this.print(node, new NamespaceContext());
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public void setNamespaceAware(final boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
    }
    
    public boolean isPreserveWhitespace() {
        return this.preserveWhitespace;
    }
    
    public void setPreserveWhitespace(final boolean preserveWhitespace) {
        this.preserveWhitespace = preserveWhitespace;
    }
    
    public String getQuote() {
        return this.quote;
    }
    
    public void setQuote(final String quote) {
        this.quote = quote;
    }
    
    protected void print(final Node node, final NamespaceContext ctx) {
        if (this.isEmptyElement(node)) {
            this.printLineBegin();
            this.out.print("<");
            this.out.print(this.getName(node));
            if (ctx != null) {
                this.printNamespace(node, ctx);
            }
            this.printNameAttributes(node.attributes(), ctx);
            this.out.print("/>");
            this.printLineEnd();
            this.out.flush();
            return;
        }
        if (this.printSpecialNode(node)) {
            this.out.flush();
            return;
        }
        final Object value = node.value();
        if (value instanceof List) {
            this.printName(node, ctx, true, this.isListOfSimple((List)value));
            this.printList((List)value, ctx);
            this.printName(node, ctx, false, this.isListOfSimple((List)value));
            this.out.flush();
            return;
        }
        this.printName(node, ctx, true, this.preserveWhitespace);
        this.printSimpleItemWithIndent(value);
        this.printName(node, ctx, false, this.preserveWhitespace);
        this.out.flush();
    }
    
    private boolean isListOfSimple(final List value) {
        for (final Object p : value) {
            if (p instanceof Node) {
                return false;
            }
        }
        return this.preserveWhitespace;
    }
    
    protected void printLineBegin() {
        this.out.printIndent();
    }
    
    protected void printLineEnd() {
        this.printLineEnd(null);
    }
    
    protected void printLineEnd(final String comment) {
        if (comment != null) {
            this.out.print(" <!-- ");
            this.out.print(comment);
            this.out.print(" -->");
        }
        this.out.println();
        this.out.flush();
    }
    
    protected void printList(final List list, final NamespaceContext ctx) {
        this.out.incrementIndent();
        for (final Object value : list) {
            final NamespaceContext context = new NamespaceContext(ctx);
            if (value instanceof Node) {
                this.print((Node)value, context);
            }
            else {
                this.printSimpleItem(value);
            }
        }
        this.out.decrementIndent();
    }
    
    protected void printSimpleItem(final Object value) {
        if (!this.preserveWhitespace) {
            this.printLineBegin();
        }
        this.printEscaped(InvokerHelper.toString(value), false);
        if (!this.preserveWhitespace) {
            this.printLineEnd();
        }
    }
    
    protected void printName(final Node node, final NamespaceContext ctx, final boolean begin, final boolean preserve) {
        if (node == null) {
            throw new NullPointerException("Node must not be null.");
        }
        final Object name = node.name();
        if (name == null) {
            throw new NullPointerException("Name must not be null.");
        }
        if (!preserve || begin) {
            this.printLineBegin();
        }
        this.out.print("<");
        if (!begin) {
            this.out.print("/");
        }
        this.out.print(this.getName(node));
        if (ctx != null) {
            this.printNamespace(node, ctx);
        }
        if (begin) {
            this.printNameAttributes(node.attributes(), ctx);
        }
        this.out.print(">");
        if (!preserve || !begin) {
            this.printLineEnd();
        }
    }
    
    protected boolean printSpecialNode(final Node node) {
        return false;
    }
    
    protected void printNamespace(final Object object, final NamespaceContext ctx) {
        if (this.namespaceAware) {
            if (object instanceof Node) {
                this.printNamespace(((Node)object).name(), ctx);
            }
            else if (object instanceof QName) {
                final QName qname = (QName)object;
                final String namespaceUri = qname.getNamespaceURI();
                if (namespaceUri != null) {
                    final String prefix = qname.getPrefix();
                    if (!ctx.isPrefixRegistered(prefix, namespaceUri)) {
                        ctx.registerNamespacePrefix(prefix, namespaceUri);
                        this.out.print(" ");
                        this.out.print("xmlns");
                        if (prefix.length() > 0) {
                            this.out.print(":");
                            this.out.print(prefix);
                        }
                        this.out.print("=" + this.quote);
                        this.out.print(namespaceUri);
                        this.out.print(this.quote);
                    }
                }
            }
        }
    }
    
    protected void printNameAttributes(final Map attributes, final NamespaceContext ctx) {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }
        for (final Object p : attributes.entrySet()) {
            final Map.Entry entry = (Map.Entry)p;
            this.out.print(" ");
            this.out.print(this.getName(entry.getKey()));
            this.out.print("=");
            final Object value = entry.getValue();
            this.out.print(this.quote);
            if (value instanceof String) {
                this.printEscaped((String)value, true);
            }
            else {
                this.printEscaped(InvokerHelper.toString(value), true);
            }
            this.out.print(this.quote);
            this.printNamespace(entry.getKey(), ctx);
        }
    }
    
    private boolean isEmptyElement(final Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node must not be null!");
        }
        return node.children().isEmpty() && node.text().length() == 0;
    }
    
    private String getName(final Object object) {
        if (object instanceof String) {
            return (String)object;
        }
        if (object instanceof QName) {
            final QName qname = (QName)object;
            if (!this.namespaceAware) {
                return qname.getLocalPart();
            }
            return qname.getQualifiedName();
        }
        else {
            if (object instanceof Node) {
                final Object name = ((Node)object).name();
                return this.getName(name);
            }
            return object.toString();
        }
    }
    
    private void printSimpleItemWithIndent(final Object value) {
        if (!this.preserveWhitespace) {
            this.out.incrementIndent();
        }
        this.printSimpleItem(value);
        if (!this.preserveWhitespace) {
            this.out.decrementIndent();
        }
    }
    
    private void printEscaped(final String s, final boolean isAttributeValue) {
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            switch (c) {
                case '<': {
                    this.out.print("&lt;");
                    break;
                }
                case '>': {
                    this.out.print("&gt;");
                    break;
                }
                case '&': {
                    this.out.print("&amp;");
                    break;
                }
                case '\'': {
                    if (this.quote.equals("'")) {
                        this.out.print("&apos;");
                        break;
                    }
                    this.out.print(c);
                    break;
                }
                case '\"': {
                    if (this.quote.equals("\"")) {
                        this.out.print("&quot;");
                        break;
                    }
                    this.out.print(c);
                    break;
                }
                case '\n': {
                    if (isAttributeValue) {
                        this.out.print("&#10;");
                        break;
                    }
                    this.out.print(c);
                    break;
                }
                case '\r': {
                    if (isAttributeValue) {
                        this.out.print("&#13;");
                        break;
                    }
                    this.out.print(c);
                    break;
                }
                default: {
                    this.out.print(c);
                    break;
                }
            }
        }
    }
    
    protected class NamespaceContext
    {
        private final Map<String, String> namespaceMap;
        
        public NamespaceContext() {
            this.namespaceMap = new HashMap<String, String>();
        }
        
        public NamespaceContext(final XmlNodePrinter xmlNodePrinter, final NamespaceContext context) {
            this(xmlNodePrinter);
            this.namespaceMap.putAll(context.namespaceMap);
        }
        
        public boolean isPrefixRegistered(final String prefix, final String uri) {
            return this.namespaceMap.containsKey(prefix) && this.namespaceMap.get(prefix).equals(uri);
        }
        
        public void registerNamespacePrefix(final String prefix, final String uri) {
            if (!this.isPrefixRegistered(prefix, uri)) {
                this.namespaceMap.put(prefix, uri);
            }
        }
        
        public String getNamespace(final String prefix) {
            final Object uri = this.namespaceMap.get(prefix);
            return (uri == null) ? null : uri.toString();
        }
    }
}
