// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class CommandOutputConsumer implements StreamConsumer
{
    private final ScmLogger logger;
    private StreamConsumer decorated;
    
    public CommandOutputConsumer(final ScmLogger logger, final StreamConsumer decorated) {
        this.decorated = decorated;
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (this.decorated != null) {
            this.decorated.consumeLine(line);
        }
    }
    
    public void waitComplete() {
        if (this.decorated instanceof XppStreamConsumer) {
            ((XppStreamConsumer)this.decorated).waitComplete();
        }
    }
}
