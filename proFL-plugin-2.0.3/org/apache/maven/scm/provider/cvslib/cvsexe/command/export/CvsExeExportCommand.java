// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.export;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.update.CvsUpdateConsumer;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.export.AbstractCvsExportCommand;

public class CvsExeExportCommand extends AbstractCvsExportCommand
{
    @Override
    protected ExportScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsUpdateConsumer consumer = new CvsUpdateConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new ExportScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new ExportScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
}
