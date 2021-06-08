// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

public interface SourceInfo
{
    int getLine();
    
    void setLine(final int p0);
    
    int getColumn();
    
    void setColumn(final int p0);
    
    int getLineLast();
    
    void setLineLast(final int p0);
    
    int getColumnLast();
    
    void setColumnLast(final int p0);
}
