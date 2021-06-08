// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import com.mks.api.response.CommandAlreadyRunningException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunner;
import java.util.HashSet;
import java.util.Set;
import com.mks.api.CmdRunnerCreator;

public abstract class CmdRunnerCreatorImpl implements CmdRunnerCreator
{
    private Set cmdRunners;
    private String defaultHost;
    private int defaultPort;
    private String defaultUser;
    private String defaultPass;
    private String impUser;
    
    public CmdRunnerCreatorImpl() {
        this.cmdRunners = new HashSet(5);
    }
    
    public String getDefaultHostname() {
        return this.defaultHost;
    }
    
    public void setDefaultHostname(final String host) {
        this.defaultHost = host;
    }
    
    public int getDefaultPort() {
        return this.defaultPort;
    }
    
    public void setDefaultPort(final int port) {
        this.defaultPort = port;
    }
    
    public String getDefaultUsername() {
        return this.defaultUser;
    }
    
    public void setDefaultUsername(final String user) {
        this.defaultUser = user;
    }
    
    public String getDefaultPassword() {
        return this.defaultPass;
    }
    
    public void setDefaultPassword(final String pass) {
        this.defaultPass = pass;
    }
    
    public void setDefaultImpersonationUser(final String impUser) {
        this.impUser = impUser;
    }
    
    public String getDefaultImpersonationUser() {
        return this.impUser;
    }
    
    protected abstract CmdRunner _createCmdRunner() throws APIException;
    
    public final CmdRunner createCmdRunner() throws APIException {
        final CmdRunner cmdRunner = this._createCmdRunner();
        synchronized (this.cmdRunners) {
            this.cmdRunners.add(cmdRunner);
        }
        if (this.defaultHost != null) {
            cmdRunner.setDefaultHostname(this.defaultHost);
        }
        if (this.defaultPort > 0) {
            cmdRunner.setDefaultPort(this.defaultPort);
        }
        if (this.defaultUser != null) {
            cmdRunner.setDefaultUsername(this.defaultUser);
        }
        if (this.defaultPass != null) {
            cmdRunner.setDefaultPassword(this.defaultPass);
        }
        if (this.impUser != null) {
            cmdRunner.setDefaultImpersonationUser(this.impUser);
        }
        return cmdRunner;
    }
    
    public final Iterator getCmdRunners() {
        final Set s;
        synchronized (this.cmdRunners) {
            s = (this.cmdRunners.isEmpty() ? Collections.EMPTY_SET : Collections.unmodifiableSet((Set<?>)new HashSet<Object>(this.cmdRunners)));
        }
        return s.iterator();
    }
    
    public final void release() throws IOException, APIException {
        this.release(false);
    }
    
    public void release(final boolean force) throws IOException, APIException {
        synchronized (this.cmdRunners) {
            for (final CmdRunner aci : this.cmdRunners) {
                if (!aci.isFinished()) {
                    if (!force) {
                        throw new CommandAlreadyRunningException();
                    }
                    aci.interrupt();
                }
            }
            this.cmdRunners.clear();
        }
    }
    
    protected void removeCmdRunner(final CmdRunner c) {
        synchronized (this.cmdRunners) {
            this.cmdRunners.remove(c);
        }
    }
}
