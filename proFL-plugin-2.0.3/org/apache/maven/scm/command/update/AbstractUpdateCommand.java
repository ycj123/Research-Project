// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.update;

import java.util.Iterator;
import java.util.Date;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import java.util.List;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ChangeSet;
import java.util.ArrayList;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractUpdateCommand extends AbstractCommand
{
    protected abstract UpdateScmResult executeUpdateCommand(final ScmProviderRepository p0, final ScmFileSet p1, final ScmVersion p2) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        final boolean runChangelog = Boolean.valueOf(parameters.getString(CommandParameter.RUN_CHANGELOG_WITH_UPDATE, "true"));
        final UpdateScmResult updateScmResult = this.executeUpdateCommand(repository, fileSet, scmVersion);
        final List<ScmFile> filesList = updateScmResult.getUpdatedFiles();
        if (!runChangelog) {
            return updateScmResult;
        }
        final ChangeLogCommand changeLogCmd = this.getChangeLogCommand();
        if (filesList != null && filesList.size() > 0 && changeLogCmd != null) {
            final ChangeLogScmResult changeLogScmResult = (ChangeLogScmResult)changeLogCmd.executeCommand(repository, fileSet, parameters);
            final List<ChangeSet> changes = new ArrayList<ChangeSet>();
            final ChangeLogSet changeLogSet = changeLogScmResult.getChangeLog();
            if (changeLogSet != null) {
                Date startDate = null;
                try {
                    startDate = parameters.getDate(CommandParameter.START_DATE);
                }
                catch (ScmException ex) {}
                for (final ChangeSet change : changeLogSet.getChangeSets()) {
                    if (startDate != null && change.getDate() != null && startDate.after(change.getDate())) {
                        continue;
                    }
                    for (final ScmFile currentFile : filesList) {
                        if (change.containsFilename(currentFile.getPath())) {
                            changes.add(change);
                            break;
                        }
                    }
                }
            }
            updateScmResult.setChanges(changes);
        }
        return updateScmResult;
    }
    
    protected abstract ChangeLogCommand getChangeLogCommand();
}
