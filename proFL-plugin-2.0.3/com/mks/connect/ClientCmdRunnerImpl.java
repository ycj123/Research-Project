// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.ICLaunchException;
import com.mks.api.response.APIException;
import com.mks.api.IntegrationPoint;
import org.mudebug.prapr.reloc.commons.httpclient.HttpClient;

class ClientCmdRunnerImpl extends HttpCmdRunnerImpl
{
    private static final String BITS_KEY = "sun.arch.data.model";
    private static final String BITS_VALUE_64 = "64";
    private static final String NATIVE_LIBRARY = "apiclientrunner";
    private static final String NATIVE_LIBRARY64 = "apiclientrunner64";
    private static boolean isInitialized;
    
    ClientCmdRunnerImpl(final UserApplicationSessionImpl uas, final HttpClient client) {
        super(uas, client);
        if (!ClientCmdRunnerImpl.isInitialized) {
            throw new UnsatisfiedLinkError("Cannot load apiclientrunner");
        }
    }
    
    private void checkIntegrityClientForLaunch() throws BlimpException, APIException {
        final IntegrationPoint ip = this.uas.getIntegrationPoint();
        final NativeReturn nr = icInitialize(ip.getAutoStartIntegrityClient());
        if (nr == null) {
            throw new BlimpException("Got nothing from icInitialize!!");
        }
        if (nr.port == 0) {
            throw new BlimpException("Integrity Client port not found");
        }
        ((IntegrationPointImpl)ip).setPort(nr.port);
        this.uas.setAuthenticationCookie(nr.cookie);
        UserApplicationSessionImpl.configureHttpClient(this.httpClient, this.httpClient.getHostConfiguration(), ip);
    }
    
    protected void executePreCondition(final String[] cmd) throws APIException {
        try {
            this.checkIntegrityClientForLaunch();
        }
        catch (BlimpException be) {
            throw new APIException(be);
        }
    }
    
    protected native boolean isIntegrityClientRunning();
    
    protected static synchronized native NativeReturn icInitialize(final boolean p0) throws BlimpException, ICLaunchException;
    
    static {
        ClientCmdRunnerImpl.isInitialized = false;
        try {
            if ("64".equals(System.getProperty("sun.arch.data.model"))) {
                System.loadLibrary("apiclientrunner64");
            }
            else {
                System.loadLibrary("apiclientrunner");
            }
            ClientCmdRunnerImpl.isInitialized = true;
        }
        catch (UnsatisfiedLinkError ule) {
            IntegrationPointFactory.getLogger().exception("API", 0, ule);
        }
    }
    
    public static class NativeReturn
    {
        public int port;
        public String cookie;
        
        public NativeReturn(final int port, final String cookie) {
            this.port = port;
            this.cookie = cookie;
        }
    }
}
