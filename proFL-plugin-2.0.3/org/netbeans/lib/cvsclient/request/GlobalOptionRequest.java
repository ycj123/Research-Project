// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class GlobalOptionRequest extends Request
{
    private final String option;
    
    public GlobalOptionRequest(final String option) {
        this.option = option;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.option == null) {
            throw new UnconfiguredRequestException("Global option has not been set");
        }
        return "Global_option " + this.option + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
