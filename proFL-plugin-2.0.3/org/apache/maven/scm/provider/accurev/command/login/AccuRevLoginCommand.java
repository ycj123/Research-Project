// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.login;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.login.LoginScmResult;
import java.io.File;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevLoginCommand extends AbstractAccuRevCommand
{
    public AccuRevLoginCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        boolean result = false;
        final AccuRev accurev = repository.getAccuRev();
        final AccuRevInfo info = accurev.info(null);
        String providerMessage = "";
        if (info == null) {
            providerMessage = "Unable to retrieve accurev info";
        }
        else if (repository.getUser() != null) {
            result = repository.getUser().equals(info.getUser());
            if (result) {
                providerMessage = "Skipping login - already logged in as " + repository.getUser();
            }
            else {
                result = accurev.login(repository.getUser(), repository.getPassword());
                providerMessage = (result ? "Success" : "Failure") + " logging in as " + repository.getUser();
            }
        }
        else {
            result = info.isLoggedIn();
            providerMessage = (result ? ("Logged in externally as " + info.getUser()) : "Not logged in");
        }
        this.getLogger().debug(providerMessage);
        return new LoginScmResult(accurev.getCommandLines(), providerMessage, accurev.getErrorOutput(), result);
    }
    
    public LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (LoginScmResult)this.execute(repository, fileSet, parameters);
    }
}
