// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import org.netbeans.lib.cvsclient.util.StringPattern;
import org.netbeans.lib.cvsclient.file.FileHandler;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.io.File;
import java.io.IOException;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.util.Date;

public interface ResponseServices
{
    void setNextFileDate(final Date p0);
    
    Date getNextFileDate();
    
    String convertPathname(final String p0, final String p1);
    
    void updateAdminData(final String p0, final String p1, final Entry p2) throws IOException;
    
    void setEntry(final File p0, final Entry p1) throws IOException;
    
    void removeEntry(final File p0) throws IOException;
    
    void removeLocalFile(final String p0) throws IOException;
    
    void removeLocalFile(final String p0, final String p1) throws IOException;
    
    void renameLocalFile(final String p0, final String p1) throws IOException;
    
    EventManager getEventManager();
    
    FileHandler getUncompressedFileHandler();
    
    FileHandler getGzipFileHandler();
    
    void dontUseGzipFileHandler();
    
    void setValidRequests(final String p0);
    
    void addWrapper(final StringPattern p0, final KeywordSubstitutionOptions p1);
    
    GlobalOptions getGlobalOptions();
}
