// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.Closure;
import java.util.Map;

public abstract class AbstractFactory implements Factory
{
    public boolean isLeaf() {
        return false;
    }
    
    public boolean isHandlesNodeChildren() {
        return false;
    }
    
    public void onFactoryRegistration(final FactoryBuilderSupport builder, final String registerdName, final String group) {
    }
    
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map attributes) {
        return true;
    }
    
    public boolean onNodeChildren(final FactoryBuilderSupport builder, final Object node, final Closure childContent) {
        return true;
    }
    
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parent, final Object node) {
    }
    
    public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
    }
    
    public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
    }
}
