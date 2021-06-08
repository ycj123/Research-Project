// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyObject;
import java.util.concurrent.atomic.AtomicInteger;

public interface CallSite
{
    CallSiteArray getArray();
    
    int getIndex();
    
    String getName();
    
    AtomicInteger getUsage();
    
    Object getProperty(final Object p0) throws Throwable;
    
    Object callGetPropertySafe(final Object p0) throws Throwable;
    
    Object callGetProperty(final Object p0) throws Throwable;
    
    Object callGroovyObjectGetProperty(final Object p0) throws Throwable;
    
    Object callGroovyObjectGetPropertySafe(final Object p0) throws Throwable;
    
    Object call(final Object p0, final Object[] p1) throws Throwable;
    
    Object call(final Object p0) throws Throwable;
    
    Object call(final Object p0, final Object p1) throws Throwable;
    
    Object call(final Object p0, final Object p1, final Object p2) throws Throwable;
    
    Object call(final Object p0, final Object p1, final Object p2, final Object p3) throws Throwable;
    
    Object call(final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) throws Throwable;
    
    Object callSafe(final Object p0, final Object[] p1) throws Throwable;
    
    Object callSafe(final Object p0) throws Throwable;
    
    Object callSafe(final Object p0, final Object p1) throws Throwable;
    
    Object callSafe(final Object p0, final Object p1, final Object p2) throws Throwable;
    
    Object callSafe(final Object p0, final Object p1, final Object p2, final Object p3) throws Throwable;
    
    Object callSafe(final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) throws Throwable;
    
    Object callCurrent(final GroovyObject p0, final Object[] p1) throws Throwable;
    
    Object callCurrent(final GroovyObject p0) throws Throwable;
    
    Object callCurrent(final GroovyObject p0, final Object p1) throws Throwable;
    
    Object callCurrent(final GroovyObject p0, final Object p1, final Object p2) throws Throwable;
    
    Object callCurrent(final GroovyObject p0, final Object p1, final Object p2, final Object p3) throws Throwable;
    
    Object callCurrent(final GroovyObject p0, final Object p1, final Object p2, final Object p3, final Object p4) throws Throwable;
    
    Object callStatic(final Class p0, final Object[] p1) throws Throwable;
    
    Object callStatic(final Class p0) throws Throwable;
    
    Object callStatic(final Class p0, final Object p1) throws Throwable;
    
    Object callStatic(final Class p0, final Object p1, final Object p2) throws Throwable;
    
    Object callStatic(final Class p0, final Object p1, final Object p2, final Object p3) throws Throwable;
    
    Object callStatic(final Class p0, final Object p1, final Object p2, final Object p3, final Object p4) throws Throwable;
    
    Object callConstructor(final Object p0, final Object[] p1) throws Throwable;
    
    Object callConstructor(final Object p0) throws Throwable;
    
    Object callConstructor(final Object p0, final Object p1) throws Throwable;
    
    Object callConstructor(final Object p0, final Object p1, final Object p2) throws Throwable;
    
    Object callConstructor(final Object p0, final Object p1, final Object p2, final Object p3) throws Throwable;
    
    Object callConstructor(final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) throws Throwable;
}
