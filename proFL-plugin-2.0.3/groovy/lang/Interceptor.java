// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public interface Interceptor
{
    Object beforeInvoke(final Object p0, final String p1, final Object[] p2);
    
    Object afterInvoke(final Object p0, final String p1, final Object[] p2, final Object p3);
    
    boolean doInvoke();
}
