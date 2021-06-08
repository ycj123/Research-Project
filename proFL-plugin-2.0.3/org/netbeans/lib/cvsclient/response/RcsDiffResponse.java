// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.text.SimpleDateFormat;
import java.util.Locale;
import org.netbeans.lib.cvsclient.file.FileHandler;
import java.io.IOException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.FileUpdatedEvent;
import java.util.Date;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.text.DateFormat;

class RcsDiffResponse implements Response
{
    private static final boolean DEBUG = false;
    private String localPath;
    private String repositoryPath;
    private String entryLine;
    private String mode;
    protected String localFile;
    private DateFormat dateFormatter;
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            this.localPath = loggedDataInputStream.readLine();
            this.repositoryPath = loggedDataInputStream.readLine();
            this.entryLine = loggedDataInputStream.readLine();
            this.mode = loggedDataInputStream.readLine();
            final String line = loggedDataInputStream.readLine();
            final boolean b = line.charAt(0) == 'z';
            int i = Integer.parseInt(b ? line.substring(1) : line);
            final String convertPathname = responseServices.convertPathname(this.localPath, this.repositoryPath);
            final File file = new File(convertPathname);
            if (responseServices.getGlobalOptions().isExcluded(file)) {
                while (i > 0) {
                    i -= (int)loggedDataInputStream.skip(i);
                }
                return;
            }
            this.localFile = file.getAbsolutePath();
            final Entry entry = new Entry(this.entryLine);
            final FileHandler fileHandler = b ? responseServices.getGzipFileHandler() : responseServices.getUncompressedFileHandler();
            fileHandler.setNextFileDate(responseServices.getNextFileDate());
            if (!entry.isBinary()) {
                fileHandler.writeRcsDiffFile(convertPathname, this.mode, loggedDataInputStream, i);
            }
            String conflict;
            if (entry.getConflict() != null && entry.getConflict().charAt(0) == '+') {
                if (entry.getConflict().charAt(1) == '=') {
                    conflict = this.getEntryConflict(new Date(file.lastModified()), true);
                }
                else {
                    conflict = entry.getConflict().substring(1);
                }
            }
            else {
                conflict = this.getEntryConflict(new Date(file.lastModified()), false);
            }
            entry.setConflict(conflict);
            responseServices.updateAdminData(this.localPath, this.repositoryPath, entry);
            responseServices.getEventManager().fireCVSEvent(new FileUpdatedEvent(this, convertPathname));
        }
        catch (IOException ex) {
            throw new ResponseException(ex);
        }
    }
    
    protected String getEntryConflict(final Date date, final boolean b) {
        return this.getDateFormatter().format(date);
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
    
    protected DateFormat getDateFormatter() {
        if (this.dateFormatter == null) {
            (this.dateFormatter = new SimpleDateFormat(Entry.getLastModifiedDateFormatter().toPattern(), Locale.US)).setTimeZone(Entry.getTimeZone());
        }
        return this.dateFormatter;
    }
}
