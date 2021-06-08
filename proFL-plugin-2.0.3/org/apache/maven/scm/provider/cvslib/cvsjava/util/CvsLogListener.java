// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.util;

import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.event.CVSAdapter;

public class CvsLogListener extends CVSAdapter
{
    private final StringBuffer taggedLine;
    private StringBuffer stdout;
    private StringBuffer stderr;
    
    public CvsLogListener() {
        this.taggedLine = new StringBuffer();
        this.stdout = new StringBuffer();
        this.stderr = new StringBuffer();
    }
    
    @Override
    public void messageSent(final MessageEvent e) {
        final String line = e.getMessage();
        final StringBuffer stream = e.isError() ? this.stderr : this.stdout;
        if (e.isTagged()) {
            final String message = MessageEvent.parseTaggedMessage(this.taggedLine, e.getMessage());
            if (message != null) {
                stream.append(message).append("\n");
            }
        }
        else {
            stream.append(line).append("\n");
        }
    }
    
    public StringBuffer getStdout() {
        return this.stdout;
    }
    
    public StringBuffer getStderr() {
        return this.stderr;
    }
}
