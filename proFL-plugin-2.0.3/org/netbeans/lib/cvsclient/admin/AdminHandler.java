// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.admin;

import java.util.Set;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public interface AdminHandler
{
    void updateAdminData(final String p0, final String p1, final Entry p2, final GlobalOptions p3) throws IOException;
    
    Entry getEntry(final File p0) throws IOException;
    
    Iterator getEntries(final File p0) throws IOException;
    
    void setEntry(final File p0, final Entry p1) throws IOException;
    
    String getRepositoryForDirectory(final String p0, final String p1) throws IOException;
    
    void removeEntry(final File p0) throws IOException;
    
    Set getAllFiles(final File p0) throws IOException;
    
    String getStickyTagForDirectory(final File p0);
    
    boolean exists(final File p0);
}
