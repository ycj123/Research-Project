// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import org.netbeans.lib.cvsclient.util.BugLog;
import java.io.File;

public class WindowsFileReadOnlyHandler implements FileReadOnlyHandler
{
    public void setFileReadOnly(final File file, final boolean b) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        try {
            Runtime.getRuntime().exec(new String[] { "attrib", b ? "+r" : "-r", file.getName() }, null, file.getParentFile()).waitFor();
        }
        catch (InterruptedException ex) {
            BugLog.getInstance().showException(ex);
        }
    }
}
