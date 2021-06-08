// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import java.util.Iterator;
import groovy.lang.Closure;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import groovy.lang.GroovyObjectSupport;

public abstract class Builder extends GroovyObjectSupport
{
    protected final Map namespaceMethodMap;
    
    public Builder(final Map namespaceMethodMap) {
        this.namespaceMethodMap = new HashMap();
        for (final Object key : namespaceMethodMap.keySet()) {
            final List value = namespaceMethodMap.get(key);
            final Closure dg = value.get(1).asWritable();
            this.namespaceMethodMap.put(key, new Object[] { value.get(0), dg, fettleMethodMap(dg, value.get(2)) });
        }
    }
    
    private static Map fettleMethodMap(final Closure defaultGenerator, final Map methodMap) {
        final Map newMethodMap = new HashMap();
        for (final Object key : methodMap.keySet()) {
            final Object o = key;
            final Object value = methodMap.get(key);
            if (value instanceof Closure) {
                newMethodMap.put(key, value);
            }
            else {
                newMethodMap.put(key, defaultGenerator.curry((Object[])value));
            }
        }
        return newMethodMap;
    }
    
    public abstract Object bind(final Closure p0);
    
    protected abstract static class Built extends GroovyObjectSupport
    {
        protected final Closure root;
        protected final Map namespaceSpecificTags;
        
        public Built(final Closure root, final Map namespaceTagMap) {
            (this.namespaceSpecificTags = new HashMap()).putAll(namespaceTagMap);
            (this.root = (Closure)root.clone()).setDelegate(this);
        }
    }
}
