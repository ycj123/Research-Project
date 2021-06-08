// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.text.lookup;

final class EnvironmentVariableStringLookup extends AbstractStringLookup
{
    static final EnvironmentVariableStringLookup INSTANCE;
    
    private EnvironmentVariableStringLookup() {
    }
    
    @Override
    public String lookup(final String key) {
        return (key != null) ? System.getenv(key) : null;
    }
    
    static {
        INSTANCE = new EnvironmentVariableStringLookup();
    }
}
