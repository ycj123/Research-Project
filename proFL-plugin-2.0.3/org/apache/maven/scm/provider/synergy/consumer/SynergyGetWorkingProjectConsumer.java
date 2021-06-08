// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.consumer;

import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SynergyGetWorkingProjectConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private String projectSpec;
    
    public SynergyGetWorkingProjectConsumer(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Consume: " + line);
        }
        if (!line.trim().equals("")) {
            this.projectSpec = line.trim();
        }
    }
    
    public String getProjectSpec() {
        return this.projectSpec;
    }
}
