// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.util.List;

public interface ValueList extends List
{
    String getDataType();
    
    String getDisplayValueOf(final Object p0);
}
