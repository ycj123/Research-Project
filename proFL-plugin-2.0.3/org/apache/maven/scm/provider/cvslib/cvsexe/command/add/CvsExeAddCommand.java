// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.add;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.add.AbstractCvsAddCommand;

public class CvsExeAddCommand extends AbstractCvsAddCommand
{
    @Override
    protected AddScmResult executeCvsCommand(final Commandline cl, final List<ScmFile> addedFiles) throws ScmException {
        final CommandLineUtils.StringStreamConsumer consumer = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new AddScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new AddScmResult(cl.toString(), addedFiles);
    }
}
