// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

public class LineColumn
{
    private int line;
    private int column;
    
    public LineColumn(final int line, final int column) {
        this.line = line;
        this.column = column;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        final LineColumn lineColumn = (LineColumn)that;
        return this.column == lineColumn.column && this.line == lineColumn.line;
    }
    
    @Override
    public int hashCode() {
        int result = this.line;
        result = 29 * result + this.column;
        return result;
    }
    
    @Override
    public String toString() {
        return "[" + this.line + "," + this.column + "]";
    }
}
