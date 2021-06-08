// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.changelog;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsConnection;
import org.apache.maven.scm.provider.cvslib.command.changelog.CvsChangeLogConsumer;
import org.apache.maven.scm.provider.cvslib.cvsjava.util.CvsLogListener;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import java.util.Date;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.changelog.AbstractCvsChangeLogCommand;

public class CvsJavaChangeLogCommand extends AbstractCvsChangeLogCommand
{
    @Override
    protected ChangeLogScmResult executeCvsCommand(final Commandline cl, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        final CvsLogListener logListener = new CvsLogListener();
        final CvsChangeLogConsumer consumer = new CvsChangeLogConsumer(this.getLogger(), datePattern);
        try {
            final boolean isSuccess = CvsConnection.processCommand(cl.getArguments(), cl.getWorkingDirectory().getAbsolutePath(), logListener, this.getLogger());
            if (!isSuccess) {
                return new ChangeLogScmResult(cl.toString(), "The cvs command failed.", logListener.getStderr().toString(), false);
            }
            final BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(logListener.getStdout().toString().getBytes())));
            String line;
            while ((line = stream.readLine()) != null) {
                consumer.consumeLine(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ChangeLogScmResult(cl.toString(), "The cvs command failed.", logListener.getStdout().toString(), false);
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(consumer.getModifications(), startDate, endDate);
        changeLogSet.setStartVersion(startVersion);
        changeLogSet.setEndVersion(endVersion);
        return new ChangeLogScmResult(cl.toString(), changeLogSet);
    }
    
    @Override
    protected void addDateRangeParameter(final Commandline cl, final String dateRange) {
        cl.createArg().setValue(dateRange);
    }
}
