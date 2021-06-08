// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import java.util.HashMap;
import java.util.Map;
import com.mks.api.response.modifiable.ModifiableValueList;
import java.util.ArrayList;

public class ValueListImpl extends ArrayList implements ModifiableValueList
{
    private String dataType;
    private Map displayValues;
    
    ValueListImpl() {
        this.displayValues = new HashMap();
    }
    
    public String getDataType() {
        return this.dataType;
    }
    
    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }
    
    public void setDisplayValueOf(final Object value, final String displayValue) {
        if (displayValue == null || value == null) {
            return;
        }
        this.displayValues.put(value, displayValue);
    }
    
    public String getDisplayValueOf(final Object value) {
        return this.displayValues.get(value);
    }
}
