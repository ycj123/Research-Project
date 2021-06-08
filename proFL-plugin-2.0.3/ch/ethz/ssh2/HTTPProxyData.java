// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public class HTTPProxyData implements ProxyData
{
    public final String proxyHost;
    public final int proxyPort;
    public final String proxyUser;
    public final String proxyPass;
    public final String[] requestHeaderLines;
    
    public HTTPProxyData(final String proxyHost, final int proxyPort) {
        this(proxyHost, proxyPort, null, null);
    }
    
    public HTTPProxyData(final String proxyHost, final int proxyPort, final String proxyUser, final String proxyPass) {
        this(proxyHost, proxyPort, proxyUser, proxyPass, null);
    }
    
    public HTTPProxyData(final String proxyHost, final int proxyPort, final String proxyUser, final String proxyPass, final String[] requestHeaderLines) {
        if (proxyHost == null) {
            throw new IllegalArgumentException("proxyHost must be non-null");
        }
        if (proxyPort < 0) {
            throw new IllegalArgumentException("proxyPort must be non-negative");
        }
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;
        this.proxyPass = proxyPass;
        this.requestHeaderLines = requestHeaderLines;
    }
}
