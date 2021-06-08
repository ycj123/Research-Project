// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.util.introspection.IntrospectionCacheData;

interface InternalHousekeepingContext
{
    void pushCurrentTemplateName(final String p0);
    
    void popCurrentTemplateName();
    
    String getCurrentTemplateName();
    
    Object[] getTemplateNameStack();
    
    IntrospectionCacheData icacheGet(final Object p0);
    
    void icachePut(final Object p0, final IntrospectionCacheData p1);
    
    Resource getCurrentResource();
    
    void setCurrentResource(final Resource p0);
    
    boolean getAllowRendering();
    
    void setAllowRendering(final boolean p0);
}
