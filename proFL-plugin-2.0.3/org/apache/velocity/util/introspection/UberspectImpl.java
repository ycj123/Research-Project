// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import org.apache.velocity.runtime.parser.node.SetExecutor;
import org.apache.velocity.runtime.parser.node.PutExecutor;
import org.apache.velocity.runtime.parser.node.MapSetExecutor;
import org.apache.velocity.runtime.parser.node.SetPropertyExecutor;
import org.apache.velocity.runtime.parser.node.AbstractExecutor;
import org.apache.velocity.runtime.parser.node.BooleanPropertyExecutor;
import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.apache.velocity.runtime.parser.node.MapGetExecutor;
import org.apache.velocity.runtime.parser.node.PropertyExecutor;
import java.lang.reflect.Method;
import org.apache.velocity.util.EnumerationIterator;
import java.util.Enumeration;
import java.util.Map;
import java.util.Collection;
import org.apache.velocity.util.ArrayIterator;
import java.util.Iterator;
import org.apache.velocity.runtime.log.RuntimeLoggerLog;
import org.apache.velocity.runtime.RuntimeLogger;
import org.apache.velocity.runtime.log.Log;

public class UberspectImpl implements Uberspect, UberspectLoggable
{
    protected Log log;
    protected Introspector introspector;
    
    public void init() {
        this.introspector = new Introspector(this.log);
    }
    
    public void setLog(final Log log) {
        this.log = log;
    }
    
    public void setRuntimeLogger(final RuntimeLogger runtimeLogger) {
        this.setLog(new RuntimeLoggerLog(runtimeLogger));
    }
    
    public Iterator getIterator(final Object obj, final Info i) throws Exception {
        if (obj.getClass().isArray()) {
            return new ArrayIterator(obj);
        }
        if (obj instanceof Collection) {
            return ((Collection)obj).iterator();
        }
        if (obj instanceof Map) {
            return ((Map)obj).values().iterator();
        }
        if (obj instanceof Iterator) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("The iterative object in the #foreach() loop at " + i + " is of type java.util.Iterator.  Because " + "it is not resettable, if used in more than once it " + "may lead to unexpected results.");
            }
            return (Iterator)obj;
        }
        if (obj instanceof Enumeration) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("The iterative object in the #foreach() loop at " + i + " is of type java.util.Enumeration.  Because " + "it is not resettable, if used in more than once it " + "may lead to unexpected results.");
            }
            return new EnumerationIterator((Enumeration)obj);
        }
        this.log.info("Could not determine type of iterator in #foreach loop at " + i);
        return null;
    }
    
    public VelMethod getMethod(final Object obj, final String methodName, final Object[] args, final Info i) throws Exception {
        if (obj == null) {
            return null;
        }
        final Method m = this.introspector.getMethod(obj.getClass(), methodName, args);
        return (m != null) ? new VelMethodImpl(m) : null;
    }
    
    public VelPropertyGet getPropertyGet(final Object obj, final String identifier, final Info i) throws Exception {
        if (obj == null) {
            return null;
        }
        final Class claz = obj.getClass();
        AbstractExecutor executor = new PropertyExecutor(this.log, this.introspector, claz, identifier);
        if (!executor.isAlive()) {
            executor = new MapGetExecutor(this.log, claz, identifier);
        }
        if (!executor.isAlive()) {
            executor = new GetExecutor(this.log, this.introspector, claz, identifier);
        }
        if (!executor.isAlive()) {
            executor = new BooleanPropertyExecutor(this.log, this.introspector, claz, identifier);
        }
        return executor.isAlive() ? new VelGetterImpl(executor) : null;
    }
    
    public VelPropertySet getPropertySet(final Object obj, final String identifier, final Object arg, final Info i) throws Exception {
        if (obj == null) {
            return null;
        }
        final Class claz = obj.getClass();
        SetExecutor executor = new SetPropertyExecutor(this.log, this.introspector, claz, identifier, arg);
        if (!executor.isAlive()) {
            executor = new MapSetExecutor(this.log, claz, identifier);
        }
        if (!executor.isAlive()) {
            executor = new PutExecutor(this.log, this.introspector, claz, arg, identifier);
        }
        return executor.isAlive() ? new VelSetterImpl(executor) : null;
    }
    
    public static class VelMethodImpl implements VelMethod
    {
        final Method method;
        
        public VelMethodImpl(final Method m) {
            this.method = m;
        }
        
        private VelMethodImpl() {
            this.method = null;
        }
        
        public Object invoke(final Object o, final Object[] params) throws Exception {
            return this.method.invoke(o, params);
        }
        
        public boolean isCacheable() {
            return true;
        }
        
        public String getMethodName() {
            return this.method.getName();
        }
        
        public Class getReturnType() {
            return this.method.getReturnType();
        }
    }
    
    public static class VelGetterImpl implements VelPropertyGet
    {
        final AbstractExecutor getExecutor;
        
        public VelGetterImpl(final AbstractExecutor exec) {
            this.getExecutor = exec;
        }
        
        private VelGetterImpl() {
            this.getExecutor = null;
        }
        
        public Object invoke(final Object o) throws Exception {
            return this.getExecutor.execute(o);
        }
        
        public boolean isCacheable() {
            return true;
        }
        
        public String getMethodName() {
            return this.getExecutor.isAlive() ? this.getExecutor.getMethod().getName() : null;
        }
    }
    
    public static class VelSetterImpl implements VelPropertySet
    {
        private final SetExecutor setExecutor;
        
        public VelSetterImpl(final SetExecutor setExecutor) {
            this.setExecutor = setExecutor;
        }
        
        private VelSetterImpl() {
            this.setExecutor = null;
        }
        
        public Object invoke(final Object o, final Object value) throws Exception {
            return this.setExecutor.execute(o, value);
        }
        
        public boolean isCacheable() {
            return true;
        }
        
        public String getMethodName() {
            return this.setExecutor.isAlive() ? this.setExecutor.getMethod().getName() : null;
        }
    }
}
