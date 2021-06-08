// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

public class NTCredentials extends UsernamePasswordCredentials
{
    private String domain;
    private String host;
    
    public NTCredentials() {
    }
    
    public NTCredentials(final String userName, final String password, final String host, final String domain) {
        super(userName, password);
        this.domain = domain;
        this.host = host;
    }
    
    public void setDomain(final String domain) {
        this.domain = domain;
    }
    
    public String getDomain() {
        return this.domain;
    }
    
    public void setHost(final String host) {
        this.host = host;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public String toString() {
        final StringBuffer sbResult = new StringBuffer(super.toString());
        sbResult.append(":");
        sbResult.append((this.host == null) ? "null" : this.host);
        sbResult.append((this.domain == null) ? "null" : this.domain);
        return sbResult.toString();
    }
}
