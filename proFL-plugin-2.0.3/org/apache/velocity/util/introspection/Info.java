// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

public class Info
{
    private int line;
    private int column;
    private String templateName;
    
    public Info(final String source, final int line, final int column) {
        this.templateName = source;
        this.line = line;
        this.column = column;
    }
    
    private Info() {
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public String toString() {
        return this.getTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + ']';
    }
}
