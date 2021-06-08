// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.io.OutputStream;
import java.io.PrintWriter;

public class IndentPrinter
{
    private int indentLevel;
    private String indent;
    private PrintWriter out;
    private final boolean addNewlines;
    
    public IndentPrinter() {
        this(new PrintWriter(System.out), "  ");
    }
    
    public IndentPrinter(final PrintWriter out) {
        this(out, "  ");
    }
    
    public IndentPrinter(final PrintWriter out, final String indent) {
        this(out, indent, true);
    }
    
    public IndentPrinter(PrintWriter out, final String indent, final boolean addNewlines) {
        this.addNewlines = addNewlines;
        if (out == null) {
            out = new PrintWriter(System.out);
        }
        this.out = out;
        this.indent = indent;
    }
    
    public void println(final String text) {
        this.out.print(text);
        this.println();
    }
    
    public void print(final String text) {
        this.out.print(text);
    }
    
    public void print(final char c) {
        this.out.print(c);
    }
    
    public void printIndent() {
        for (int i = 0; i < this.indentLevel; ++i) {
            this.out.print(this.indent);
        }
    }
    
    public void println() {
        if (this.addNewlines) {
            this.out.print("\n");
        }
    }
    
    public void incrementIndent() {
        ++this.indentLevel;
    }
    
    public void decrementIndent() {
        --this.indentLevel;
    }
    
    public int getIndentLevel() {
        return this.indentLevel;
    }
    
    public void setIndentLevel(final int indentLevel) {
        this.indentLevel = indentLevel;
    }
    
    public void flush() {
        this.out.flush();
    }
}
