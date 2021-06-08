// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.checkin;

import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.FileUtils;
import java.io.File;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public abstract class AbstractCvsCheckInCommand extends AbstractCheckInCommand implements CvsCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("commit", repository, fileSet, false);
        if (version != null && !StringUtils.isEmpty(version.getName())) {
            cl.createArg().setValue("-r" + version.getName());
        }
        cl.createArg().setValue("-R");
        cl.createArg().setValue("-F");
        File messageFile;
        try {
            messageFile = File.createTempFile("scm-commit-message", ".txt");
            FileUtils.fileWrite(messageFile.getAbsolutePath(), message);
        }
        catch (IOException ex) {
            throw new ScmException("Error while making a temporary commit message file.");
        }
        cl.createArg().setValue(messageFile.getAbsolutePath());
        final List<File> files = fileSet.getFileList();
        for (final File f : files) {
            cl.createArg().setValue(f.getPath().replace('\\', '/'));
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        final CheckInScmResult result = this.executeCvsCommand(cl, repository, messageFile);
        try {
            FileUtils.forceDelete(messageFile);
        }
        catch (IOException ex2) {}
        return result;
    }
    
    protected abstract CheckInScmResult executeCvsCommand(final Commandline p0, final CvsScmProviderRepository p1, final File p2) throws ScmException;
}
