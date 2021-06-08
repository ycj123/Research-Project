// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;

public abstract class AbstractCommand implements Command
{
    private ScmLogger logger;
    
    protected abstract ScmResult executeCommand(final ScmProviderRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException;
    
    public final ScmResult execute(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (repository == null) {
            throw new NullPointerException("repository cannot be null");
        }
        if (fileSet == null) {
            throw new NullPointerException("fileSet cannot be null");
        }
        try {
            return this.executeCommand(repository, fileSet, parameters);
        }
        catch (Exception ex) {
            throw new ScmException("Exception while executing SCM command.", ex);
        }
    }
    
    public final ScmLogger getLogger() {
        return this.logger;
    }
    
    public final void setLogger(final ScmLogger logger) {
        this.logger = logger;
    }
}
