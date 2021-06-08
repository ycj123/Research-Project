// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import com.mks.api.response.Response;
import com.mks.api.response.APIError;
import com.mks.api.response.InvalidIntegrationPointException;
import com.mks.api.response.InvalidHostException;
import com.mks.api.response.ApplicationConnectionException;
import com.mks.api.response.UnsupportedApplicationException;
import java.io.IOException;
import com.mks.connect.AbstractCmdRunner;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;
import com.mks.api.response.APIException;
import com.mks.api.Session;
import com.mks.api.IntegrationPoint;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.CmdRunner;

public class APIViewer
{
    protected CmdRunner init() throws APIException {
        final String ipHost = System.getProperty("com.mks.api.ip.hostname");
        final boolean ipSecure = Boolean.getBoolean("com.mks.api.ip.secure");
        String tmpStr = System.getProperty("com.mks.api.ip.port");
        int ipPort = 0;
        if (tmpStr != null && tmpStr.trim().length() > 0) {
            ipPort = Integer.parseInt(tmpStr);
        }
        final boolean isCommon = Boolean.getBoolean("com.mks.api.session.common");
        final String sessionUser = System.getProperty("com.mks.api.session.username");
        final String sessionPass = System.getProperty("com.mks.api.session.password");
        final String defaultHost = System.getProperty("com.mks.api.default.hostname");
        int defaultPort = 0;
        tmpStr = System.getProperty("com.mks.api.default.port");
        if (tmpStr != null && tmpStr.trim().length() > 0) {
            defaultPort = Integer.parseInt(tmpStr);
        }
        final String defaultUser = System.getProperty("com.mks.api.default.username");
        final String defaultPass = System.getProperty("com.mks.api.default.password");
        int majorVersion = 0;
        int minorVersion = 0;
        tmpStr = System.getProperty("com.mks.api.version.major");
        if (tmpStr != null && tmpStr.trim().length() > 0) {
            majorVersion = Integer.parseInt(tmpStr);
        }
        tmpStr = System.getProperty("com.mks.api.version.minor");
        if (tmpStr != null && tmpStr.trim().length() > 0) {
            minorVersion = Integer.parseInt(tmpStr);
        }
        final IntegrationPointFactory ipf = IntegrationPointFactory.getInstance();
        IntegrationPoint ip = null;
        if (ipHost == null || (ipHost.equals("localhost") && ipPort == 0)) {
            ip = ipf.createLocalIntegrationPoint(majorVersion, minorVersion);
        }
        else {
            ip = ipf.createIntegrationPoint(ipHost, ipPort, ipSecure, majorVersion, minorVersion);
        }
        Session session = null;
        if (isCommon) {
            session = ip.getCommonSession(sessionUser, sessionPass);
        }
        else {
            session = ip.createSession(sessionUser, sessionPass);
        }
        final CmdRunner cr = session.createCmdRunner();
        if (defaultHost != null && defaultHost.trim().length() > 0) {
            cr.setDefaultHostname(defaultHost.trim());
        }
        if (defaultPort > 0) {
            cr.setDefaultPort(defaultPort);
        }
        if (defaultUser != null && defaultUser.trim().length() > 0) {
            cr.setDefaultUsername(defaultUser.trim());
        }
        if (defaultPass != null && defaultPass.trim().length() > 0) {
            cr.setDefaultPassword(defaultPass.trim());
        }
        return cr;
    }
    
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: APIViewer -Dapiviewer.config.file=<Configuration File><Integrity Application> <Integrity Application Command> [options] [selection]");
            System.exit(1);
        }
        CmdRunner cr = null;
        try {
            final String cf = System.getProperty("apiviewer.config.file");
            final File f = (cf != null) ? new File(cf) : null;
            if (f != null) {
                if (!f.exists() || !f.canRead()) {
                    System.out.println("Cannot open configuration file: " + f);
                    System.exit(1);
                }
                final Properties p = new Properties(System.getProperties());
                p.load(new FileInputStream(f));
                System.setProperties(p);
            }
            final APIViewer apiViewer = new APIViewer();
            cr = apiViewer.init();
            if (Boolean.getBoolean("com.mks.api.xml.output")) {
                final AbstractCmdRunner cri = (AbstractCmdRunner)cr;
                System.out.println(cri.executeWithXML(args));
            }
            else {
                Response response = null;
                if (Boolean.getBoolean("com.mks.api.response.interim")) {
                    response = cr.executeWithInterim(args, Boolean.getBoolean("com.mks.api.response.cache"));
                }
                else {
                    response = cr.execute(args);
                }
                ResponseUtil.printResponse(response, 1, System.out);
            }
        }
        catch (IOException ex) {
            System.out.println("Could not load the configuration file.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {
            System.out.println("Could not load the configuration file.");
            System.out.println(ex2.getMessage());
            ex2.printStackTrace();
        }
        catch (UnsupportedApplicationException ex3) {
            ResponseUtil.printAPIException("UnsupportedApplicationException:", ex3, 0, System.out);
        }
        catch (ApplicationConnectionException ex4) {
            ResponseUtil.printAPIException("ApplicationConnectionException:", ex4, 0, System.out);
        }
        catch (InvalidHostException ex5) {
            ResponseUtil.printAPIException("InvalidHostException:", ex5, 0, System.out);
        }
        catch (InvalidIntegrationPointException ex6) {
            ResponseUtil.printAPIException("InvalidIntegrationPointException:", ex6, 0, System.out);
        }
        catch (APIException ex7) {
            if (ex7.getResponse() != null) {
                ResponseUtil.printResponse(ex7.getResponse(), 1, System.out);
            }
            else {
                ResponseUtil.printAPIException("APIException:", ex7, 0, System.out);
            }
        }
        catch (APIError err) {
            ResponseUtil.printAPIError(err, 0, System.out);
        }
        finally {
            final Session session = (cr == null) ? null : cr.getSession();
            try {
                if (cr != null) {
                    cr.release();
                }
            }
            catch (Exception ex8) {}
            try {
                if (session != null) {
                    final IntegrationPoint ip = session.getIntegrationPoint();
                    try {
                        session.release();
                    }
                    finally {
                        ip.release();
                    }
                }
            }
            catch (Exception ex9) {}
        }
    }
}
