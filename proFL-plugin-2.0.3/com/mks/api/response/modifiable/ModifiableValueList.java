// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.modifiable;

import com.mks.api.response.ValueList;

public interface ModifiableValueList extends ValueList
{
    void setDataType(final String p0);
    
    void setDisplayValueOf(final Object p0, final String p1);
}
