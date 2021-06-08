// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.IOException;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.util.Date;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.text.DateFormat;

class CheckedInResponse implements Response
{
    private DateFormat dateFormatter;
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String line3 = loggedDataInputStream.readLine();
            final File file = new File(responseServices.convertPathname(line, line2));
            final Date date = new Date(file.lastModified());
            final Entry entry = new Entry(line3);
            entry.setConflict(this.getDateFormatter().format(date));
            if (entry.isNewUserFile() || entry.isUserFileToBeRemoved()) {
                entry.setConflict("dummy timestamp");
            }
            responseServices.setEntry(file, entry);
        }
        catch (IOException ex) {
            throw new ResponseException((Exception)ex.fillInStackTrace(), ex.getLocalizedMessage());
        }
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
