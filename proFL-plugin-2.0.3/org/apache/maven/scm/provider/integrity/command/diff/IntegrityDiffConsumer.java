// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.diff;

import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class IntegrityDiffConsumer implements StreamConsumer
{
    private ScmLogger logger;
    
    public IntegrityDiffConsumer(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        this.logger.info(line);
    }
}
