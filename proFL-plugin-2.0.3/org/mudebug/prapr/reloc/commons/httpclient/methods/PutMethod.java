// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

public class PutMethod extends EntityEnclosingMethod
{
    public PutMethod() {
    }
    
    public PutMethod(final String uri) {
        super(uri);
    }
    
    public String getName() {
        return "PUT";
    }
}
