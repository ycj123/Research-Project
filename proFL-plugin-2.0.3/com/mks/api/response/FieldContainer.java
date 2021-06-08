// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.util.Iterator;

public interface FieldContainer
{
    Field getField(final String p0);
    
    Iterator getFields();
    
    int getFieldListSize();
    
    boolean contains(final String p0);
}
