// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.checkin;

import org.apache.maven.scm.ScmException;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.checkin.CvsCheckInConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import java.io.File;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.checkin.AbstractCvsCheckInCommand;

public class CvsJavaCheckInCommand extends AbstractCvsCheckInCommand
{
    @Override
    protected CheckInScmResult executeCvsCommand(final Commandline cl, final CvsScmProviderRepository repository, final File messageFile) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsCheckInConsumer consumer = new CvsCheckInConsumer(repository.getPath(), this.getLogger());
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new CheckInScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new CheckInScmResult(cl.toString(), "The cvs command failed.", logListener.getStdout().toString(), false);
        }
        finally {
            try {
                FileUtils.forceDelete(messageFile);
            }
            catch (IOException ex) {}
        }
        return new CheckInScmResult(cl.toString(), consumer.getCheckedInFiles());
    }
}
