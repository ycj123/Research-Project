// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

import java.io.IOException;

public interface CharStream
{
    char readChar() throws IOException;
    
    int getColumn();
    
    int getLine();
    
    int getEndColumn();
    
    int getEndLine();
    
    int getBeginColumn();
    
    int getBeginLine();
    
    void backup(final int p0);
    
    char BeginToken() throws IOException;
    
    String GetImage();
    
    char[] GetSuffix(final int p0);
    
    void Done();
}
