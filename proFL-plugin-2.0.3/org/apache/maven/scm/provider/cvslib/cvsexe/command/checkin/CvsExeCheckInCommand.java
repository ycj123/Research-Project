// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.checkin;

import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.checkin.CvsCheckInConsumer;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import java.io.File;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.checkin.AbstractCvsCheckInCommand;

public class CvsExeCheckInCommand extends AbstractCvsCheckInCommand
{
    @Override
    protected CheckInScmResult executeCvsCommand(final Commandline cl, final CvsScmProviderRepository repository, final File messageFile) throws ScmException {
        final CvsCheckInConsumer consumer = new CvsCheckInConsumer(repository.getPath(), this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        try {
            FileUtils.forceDelete(messageFile);
        }
        catch (IOException ex2) {}
        if (exitCode != 0) {
            return new CheckInScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new CheckInScmResult(cl.toString(), consumer.getCheckedInFiles());
    }
}
