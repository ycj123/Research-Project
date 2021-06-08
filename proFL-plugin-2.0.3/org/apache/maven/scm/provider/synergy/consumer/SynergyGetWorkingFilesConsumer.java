// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.consumer;

import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SynergyGetWorkingFilesConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<String> files;
    public static final String OUTPUT_FORMAT = "%name";
    
    public SynergyGetWorkingFilesConsumer(final ScmLogger logger) {
        this.files = new ArrayList<String>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (!line.trim().equals("")) {
            this.files.add(line);
        }
    }
    
    public List<String> getFiles() {
        return this.files;
    }
}
