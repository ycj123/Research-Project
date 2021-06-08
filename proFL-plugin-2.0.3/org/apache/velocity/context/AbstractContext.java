// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

public abstract class AbstractContext extends InternalContextBase implements Context
{
    private Context innerContext;
    
    public abstract Object internalGet(final String p0);
    
    public abstract Object internalPut(final String p0, final Object p1);
    
    public abstract boolean internalContainsKey(final Object p0);
    
    public abstract Object[] internalGetKeys();
    
    public abstract Object internalRemove(final Object p0);
    
    public AbstractContext() {
        this.innerContext = null;
    }
    
    public AbstractContext(final Context inner) {
        this.innerContext = null;
        this.innerContext = inner;
        if (this.innerContext instanceof InternalEventContext) {
            this.attachEventCartridge(((InternalEventContext)this.innerContext).getEventCartridge());
        }
    }
    
    public Object put(final String key, final Object value) {
        if (key == null) {
            return null;
        }
        if (value == null) {
            return null;
        }
        return this.internalPut(key, value);
    }
    
    public Object get(final String key) {
        if (key == null) {
            return null;
        }
        Object o = this.internalGet(key);
        if (o == null && this.innerContext != null) {
            o = this.innerContext.get(key);
        }
        return o;
    }
    
    public boolean containsKey(final Object key) {
        return key != null && this.internalContainsKey(key);
    }
    
    public Object[] getKeys() {
        return this.internalGetKeys();
    }
    
    public Object remove(final Object key) {
        if (key == null) {
            return null;
        }
        return this.internalRemove(key);
    }
    
    public Context getChainedContext() {
        return this.innerContext;
    }
}
