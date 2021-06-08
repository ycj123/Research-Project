// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public final class RootRequest extends Request
{
    private final String cvsRoot;
    
    public RootRequest(final String cvsRoot) {
        if (cvsRoot == null) {
            throw new IllegalArgumentException("cvsRoot must not be null!");
        }
        this.cvsRoot = cvsRoot;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "Root " + this.cvsRoot + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
