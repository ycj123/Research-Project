// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;

public class DeleteMethod extends HttpMethodBase
{
    public DeleteMethod() {
    }
    
    public DeleteMethod(final String uri) {
        super(uri);
    }
    
    public String getName() {
        return "DELETE";
    }
}
