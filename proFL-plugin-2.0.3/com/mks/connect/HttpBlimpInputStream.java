// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.text.MessageFormat;
import org.mudebug.prapr.reloc.commons.httpclient.methods.PostMethod;
import com.mks.api.IntegrationPointFactory;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.httpclient.URIException;
import java.io.IOException;
import java.net.ConnectException;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethod;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;
import org.mudebug.prapr.reloc.commons.httpclient.methods.GetMethod;
import java.util.HashMap;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.httpclient.URI;
import org.mudebug.prapr.reloc.commons.httpclient.HttpClient;

class HttpBlimpInputStream extends BlimpInputStream
{
    private HttpCmdRunnerImpl cmdRunner;
    private static final String MKS_APP_URI_PARAMETER = "&mksApp=";
    private HttpClient httpClient;
    private URI url;
    private Map connections;
    
    protected HttpBlimpInputStream(final HttpCmdRunnerImpl cmdRunner, final String[] cmd, final HttpClient httpClient) {
        super(cmdRunner, cmd);
        this.connections = new HashMap(1);
        this.cmdRunner = cmdRunner;
        this.httpClient = httpClient;
    }
    
    protected void blimpInterrupt(final String appName) throws IOException {
        final HttpClient hc = ((UserApplicationSessionImpl)this.cmdRunner.getSession()).createHttpClient();
        try {
            final URI uri = this.getSessionURI(appName);
            final GetMethod method = new GetMethod(uri.getPath());
            try {
                this.setupRequest(method);
                method.setRequestHeader(UserApplicationSessionImpl.OUT_OF_BAND_MESSAGE);
                method.setQueryString(uri.getQuery());
                UserApplicationSessionImpl.handleHTTPResponse(hc, method);
            }
            finally {
                method.releaseConnection();
            }
        }
        catch (ConnectException ce) {
            this.cleanup();
        }
    }
    
    public boolean isFinished() {
        return super.isFinished() || this.url == null;
    }
    
    private URI getSessionURI(final String appName) throws IOException {
        if (this.url == null) {
            try {
                final URI uri = this.cmdRunner.getSessionURI();
                this.url = new URI(uri.getURI() + "&mksApp=" + appName);
            }
            catch (URIException ue) {
                throw new BlimpException(ue);
            }
        }
        return this.url;
    }
    
    private void invalidateURI() {
        this.cmdRunner.invalidateURI();
        this.url = null;
    }
    
    protected InputStream blimpInitiate(final String appName) throws IOException {
        InputStream input = null;
        GetMethod method = null;
        try {
            final URI uri = this.getSessionURI(appName);
            method = new GetMethod(uri.getPath());
            try {
                this.setupRequest(method);
                method.setQueryString(uri.getQuery());
                UserApplicationSessionImpl.handleHTTPResponse(this.httpClient, method);
                this.checkStatusCode(method);
                input = method.getResponseBodyAsStream();
                this.connections.put(input, method);
                return input;
            }
            catch (InvalidSessionException ise) {
                throw ise;
            }
            catch (IOException ie) {
                method.releaseConnection();
                throw ie;
            }
        }
        catch (InvalidSessionException ise2) {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException ie) {
                    IntegrationPointFactory.getLogger().exception(ie);
                }
            }
            input = null;
            if (method != null) {
                method.releaseConnection();
            }
            this.invalidateURI();
            if (!this.cmdRunner.getAutoReconnect()) {
                throw ise2;
            }
            return this.blimpInitiate(appName);
        }
    }
    
    protected void blimpTerminate(final InputStream stream) throws IOException {
        try {
            try {
                while (stream.read() != -1) {}
            }
            finally {
                stream.close();
            }
        }
        finally {
            final HttpMethodBase method = this.connections.get(stream);
            if (method != null) {
                this.connections.remove(stream);
                method.releaseConnection();
            }
        }
    }
    
    protected void setupRequest(final HttpMethodBase method) {
        ((UserApplicationSessionImpl)this.cmdRunner.getSession()).setupRequest(method);
        method.setFollowRedirects(false);
        method.setRequestHeader("enableAdvancedFeatures", String.valueOf(this.generateSubRtns));
    }
    
    protected InputStream blimpResponse(final InputStream response) throws IOException {
        InputStream input = null;
        try {
            final URI uri = this.cmdRunner.getSessionURI();
            final PostMethod method = new PostMethod(uri.getPath());
            try {
                this.setupRequest(method);
                method.setQueryString(uri.getQuery());
                method.setRequestBody(response);
                if (((UserApplicationSessionImpl)this.cmdRunner.getSession()).supportsChunking()) {
                    method.setRequestContentLength(-1);
                }
                UserApplicationSessionImpl.handleHTTPResponse(this.httpClient, method);
                input = method.getResponseBodyAsStream();
                this.checkStatusCode(method);
                this.connections.put(input, method);
                return input;
            }
            catch (IOException ie) {
                method.releaseConnection();
                throw ie;
            }
        }
        catch (ConnectException ce) {
            this.cleanup();
            throw ce;
        }
    }
    
    private void checkStatusCode(final HttpMethod method) throws IOException {
        if (method.getStatusCode() == 503) {
            this.cleanup();
            throw new InvalidAppException(MessageFormat.format("Invalid App Name: {0}", method.getStatusText()));
        }
        if (method.getStatusCode() == 410) {
            this.cleanup();
            throw new InvalidSessionException();
        }
        if (method.getStatusCode() == 412) {
            this.cleanup();
            final String msg = method.getResponseBodyAsString();
            throw new VersionMismatchException(msg);
        }
        if (method.getStatusCode() != 200) {
            this.cleanup();
            throw new BlimpException(MessageFormat.format("Unexpected HTTP status: {0}", method.getStatusText()));
        }
    }
}
