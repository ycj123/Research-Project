// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.io.IOException;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import com.mks.api.util.APIVersion;
import com.mks.api.response.APIException;
import com.mks.api.Session;
import java.util.HashSet;
import com.mks.api.IntegrationPointFactory;
import java.util.Set;
import com.mks.api.VersionNumber;
import com.mks.api.IntegrationPoint;

public class IntegrationPointImpl implements IntegrationPoint
{
    private VersionNumber apiVersion;
    private String host;
    private int port;
    private boolean isClientIP;
    private Set sessions;
    private boolean secure;
    private boolean autoStartIC;
    private IntegrationPointFactory ipf;
    
    public IntegrationPointImpl(final IntegrationPointFactory ipf, final String hostname, final int port, final boolean secure, final VersionNumber apiVersion) {
        this.ipf = ipf;
        this.host = hostname;
        this.port = port;
        this.secure = secure;
        this.sessions = new HashSet();
        this.isClientIP = (port == 0);
        this.apiVersion = apiVersion;
    }
    
    public VersionNumber getAPIRequestVersion() {
        return this.apiVersion;
    }
    
    public String getHostname() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    void setPort(final int port) {
        this.port = port;
    }
    
    public boolean isSecure() {
        return this.secure;
    }
    
    public Session createSession() throws APIException {
        return this.createSession(null, null);
    }
    
    public Session createSession(final String username, final String password) throws APIException {
        return this.createSession(this.getAPIRequestVersion(), false, username, password);
    }
    
    public Session createSession(final String username, final String password, final int apiMajorVersion, final int apiMinorVersion) throws APIException {
        return this.createSession(new APIVersion(apiMajorVersion, apiMinorVersion), false, username, password);
    }
    
    private Session createSession(final VersionNumber apiRequestVersion, final boolean anonymous, final String username, final String password) throws APIException {
        final Session uas = new UserApplicationSessionImpl(this, apiRequestVersion, username, password, anonymous);
        synchronized (this.sessions) {
            this.sessions.add(uas);
        }
        return uas;
    }
    
    public Session getCommonSession() throws APIException {
        return this.getCommonSession(null, null);
    }
    
    public Session getCommonSession(final String username, final String password) throws APIException {
        return this.createSession(this.getAPIRequestVersion(), true, username, password);
    }
    
    public Iterator getSessions() {
        return Collections.unmodifiableSet((Set<?>)new HashSet<Object>(this.sessions)).iterator();
    }
    
    public boolean getAutoStartIntegrityClient() {
        return this.autoStartIC;
    }
    
    public void setAutoStartIntegrityClient(final boolean autoStartIC) {
        this.autoStartIC = autoStartIC;
    }
    
    public void release() {
        synchronized (this.sessions) {
            for (final UserApplicationSessionImpl uasi : this.sessions) {
                try {
                    uasi.release(true, false);
                }
                catch (IOException ex) {
                    IntegrationPointFactory.getLogger().exception(this, "API", 0, ex);
                }
                catch (APIException ex2) {
                    IntegrationPointFactory.getLogger().exception(this, "API", 0, ex2);
                }
            }
            this.sessions.removeAll(this.sessions);
        }
        this.ipf.removeIntegrationPoint(this);
    }
    
    protected void removeSession(final Session s) {
        synchronized (this.sessions) {
            this.sessions.remove(s);
        }
    }
    
    public boolean isClientIntegrationPoint() {
        return this.isClientIP;
    }
}
