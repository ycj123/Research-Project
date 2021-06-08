// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.checkout;

import org.apache.maven.scm.ScmFileStatus;
import java.util.regex.Matcher;
import org.apache.maven.scm.provider.perforce.command.PerforceVerbMapper;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceCheckOutConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    public static final int STATE_CLIENTSPEC = 0;
    public static final int STATE_NORMAL = 1;
    public static final int STATE_ERROR = 2;
    private int currentState;
    private Pattern fileRegexp;
    private List<ScmFile> checkedout;
    private String repo;
    private String specname;
    
    public PerforceCheckOutConsumer(final String clientspec, final String repoPath) {
        this.currentState = 0;
        this.fileRegexp = Pattern.compile("([^#]+)#\\d+ - ([a-z]+)");
        this.checkedout = new ArrayList<ScmFile>();
        this.repo = null;
        this.specname = null;
        this.repo = repoPath;
        this.specname = clientspec;
    }
    
    public void consumeLine(final String line) {
        if (this.currentState == 0 && (line.startsWith("Client " + this.specname + " saved.") || line.startsWith("Client " + this.specname + " not changed."))) {
            this.currentState = 1;
            return;
        }
        if (this.currentState == 1 && line.indexOf("ile(s) up-to-date") != -1) {
            return;
        }
        final Matcher matcher;
        if (this.currentState != 2 && (matcher = this.fileRegexp.matcher(line)).find()) {
            String location = matcher.group(1);
            if (location.startsWith(this.repo)) {
                location = location.substring(this.repo.length() + 1);
            }
            final ScmFileStatus status = PerforceVerbMapper.toStatus(matcher.group(2));
            if (status != null) {
                this.checkedout.add(new ScmFile(location, status));
            }
            return;
        }
        this.error(line);
    }
    
    private void error(final String line) {
        this.currentState = 2;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState == 1;
    }
    
    public List<ScmFile> getCheckedout() {
        return this.checkedout;
    }
}
