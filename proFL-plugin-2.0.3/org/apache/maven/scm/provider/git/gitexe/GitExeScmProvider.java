// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.command.info.InfoItem;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import java.io.File;
import org.apache.maven.scm.provider.git.gitexe.command.remoteinfo.GitRemoteInfoCommand;
import org.apache.maven.scm.provider.git.gitexe.command.blame.GitBlameCommand;
import org.apache.maven.scm.provider.git.gitexe.command.info.GitInfoCommand;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListCommand;
import org.apache.maven.scm.provider.git.gitexe.command.update.GitUpdateCommand;
import org.apache.maven.scm.provider.git.gitexe.command.tag.GitTagCommand;
import org.apache.maven.scm.provider.git.gitexe.command.status.GitStatusCommand;
import org.apache.maven.scm.provider.git.gitexe.command.remove.GitRemoveCommand;
import org.apache.maven.scm.provider.git.gitexe.command.diff.GitDiffCommand;
import org.apache.maven.scm.provider.git.gitexe.command.checkout.GitCheckOutCommand;
import org.apache.maven.scm.provider.git.gitexe.command.checkin.GitCheckInCommand;
import org.apache.maven.scm.provider.git.gitexe.command.changelog.GitChangeLogCommand;
import org.apache.maven.scm.provider.git.gitexe.command.branch.GitBranchCommand;
import org.apache.maven.scm.provider.git.gitexe.command.add.GitAddCommand;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.provider.git.AbstractGitScmProvider;

public class GitExeScmProvider extends AbstractGitScmProvider
{
    @Override
    protected GitCommand getAddCommand() {
        return new GitAddCommand();
    }
    
    @Override
    protected GitCommand getBranchCommand() {
        return new GitBranchCommand();
    }
    
    @Override
    protected GitCommand getChangeLogCommand() {
        return new GitChangeLogCommand();
    }
    
    @Override
    protected GitCommand getCheckInCommand() {
        return new GitCheckInCommand();
    }
    
    @Override
    protected GitCommand getCheckOutCommand() {
        return new GitCheckOutCommand();
    }
    
    @Override
    protected GitCommand getDiffCommand() {
        return new GitDiffCommand();
    }
    
    @Override
    protected GitCommand getExportCommand() {
        return null;
    }
    
    @Override
    protected GitCommand getRemoveCommand() {
        return new GitRemoveCommand();
    }
    
    @Override
    protected GitCommand getStatusCommand() {
        return new GitStatusCommand();
    }
    
    @Override
    protected GitCommand getTagCommand() {
        return new GitTagCommand();
    }
    
    @Override
    protected GitCommand getUpdateCommand() {
        return new GitUpdateCommand();
    }
    
    @Override
    protected GitCommand getListCommand() {
        return new GitListCommand();
    }
    
    public GitCommand getInfoCommand() {
        return new GitInfoCommand();
    }
    
    @Override
    protected GitCommand getBlameCommand() {
        return new GitBlameCommand();
    }
    
    @Override
    protected GitCommand getRemoteInfoCommand() {
        return new GitRemoteInfoCommand();
    }
    
    @Override
    protected String getRepositoryURL(final File path) throws ScmException {
        final InfoScmResult result = this.info(new GitScmProviderRepository(path.getPath()), new ScmFileSet(path), null);
        if (result.getInfoItems().size() != 1) {
            throw new ScmRepositoryException("Cannot find URL: " + ((result.getInfoItems().size() == 0) ? "no" : "multiple") + " items returned by the info command");
        }
        return result.getInfoItems().get(0).getURL();
    }
}
