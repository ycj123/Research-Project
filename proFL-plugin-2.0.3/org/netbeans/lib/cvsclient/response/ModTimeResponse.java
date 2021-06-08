// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.admin.Entry;
import java.util.Locale;
import java.util.Date;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.text.SimpleDateFormat;

class ModTimeResponse implements Response
{
    protected static final SimpleDateFormat dateFormatter;
    protected static final String SERVER_DATE_FORMAT = "dd MMM yyyy HH:mm:ss";
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final Date parse = ModTimeResponse.dateFormatter.parse(line.substring(0, line.length() - 6));
            if (parse.getTime() < 0L) {
                if (parse.getYear() < 100 && parse.getYear() >= 70) {
                    parse.setYear(parse.getYear() + 1900);
                }
                else if (parse.getYear() >= 0 && parse.getYear() < 70) {
                    parse.setYear(parse.getYear() + 2000);
                }
                else {
                    parse.setYear(2000 + parse.getYear());
                }
            }
            responseServices.setNextFileDate(parse);
        }
        catch (Exception ex) {
            throw new ResponseException(ex);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
    
    static {
        (dateFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US)).setTimeZone(Entry.getTimeZone());
    }
}
