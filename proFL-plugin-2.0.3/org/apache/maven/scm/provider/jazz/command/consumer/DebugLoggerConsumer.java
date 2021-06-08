// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.consumer;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;

public class DebugLoggerConsumer extends AbstractRepositoryConsumer
{
    private StringBuilder content;
    private String ls;
    
    public DebugLoggerConsumer(final ScmLogger logger) {
        super(null, logger);
        this.content = new StringBuilder();
        this.ls = System.getProperty("line.separator");
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        this.content.append(line).append(this.ls);
    }
    
    public String getOutput() {
        return this.content.toString();
    }
}
