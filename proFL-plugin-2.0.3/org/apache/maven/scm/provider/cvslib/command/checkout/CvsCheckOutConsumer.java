// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.checkout;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class CvsCheckOutConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> files;
    
    public CvsCheckOutConsumer(final ScmLogger logger) {
        this.files = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (line.length() < 3) {
            if (StringUtils.isNotEmpty(line) && this.logger.isWarnEnabled()) {
                this.logger.warn("Unable to parse output from command: line length must be bigger than 3. (" + line + ").");
            }
            return;
        }
        final String status = line.substring(0, 2);
        String file = line.substring(2);
        file = file.substring(file.indexOf(47));
        if (status.equals("U ")) {
            this.files.add(new ScmFile(file, ScmFileStatus.UPDATED));
        }
        else if (status.equals("P ")) {
            this.files.add(new ScmFile(file, ScmFileStatus.PATCHED));
        }
        else if (status.equals("C ")) {
            this.files.add(new ScmFile(file, ScmFileStatus.CONFLICT));
        }
        else if (this.logger.isWarnEnabled()) {
            this.logger.warn("Unknown status: '" + status + "'.");
        }
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.files;
    }
}
