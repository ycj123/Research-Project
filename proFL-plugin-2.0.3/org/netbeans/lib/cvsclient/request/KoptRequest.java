// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class KoptRequest extends Request
{
    private final String option;
    
    public KoptRequest(final String option) {
        this.option = option;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "Kopt " + this.option + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
