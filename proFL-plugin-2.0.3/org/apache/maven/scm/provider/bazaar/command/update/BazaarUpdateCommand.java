// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.update;

import org.apache.maven.scm.provider.bazaar.command.changelog.BazaarChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import java.util.Iterator;
import java.util.Map;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.provider.bazaar.command.diff.BazaarDiffConsumer;
import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class BazaarUpdateCommand extends AbstractUpdateCommand implements Command
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            throw new ScmException("This provider can't handle tags.");
        }
        final File workingDir = fileSet.getBasedir();
        final String[] updateCmd = { "pull" };
        final ScmResult updateResult = BazaarUtils.execute(new BazaarConsumer(this.getLogger()), this.getLogger(), workingDir, updateCmd);
        if (!updateResult.isSuccess()) {
            return new UpdateScmResult(null, null, updateResult);
        }
        final int currentRevision = BazaarUtils.getCurrentRevisionNumber(this.getLogger(), workingDir);
        final int previousRevision = currentRevision - 1;
        final String[] diffCmd = { "diff", "--revision", "" + previousRevision };
        final BazaarDiffConsumer diffConsumer = new BazaarDiffConsumer(this.getLogger(), workingDir);
        final ScmResult diffResult = BazaarUtils.execute(diffConsumer, this.getLogger(), workingDir, diffCmd);
        final List<ScmFile> updatedFiles = new ArrayList<ScmFile>();
        final List<CharSequence> changes = new ArrayList<CharSequence>();
        final List<ScmFile> diffFiles = diffConsumer.getChangedFiles();
        final Map<String, CharSequence> diffChanges = diffConsumer.getDifferences();
        for (final ScmFile file : diffFiles) {
            changes.add(diffChanges.get(file));
            if (file.getStatus() == ScmFileStatus.MODIFIED) {
                updatedFiles.add(new ScmFile(file.getPath(), ScmFileStatus.PATCHED));
            }
            else {
                updatedFiles.add(file);
            }
        }
        return new UpdateScmResultWithRevision(updatedFiles, new ArrayList<ChangeSet>(0), String.valueOf(currentRevision), diffResult);
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final BazaarChangeLogCommand command = new BazaarChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
