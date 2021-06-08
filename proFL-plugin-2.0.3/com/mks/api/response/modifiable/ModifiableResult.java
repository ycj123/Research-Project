// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.modifiable;

import com.mks.api.response.Result;

public interface ModifiableResult extends Result, ModifiableFieldContainer
{
    void setMessage(final String p0);
}
