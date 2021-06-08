// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.add;

import org.apache.maven.scm.ScmException;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.add.AbstractCvsAddCommand;

public class CvsJavaAddCommand extends AbstractCvsAddCommand
{
    @Override
    protected AddScmResult executeCvsCommand(final Commandline cl, final List<ScmFile> addedFiles) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new AddScmResult(cl.toString(), "The cvs command failed.", logListener.getStdout().toString(), false);
            }
            return new AddScmResult(cl.toString(), addedFiles);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new AddScmResult(cl.toString(), "The cvs command failed.", logListener.getStdout().toString(), false);
        }
    }
}
