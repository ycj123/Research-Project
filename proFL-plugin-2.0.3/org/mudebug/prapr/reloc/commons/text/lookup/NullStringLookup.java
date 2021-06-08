// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.text.lookup;

final class NullStringLookup extends AbstractStringLookup
{
    static final NullStringLookup INSTANCE;
    
    private NullStringLookup() {
    }
    
    @Override
    public String lookup(final String key) {
        return null;
    }
    
    static {
        INSTANCE = new NullStringLookup();
    }
}
