// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.tag;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.tag.CvsTagConsumer;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.tag.AbstractCvsTagCommand;

public class CvsExeTagCommand extends AbstractCvsTagCommand
{
    @Override
    protected TagScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsTagConsumer consumer = new CvsTagConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new TagScmResult(cl.toString(), "The cvs tag command failed.", stderr.getOutput(), false);
        }
        return new TagScmResult(cl.toString(), consumer.getTaggedFiles());
    }
}
