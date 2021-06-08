// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.update;

import org.apache.maven.scm.provider.cvslib.cvsjava.command.changelog.CvsJavaChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.update.CvsUpdateConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.update.AbstractCvsUpdateCommand;

public class CvsJavaUpdateCommand extends AbstractCvsUpdateCommand
{
    @Override
    protected UpdateScmResult executeCvsCommand(final Commandline cl) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsUpdateConsumer consumer = new CvsUpdateConsumer(this.getLogger());
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new UpdateScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new UpdateScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
        }
        return new UpdateScmResult(cl.toString(), consumer.getUpdatedFiles());
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final CvsJavaChangeLogCommand command = new CvsJavaChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
