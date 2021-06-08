// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.lang.reflect.Proxy;
import groovy.lang.GroovySystem;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyObject;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import groovy.lang.MetaClass;
import java.lang.reflect.InvocationHandler;

public final class GroovyResultSetProxy implements InvocationHandler
{
    private GroovyResultSetExtension extension;
    private MetaClass metaClass;
    
    public GroovyResultSetProxy(final ResultSet set) {
        this.extension = new GroovyResultSetExtension(set);
    }
    
    public GroovyResultSetProxy(final GroovyResultSetExtension ext) {
        this.extension = ext;
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String name = method.getName();
        if (method.getDeclaringClass() == GroovyObject.class) {
            if (name.equals("getMetaClass")) {
                return this.getMetaClass();
            }
            if (name.equals("setMetaClass")) {
                return this.setMetaClass((MetaClass)args[0]);
            }
        }
        return InvokerHelper.invokeMethod(this.extension, method.getName(), args);
    }
    
    private MetaClass setMetaClass(final MetaClass mc) {
        return this.metaClass = mc;
    }
    
    private MetaClass getMetaClass() {
        if (this.metaClass == null) {
            this.metaClass = GroovySystem.getMetaClassRegistry().getMetaClass(GroovyResultSet.class);
        }
        return this.metaClass;
    }
    
    public GroovyResultSet getImpl() {
        return (GroovyResultSet)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { GroovyResultSet.class }, this);
    }
}
