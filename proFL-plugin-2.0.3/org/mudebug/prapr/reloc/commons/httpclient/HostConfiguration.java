// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.net.InetAddress;
import org.mudebug.prapr.reloc.commons.httpclient.protocol.Protocol;

public class HostConfiguration implements Cloneable
{
    private String host;
    private String virtualHost;
    private int port;
    private Protocol protocol;
    private boolean hostSet;
    private String proxyHost;
    private int proxyPort;
    private boolean proxySet;
    private InetAddress localAddress;
    
    public HostConfiguration() {
        this.host = null;
        this.virtualHost = null;
        this.port = -1;
        this.protocol = null;
        this.hostSet = false;
        this.proxyHost = null;
        this.proxyPort = -1;
        this.proxySet = false;
        this.localAddress = null;
    }
    
    public HostConfiguration(final HostConfiguration hostConfiguration) {
        synchronized (hostConfiguration) {
            this.host = hostConfiguration.getHost();
            this.virtualHost = hostConfiguration.getVirtualHost();
            this.port = hostConfiguration.getPort();
            this.protocol = hostConfiguration.getProtocol();
            this.hostSet = hostConfiguration.isHostSet();
            this.proxyHost = hostConfiguration.getProxyHost();
            this.proxyPort = hostConfiguration.getProxyPort();
            this.proxySet = hostConfiguration.isProxySet();
            this.localAddress = hostConfiguration.getLocalAddress();
        }
    }
    
    public Object clone() {
        return new HostConfiguration(this);
    }
    
    public synchronized String toString() {
        boolean appendComma = false;
        final StringBuffer b = new StringBuffer(50);
        b.append("HostConfiguration[");
        if (this.isHostSet()) {
            appendComma = true;
            b.append("host=").append(this.host);
            b.append(", protocol=").append(this.protocol);
            b.append(", port=").append(this.port);
            if (this.virtualHost != null) {
                b.append(", virtualHost=").append(this.virtualHost);
            }
        }
        if (this.isProxySet()) {
            if (appendComma) {
                b.append(", ");
            }
            else {
                appendComma = true;
            }
            b.append("proxyHost=").append(this.proxyHost);
            b.append(", proxyPort=").append(this.proxyPort);
        }
        if (this.localAddress != null) {
            if (appendComma) {
                b.append(", ");
            }
            else {
                appendComma = true;
            }
            b.append("localAddress=").append(this.localAddress);
        }
        b.append("]");
        return b.toString();
    }
    
    public synchronized boolean hostEquals(final HttpConnection connection) {
        if (!this.hostSet) {
            return false;
        }
        if (!this.host.equalsIgnoreCase(connection.getHost())) {
            return false;
        }
        if (this.virtualHost != null) {
            if (!this.virtualHost.equalsIgnoreCase(connection.getVirtualHost())) {
                return false;
            }
        }
        else if (connection.getVirtualHost() != null) {
            return false;
        }
        if (this.port != connection.getPort()) {
            return false;
        }
        if (!this.protocol.equals(connection.getProtocol())) {
            return false;
        }
        if (this.localAddress != null) {
            if (!this.localAddress.equals(connection.getLocalAddress())) {
                return false;
            }
        }
        else if (connection.getLocalAddress() != null) {
            return false;
        }
        return true;
    }
    
    public synchronized boolean proxyEquals(final HttpConnection connection) {
        if (this.proxyHost == null) {
            return connection.getProxyHost() == null;
        }
        return this.proxyHost.equalsIgnoreCase(connection.getProxyHost()) && this.proxyPort == connection.getProxyPort();
    }
    
    public synchronized boolean isHostSet() {
        return this.hostSet;
    }
    
    public synchronized void setHost(final String host, final int port, final String protocol) {
        this.setHost(host, null, port, Protocol.getProtocol(protocol));
    }
    
    public synchronized void setHost(final String host, final String virtualHost, final int port, final Protocol protocol) {
        if (host == null) {
            throw new IllegalArgumentException("host must not be null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("protocol must not be null");
        }
        this.host = host;
        this.virtualHost = virtualHost;
        this.port = ((port == -1) ? protocol.getDefaultPort() : port);
        this.protocol = protocol;
        this.hostSet = true;
    }
    
    public synchronized void setHost(final String host, final int port, final Protocol protocol) {
        this.setHost(host, null, port, protocol);
    }
    
    public synchronized void setHost(final String host, final int port) {
        this.setHost(host, null, port, Protocol.getProtocol("http"));
    }
    
    public synchronized void setHost(final String host) {
        final Protocol defaultProtocol = Protocol.getProtocol("http");
        this.setHost(host, null, defaultProtocol.getDefaultPort(), defaultProtocol);
    }
    
    public synchronized void setHost(final URI uri) {
        try {
            this.setHost(uri.getHost(), uri.getPort(), uri.getScheme());
        }
        catch (URIException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }
    
    public synchronized String getHostURL() {
        if (!this.hostSet) {
            throw new IllegalStateException("a default host must be set to create a host URL");
        }
        String url = this.protocol.getScheme() + "://" + this.host;
        if (this.port != -1 && this.port != this.protocol.getDefaultPort()) {
            url = url + ":" + this.port;
        }
        return url;
    }
    
    public synchronized String getHost() {
        return this.host;
    }
    
    public synchronized String getVirtualHost() {
        return this.virtualHost;
    }
    
    public synchronized int getPort() {
        return this.port;
    }
    
    public synchronized Protocol getProtocol() {
        return this.protocol;
    }
    
    public synchronized boolean isProxySet() {
        return this.proxySet;
    }
    
    public synchronized void setProxy(final String proxyHost, final int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxySet = true;
    }
    
    public synchronized String getProxyHost() {
        return this.proxyHost;
    }
    
    public synchronized int getProxyPort() {
        return this.proxyPort;
    }
    
    public synchronized void setLocalAddress(final InetAddress localAddress) {
        this.localAddress = localAddress;
    }
    
    public synchronized InetAddress getLocalAddress() {
        return this.localAddress;
    }
    
    public synchronized boolean equals(final Object o) {
        if (!(o instanceof HostConfiguration)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        final HostConfiguration config = (HostConfiguration)o;
        if (this.hostSet) {
            if (!this.host.equalsIgnoreCase(config.getHost())) {
                return false;
            }
            if (this.virtualHost != null) {
                if (!this.virtualHost.equalsIgnoreCase(config.getVirtualHost())) {
                    return false;
                }
            }
            else if (config.getVirtualHost() != null) {
                return false;
            }
            if (this.port != config.getPort()) {
                return false;
            }
            if (!this.protocol.equals(config.getProtocol())) {
                return false;
            }
        }
        else if (config.isHostSet()) {
            return false;
        }
        if (this.proxyHost != null) {
            if (!this.proxyHost.equalsIgnoreCase(config.getProxyHost()) || this.proxyPort != config.getProxyPort()) {
                return false;
            }
        }
        else if (config.getProxyHost() != null) {
            return false;
        }
        if (this.localAddress != null) {
            if (!this.localAddress.equals(config.getLocalAddress())) {
                return false;
            }
        }
        else if (config.getLocalAddress() != null) {
            return false;
        }
        return true;
    }
    
    public int hashCode() {
        if (this.host != null) {
            return this.host.hashCode();
        }
        if (this.proxyHost != null) {
            return this.proxyHost.hashCode();
        }
        return super.hashCode();
    }
}
