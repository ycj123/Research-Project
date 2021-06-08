// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import org.netbeans.lib.cvsclient.util.BugLog;

public class SetRequest extends Request
{
    private String keyValue;
    
    public SetRequest(final String s) {
        BugLog.getInstance().assertTrue(s.indexOf(61) > 0, "Wrong SetRequest=" + s);
        this.keyValue = s;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "Set " + this.keyValue + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
