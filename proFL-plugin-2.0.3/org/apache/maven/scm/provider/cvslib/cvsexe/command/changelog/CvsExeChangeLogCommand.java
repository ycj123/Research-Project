// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.changelog;

import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.changelog.CvsChangeLogConsumer;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import java.util.Date;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.changelog.AbstractCvsChangeLogCommand;

public class CvsExeChangeLogCommand extends AbstractCvsChangeLogCommand
{
    @Override
    protected ChangeLogScmResult executeCvsCommand(final Commandline cl, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        final CvsChangeLogConsumer consumer = new CvsChangeLogConsumer(this.getLogger(), datePattern);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing cvs command.", ex);
        }
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(consumer.getModifications(), startDate, endDate);
        changeLogSet.setStartVersion(startVersion);
        changeLogSet.setEndVersion(endVersion);
        return new ChangeLogScmResult(cl.toString(), changeLogSet);
    }
}
