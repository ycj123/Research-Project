// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.GroovySystem;
import java.lang.ref.WeakReference;

public class MixedInMetaClass extends OwnedMetaClass
{
    private final WeakReference owner;
    
    public MixedInMetaClass(final Object instance, final Object owner) {
        super(GroovySystem.getMetaClassRegistry().getMetaClass(instance.getClass()));
        this.owner = new WeakReference((T)owner);
        DefaultGroovyMethods.setMetaClass(instance, this);
    }
    
    @Override
    protected Object getOwner() {
        return this.owner.get();
    }
    
    @Override
    protected MetaClass getOwnerMetaClass(final Object owner) {
        return InvokerHelper.getMetaClass(owner);
    }
    
    @Override
    public Object invokeMethod(final Class sender, final Object receiver, final String methodName, final Object[] arguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        if (isCallToSuper) {
            return this.delegate.invokeMethod(sender, receiver, methodName, arguments, true, fromInsideClass);
        }
        return super.invokeMethod(sender, receiver, methodName, arguments, false, fromInsideClass);
    }
}
