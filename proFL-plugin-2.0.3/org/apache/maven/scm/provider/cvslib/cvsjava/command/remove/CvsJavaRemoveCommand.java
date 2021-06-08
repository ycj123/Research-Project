// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.remove;

import org.apache.maven.scm.ScmException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.remove.AbstractCvsRemoveCommand;

public class CvsJavaRemoveCommand extends AbstractCvsRemoveCommand
{
    @Override
    protected RemoveScmResult executeCvsCommand(final Commandline cl, final List<ScmFile> removedFiles) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new RemoveScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            if (this.getLogger().isDebugEnabled()) {
                String line;
                while ((line = stream.readLine()) != null) {
                    this.getLogger().debug(line);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new RemoveScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
        }
        return new RemoveScmResult(cl.toString(), removedFiles);
    }
}
