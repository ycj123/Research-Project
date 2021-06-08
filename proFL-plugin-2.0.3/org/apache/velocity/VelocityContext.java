// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity;

import java.util.HashMap;
import org.apache.velocity.context.Context;
import java.util.Map;
import org.apache.velocity.context.AbstractContext;

public class VelocityContext extends AbstractContext implements Cloneable
{
    private static final long serialVersionUID = 9033846851064645037L;
    private Map context;
    
    public VelocityContext() {
        this(null, null);
    }
    
    public VelocityContext(final Map context) {
        this(context, null);
    }
    
    public VelocityContext(final Context innerContext) {
        this(null, innerContext);
    }
    
    public VelocityContext(final Map context, final Context innerContext) {
        super(innerContext);
        this.context = null;
        this.context = ((context == null) ? new HashMap() : context);
    }
    
    public Object internalGet(final String key) {
        return this.context.get(key);
    }
    
    public Object internalPut(final String key, final Object value) {
        return this.context.put(key, value);
    }
    
    public boolean internalContainsKey(final Object key) {
        return this.context.containsKey(key);
    }
    
    public Object[] internalGetKeys() {
        return this.context.keySet().toArray();
    }
    
    public Object internalRemove(final Object key) {
        return this.context.remove(key);
    }
    
    public Object clone() {
        VelocityContext clone = null;
        try {
            clone = (VelocityContext)super.clone();
            clone.context = new HashMap(this.context);
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
}
