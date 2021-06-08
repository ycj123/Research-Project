// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.io.UnsupportedEncodingException;
import com.mks.api.response.APIError;
import java.io.ByteArrayOutputStream;
import com.mks.api.Command;
import com.mks.api.response.impl.UnsupportedApplicationError;
import com.mks.api.response.UnsupportedApplicationException;
import com.mks.api.response.impl.UnsupportedVersionError;
import com.mks.api.response.IncompatibleVersionException;
import com.mks.api.response.impl.ApplicationConnectionError;
import com.mks.api.response.APIExceptionFactory;
import com.mks.api.response.APIConnectionException;
import com.mks.api.response.modifiable.ModifiableResponse;
import java.io.InputStream;
import com.mks.api.response.impl.XMLResponseHandler;
import java.util.List;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Arrays;
import com.mks.api.response.CommandAlreadyRunningException;
import com.mks.api.response.APIException;
import java.io.IOException;
import com.mks.api.response.InvalidCommandRunnerStateException;
import com.mks.api.Session;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.util.MKSLogger;
import com.mks.api.response.Response;
import com.mks.api.CmdRunnerCreator;
import com.mks.api.CmdRunner;

public abstract class AbstractCmdRunner implements CmdRunner
{
    private static final String EXECUTE_MSG = "Executing the command : {0}";
    private static final String EXECUTION_TIME_MSG = "Total time to execute \"{0}\": {1}ms";
    protected static final String FLAG_OPTION_MSG = "Flags/Options:";
    protected static final String SELECTION_MSG = "Selection:";
    protected static final String CACHE_RESPONSE_MSG = "Setting the cacheResponse flag to : {0}";
    protected static final String COMPLETE_RESPONSE_MSG = "Setting the setCompleteResponse flag to : {0}";
    private CmdRunnerCreator session;
    protected boolean interrupted;
    protected BlimpInputStream bis;
    protected String defaultHost;
    protected int defaultPort;
    protected String defaultUser;
    protected String defaultPass;
    protected String impUser;
    protected boolean released;
    protected Response interimResponse;
    protected MKSLogger apiLogger;
    
    protected AbstractCmdRunner(final CmdRunnerCreator session) {
        this.session = session;
        this.apiLogger = IntegrationPointFactory.getLogger();
    }
    
    public Session getSession() {
        return (this.session instanceof Session) ? ((Session)this.session) : null;
    }
    
    protected abstract BlimpInputStream createBlimpStream(final String[] p0, final boolean p1);
    
    public void interrupt() throws APIException {
        try {
            if (this.interimResponse != null) {
                this.interimResponse.interrupt();
                this.interimResponse = null;
            }
            if (this.bis != null) {
                this.bis.close();
            }
        }
        catch (IOException ex) {
            this.apiLogger.exception(this, "API", 0, ex);
            throw new InvalidCommandRunnerStateException(ex);
        }
    }
    
    protected void setInterrupted() {
        this.interrupted = true;
    }
    
    protected boolean isInterrupted() {
        return this.interrupted;
    }
    
    protected void resetInterrupt() {
        this.interrupted = false;
    }
    
    public boolean isFinished() {
        return this.bis == null || this.bis.isFinished();
    }
    
    protected final Response executeCommand(final String[] args, final boolean interimResponse, final boolean cacheResults, final boolean generateSubRtns) throws APIException {
        if (this.isInterrupted() || this.released) {
            throw new InvalidCommandRunnerStateException();
        }
        if (!this.isFinished()) {
            throw new CommandAlreadyRunningException();
        }
        final List tmpList = new LinkedList(Arrays.asList(args));
        if (args.length > 1) {
            final int idx = 2;
            if (this.impUser != null) {
                tmpList.add(idx, "--impersonateuser=" + this.impUser);
            }
            if (this.getDefaultHostname() != null) {
                tmpList.add(idx, "--hostname=" + this.getDefaultHostname());
            }
            if (this.getDefaultPort() > 0) {
                tmpList.add(idx, "--port=" + this.getDefaultPort());
            }
            if (this.getDefaultUsername() != null) {
                tmpList.add(idx, "--user=" + this.getDefaultUsername());
            }
            if (this.getDefaultPassword() != null) {
                tmpList.add(idx, "--password=" + this.getDefaultPassword());
            }
        }
        final String[] cmd = tmpList.toArray(new String[tmpList.size()]);
        final StringBuffer cc = new StringBuffer(cmd[0]);
        for (int i = 1; i < cmd.length; ++i) {
            if (!cmd[i].startsWith("--password=")) {
                cc.append(" ");
                cc.append(cmd[i]);
            }
        }
        this.executePreCondition(cmd);
        final long startTime = System.currentTimeMillis();
        String msg = MessageFormat.format("Executing the command : {0}", cc);
        this.apiLogger.message(this, "API", 0, msg);
        final Response response = this.executeCommand(cc, cmd, interimResponse, cacheResults, generateSubRtns);
        final long endTime = System.currentTimeMillis();
        final long diffTime = endTime - startTime;
        msg = MessageFormat.format("Total time to execute \"{0}\": {1}ms", cc, new Long(diffTime));
        this.apiLogger.message(this, "API", 10, msg);
        if (!interimResponse && response.getAPIException() != null) {
            throw response.getAPIException();
        }
        return response;
    }
    
    protected void executePreCondition(final String[] cmd) throws APIException {
    }
    
    protected Response executeCommand(final StringBuffer cmdline, final String[] cmd, final boolean interimResponse, final boolean cacheResults, final boolean generateSubRtns) throws APIException {
        final String appName = cmd[0];
        this.bis = this.createBlimpStream(cmd, generateSubRtns);
        Response response = null;
        final XMLResponseHandler xrh = new XMLResponseHandler(this.bis, "UTF-8");
        try {
            if (!interimResponse) {
                response = xrh.getResponse(null, cmdline.toString(), true);
                ((ModifiableResponse)response).setApplicationName(appName);
                if (response != null && response.getAPIException() != null) {
                    final APIException ex = response.getAPIException();
                    throw ex;
                }
            }
            else {
                response = xrh.getResponse(this, cmdline.toString(), false);
                ((ModifiableResponse)response).setApplicationName(appName);
                ((ModifiableResponse)response).setUseInterim(interimResponse);
                ((ModifiableResponse)response).setCacheContents(cacheResults);
                this.interimResponse = response;
            }
        }
        catch (ApplicationConnectionError err) {
            this.apiLogger.exception(this, "API", 0, err);
            final APIConnectionException ace = (APIConnectionException)APIExceptionFactory.createAPIException("APIConnectionException", err.getMessage());
            throw ace;
        }
        catch (UnsupportedVersionError err2) {
            this.apiLogger.exception(this, "API", 0, err2);
            final APIException ex2 = APIExceptionFactory.createAPIException("IncompatibleVersionException", err2.getMessage());
            final IncompatibleVersionException ive = (IncompatibleVersionException)ex2;
            throw ive;
        }
        catch (UnsupportedApplicationError err3) {
            this.apiLogger.exception(this, "API", 0, err3);
            final UnsupportedApplicationException uae = (UnsupportedApplicationException)APIExceptionFactory.createAPIException("UnsupportedApplicationException", (String)null);
            uae.addField("applicationName", appName);
            throw uae;
        }
        finally {
            try {
                if (this.interimResponse == null && this.bis != null) {
                    this.bis.close();
                    this.bis = null;
                }
            }
            catch (IOException ex3) {
                this.apiLogger.exception(this, "API", 0, ex3);
            }
        }
        return response;
    }
    
    public final Response executeWithInterim(final String[] args, final boolean cacheResults) throws APIException {
        return this.executeCommand(args, true, cacheResults, false);
    }
    
    public final Response executeWithInterim(final Command cmd, final boolean cacheResults) throws APIException {
        return this.executeCommand(cmd.toStringArray(), true, cacheResults, cmd.getGenerateSubRoutines());
    }
    
    public final Response execute(final String[] args) throws APIException {
        return this.executeCommand(args, false, true, false);
    }
    
    public final Response execute(final Command c) throws APIException {
        return this.executeCommand(c.toStringArray(), false, true, c.getGenerateSubRoutines());
    }
    
    public void release() throws APIException {
        if (this.isFinished()) {
            if (this.bis != null) {
                try {
                    if (this.interimResponse != null) {
                        this.interimResponse.interrupt();
                        this.interimResponse = null;
                    }
                    if (this.bis != null) {
                        this.bis.close();
                        this.bis = null;
                    }
                }
                catch (IOException ex) {
                    this.apiLogger.exception(this, "API", 0, ex);
                }
            }
            if (!this.released && this.session instanceof CmdRunnerCreatorImpl) {
                ((CmdRunnerCreatorImpl)this.session).removeCmdRunner(this);
            }
            this.released = true;
            return;
        }
        throw new CommandAlreadyRunningException();
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
    
    public String executeWithXML(final String[] cmd) throws APIException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        if (this.isInterrupted() || this.released) {
            throw new InvalidCommandRunnerStateException();
        }
        if (!this.isFinished()) {
            throw new CommandAlreadyRunningException();
        }
        final List tmpList = new LinkedList(Arrays.asList(cmd));
        if (cmd.length > 1) {
            final int idx = 2;
            if (this.impUser != null) {
                tmpList.add(idx, "--impersonateuser=" + this.impUser);
            }
            if (this.getDefaultHostname() != null) {
                tmpList.add(idx, "--hostname=" + this.getDefaultHostname());
            }
            if (this.getDefaultPort() > 0) {
                tmpList.add(idx, "--port=" + this.getDefaultPort());
            }
            if (this.getDefaultUsername() != null) {
                tmpList.add(idx, "--user=" + this.getDefaultUsername());
            }
            if (this.getDefaultPassword() != null) {
                tmpList.add(idx, "--password=" + this.getDefaultPassword());
            }
        }
        final String[] cmdArr = tmpList.toArray(new String[tmpList.size()]);
        final String appName = cmdArr[0];
        final StringBuffer cc = new StringBuffer(cmdArr[0]);
        for (int i = 1; i < cmdArr.length; ++i) {
            if (!cmdArr[i].startsWith("--password=")) {
                cc.append(" ");
                cc.append(cmdArr[i]);
            }
        }
        final String msg = MessageFormat.format("Executing the command : {0}", cc);
        this.apiLogger.message(this, "API", 0, msg);
        this.bis = this.createBlimpStream(cmdArr, false);
        try {
            final byte[] arr = new byte[1024];
            int length = -1;
            while ((length = this.bis.read(arr, 0, arr.length)) != -1) {
                os.write(arr, 0, length);
            }
        }
        catch (ApplicationConnectionError err) {
            this.apiLogger.exception(this, "API", 0, err);
            final APIConnectionException ace = (APIConnectionException)APIExceptionFactory.createAPIException("APIConnectionException", err.getMessage());
            throw ace;
        }
        catch (UnsupportedApplicationError err2) {
            this.apiLogger.exception(this, "API", 0, err2);
            final UnsupportedApplicationException uae = (UnsupportedApplicationException)APIExceptionFactory.createAPIException("UnsupportedApplicationException", (String)null);
            uae.addField("applicationName", appName);
            throw uae;
        }
        catch (IOException ex) {
            throw new APIError(ex);
        }
        finally {
            try {
                this.bis.close();
                this.bis = null;
                os.flush();
            }
            catch (IOException ex3) {}
        }
        try {
            return os.toString("UTF-8");
        }
        catch (UnsupportedEncodingException ex2) {
            throw new APIError(ex2);
        }
    }
}
