// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.fileinfo;

import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class IntegrityFileInfoConsumer implements StreamConsumer
{
    private ScmLogger logger;
    
    public IntegrityFileInfoConsumer(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        this.logger.info(line);
    }
}
