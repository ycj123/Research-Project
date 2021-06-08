// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity;

import java.io.IOException;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunner;
import com.mks.api.Command;
import org.codehaus.plexus.util.StringUtils;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.Response;
import com.mks.api.Session;
import com.mks.api.IntegrationPoint;
import org.apache.maven.scm.log.ScmLogger;

public class APISession
{
    public static final String VERSION;
    public static final int MAJOR_VERSION;
    public static final int MINOR_VERSION;
    private ScmLogger logger;
    private String hostName;
    private int port;
    private String userName;
    private String password;
    private IntegrationPoint ip;
    private Session session;
    private boolean terminated;
    
    public APISession(final ScmLogger logger) {
        this.port = 0;
        logger.info("MKS Integrity API Version: " + APISession.VERSION);
        this.logger = logger;
    }
    
    public Response connect(final String host, final int portNum, final String user, final String paswd) throws APIException {
        this.terminated = false;
        (this.ip = IntegrationPointFactory.getInstance().createLocalIntegrationPoint(APISession.MAJOR_VERSION, APISession.MINOR_VERSION)).setAutoStartIntegrityClient(true);
        if (null != paswd && paswd.length() > 0) {
            this.logger.info("Creating session for " + user + "/" + StringUtils.repeat("*", paswd.length()));
            this.session = this.ip.createSession(user, paswd);
            this.logger.info("Attempting to establish connection using " + user + "@" + host + ":" + portNum);
        }
        else {
            this.logger.info("Using a common session.  Connection information is obtained from client preferences");
            this.session = this.ip.getCommonSession();
        }
        final Command ping = new Command("si", "connect");
        final CmdRunner cmdRunner = this.session.createCmdRunner();
        if (null != host && host.length() > 0) {
            cmdRunner.setDefaultHostname(host);
        }
        if (portNum > 0) {
            cmdRunner.setDefaultPort(portNum);
        }
        if (null != user && user.length() > 0) {
            cmdRunner.setDefaultUsername(user);
        }
        if (null != paswd && paswd.length() > 0) {
            cmdRunner.setDefaultPassword(paswd);
        }
        final Response res = cmdRunner.execute(ping);
        this.logger.debug(res.getCommandString() + " returned exit code " + res.getExitCode());
        this.hostName = res.getConnectionHostname();
        this.port = res.getConnectionPort();
        this.userName = res.getConnectionUsername();
        this.password = paswd;
        cmdRunner.release();
        this.logger.info("Successfully established connection " + this.userName + "@" + this.hostName + ":" + this.port);
        return res;
    }
    
    public Response runCommand(final Command cmd) throws APIException {
        final CmdRunner cmdRunner = this.session.createCmdRunner();
        cmdRunner.setDefaultHostname(this.hostName);
        cmdRunner.setDefaultPort(this.port);
        cmdRunner.setDefaultUsername(this.userName);
        if (null != this.password && this.password.length() > 0) {
            cmdRunner.setDefaultPassword(this.password);
        }
        final Response res = cmdRunner.execute(cmd);
        this.logger.debug(res.getCommandString() + " returned exit code " + res.getExitCode());
        cmdRunner.release();
        return res;
    }
    
    public Response runCommandAs(final Command cmd, final String impersonateUser) throws APIException {
        final CmdRunner cmdRunner = this.session.createCmdRunner();
        cmdRunner.setDefaultHostname(this.hostName);
        cmdRunner.setDefaultPort(this.port);
        cmdRunner.setDefaultUsername(this.userName);
        if (null != this.password && this.password.length() > 0) {
            cmdRunner.setDefaultPassword(this.password);
        }
        cmdRunner.setDefaultImpersonationUser(impersonateUser);
        final Response res = cmdRunner.execute(cmd);
        this.logger.debug(res.getCommandString() + " returned exit code " + res.getExitCode());
        cmdRunner.release();
        return res;
    }
    
    public void terminate() {
        if (!this.terminated) {
            try {
                if (null != this.session) {
                    this.session.release();
                }
                if (null != this.ip) {
                    this.ip.release();
                }
                this.terminated = true;
                this.logger.info("Successfully disconnected connection " + this.userName + "@" + this.hostName + ":" + this.port);
            }
            catch (APIException aex) {
                this.logger.debug("Caught API Exception when releasing session!");
                aex.printStackTrace();
            }
            catch (IOException ioe) {
                this.logger.debug("Caught IO Exception when releasing session!");
                ioe.printStackTrace();
            }
        }
    }
    
    public String getHostName() {
        return this.hostName;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getPassword() {
        if (null != this.password && this.password.length() > 0) {
            return this.password;
        }
        return "";
    }
    
    public ScmLogger getLogger() {
        return this.logger;
    }
    
    static {
        VERSION = IntegrationPointFactory.getAPIVersion().substring(0, IntegrationPointFactory.getAPIVersion().indexOf(32));
        MAJOR_VERSION = Integer.parseInt(APISession.VERSION.substring(0, APISession.VERSION.indexOf(46)));
        MINOR_VERSION = Integer.parseInt(APISession.VERSION.substring(APISession.VERSION.indexOf(46) + 1, APISession.VERSION.length()));
    }
}
