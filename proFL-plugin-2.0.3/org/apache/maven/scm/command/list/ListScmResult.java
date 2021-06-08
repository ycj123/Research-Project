// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.list;

import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class ListScmResult extends ScmResult
{
    private static final long serialVersionUID = 5402161066844465281L;
    private List<ScmFile> files;
    
    public ListScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.files = new ArrayList<ScmFile>(0);
    }
    
    public ListScmResult(final String commandLine, final List<ScmFile> files) {
        super(commandLine, null, null, true);
        this.files = files;
    }
    
    public ListScmResult(final List<ScmFile> files, final ScmResult result) {
        super(result);
        this.files = files;
    }
    
    public List<ScmFile> getFiles() {
        return this.files;
    }
}
