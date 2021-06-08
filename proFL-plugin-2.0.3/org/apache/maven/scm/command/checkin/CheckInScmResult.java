// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.checkin;

import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class CheckInScmResult extends ScmResult
{
    private static final long serialVersionUID = 954225589449445354L;
    private List<ScmFile> checkedInFiles;
    private String scmRevision;
    
    public CheckInScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public CheckInScmResult(final String commandLine, final List<ScmFile> checkedInFiles) {
        super(commandLine, null, null, true);
        this.checkedInFiles = checkedInFiles;
    }
    
    public CheckInScmResult(final String commandLine, final List<ScmFile> checkedInFiles, final String scmRevision) {
        this(commandLine, checkedInFiles);
        this.scmRevision = scmRevision;
    }
    
    public CheckInScmResult(final List<ScmFile> checkedInFiles, final ScmResult result) {
        super(result);
        this.checkedInFiles = checkedInFiles;
    }
    
    public List<ScmFile> getCheckedInFiles() {
        if (this.checkedInFiles == null) {
            this.checkedInFiles = new ArrayList<ScmFile>();
        }
        return this.checkedInFiles;
    }
    
    public String getScmRevision() {
        return this.scmRevision;
    }
}
