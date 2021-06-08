// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class ValidRequestsRequest extends Request
{
    public String getRequestString() throws UnconfiguredRequestException {
        return "valid-requests \n";
    }
    
    public boolean isResponseExpected() {
        return true;
    }
}
