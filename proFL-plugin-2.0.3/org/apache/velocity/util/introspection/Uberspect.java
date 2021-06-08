// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.util.Iterator;

public interface Uberspect
{
    void init() throws Exception;
    
    Iterator getIterator(final Object p0, final Info p1) throws Exception;
    
    VelMethod getMethod(final Object p0, final String p1, final Object[] p2, final Info p3) throws Exception;
    
    VelPropertyGet getPropertyGet(final Object p0, final String p1, final Info p2) throws Exception;
    
    VelPropertySet getPropertySet(final Object p0, final String p1, final Object p2, final Info p3) throws Exception;
}
