// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.login;

import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.login.AbstractLoginCommand;

public class CvsLoginCommand extends AbstractLoginCommand
{
    @Override
    public LoginScmResult executeLoginCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final CvsScmProviderRepository repo = (CvsScmProviderRepository)repository;
        if (!"pserver".equals(repo.getTransport())) {
            return new LoginScmResult(null, "The cvs login ignored for " + repo.getTransport() + ".", "", true);
        }
        if (this.isCvsNT()) {
            return new LoginScmResult(null, "The cvs login ignored for CVSNT.", "", true);
        }
        final CvsPass passGenerator = new CvsPass(this.getLogger());
        passGenerator.setCvsroot(repo.getCvsRootForCvsPass());
        passGenerator.setPassword(repo.getPassword());
        try {
            passGenerator.execute();
        }
        catch (IOException e) {
            throw new ScmException("Error while executing cvs login command.", e);
        }
        return new LoginScmResult(null, "The cvs command succeed.", "", true);
    }
    
    public boolean isCvsNT() throws ScmException {
        return CvsCommandUtils.isCvsNT();
    }
}
