// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.List;
import groovy.lang.MissingMethodException;
import java.util.Map;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;

public abstract class BuilderSupport extends GroovyObjectSupport
{
    private Object current;
    private Closure nameMappingClosure;
    private final BuilderSupport proxyBuilder;
    
    public BuilderSupport() {
        this.proxyBuilder = this;
    }
    
    public BuilderSupport(final BuilderSupport proxyBuilder) {
        this(null, proxyBuilder);
    }
    
    public BuilderSupport(final Closure nameMappingClosure, final BuilderSupport proxyBuilder) {
        this.nameMappingClosure = nameMappingClosure;
        this.proxyBuilder = proxyBuilder;
    }
    
    public Object invokeMethod(final String methodName) {
        return this.invokeMethod(methodName, null);
    }
    
    @Override
    public Object invokeMethod(final String methodName, final Object args) {
        final Object name = this.getName(methodName);
        return this.doInvokeMethod(methodName, name, args);
    }
    
    protected Object doInvokeMethod(final String methodName, final Object name, final Object args) {
        Object node = null;
        Closure closure = null;
        final List list = InvokerHelper.asList(args);
        switch (list.size()) {
            case 0: {
                node = this.proxyBuilder.createNode(name);
                break;
            }
            case 1: {
                final Object object = list.get(0);
                if (object instanceof Map) {
                    node = this.proxyBuilder.createNode(name, (Map)object);
                }
                else if (object instanceof Closure) {
                    closure = (Closure)object;
                    node = this.proxyBuilder.createNode(name);
                }
                else {
                    node = this.proxyBuilder.createNode(name, object);
                }
                break;
            }
            case 2: {
                final Object object2 = list.get(0);
                final Object object3 = list.get(1);
                if (object2 instanceof Map) {
                    if (object3 instanceof Closure) {
                        closure = (Closure)object3;
                        node = this.proxyBuilder.createNode(name, (Map)object2);
                    }
                    else {
                        node = this.proxyBuilder.createNode(name, (Map)object2, object3);
                    }
                }
                else if (object3 instanceof Closure) {
                    closure = (Closure)object3;
                    node = this.proxyBuilder.createNode(name, object2);
                }
                else {
                    if (!(object3 instanceof Map)) {
                        throw new MissingMethodException(name.toString(), this.getClass(), list.toArray(), false);
                    }
                    node = this.proxyBuilder.createNode(name, (Map)object3, object2);
                }
                break;
            }
            case 3: {
                final Object arg0 = list.get(0);
                final Object arg2 = list.get(1);
                final Object arg3 = list.get(2);
                if (arg0 instanceof Map && arg3 instanceof Closure) {
                    closure = (Closure)arg3;
                    node = this.proxyBuilder.createNode(name, (Map)arg0, arg2);
                }
                else {
                    if (!(arg2 instanceof Map) || !(arg3 instanceof Closure)) {
                        throw new MissingMethodException(name.toString(), this.getClass(), list.toArray(), false);
                    }
                    closure = (Closure)arg3;
                    node = this.proxyBuilder.createNode(name, (Map)arg2, arg0);
                }
                break;
            }
            default: {
                throw new MissingMethodException(name.toString(), this.getClass(), list.toArray(), false);
            }
        }
        if (this.current != null) {
            this.proxyBuilder.setParent(this.current, node);
        }
        if (closure != null) {
            final Object oldCurrent = this.getCurrent();
            this.setCurrent(node);
            this.setClosureDelegate(closure, node);
            closure.call();
            this.setCurrent(oldCurrent);
        }
        this.proxyBuilder.nodeCompleted(this.current, node);
        return this.proxyBuilder.postNodeCompletion(this.current, node);
    }
    
    protected void setClosureDelegate(final Closure closure, final Object node) {
        closure.setDelegate(this);
    }
    
    protected abstract void setParent(final Object p0, final Object p1);
    
    protected abstract Object createNode(final Object p0);
    
    protected abstract Object createNode(final Object p0, final Object p1);
    
    protected abstract Object createNode(final Object p0, final Map p1);
    
    protected abstract Object createNode(final Object p0, final Map p1, final Object p2);
    
    protected Object getName(final String methodName) {
        if (this.nameMappingClosure != null) {
            return this.nameMappingClosure.call(methodName);
        }
        return methodName;
    }
    
    protected void nodeCompleted(final Object parent, final Object node) {
    }
    
    protected Object postNodeCompletion(final Object parent, final Object node) {
        return node;
    }
    
    protected Object getCurrent() {
        return this.current;
    }
    
    protected void setCurrent(final Object current) {
        this.current = current;
    }
}
