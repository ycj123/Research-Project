// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.status;

import org.apache.maven.scm.ScmException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.status.CvsStatusConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.status.AbstractCvsStatusCommand;

public class CvsJavaStatusCommand extends AbstractCvsStatusCommand
{
    @Override
    protected StatusScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsStatusConsumer consumer = new CvsStatusConsumer(this.getLogger(), cl.getWorkingDirectory());
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new StatusScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new StatusScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getChangedFiles());
    }
}
