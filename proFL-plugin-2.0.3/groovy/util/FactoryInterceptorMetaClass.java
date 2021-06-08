// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.MetaClass;
import groovy.lang.DelegatingMetaClass;

class FactoryInterceptorMetaClass extends DelegatingMetaClass
{
    FactoryBuilderSupport factory;
    
    public FactoryInterceptorMetaClass(final MetaClass delegate, final FactoryBuilderSupport factory) {
        super(delegate);
        this.factory = factory;
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object arguments) {
        try {
            return this.delegate.invokeMethod(object, methodName, arguments);
        }
        catch (MissingMethodException mme) {
            try {
                if (this.factory.getMetaClass().respondsTo(this.factory, methodName).isEmpty()) {
                    return this.factory.invokeMethod(methodName, arguments);
                }
                return InvokerHelper.invokeMethod(this.factory, methodName, arguments);
            }
            catch (MissingMethodException mme2) {
                Throwable root;
                for (root = mme; root.getCause() != null; root = root.getCause()) {}
                root.initCause(mme2);
                throw mme;
            }
        }
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
        try {
            return this.delegate.invokeMethod(object, methodName, arguments);
        }
        catch (MissingMethodException mme) {
            try {
                if (this.factory.getMetaClass().respondsTo(this.factory, methodName).isEmpty()) {
                    return this.factory.invokeMethod(methodName, arguments);
                }
                return InvokerHelper.invokeMethod(this.factory, methodName, arguments);
            }
            catch (MissingMethodException mme2) {
                Throwable root;
                for (root = mme; root.getCause() != null; root = root.getCause()) {}
                root.initCause(mme2);
                throw mme;
            }
        }
    }
}
