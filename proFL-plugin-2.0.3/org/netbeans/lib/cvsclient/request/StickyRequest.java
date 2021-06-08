// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public final class StickyRequest extends Request
{
    private String sticky;
    
    public StickyRequest(final String sticky) {
        this.sticky = sticky;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.sticky == null) {
            throw new UnconfiguredRequestException("Sticky tag has not been set");
        }
        return "Sticky " + this.sticky + "\n";
    }
    
    public void setStickyTag(final String sticky) {
        this.sticky = sticky;
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
