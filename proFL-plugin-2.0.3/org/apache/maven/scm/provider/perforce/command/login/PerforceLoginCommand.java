// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.login;

import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.login.AbstractLoginCommand;

public class PerforceLoginCommand extends AbstractLoginCommand implements PerforceCommand
{
    @Override
    public LoginScmResult executeLoginCommand(final ScmProviderRepository repo, final ScmFileSet files, final CommandParameters params) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir());
        final PerforceLoginConsumer consumer = new PerforceLoginConsumer();
        boolean isSuccess = false;
        try {
            final String password = repo.getPassword();
            if (StringUtils.isEmpty(password)) {
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info("No password found, proceeding without it.");
                }
                isSuccess = true;
            }
            else {
                final CommandLineUtils.StringStreamConsumer err = new CommandLineUtils.StringStreamConsumer();
                final int exitCode = CommandLineUtils.executeCommandLine(cl, new ByteArrayInputStream(password.getBytes()), consumer, err);
                isSuccess = consumer.isSuccess();
                if (!isSuccess) {
                    final String cmdLine = CommandLineUtils.toString(cl.getCommandline());
                    final StringBuilder msg = new StringBuilder("Exit code: " + exitCode + " - " + err.getOutput());
                    msg.append('\n');
                    msg.append("Command line was:" + cmdLine);
                    throw new CommandLineException(msg.toString());
                }
            }
        }
        catch (CommandLineException e) {
            throw new ScmException(e.getMessage(), e);
        }
        return new LoginScmResult(cl.toString(), isSuccess ? "Login successful" : "Login failed", consumer.getOutput(), isSuccess);
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDir) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDir);
        command.createArg().setValue("login");
        if (!StringUtils.isEmpty(repo.getUser())) {
            command.createArg().setValue(repo.getUser());
        }
        return command;
    }
}
