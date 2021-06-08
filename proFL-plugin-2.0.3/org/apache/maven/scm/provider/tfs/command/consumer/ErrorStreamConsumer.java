// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.consumer;

import org.codehaus.plexus.util.cli.CommandLineUtils;

public class ErrorStreamConsumer extends CommandLineUtils.StringStreamConsumer
{
    private boolean fed;
    
    public ErrorStreamConsumer() {
        this.fed = false;
    }
    
    @Override
    public void consumeLine(final String line) {
        this.fed = true;
        super.consumeLine(line);
    }
    
    public boolean hasBeenFed() {
        return this.fed;
    }
}
