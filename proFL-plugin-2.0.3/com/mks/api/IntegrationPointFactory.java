// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.util.Collection;
import java.util.Iterator;
import com.mks.api.response.APIExceptionFactory;
import com.mks.connect.IntegrationPointImpl;
import java.net.UnknownHostException;
import com.mks.api.response.InvalidHostException;
import java.net.InetAddress;
import com.mks.api.response.APIException;
import com.mks.api.util.APIVersion;
import java.util.Enumeration;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import com.mks.api.util.MKSLogger;

public class IntegrationPointFactory
{
    private static final String API_IMP_VERSION_MSG = "MKS API Implementation Version: {0}";
    private static final String INVALID_HOST_ERROR_MSG = "Invalid hostname: {0}";
    private static final String CREATE_IP_MSG = "Creating an IntegrationPoint to: {0}:{1,number,#} (Secure = {2})";
    private static final String IP_ADD_ERROR_MSG = "IntegrationPoint not added to the set";
    private MKSLogger apiLogger;
    private Set points;
    private IntegrationPoint localPoint;
    private static IntegrationPointFactory instance;
    
    private IntegrationPointFactory() {
        this.points = new HashSet();
        this.localPoint = null;
        this.initLogger();
        try {
            final String msg = MessageFormat.format("MKS API Implementation Version: {0}", getAPIVersion());
            this.apiLogger.message(this, "API", 10, msg);
        }
        catch (Throwable t) {
            this.apiLogger.exception("DEBUG", 10, t);
        }
    }
    
    private void initLogger() {
        final Enumeration en = System.getProperties().propertyNames();
        boolean logEnabled = false;
        while (en.hasMoreElements()) {
            final String str = en.nextElement();
            if (str.startsWith("IntegrityAPI.logging.")) {
                logEnabled = true;
                break;
            }
        }
        String logFileName = System.getProperty("IntegrityAPI.log.filename");
        if (logFileName == null || logFileName.trim().length() == 0) {
            logFileName = "mksapi.log";
        }
        File logFile = new File(logFileName);
        if (!logFile.isAbsolute()) {
            logFile = new File(System.getProperty("java.io.tmpdir"), logFileName);
        }
        this.apiLogger = new MKSLogger(logFile.getAbsolutePath());
        if (logEnabled) {
            this.apiLogger.configure(System.getProperties());
        }
    }
    
    public static synchronized IntegrationPointFactory getInstance() {
        if (IntegrationPointFactory.instance == null) {
            IntegrationPointFactory.instance = new IntegrationPointFactory();
        }
        return IntegrationPointFactory.instance;
    }
    
    public static String getAPIVersion() {
        return APIVersion.getAPIReleaseVersion();
    }
    
    public static MKSLogger getLogger() {
        return getInstance().apiLogger;
    }
    
    public IntegrationPoint createLocalIntegrationPoint() throws APIException {
        return this.createLocalIntegrationPoint(4, 9);
    }
    
    public IntegrationPoint createLocalIntegrationPoint(final int apiMajorVersion, final int apiMinorVersion) throws APIException {
        return this.createLocalIntegrationPoint(new APIVersion(apiMajorVersion, apiMinorVersion));
    }
    
    public IntegrationPoint createLocalIntegrationPoint(final VersionNumber apiRequestVersion) throws APIException {
        return this.createIntegrationPoint("127.0.0.1", 0, apiRequestVersion);
    }
    
    public IntegrationPoint createIntegrationPoint(final String host, final int port) throws APIException {
        return this.createIntegrationPoint(host, port, false);
    }
    
    public IntegrationPoint createIntegrationPoint(final String host, final int port, final int apiMajorVersion, final int apiMinorVersion) throws APIException {
        return this.createIntegrationPoint(host, port, new APIVersion(apiMajorVersion, apiMinorVersion));
    }
    
    public IntegrationPoint createIntegrationPoint(final String host, final int port, final VersionNumber apiRequestVersion) throws APIException {
        return this.createIntegrationPoint(host, port, false, apiRequestVersion);
    }
    
    public synchronized IntegrationPoint createIntegrationPoint(final String host, final int port, final boolean secure) throws APIException {
        return this.createIntegrationPoint(host, port, secure, 4, 9);
    }
    
    public synchronized IntegrationPoint createIntegrationPoint(final String host, final int port, final boolean secure, final int apiMajorVersion, final int apiMinorVersion) throws APIException {
        return this.createIntegrationPoint(host, port, secure, new APIVersion(apiMajorVersion, apiMinorVersion));
    }
    
    public synchronized IntegrationPoint createIntegrationPoint(final String host, final int port, final boolean secure, final VersionNumber apiRequestVersion) throws APIException {
        String msg = MessageFormat.format("Creating an IntegrationPoint to: {0}:{1,number,#} (Secure = {2})", host, new Integer(port), new Boolean(secure));
        this.apiLogger.message(this, "API", 10, msg);
        try {
            InetAddress.getByName(host);
        }
        catch (UnknownHostException ex) {
            msg = MessageFormat.format("Invalid hostname: {0}", host);
            this.apiLogger.message(this, "API", 0, msg);
            throw new InvalidHostException(msg);
        }
        if (port == 0) {
            if (this.localPoint == null) {
                this.localPoint = new IntegrationPointImpl(this, host, port, secure, apiRequestVersion);
            }
            return this.localPoint;
        }
        final IntegrationPoint ip = new IntegrationPointImpl(this, host, port, secure, apiRequestVersion);
        synchronized (this.points) {
            if (!this.points.add(ip)) {
                msg = "IntegrationPoint not added to the set";
                this.apiLogger.message(this, "API", 0, msg);
                APIExceptionFactory.createAPIException("APIInternalError", msg);
            }
            return ip;
        }
    }
    
    public synchronized void removeIntegrationPoint(final IntegrationPoint ip) {
        boolean release = false;
        if (this.localPoint == ip) {
            this.localPoint = null;
            release = true;
        }
        else {
            synchronized (this.points) {
                if (release = this.points.contains(ip)) {
                    this.points.remove(ip);
                }
            }
        }
        if (release) {
            ip.release();
        }
    }
    
    public Iterator getIntegrationPoints() {
        Set s = null;
        synchronized (this.points) {
            s = new HashSet(this.points);
        }
        if (this.localPoint != null) {
            s.add(this.localPoint);
        }
        return s.iterator();
    }
}
