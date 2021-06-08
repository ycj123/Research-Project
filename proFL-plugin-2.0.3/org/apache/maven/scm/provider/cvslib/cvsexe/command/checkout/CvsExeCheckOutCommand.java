// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.checkout;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.cvslib.command.checkout.CvsCheckOutConsumer;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.checkout.AbstractCvsCheckOutCommand;

public class CvsExeCheckOutCommand extends AbstractCvsCheckOutCommand
{
    @Override
    protected CheckOutScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsCheckOutConsumer consumer = new CvsCheckOutConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new CheckOutScmResult(cl.toString(), "The cvs command failed.", stderr.getOutput(), false);
        }
        return new CheckOutScmResult(cl.toString(), consumer.getCheckedOutFiles());
    }
}
