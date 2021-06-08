// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.modifiable;

import com.mks.api.response.Field;

public interface ModifiableField extends Field
{
    void setValue(final Object p0);
    
    void setModelType(final String p0);
    
    void setDataType(final String p0);
    
    void setDisplayValue(final String p0);
}
