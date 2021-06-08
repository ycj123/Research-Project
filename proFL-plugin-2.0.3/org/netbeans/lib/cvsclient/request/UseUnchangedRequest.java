// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class UseUnchangedRequest extends Request
{
    public String getRequestString() throws UnconfiguredRequestException {
        return "UseUnchanged \n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
