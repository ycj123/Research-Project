// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.List;

public interface MetaObjectProtocol
{
    List<MetaProperty> getProperties();
    
    List<MetaMethod> getMethods();
    
    List<MetaMethod> respondsTo(final Object p0, final String p1, final Object[] p2);
    
    List<MetaMethod> respondsTo(final Object p0, final String p1);
    
    MetaProperty hasProperty(final Object p0, final String p1);
    
    MetaProperty getMetaProperty(final String p0);
    
    MetaMethod getStaticMetaMethod(final String p0, final Object[] p1);
    
    MetaMethod getMetaMethod(final String p0, final Object[] p1);
    
    Class getTheClass();
    
    Object invokeConstructor(final Object[] p0);
    
    Object invokeMethod(final Object p0, final String p1, final Object[] p2);
    
    Object invokeMethod(final Object p0, final String p1, final Object p2);
    
    Object invokeStaticMethod(final Object p0, final String p1, final Object[] p2);
    
    Object getProperty(final Object p0, final String p1);
    
    void setProperty(final Object p0, final String p1, final Object p2);
    
    Object getAttribute(final Object p0, final String p1);
    
    void setAttribute(final Object p0, final String p1, final Object p2);
}
