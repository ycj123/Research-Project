// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;

public class TraceMethod extends HttpMethodBase
{
    public TraceMethod(final String uri) {
        super(uri);
        this.setFollowRedirects(false);
    }
    
    public String getName() {
        return "TRACE";
    }
    
    public void recycle() {
        super.recycle();
        this.setFollowRedirects(false);
    }
}
