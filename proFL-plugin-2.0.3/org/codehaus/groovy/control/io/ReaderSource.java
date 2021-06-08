// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import org.codehaus.groovy.control.Janitor;
import java.io.IOException;
import java.io.Reader;
import org.codehaus.groovy.control.HasCleanup;

public interface ReaderSource extends HasCleanup
{
    Reader getReader() throws IOException;
    
    boolean canReopenSource();
    
    String getLine(final int p0, final Janitor p1);
    
    void cleanup();
}
