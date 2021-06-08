// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public interface PropertyAccessInterceptor extends Interceptor
{
    Object beforeGet(final Object p0, final String p1);
    
    void beforeSet(final Object p0, final String p1, final Object p2);
}
