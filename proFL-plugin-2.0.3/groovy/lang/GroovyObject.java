// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public interface GroovyObject
{
    Object invokeMethod(final String p0, final Object p1);
    
    Object getProperty(final String p0);
    
    void setProperty(final String p0, final Object p1);
    
    MetaClass getMetaClass();
    
    void setMetaClass(final MetaClass p0);
}
