// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.GroovyBugError;
import java.util.Map;
import org.codehaus.groovy.util.ListHashMap;

public class ASTNode
{
    private int lineNumber;
    private int columnNumber;
    private int lastLineNumber;
    private int lastColumnNumber;
    private ListHashMap metaDataMap;
    
    public ASTNode() {
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.lastLineNumber = -1;
        this.lastColumnNumber = -1;
        this.metaDataMap = new ListHashMap();
    }
    
    public void visit(final GroovyCodeVisitor visitor) {
        throw new RuntimeException("No visit() method implemented for class: " + this.getClass().getName());
    }
    
    public String getText() {
        return "<not implemented yet for class: " + this.getClass().getName() + ">";
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public void setLineNumber(final int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
    
    public void setColumnNumber(final int columnNumber) {
        this.columnNumber = columnNumber;
    }
    
    public int getLastLineNumber() {
        return this.lastLineNumber;
    }
    
    public void setLastLineNumber(final int lastLineNumber) {
        this.lastLineNumber = lastLineNumber;
    }
    
    public int getLastColumnNumber() {
        return this.lastColumnNumber;
    }
    
    public void setLastColumnNumber(final int lastColumnNumber) {
        this.lastColumnNumber = lastColumnNumber;
    }
    
    public void setSourcePosition(final ASTNode node) {
        this.columnNumber = node.getColumnNumber();
        this.lastLineNumber = node.getLastLineNumber();
        this.lastColumnNumber = node.getLastColumnNumber();
        this.lineNumber = node.getLineNumber();
    }
    
    public Object getNodeMetaData(final Object key) {
        return this.metaDataMap.get(key);
    }
    
    public void copyNodeMetaData(final ASTNode other) {
        this.metaDataMap.putAll(other.metaDataMap);
    }
    
    public void setNodeMetaData(final Object key, final Object value) {
        if (key == null) {
            throw new GroovyBugError("Tried to set meta data with null key on " + this + ".");
        }
        final Object old = this.metaDataMap.put(key, value);
        if (old != null) {
            throw new GroovyBugError("Tried to overwrite existing meta data " + this + ".");
        }
    }
    
    public void removeNodeMetaData(final Object key) {
        if (key == null) {
            throw new GroovyBugError("Tried to remove meta data with null key.");
        }
        this.metaDataMap.remove(key);
        if (this.metaDataMap.size() == 0) {
            this.metaDataMap = null;
        }
    }
}
