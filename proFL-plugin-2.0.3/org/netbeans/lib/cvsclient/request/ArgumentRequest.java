// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class ArgumentRequest extends Request
{
    private final String argument;
    
    public ArgumentRequest(final String argument) {
        if (argument == null) {
            throw new IllegalArgumentException("argument must not be null!");
        }
        this.argument = argument;
    }
    
    public String getRequestString() {
        return "Argument " + this.argument + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
