// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.util;

import org.apache.maven.doxia.parser.ParseException;

public interface ByLineSource
{
    String getNextLine() throws ParseException;
    
    String getName();
    
    int getLineNumber();
    
    void ungetLine() throws IllegalStateException;
    
    void unget(final String p0) throws IllegalStateException;
    
    void close();
}
