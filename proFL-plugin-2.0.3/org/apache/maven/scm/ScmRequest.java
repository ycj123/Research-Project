// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import org.apache.maven.scm.repository.ScmRepository;
import java.io.Serializable;

public class ScmRequest implements Serializable
{
    private static final long serialVersionUID = 20120620L;
    private ScmRepository scmRepository;
    private ScmFileSet scmFileSet;
    protected final CommandParameters parameters;
    
    public ScmRequest() {
        this.parameters = new CommandParameters();
    }
    
    public ScmRequest(final ScmRepository scmRepository, final ScmFileSet scmFileSet) {
        this.parameters = new CommandParameters();
        this.scmRepository = scmRepository;
        this.scmFileSet = scmFileSet;
    }
    
    public ScmRepository getScmRepository() {
        return this.scmRepository;
    }
    
    public void setScmRepository(final ScmRepository scmRepository) {
        this.scmRepository = scmRepository;
    }
    
    public ScmFileSet getScmFileSet() {
        return this.scmFileSet;
    }
    
    public void setScmFileSet(final ScmFileSet scmFileSet) {
        this.scmFileSet = scmFileSet;
    }
    
    public CommandParameters getCommandParameters() {
        return this.parameters;
    }
}
