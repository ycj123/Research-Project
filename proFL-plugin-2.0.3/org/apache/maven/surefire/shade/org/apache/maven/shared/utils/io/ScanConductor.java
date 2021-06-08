// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.io.File;

public interface ScanConductor
{
    ScanAction visitDirectory(final String p0, final File p1);
    
    ScanAction visitFile(final String p0, final File p1);
    
    public enum ScanAction
    {
        ABORT, 
        CONTINUE, 
        NO_RECURSE, 
        ABORT_DIRECTORY;
    }
}
