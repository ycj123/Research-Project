// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import groovyjarjarantlr.Token;

public class GroovySourceToken extends Token implements SourceInfo
{
    protected int line;
    protected String text;
    protected int col;
    protected int lineLast;
    protected int colLast;
    
    public GroovySourceToken(final int t) {
        super(t);
        this.text = "";
    }
    
    @Override
    public int getLine() {
        return this.line;
    }
    
    @Override
    public String getText() {
        return this.text;
    }
    
    @Override
    public void setLine(final int l) {
        this.line = l;
    }
    
    @Override
    public void setText(final String s) {
        this.text = s;
    }
    
    @Override
    public String toString() {
        return "[\"" + this.getText() + "\",<" + this.type + ">," + "line=" + this.line + ",col=" + this.col + ",lineLast=" + this.lineLast + ",colLast=" + this.colLast + "]";
    }
    
    @Override
    public int getColumn() {
        return this.col;
    }
    
    @Override
    public void setColumn(final int c) {
        this.col = c;
    }
    
    public int getLineLast() {
        return this.lineLast;
    }
    
    public void setLineLast(final int lineLast) {
        this.lineLast = lineLast;
    }
    
    public int getColumnLast() {
        return this.colLast;
    }
    
    public void setColumnLast(final int colLast) {
        this.colLast = colLast;
    }
}
