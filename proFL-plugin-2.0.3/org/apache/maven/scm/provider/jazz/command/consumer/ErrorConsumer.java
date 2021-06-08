// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.consumer;

import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.CommandLineUtils;

public class ErrorConsumer extends CommandLineUtils.StringStreamConsumer
{
    private boolean fFed;
    private ScmLogger logger;
    
    public ErrorConsumer(final ScmLogger logger) {
        this.fFed = false;
        this.logger = logger;
    }
    
    public boolean hasBeenFed() {
        return this.fFed;
    }
    
    @Override
    public void consumeLine(final String line) {
        this.logger.debug("ErrorConsumer.consumeLine: " + line);
        this.fFed = true;
        super.consumeLine(line);
    }
}
