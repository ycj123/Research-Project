// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.MetaClassImpl;
import groovy.lang.MetaMethod;

public abstract class CallSiteAwareMetaMethod extends MetaMethod
{
    public abstract CallSite createPojoCallSite(final CallSite p0, final MetaClassImpl p1, final MetaMethod p2, final Class[] p3, final Object p4, final Object[] p5);
}
