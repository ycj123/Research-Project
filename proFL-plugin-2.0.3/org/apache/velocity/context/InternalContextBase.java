// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

import org.apache.velocity.util.introspection.IntrospectionCacheData;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.app.event.EventCartridge;
import java.util.Stack;
import java.util.HashMap;

class InternalContextBase implements InternalHousekeepingContext, InternalEventContext
{
    private static final long serialVersionUID = -245905472770843470L;
    private HashMap introspectionCache;
    private Stack templateNameStack;
    private EventCartridge eventCartridge;
    private Resource currentResource;
    private boolean allowRendering;
    
    InternalContextBase() {
        this.introspectionCache = new HashMap(33);
        this.templateNameStack = new Stack();
        this.eventCartridge = null;
        this.currentResource = null;
        this.allowRendering = true;
    }
    
    public void pushCurrentTemplateName(final String s) {
        this.templateNameStack.push(s);
    }
    
    public void popCurrentTemplateName() {
        this.templateNameStack.pop();
    }
    
    public String getCurrentTemplateName() {
        if (this.templateNameStack.empty()) {
            return "<undef>";
        }
        return this.templateNameStack.peek();
    }
    
    public Object[] getTemplateNameStack() {
        return this.templateNameStack.toArray();
    }
    
    public IntrospectionCacheData icacheGet(final Object key) {
        return this.introspectionCache.get(key);
    }
    
    public void icachePut(final Object key, final IntrospectionCacheData o) {
        this.introspectionCache.put(key, o);
    }
    
    public void setCurrentResource(final Resource r) {
        this.currentResource = r;
    }
    
    public Resource getCurrentResource() {
        return this.currentResource;
    }
    
    public boolean getAllowRendering() {
        return this.allowRendering;
    }
    
    public void setAllowRendering(final boolean v) {
        this.allowRendering = v;
    }
    
    public EventCartridge attachEventCartridge(final EventCartridge ec) {
        final EventCartridge temp = this.eventCartridge;
        this.eventCartridge = ec;
        return temp;
    }
    
    public EventCartridge getEventCartridge() {
        return this.eventCartridge;
    }
}
