// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;

public interface WriteTextFilePreprocessor
{
    void copyTextFileToLocation(final InputStream p0, final File p1, final OutputStreamProvider p2) throws IOException;
}
