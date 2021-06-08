// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.util.introspection.IntrospectionCacheData;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.runtime.directive.VMProxyArg;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.HashMap;

public class VMContext implements InternalContextAdapter
{
    HashMap vmproxyhash;
    HashMap localcontext;
    InternalContextAdapter innerContext;
    InternalContextAdapter wrappedContext;
    private boolean localcontextscope;
    
    public VMContext(final InternalContextAdapter inner, final RuntimeServices rsvc) {
        this.vmproxyhash = new HashMap();
        this.localcontext = new HashMap();
        this.innerContext = null;
        this.wrappedContext = null;
        this.localcontextscope = false;
        this.localcontextscope = rsvc.getBoolean("velocimacro.context.localscope", false);
        this.wrappedContext = inner;
        this.innerContext = inner.getBaseContext();
    }
    
    public Context getInternalUserContext() {
        return this.innerContext.getInternalUserContext();
    }
    
    public InternalContextAdapter getBaseContext() {
        return this.innerContext.getBaseContext();
    }
    
    public void addVMProxyArg(final VMProxyArg vmpa) throws MethodInvocationException {
        final String key = vmpa.getContextReference();
        if (vmpa.isConstant()) {
            this.localcontext.put(key, vmpa.getObject(this.wrappedContext));
        }
        else {
            this.vmproxyhash.put(key, vmpa);
        }
    }
    
    public Object put(final String key, final Object value) {
        return this.put(key, value, this.localcontextscope);
    }
    
    public Object localPut(final String key, final Object value) {
        return this.put(key, value, true);
    }
    
    protected Object put(final String key, final Object value, final boolean forceLocal) {
        final VMProxyArg vmpa = this.vmproxyhash.get(key);
        if (vmpa != null) {
            return vmpa.setObject(this.wrappedContext, value);
        }
        if (forceLocal) {
            return this.localcontext.put(key, value);
        }
        if (this.localcontext.containsKey(key)) {
            return this.localcontext.put(key, value);
        }
        return this.innerContext.put(key, value);
    }
    
    public Object get(final String key) {
        Object o = null;
        final VMProxyArg vmpa = this.vmproxyhash.get(key);
        if (vmpa != null) {
            o = vmpa.getObject(this.wrappedContext);
        }
        else {
            o = this.localcontext.get(key);
            if (o == null) {
                o = this.innerContext.get(key);
            }
        }
        return o;
    }
    
    public boolean containsKey(final Object key) {
        return false;
    }
    
    public Object[] getKeys() {
        return this.vmproxyhash.keySet().toArray();
    }
    
    public Object remove(final Object key) {
        return this.vmproxyhash.remove(key);
    }
    
    public void pushCurrentTemplateName(final String s) {
        this.innerContext.pushCurrentTemplateName(s);
    }
    
    public void popCurrentTemplateName() {
        this.innerContext.popCurrentTemplateName();
    }
    
    public String getCurrentTemplateName() {
        return this.innerContext.getCurrentTemplateName();
    }
    
    public Object[] getTemplateNameStack() {
        return this.innerContext.getTemplateNameStack();
    }
    
    public IntrospectionCacheData icacheGet(final Object key) {
        return this.innerContext.icacheGet(key);
    }
    
    public void icachePut(final Object key, final IntrospectionCacheData o) {
        this.innerContext.icachePut(key, o);
    }
    
    public boolean getAllowRendering() {
        return this.innerContext.getAllowRendering();
    }
    
    public void setAllowRendering(final boolean v) {
        this.innerContext.setAllowRendering(v);
    }
    
    public EventCartridge attachEventCartridge(final EventCartridge ec) {
        final EventCartridge cartridge = this.innerContext.attachEventCartridge(ec);
        return cartridge;
    }
    
    public EventCartridge getEventCartridge() {
        return this.innerContext.getEventCartridge();
    }
    
    public void setCurrentResource(final Resource r) {
        this.innerContext.setCurrentResource(r);
    }
    
    public Resource getCurrentResource() {
        return this.innerContext.getCurrentResource();
    }
}
