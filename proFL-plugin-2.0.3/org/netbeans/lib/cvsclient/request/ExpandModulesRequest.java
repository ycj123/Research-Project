// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class ExpandModulesRequest extends Request
{
    public String getRequestString() throws UnconfiguredRequestException {
        return "expand-modules \n";
    }
    
    public boolean isResponseExpected() {
        return true;
    }
}
