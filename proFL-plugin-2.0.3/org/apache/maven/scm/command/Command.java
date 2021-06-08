// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command;

import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;

public interface Command
{
    public static final String ROLE = Command.class.getName();
    
    ScmResult execute(final ScmProviderRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException;
    
    void setLogger(final ScmLogger p0);
    
    ScmLogger getLogger();
}
