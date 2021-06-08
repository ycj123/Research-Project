// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class FalseFileFilter implements IOFileFilter, Serializable
{
    public static final IOFileFilter FALSE;
    public static final IOFileFilter INSTANCE;
    
    protected FalseFileFilter() {
    }
    
    public boolean accept(final File file) {
        return false;
    }
    
    public boolean accept(final File dir, final String name) {
        return false;
    }
    
    static {
        FALSE = new FalseFileFilter();
        INSTANCE = FalseFileFilter.FALSE;
    }
}
