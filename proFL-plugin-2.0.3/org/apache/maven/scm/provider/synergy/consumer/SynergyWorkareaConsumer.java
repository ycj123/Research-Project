// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.consumer;

import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SynergyWorkareaConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private File workarea;
    
    public SynergyWorkareaConsumer(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (line.indexOf(" '") > -1) {
            final int beginIndex = line.indexOf(" '");
            final String fileName = line.substring(beginIndex + 2, line.indexOf("'", beginIndex + 2));
            this.workarea = new File(fileName);
        }
    }
    
    public File getWorkAreaPath() {
        return this.workarea;
    }
}
