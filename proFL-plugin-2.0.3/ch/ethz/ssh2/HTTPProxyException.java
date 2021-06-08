// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.io.IOException;

public class HTTPProxyException extends IOException
{
    private static final long serialVersionUID = 2241537397104426186L;
    public final String httpResponse;
    public final int httpErrorCode;
    
    public HTTPProxyException(final String httpResponse, final int httpErrorCode) {
        super("HTTP Proxy Error (" + httpErrorCode + " " + httpResponse + ")");
        this.httpResponse = httpResponse;
        this.httpErrorCode = httpErrorCode;
    }
}
