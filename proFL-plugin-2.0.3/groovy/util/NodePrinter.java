// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Map;
import java.util.List;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class NodePrinter
{
    protected final IndentPrinter out;
    
    public NodePrinter() {
        this(new IndentPrinter(new PrintWriter(new OutputStreamWriter(System.out))));
    }
    
    public NodePrinter(final PrintWriter out) {
        this(new IndentPrinter(out));
    }
    
    public NodePrinter(final IndentPrinter out) {
        if (out == null) {
            throw new NullPointerException("IndentPrinter 'out' must not be null!");
        }
        this.out = out;
    }
    
    public void print(final Node node) {
        this.out.printIndent();
        this.printName(node);
        final Map attributes = node.attributes();
        final boolean hasAttributes = attributes != null && !attributes.isEmpty();
        if (hasAttributes) {
            this.printAttributes(attributes);
        }
        final Object value = node.value();
        if (value instanceof List) {
            if (!hasAttributes) {
                this.out.print("()");
            }
            this.printList((List)value);
        }
        else if (value instanceof String) {
            this.out.print("('");
            this.out.print((String)value);
            this.out.println("')");
        }
        else {
            this.out.println("()");
        }
        this.out.flush();
    }
    
    protected void printName(final Node node) {
        final Object name = node.name();
        if (name != null) {
            this.out.print(name.toString());
        }
        else {
            this.out.print("null");
        }
    }
    
    protected void printList(final List list) {
        if (list.isEmpty()) {
            this.out.println("");
        }
        else {
            this.out.println(" {");
            this.out.incrementIndent();
            for (final Object value : list) {
                if (value instanceof Node) {
                    this.print((Node)value);
                }
                else {
                    this.out.printIndent();
                    this.out.print("builder.append(");
                    this.out.print(InvokerHelper.toString(value));
                    this.out.println(")");
                }
            }
            this.out.decrementIndent();
            this.out.printIndent();
            this.out.println("}");
        }
    }
    
    protected void printAttributes(final Map attributes) {
        this.out.print("(");
        boolean first = true;
        for (final Map.Entry entry : attributes.entrySet()) {
            if (first) {
                first = false;
            }
            else {
                this.out.print(", ");
            }
            this.out.print(entry.getKey().toString());
            this.out.print(":");
            if (entry.getValue() instanceof String) {
                this.out.print("'" + entry.getValue() + "'");
            }
            else {
                this.out.print(InvokerHelper.toString(entry.getValue()));
            }
        }
        this.out.print(")");
    }
}
