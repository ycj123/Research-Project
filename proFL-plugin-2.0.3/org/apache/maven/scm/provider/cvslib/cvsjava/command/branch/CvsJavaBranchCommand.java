// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.branch;

import org.apache.maven.scm.ScmException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.branch.CvsBranchConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.branch.AbstractCvsBranchCommand;

public class CvsJavaBranchCommand extends AbstractCvsBranchCommand
{
    @Override
    protected BranchScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsBranchConsumer consumer = new CvsBranchConsumer(this.getLogger());
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new BranchScmResult(cl.toString(), "The cvs branch command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            this.getLogger().error(e.getMessage(), e);
            return new BranchScmResult(cl.toString(), "The cvs branch command failed.", logListener.getStderr().toString(), false);
        }
        return new BranchScmResult(cl.toString(), consumer.getTaggedFiles());
    }
}
