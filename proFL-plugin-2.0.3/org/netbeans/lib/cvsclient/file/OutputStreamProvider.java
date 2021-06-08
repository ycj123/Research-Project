// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.OutputStream;

public interface OutputStreamProvider
{
    OutputStream createOutputStream() throws IOException;
}
