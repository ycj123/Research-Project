// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.File;

public interface FileReadOnlyHandler
{
    void setFileReadOnly(final File p0, final boolean p1) throws IOException;
}
