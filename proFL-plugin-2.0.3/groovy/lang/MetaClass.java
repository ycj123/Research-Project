// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.ast.ClassNode;
import java.util.List;

public interface MetaClass extends MetaObjectProtocol
{
    Object invokeMethod(final Class p0, final Object p1, final String p2, final Object[] p3, final boolean p4, final boolean p5);
    
    Object getProperty(final Class p0, final Object p1, final String p2, final boolean p3, final boolean p4);
    
    void setProperty(final Class p0, final Object p1, final String p2, final Object p3, final boolean p4, final boolean p5);
    
    Object invokeMissingMethod(final Object p0, final String p1, final Object[] p2);
    
    Object invokeMissingProperty(final Object p0, final String p1, final Object p2, final boolean p3);
    
    Object getAttribute(final Class p0, final Object p1, final String p2, final boolean p3);
    
    void setAttribute(final Class p0, final Object p1, final String p2, final Object p3, final boolean p4, final boolean p5);
    
    void initialize();
    
    List<MetaProperty> getProperties();
    
    List<MetaMethod> getMethods();
    
    ClassNode getClassNode();
    
    List<MetaMethod> getMetaMethods();
    
    int selectConstructorAndTransformArguments(final int p0, final Object[] p1);
    
    MetaMethod pickMethod(final String p0, final Class[] p1);
}
