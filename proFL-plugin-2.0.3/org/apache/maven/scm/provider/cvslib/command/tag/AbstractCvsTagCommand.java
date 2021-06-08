// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.tag;

import org.apache.maven.scm.command.tag.TagScmResult;
import java.util.Iterator;
import org.apache.maven.scm.providers.cvslib.settings.Settings;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import org.apache.maven.scm.provider.cvslib.util.CvsUtil;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public abstract class AbstractCvsTagCommand extends AbstractTagCommand implements CvsCommand
{
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(repo, fileSet, tag, new ScmTagParameters(message));
    }
    
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("tag", repository, fileSet, false);
        final Settings settings = CvsUtil.getSettings();
        if (settings.isUseForceTag()) {
            cl.createArg().setValue("-F");
        }
        cl.createArg().setValue("-c");
        cl.createArg().setValue(tag);
        if (fileSet.getFileList() != null && !fileSet.getFileList().isEmpty()) {
            for (final File fileName : fileSet.getFileList()) {
                cl.createArg().setValue(fileName.toString());
            }
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract TagScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
