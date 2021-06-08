// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.Closure;
import java.util.Map;

public interface Factory
{
    boolean isLeaf();
    
    boolean isHandlesNodeChildren();
    
    void onFactoryRegistration(final FactoryBuilderSupport p0, final String p1, final String p2);
    
    Object newInstance(final FactoryBuilderSupport p0, final Object p1, final Object p2, final Map p3) throws InstantiationException, IllegalAccessException;
    
    boolean onHandleNodeAttributes(final FactoryBuilderSupport p0, final Object p1, final Map p2);
    
    boolean onNodeChildren(final FactoryBuilderSupport p0, final Object p1, final Closure p2);
    
    void onNodeCompleted(final FactoryBuilderSupport p0, final Object p1, final Object p2);
    
    void setParent(final FactoryBuilderSupport p0, final Object p1, final Object p2);
    
    void setChild(final FactoryBuilderSupport p0, final Object p1, final Object p2);
}
