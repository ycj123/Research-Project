// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.blame;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.blame.CvsBlameConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.blame.AbstractCvsBlameCommand;

public class CvsJavaBlameCommand extends AbstractCvsBlameCommand
{
    @Override
    protected BlameScmResult executeCvsCommand(final Commandline cl, final CvsScmProviderRepository repository) {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsBlameConsumer consumer = new CvsBlameConsumer(this.getLogger());
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new BlameScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            this.getLogger().error(e);
            return new BlameScmResult(cl.toString(), "The cvs command failed.", logListener.getStdout().toString(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
}
