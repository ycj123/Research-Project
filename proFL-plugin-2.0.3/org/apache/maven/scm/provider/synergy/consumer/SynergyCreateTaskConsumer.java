// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.consumer;

import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.util.AbstractConsumer;

public class SynergyCreateTaskConsumer extends AbstractConsumer
{
    private int task;
    
    public int getTask() {
        return this.task;
    }
    
    public SynergyCreateTaskConsumer(final ScmLogger logger) {
        super(logger);
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Consume: " + line);
        }
        if (line.startsWith("Task ") && line.indexOf(" created.") > -1) {
            this.task = Integer.parseInt(line.substring(5, line.indexOf(" created.")));
        }
    }
}
