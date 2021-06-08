// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.File;

public interface TransmitTextFilePreprocessor
{
    File getPreprocessedTextFile(final File p0) throws IOException;
    
    void cleanup(final File p0);
    
    void setTempDir(final File p0);
}
