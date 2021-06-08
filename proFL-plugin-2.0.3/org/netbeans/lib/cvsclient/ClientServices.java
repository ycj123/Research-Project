// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient;

import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.util.Map;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.file.FileHandler;
import org.netbeans.lib.cvsclient.util.IgnoreFileFilter;
import java.util.Set;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.File;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.response.ResponseException;
import org.netbeans.lib.cvsclient.request.UnconfiguredRequestException;
import java.io.IOException;
import java.util.List;

public interface ClientServices
{
    void processRequests(final List p0) throws IOException, UnconfiguredRequestException, ResponseException, CommandAbortedException;
    
    String getRepository();
    
    String getRepositoryForDirectory(final String p0) throws IOException;
    
    String getRepositoryForDirectory(final File p0) throws IOException;
    
    String getLocalPath();
    
    Entry getEntry(final File p0) throws IOException;
    
    Iterator getEntries(final File p0) throws IOException;
    
    void updateAdminData(final String p0, final String p1, final Entry p2) throws IOException;
    
    Set getAllFiles(final File p0) throws IOException;
    
    boolean isFirstCommand();
    
    void setIsFirstCommand(final boolean p0);
    
    void removeEntry(final File p0) throws IOException;
    
    void setIgnoreFileFilter(final IgnoreFileFilter p0);
    
    IgnoreFileFilter getIgnoreFileFilter();
    
    boolean shouldBeIgnored(final File p0, final String p1);
    
    void setUncompressedFileHandler(final FileHandler p0);
    
    void setGzipFileHandler(final FileHandler p0);
    
    String getStickyTagForDirectory(final File p0);
    
    void ensureConnection() throws AuthenticationException;
    
    Map getWrappersMap() throws CommandException;
    
    GlobalOptions getGlobalOptions();
    
    boolean exists(final File p0);
    
    boolean isAborted();
}
