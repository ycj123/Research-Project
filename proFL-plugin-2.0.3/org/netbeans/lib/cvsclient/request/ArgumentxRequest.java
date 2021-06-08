// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class ArgumentxRequest extends Request
{
    private String argument;
    
    public ArgumentxRequest(final String argument) {
        this.argument = argument;
    }
    
    public void setArgument(final String argument) {
        this.argument = argument;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.argument == null) {
            throw new UnconfiguredRequestException("Argument has not been set");
        }
        return "Argumentx " + this.argument + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
