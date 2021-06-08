// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.util.introspection.IntrospectionCacheData;

public final class InternalContextAdapterImpl implements InternalContextAdapter
{
    Context context;
    InternalHousekeepingContext icb;
    InternalEventContext iec;
    
    public InternalContextAdapterImpl(final Context c) {
        this.context = null;
        this.icb = null;
        this.iec = null;
        this.context = c;
        if (!(c instanceof InternalHousekeepingContext)) {
            this.icb = new InternalContextBase();
        }
        else {
            this.icb = (InternalHousekeepingContext)this.context;
        }
        if (c instanceof InternalEventContext) {
            this.iec = (InternalEventContext)this.context;
        }
    }
    
    public void pushCurrentTemplateName(final String s) {
        this.icb.pushCurrentTemplateName(s);
    }
    
    public void popCurrentTemplateName() {
        this.icb.popCurrentTemplateName();
    }
    
    public String getCurrentTemplateName() {
        return this.icb.getCurrentTemplateName();
    }
    
    public Object[] getTemplateNameStack() {
        return this.icb.getTemplateNameStack();
    }
    
    public IntrospectionCacheData icacheGet(final Object key) {
        return this.icb.icacheGet(key);
    }
    
    public void icachePut(final Object key, final IntrospectionCacheData o) {
        this.icb.icachePut(key, o);
    }
    
    public void setCurrentResource(final Resource r) {
        this.icb.setCurrentResource(r);
    }
    
    public Resource getCurrentResource() {
        return this.icb.getCurrentResource();
    }
    
    public boolean getAllowRendering() {
        return this.icb.getAllowRendering();
    }
    
    public void setAllowRendering(final boolean v) {
        this.icb.setAllowRendering(v);
    }
    
    public Object put(final String key, final Object value) {
        return this.context.put(key, value);
    }
    
    public Object localPut(final String key, final Object value) {
        return this.put(key, value);
    }
    
    public Object get(final String key) {
        return this.context.get(key);
    }
    
    public boolean containsKey(final Object key) {
        return this.context.containsKey(key);
    }
    
    public Object[] getKeys() {
        return this.context.getKeys();
    }
    
    public Object remove(final Object key) {
        return this.context.remove(key);
    }
    
    public Context getInternalUserContext() {
        return this.context;
    }
    
    public InternalContextAdapter getBaseContext() {
        return this;
    }
    
    public EventCartridge attachEventCartridge(final EventCartridge ec) {
        if (this.iec != null) {
            return this.iec.attachEventCartridge(ec);
        }
        return null;
    }
    
    public EventCartridge getEventCartridge() {
        if (this.iec != null) {
            return this.iec.getEventCartridge();
        }
        return null;
    }
}
