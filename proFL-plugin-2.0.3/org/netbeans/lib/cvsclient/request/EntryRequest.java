// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import org.netbeans.lib.cvsclient.admin.Entry;

public class EntryRequest extends Request
{
    private Entry entry;
    
    public EntryRequest(final Entry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("EntryRequest: Entry must not be null");
        }
        this.entry = entry;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "Entry " + this.entry.toString() + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
    
    public Entry getEntry() {
        return this.entry;
    }
}
