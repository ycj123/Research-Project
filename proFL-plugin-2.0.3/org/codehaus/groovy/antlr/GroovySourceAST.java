// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import java.util.List;
import java.util.ArrayList;
import groovyjarjarantlr.collections.AST;
import groovyjarjarantlr.Token;
import groovyjarjarantlr.CommonAST;

public class GroovySourceAST extends CommonAST implements Comparable, SourceInfo
{
    private int line;
    private int col;
    private int lineLast;
    private int colLast;
    private String snippet;
    
    public GroovySourceAST() {
    }
    
    public GroovySourceAST(final Token t) {
        super(t);
    }
    
    @Override
    public void initialize(final AST ast) {
        super.initialize(ast);
        this.line = ast.getLine();
        this.col = ast.getColumn();
        if (ast instanceof GroovySourceAST) {
            final GroovySourceAST node = (GroovySourceAST)ast;
            this.lineLast = node.getLineLast();
            this.colLast = node.getColumnLast();
        }
    }
    
    @Override
    public void initialize(final Token t) {
        super.initialize(t);
        this.line = t.getLine();
        this.col = t.getColumn();
        if (t instanceof SourceInfo) {
            final SourceInfo info = (SourceInfo)t;
            this.lineLast = info.getLineLast();
            this.colLast = info.getColumnLast();
        }
    }
    
    public void setLast(final Token last) {
        this.lineLast = last.getLine();
        this.colLast = last.getColumn();
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
    
    public void setLine(final int line) {
        this.line = line;
    }
    
    @Override
    public int getLine() {
        return this.line;
    }
    
    public void setColumn(final int column) {
        this.col = column;
    }
    
    @Override
    public int getColumn() {
        return this.col;
    }
    
    public void setSnippet(final String snippet) {
        this.snippet = snippet;
    }
    
    public String getSnippet() {
        return this.snippet;
    }
    
    public int compareTo(final Object object) {
        if (object == null) {
            return 0;
        }
        if (!(object instanceof AST)) {
            return 0;
        }
        final AST that = (AST)object;
        if (this.getLine() < that.getLine()) {
            return -1;
        }
        if (this.getLine() > that.getLine()) {
            return 1;
        }
        if (this.getColumn() < that.getColumn()) {
            return -1;
        }
        if (this.getColumn() > that.getColumn()) {
            return 1;
        }
        return 0;
    }
    
    public GroovySourceAST childAt(final int position) {
        final List list = new ArrayList();
        for (AST child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            list.add(child);
        }
        try {
            return list.get(position);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public GroovySourceAST childOfType(final int type) {
        for (AST child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == type) {
                return (GroovySourceAST)child;
            }
        }
        return null;
    }
    
    public List<GroovySourceAST> childrenOfType(final int type) {
        final List<GroovySourceAST> result = new ArrayList<GroovySourceAST>();
        for (AST child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == type) {
                result.add((GroovySourceAST)child);
            }
        }
        return result;
    }
}
