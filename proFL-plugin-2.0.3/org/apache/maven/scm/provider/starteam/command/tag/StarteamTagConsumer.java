// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.tag;

import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StarteamTagConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> tags;
    
    public StarteamTagConsumer(final ScmLogger logger) {
        this.tags = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(line);
        }
    }
    
    public List<ScmFile> getTaggedFiles() {
        return this.tags;
    }
}
