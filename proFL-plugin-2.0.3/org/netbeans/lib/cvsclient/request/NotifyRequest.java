// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.io.File;
import java.text.DateFormat;

public class NotifyRequest extends Request
{
    private static final DateFormat DATE_FORMAT;
    private static final String HOST_NAME;
    private final String request;
    
    public NotifyRequest(final File file, final String str, final String str2) {
        if (file == null) {
            throw new IllegalArgumentException("File must not be null!");
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Notify ");
        sb.append(file.getName());
        sb.append('\n');
        sb.append(str);
        sb.append('\t');
        sb.append(NotifyRequest.DATE_FORMAT.format(new Date()));
        sb.append('\t');
        sb.append(NotifyRequest.HOST_NAME);
        sb.append('\t');
        sb.append(file.getParent());
        sb.append('\t');
        sb.append(str2);
        sb.append('\n');
        this.request = sb.toString();
    }
    
    public String getRequestString() {
        return this.request;
    }
    
    public boolean isResponseExpected() {
        return false;
    }
    
    static {
        DATE_FORMAT = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy z", Locale.US);
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        HOST_NAME = hostName;
    }
}
