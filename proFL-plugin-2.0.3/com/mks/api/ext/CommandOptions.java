// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

import java.util.Iterator;

public interface CommandOptions
{
    Iterator options();
    
    boolean isOptionSet(final String p0);
    
    String getOptionValue(final String p0);
}
