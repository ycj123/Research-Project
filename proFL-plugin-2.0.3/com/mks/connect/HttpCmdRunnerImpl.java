// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import com.mks.api.response.APIException;
import com.mks.api.CmdRunner;
import java.io.IOException;
import com.mks.api.CmdRunnerCreator;
import org.mudebug.prapr.reloc.commons.httpclient.HttpClient;
import org.mudebug.prapr.reloc.commons.httpclient.URI;

class HttpCmdRunnerImpl extends AbstractCmdRunner
{
    protected UserApplicationSessionImpl uas;
    protected URI url;
    protected HttpClient httpClient;
    protected boolean autoReconnect;
    
    HttpCmdRunnerImpl(final UserApplicationSessionImpl uas, final HttpClient client) {
        super(uas);
        this.uas = uas;
        this.httpClient = client;
        this.autoReconnect = uas.getAutoReconnect();
    }
    
    protected BlimpInputStream createBlimpStream(final String[] cmd, final boolean generateSubRtns) {
        final BlimpInputStream bis = new HttpBlimpInputStream(this, cmd, this.httpClient);
        bis.setCodePage("UTF-8");
        bis.setGenerateSubRoutines(generateSubRtns);
        return bis;
    }
    
    protected synchronized URI getSessionURI() throws IOException {
        if (this.url == null) {
            this.url = this.uas.getSession(this.uas.getSessionURI());
        }
        return this.url;
    }
    
    protected synchronized void invalidateURI() {
        this.uas.invalidateURI();
        this.url = null;
    }
    
    protected boolean getAutoReconnect() {
        return this.autoReconnect;
    }
    
    public void release() throws APIException {
        try {
            if (this.isFinished()) {
                this.uas.removeConnection(this);
            }
            super.release();
        }
        finally {
            UserApplicationSessionImpl.releaseHttpClient(this.httpClient);
        }
    }
}
