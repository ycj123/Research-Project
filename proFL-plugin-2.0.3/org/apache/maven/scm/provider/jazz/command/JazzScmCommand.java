// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command;

import org.codehaus.plexus.util.Os;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import java.util.Iterator;
import java.io.File;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.log.ScmLogger;

public class JazzScmCommand
{
    private ScmLogger fLogger;
    private Commandline fCommand;
    
    public JazzScmCommand(final String cmd, final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmLogger logger) {
        this(cmd, null, repo, true, fileSet, logger);
    }
    
    public JazzScmCommand(final String cmd, final String subCmd, final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmLogger logger) {
        this(cmd, subCmd, repo, true, fileSet, logger);
    }
    
    public JazzScmCommand(final String cmd, final String subCmd, final ScmProviderRepository repo, final boolean addRepositoryWorkspaceArg, final ScmFileSet fileSet, final ScmLogger logger) {
        this.fLogger = logger;
        (this.fCommand = new Commandline()).setExecutable("scm");
        if (fileSet != null) {
            this.fCommand.setWorkingDirectory(fileSet.getBasedir().getAbsolutePath());
            if (!this.fCommand.getWorkingDirectory().exists()) {
                final boolean success = this.fCommand.getWorkingDirectory().mkdirs();
                if (!success) {
                    this.logErrorMessage("Working directory did not exist and it couldn't be created: " + this.fCommand.getWorkingDirectory());
                }
            }
        }
        if (!StringUtils.isEmpty(cmd)) {
            this.addArgument(cmd);
        }
        if (!StringUtils.isEmpty(subCmd)) {
            this.addArgument(subCmd);
        }
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        if (addRepositoryWorkspaceArg) {
            final String repositoryWorkspace = jazzRepo.getRepositoryURI();
            if (!StringUtils.isEmpty(repositoryWorkspace)) {
                this.addArgument("--repository-uri");
                this.addArgument(jazzRepo.getRepositoryURI());
            }
        }
        final String user = jazzRepo.getUser();
        if (!StringUtils.isEmpty(user)) {
            this.addArgument("--username");
            this.addArgument(jazzRepo.getUser());
        }
        final String password = jazzRepo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            this.addArgument("--password");
            this.addArgument(jazzRepo.getPassword());
        }
    }
    
    public void addArgument(final ScmFileSet fileSet) {
        this.logInfoMessage("files: " + fileSet.getBasedir().getAbsolutePath());
        final Iterator<File> iter = fileSet.getFileList().iterator();
        while (iter.hasNext()) {
            this.fCommand.createArg().setValue(iter.next().getPath());
        }
    }
    
    public void addArgument(final String arg) {
        this.fCommand.createArg().setValue(arg);
    }
    
    public int execute(final StreamConsumer out, final ErrorConsumer err) throws ScmException {
        this.logInfoMessage("Executing: " + cryptPassword(this.fCommand));
        if (this.fCommand.getWorkingDirectory() != null) {
            this.logInfoMessage("Working directory: " + this.fCommand.getWorkingDirectory().getAbsolutePath());
        }
        int status = 0;
        try {
            status = CommandLineUtils.executeCommandLine(this.fCommand, out, err);
        }
        catch (CommandLineException e) {
            final String errorOutput = err.getOutput();
            if (errorOutput.length() > 0) {
                this.logErrorMessage("Error: " + err.getOutput());
            }
            throw new ScmException("Error while executing Jazz SCM command line - " + this.getCommandString(), e);
        }
        final String errorOutput2 = err.getOutput();
        if (errorOutput2.length() > 0) {
            this.logErrorMessage("Error: " + err.getOutput());
        }
        return status;
    }
    
    public String getCommandString() {
        return this.fCommand.toString();
    }
    
    public Commandline getCommandline() {
        return this.fCommand;
    }
    
    private void logErrorMessage(final String message) {
        if (this.fLogger != null) {
            this.fLogger.error(message);
        }
    }
    
    private void logInfoMessage(final String message) {
        if (this.fLogger != null) {
            this.fLogger.info(message);
        }
    }
    
    private void logDebugMessage(final String message) {
        if (this.fLogger != null) {
            this.fLogger.debug(message);
        }
    }
    
    public static String cryptPassword(final Commandline cl) {
        String clString = cl.toString();
        int pos = clString.indexOf("--password");
        if (pos > 0) {
            final String beforePassword = clString.substring(0, pos + "--password ".length());
            String afterPassword = clString.substring(pos + "--password ".length());
            pos = afterPassword.indexOf(32);
            if (pos > 0) {
                afterPassword = afterPassword.substring(pos);
            }
            else {
                afterPassword = "\"";
            }
            if (Os.isFamily("windows")) {
                clString = beforePassword + "*****" + afterPassword;
            }
            else {
                clString = beforePassword + "'*****'";
            }
        }
        return clString;
    }
}
