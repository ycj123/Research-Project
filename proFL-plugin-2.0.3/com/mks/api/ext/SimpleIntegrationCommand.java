// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

import com.mks.api.Command;
import com.mks.api.response.WorkItem;
import com.mks.api.response.Response;
import java.util.Iterator;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.CmdRunner;
import com.mks.api.response.APIException;
import com.mks.api.response.APIInternalError;
import com.mks.api.util.APIVersion;
import com.mks.api.CmdRunnerCreator;
import com.mks.api.IntegrationPoint;
import com.mks.api.VersionNumber;

public abstract class SimpleIntegrationCommand implements VersionedIntegrationCommand
{
    private VersionNumber apiVersion;
    private IntegrationPoint localIP;
    private CmdRunnerCreator cmdFactory;
    
    protected SimpleIntegrationCommand() {
    }
    
    protected SimpleIntegrationCommand(final int apiMajorNumber, final int apiMinorNumber) {
        this.apiVersion = new APIVersion(apiMajorNumber, apiMinorNumber);
    }
    
    public final VersionNumber getAPIExecutionVersion() {
        return this.apiVersion;
    }
    
    protected final VersionNumber getAPIRequestVersion() {
        if (this.localIP == null) {
            return null;
        }
        return this.localIP.getAPIRequestVersion();
    }
    
    public void setDefaultHostname(final String host) {
        if (this.cmdFactory == null) {
            throw new APIInternalError("CmdRunnerCreator not initialized");
        }
        this.cmdFactory.setDefaultHostname(host);
    }
    
    public void setDefaultImpersonationUser(final String impUser) {
        if (this.cmdFactory == null) {
            throw new APIInternalError("CmdRunnerCreator not initialized");
        }
        this.cmdFactory.setDefaultImpersonationUser(impUser);
    }
    
    public void setDefaultPassword(final String pass) {
        if (this.cmdFactory == null) {
            throw new APIInternalError("CmdRunnerCreator not initialized");
        }
        this.cmdFactory.setDefaultPassword(pass);
    }
    
    public void setDefaultPort(final int port) {
        if (this.cmdFactory == null) {
            throw new APIInternalError("CmdRunnerCreator not initialized");
        }
        this.cmdFactory.setDefaultPort(port);
    }
    
    public void setDefaultUsername(final String user) {
        if (this.cmdFactory == null) {
            throw new APIInternalError("CmdRunnerCreator not initialized");
        }
        this.cmdFactory.setDefaultUsername(user);
    }
    
    public IntegrationPoint getLocalIntegrationPoint() {
        return this.localIP;
    }
    
    protected CmdRunnerCreator getLocalCmdRunnerCreator() {
        return this.cmdFactory;
    }
    
    protected int getExitCode() {
        return 0;
    }
    
    public final int execute(final IntegrationPoint localIP, final CmdRunnerCreator cmdFactory, final ResponseWriter apiout, final CommandOptions options, final CommandSelection selection) throws APIException {
        try {
            this.localIP = localIP;
            this.cmdFactory = cmdFactory;
            this.apiVersion = cmdFactory.getAPIRequestVersion();
            this.execute(apiout, options, selection);
            return this.getExitCode();
        }
        finally {
            this.release();
        }
    }
    
    protected abstract void execute(final ResponseWriter p0, final CommandOptions p1, final CommandSelection p2) throws APIException;
    
    private void release() {
        final Iterator it = this.cmdFactory.getCmdRunners();
        while (it.hasNext()) {
            final CmdRunner c = it.next();
            try {
                c.interrupt();
                c.release();
            }
            catch (APIException cleanupEx) {
                IntegrationPointFactory.getLogger().exception("ERROR", cleanupEx);
            }
        }
    }
    
    public Response runLocalCommand(final String[] command) throws APIException {
        Response response = null;
        APIException cmdEx = null;
        final CmdRunner c = this.cmdFactory.createCmdRunner();
        try {
            response = c.execute(command);
        }
        catch (APIException ex) {
            cmdEx = ex;
            response = ex.getResponse();
            if (response != null && response.getWorkItemListSize() == 1) {
                try {
                    final WorkItem wi = response.getWorkItems().next();
                    if (wi.getAPIException() != null) {
                        throw wi.getAPIException();
                    }
                }
                catch (APIException wiex) {
                    cmdEx = wiex;
                }
            }
        }
        finally {
            c.setDefaultPassword(null);
        }
        if (cmdEx != null) {
            throw cmdEx;
        }
        return response;
    }
    
    public final Response runLocalCommand(final Command command) throws APIException {
        return this.runLocalCommand(command.toStringArray());
    }
    
    public Response invokeLocalCommand(final String[] command) throws APIException {
        return this.invokeLocalCommand(command, true);
    }
    
    public Response invokeLocalCommand(final Command command) throws APIException {
        return this.invokeLocalCommand(command, true);
    }
    
    public Response invokeLocalCommand(final String[] command, final boolean enableCache) throws APIException {
        final CmdRunner c = this.cmdFactory.createCmdRunner();
        try {
            return c.executeWithInterim(command, enableCache);
        }
        finally {
            c.setDefaultPassword(null);
        }
    }
    
    public Response invokeLocalCommand(final Command command, final boolean enableCache) throws APIException {
        final CmdRunner c = this.cmdFactory.createCmdRunner();
        try {
            return c.executeWithInterim(command, enableCache);
        }
        finally {
            c.setDefaultPassword(null);
        }
    }
}
