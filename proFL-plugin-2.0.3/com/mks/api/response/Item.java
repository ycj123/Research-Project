// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.util.Enumeration;

public interface Item extends FieldContainer
{
    String getId();
    
    String getModelType();
    
    String getContext();
    
    String getDisplayId();
    
    String getContext(final String p0);
    
    Enumeration getContextKeys();
    
    String toString();
}
