// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.blame;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.ScmRequest;

public class BlameScmRequest extends ScmRequest
{
    private boolean ignoreWhitespace;
    
    public BlameScmRequest(final ScmRepository scmRepository, final ScmFileSet scmFileSet) {
        super(scmRepository, scmFileSet);
    }
    
    public void setFilename(final String filename) throws ScmException {
        this.getCommandParameters().setString(CommandParameter.FILE, filename);
    }
    
    public String getFilename() throws ScmException {
        return this.getCommandParameters().getString(CommandParameter.FILE);
    }
    
    public boolean isIgnoreWhitespace() {
        return this.ignoreWhitespace;
    }
    
    public void setIgnoreWhitespace(final boolean ignoreWhitespace) throws ScmException {
        this.ignoreWhitespace = ignoreWhitespace;
        if (ignoreWhitespace) {
            this.getCommandParameters().setString(CommandParameter.IGNORE_WHITESPACE, "TRUE");
        }
        else {
            this.getCommandParameters().setString(CommandParameter.IGNORE_WHITESPACE, "FALSE");
        }
    }
}
