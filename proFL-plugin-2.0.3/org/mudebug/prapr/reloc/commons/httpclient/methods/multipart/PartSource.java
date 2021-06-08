// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.InputStream;

public interface PartSource
{
    long getLength();
    
    String getFileName();
    
    InputStream createInputStream() throws IOException;
}
