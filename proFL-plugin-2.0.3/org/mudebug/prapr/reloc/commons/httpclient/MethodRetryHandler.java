// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

public interface MethodRetryHandler
{
    boolean retryMethod(final HttpMethod p0, final HttpConnection p1, final HttpRecoverableException p2, final int p3, final boolean p4);
}
