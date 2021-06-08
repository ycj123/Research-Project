// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

public interface InternalWrapperContext
{
    Context getInternalUserContext();
    
    InternalContextAdapter getBaseContext();
    
    Object localPut(final String p0, final Object p1);
}
