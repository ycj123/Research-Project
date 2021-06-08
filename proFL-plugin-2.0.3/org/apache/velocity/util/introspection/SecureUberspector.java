// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.util.Iterator;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.RuntimeServicesAware;

public class SecureUberspector extends UberspectImpl implements RuntimeServicesAware
{
    RuntimeServices runtimeServices;
    
    public void init() {
        final String[] badPackages = this.runtimeServices.getConfiguration().getStringArray("introspector.restrict.packages");
        final String[] badClasses = this.runtimeServices.getConfiguration().getStringArray("introspector.restrict.classes");
        this.introspector = new SecureIntrospectorImpl(badClasses, badPackages, this.log);
    }
    
    public Iterator getIterator(final Object obj, final Info i) throws Exception {
        if (obj != null && !((SecureIntrospectorControl)this.introspector).checkObjectExecutePermission(obj.getClass(), null)) {
            this.log.warn("Cannot retrieve iterator from object of class " + obj.getClass().getName() + " due to security restrictions.");
            return null;
        }
        return super.getIterator(obj, i);
    }
    
    public void setRuntimeServices(final RuntimeServices rs) {
        this.runtimeServices = rs;
    }
}
