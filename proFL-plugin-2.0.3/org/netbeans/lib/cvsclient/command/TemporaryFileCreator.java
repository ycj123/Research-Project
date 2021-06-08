// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.IOException;
import java.io.File;

public interface TemporaryFileCreator
{
    File createTempFile(final String p0) throws IOException;
}
