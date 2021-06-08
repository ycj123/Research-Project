// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.provider.tfs.command.consumer.FileListConsumer;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import java.util.Iterator;
import java.io.File;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.log.ScmLogger;

public class TfsCommand
{
    private ScmLogger logger;
    private Commandline command;
    
    public TfsCommand(final String cmd, final ScmProviderRepository r, final ScmFileSet f, final ScmLogger logger) {
        (this.command = new Commandline()).setExecutable("tf");
        if (f != null) {
            this.command.setWorkingDirectory(f.getBasedir().getAbsolutePath());
        }
        this.command.createArg().setValue(cmd);
        if (r.getUser() != null) {
            this.command.createArg().setValue("-login:" + r.getUser() + "," + r.getPassword());
        }
        this.logger = logger;
    }
    
    public void addArgument(final ScmFileSet f) {
        this.info("files: " + f.getBasedir().getAbsolutePath());
        final Iterator<File> iter = f.getFileList().iterator();
        while (iter.hasNext()) {
            this.command.createArg().setValue(iter.next().getPath());
        }
    }
    
    public void addArgument(final String s) {
        this.command.createArg().setValue(s);
    }
    
    public int execute(final StreamConsumer out, final ErrorStreamConsumer err) throws ScmException {
        this.info("Command line - " + this.getCommandString());
        int status;
        try {
            status = CommandLineUtils.executeCommandLine(this.command, out, err);
        }
        catch (CommandLineException e) {
            throw new ScmException("Error while executing TFS command line - " + this.getCommandString(), e);
        }
        this.info("err - " + err.getOutput());
        if (out instanceof CommandLineUtils.StringStreamConsumer) {
            final CommandLineUtils.StringStreamConsumer sc = (CommandLineUtils.StringStreamConsumer)out;
            this.debug(sc.getOutput());
        }
        if (out instanceof FileListConsumer) {
            final FileListConsumer f = (FileListConsumer)out;
            for (final ScmFile file : f.getFiles()) {
                this.debug(file.getPath());
            }
        }
        return status;
    }
    
    public String getCommandString() {
        return this.command.toString();
    }
    
    public Commandline getCommandline() {
        return this.command;
    }
    
    private void info(final String message) {
        if (this.logger != null) {
            this.logger.info(message);
        }
    }
    
    private void debug(final String message) {
        if (this.logger != null) {
            this.logger.debug(message);
        }
    }
}
