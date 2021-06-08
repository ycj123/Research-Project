// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.util.Iterator;
import java.util.Map;
import java.io.Writer;
import java.io.PrintWriter;
import groovy.util.IndentPrinter;
import groovy.util.BuilderSupport;

public class MarkupBuilder extends BuilderSupport
{
    private IndentPrinter out;
    private boolean nospace;
    private int state;
    private boolean nodeIsEmpty;
    private boolean useDoubleQuotes;
    private boolean omitNullAttributes;
    private boolean omitEmptyAttributes;
    
    public MarkupBuilder() {
        this(new IndentPrinter());
    }
    
    public MarkupBuilder(final PrintWriter pw) {
        this(new IndentPrinter(pw));
    }
    
    public MarkupBuilder(final Writer writer) {
        this(new IndentPrinter(new PrintWriter(writer)));
    }
    
    public MarkupBuilder(final IndentPrinter out) {
        this.nodeIsEmpty = true;
        this.useDoubleQuotes = false;
        this.omitNullAttributes = false;
        this.omitEmptyAttributes = false;
        this.out = out;
    }
    
    public boolean getDoubleQuotes() {
        return this.useDoubleQuotes;
    }
    
    public void setDoubleQuotes(final boolean useDoubleQuotes) {
        this.useDoubleQuotes = useDoubleQuotes;
    }
    
    public boolean isOmitNullAttributes() {
        return this.omitNullAttributes;
    }
    
    public void setOmitNullAttributes(final boolean omitNullAttributes) {
        this.omitNullAttributes = omitNullAttributes;
    }
    
    public boolean isOmitEmptyAttributes() {
        return this.omitEmptyAttributes;
    }
    
    public void setOmitEmptyAttributes(final boolean omitEmptyAttributes) {
        this.omitEmptyAttributes = omitEmptyAttributes;
    }
    
    protected IndentPrinter getPrinter() {
        return this.out;
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
    }
    
    public Object getMkp() {
        return new MarkupBuilderHelper(this);
    }
    
    void pi(final Map<String, Map<String, Object>> args) {
        final Iterator<Map.Entry<String, Map<String, Object>>> iterator = args.entrySet().iterator();
        if (iterator.hasNext()) {
            final Map.Entry<String, Map<String, Object>> mapEntry = iterator.next();
            this.createNode("?" + mapEntry.getKey(), mapEntry.getValue());
            this.state = 2;
            this.out.println("?>");
        }
    }
    
    void yield(final String value, final boolean escaping) {
        if (this.state == 1) {
            this.state = 2;
            this.nodeIsEmpty = false;
            this.out.print(">");
        }
        if (this.state == 2 || this.state == 3) {
            this.out.print(escaping ? this.escapeElementContent(value) : value);
        }
    }
    
    @Override
    protected Object createNode(final Object name) {
        final Object theName = this.getName(name);
        this.toState(1, theName);
        this.nodeIsEmpty = true;
        return theName;
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        final Object theName = this.getName(name);
        if (value == null) {
            return this.createNode(theName);
        }
        this.toState(2, theName);
        this.nodeIsEmpty = false;
        this.out.print(">");
        this.out.print(this.escapeElementContent(value.toString()));
        return theName;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        final Object theName = this.getName(name);
        this.toState(1, theName);
        for (final Object p : attributes.entrySet()) {
            final Map.Entry entry = (Map.Entry)p;
            final Object attributeValue = entry.getValue();
            final boolean skipNull = attributeValue == null && this.omitNullAttributes;
            final boolean skipEmpty = attributeValue != null && this.omitEmptyAttributes && attributeValue.toString().length() == 0;
            if (!skipNull && !skipEmpty) {
                this.out.print(" ");
                this.print(entry.getKey().toString());
                this.out.print(this.useDoubleQuotes ? "=\"" : "='");
                this.print((attributeValue == null) ? "" : this.escapeAttributeValue(attributeValue.toString()));
                this.out.print(this.useDoubleQuotes ? "\"" : "'");
            }
        }
        if (value != null) {
            this.yield(value.toString(), true);
        }
        else {
            this.nodeIsEmpty = true;
        }
        return theName;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        return this.createNode(name, attributes, null);
    }
    
    @Override
    protected void nodeCompleted(final Object parent, final Object node) {
        this.toState(3, node);
        this.out.flush();
    }
    
    protected void print(final Object node) {
        this.out.print((node == null) ? "null" : node.toString());
    }
    
    @Override
    protected Object getName(final String methodName) {
        return super.getName(methodName);
    }
    
    @Deprecated
    protected String transformValue(String value) {
        if (value.matches(".*&.*")) {
            value = value.replaceAll("&", "&amp;");
        }
        if (value.matches(".*\\'.*")) {
            value = value.replaceAll("'", "&apos;");
        }
        if (value.matches(".*<.*")) {
            value = value.replaceAll("<", "&lt;");
        }
        if (value.matches(".*>.*")) {
            value = value.replaceAll(">", "&gt;");
        }
        return value;
    }
    
    private String escapeAttributeValue(final String value) {
        return this.escapeXmlValue(value, true);
    }
    
    private String escapeElementContent(final String value) {
        return this.escapeXmlValue(value, false);
    }
    
    private String escapeXmlValue(final String value, final boolean isAttrValue) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = null;
        for (int i = 0, len = value.length(); i < len; ++i) {
            final char ch = value.charAt(i);
            final String replacement = this.checkForReplacement(isAttrValue, ch);
            if (replacement != null) {
                if (sb == null) {
                    sb = new StringBuilder((int)(1.1 * len));
                    sb.append(value.substring(0, i));
                }
                sb.append(replacement);
            }
            else if (sb != null) {
                sb.append(ch);
            }
        }
        return (sb == null) ? value : sb.toString();
    }
    
    private String checkForReplacement(final boolean isAttrValue, final char ch) {
        switch (ch) {
            case '&': {
                return "&amp;";
            }
            case '<': {
                return "&lt;";
            }
            case '>': {
                return "&gt;";
            }
            case '\"': {
                if (isAttrValue && this.useDoubleQuotes) {
                    return "&quot;";
                }
                break;
            }
            case '\'': {
                if (isAttrValue && !this.useDoubleQuotes) {
                    return "&apos;";
                }
                break;
            }
        }
        return null;
    }
    
    private void toState(final int next, final Object name) {
        Label_0434: {
            switch (this.state) {
                case 0: {
                    switch (next) {
                        case 1:
                        case 2: {
                            this.out.print("<");
                            this.print(name);
                            break;
                        }
                        case 3: {
                            throw new Error();
                        }
                    }
                    break;
                }
                case 1: {
                    switch (next) {
                        case 1:
                        case 2: {
                            this.out.print(">");
                            if (this.nospace) {
                                this.nospace = false;
                            }
                            else {
                                this.out.println();
                                this.out.incrementIndent();
                                this.out.printIndent();
                            }
                            this.out.print("<");
                            this.print(name);
                            break;
                        }
                        case 3: {
                            if (this.nodeIsEmpty) {
                                this.out.print(" />");
                                break;
                            }
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (next) {
                        case 1:
                        case 2: {
                            if (!this.nodeIsEmpty) {
                                this.out.println();
                                this.out.incrementIndent();
                                this.out.printIndent();
                            }
                            this.out.print("<");
                            this.print(name);
                            break;
                        }
                        case 3: {
                            this.out.print("</");
                            this.print(name);
                            this.out.print(">");
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (next) {
                        case 1:
                        case 2: {
                            if (this.nospace) {
                                this.nospace = false;
                            }
                            else {
                                this.out.println();
                                this.out.printIndent();
                            }
                            this.out.print("<");
                            this.print(name);
                            break Label_0434;
                        }
                        case 3: {
                            if (this.nospace) {
                                this.nospace = false;
                            }
                            else {
                                this.out.println();
                                this.out.decrementIndent();
                                this.out.printIndent();
                            }
                            this.out.print("</");
                            this.print(name);
                            this.out.print(">");
                            break Label_0434;
                        }
                    }
                    break;
                }
            }
        }
        this.state = next;
    }
    
    private Object getName(final Object name) {
        if (name instanceof QName) {
            return ((QName)name).getQualifiedName();
        }
        return name;
    }
}
