// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.update;

import org.apache.maven.scm.provider.hg.command.changelog.HgChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.Map;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.provider.hg.command.diff.HgDiffConsumer;
import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class HgUpdateCommand extends AbstractUpdateCommand implements Command
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion tag) throws ScmException {
        final File workingDir = fileSet.getBasedir();
        String[] updateCmd;
        if (repo.isPushChanges()) {
            updateCmd = new String[] { "pull", "-r", (tag != null && !StringUtils.isEmpty(tag.getName())) ? tag.getName() : "tip" };
        }
        else {
            updateCmd = new String[] { "update", (tag != null && !StringUtils.isEmpty(tag.getName())) ? tag.getName() : "tip", "-c" };
        }
        final ScmResult updateResult = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), workingDir, updateCmd);
        if (!updateResult.isSuccess()) {
            return new UpdateScmResult(null, null, updateResult);
        }
        final int currentRevision = HgUtils.getCurrentRevisionNumber(this.getLogger(), workingDir);
        final int previousRevision = currentRevision - 1;
        final String[] diffCmd = { "diff", "-r", "" + previousRevision };
        final HgDiffConsumer diffConsumer = new HgDiffConsumer(this.getLogger(), workingDir);
        final ScmResult diffResult = HgUtils.execute(diffConsumer, this.getLogger(), workingDir, diffCmd);
        final List<ScmFile> updatedFiles = new ArrayList<ScmFile>();
        final List<CharSequence> changes = new ArrayList<CharSequence>();
        final List<ScmFile> diffFiles = diffConsumer.getChangedFiles();
        final Map<String, CharSequence> diffChanges = diffConsumer.getDifferences();
        for (final ScmFile file : diffFiles) {
            changes.add(diffChanges.get(file.getPath()));
            if (file.getStatus() == ScmFileStatus.MODIFIED) {
                updatedFiles.add(new ScmFile(file.getPath(), ScmFileStatus.PATCHED));
            }
            else {
                updatedFiles.add(file);
            }
        }
        if (repo.isPushChanges()) {
            final String[] hgUpdateCmd = { "update" };
            HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), workingDir, hgUpdateCmd);
        }
        return new UpdateScmResultWithRevision(updatedFiles, new ArrayList<ChangeSet>(0), String.valueOf(currentRevision), diffResult);
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final HgChangeLogCommand command = new HgChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
