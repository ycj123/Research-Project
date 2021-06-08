// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe;

import org.apache.maven.scm.provider.svn.svnexe.command.remoteinfo.SvnRemoteInfoCommand;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.apache.maven.scm.command.info.InfoItem;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.ScmFileSet;
import java.io.File;
import org.apache.maven.scm.provider.svn.svnexe.command.mkdir.SvnMkdirCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.blame.SvnBlameCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.info.SvnInfoCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.list.SvnListCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.update.SvnUpdateCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.tag.SvnTagCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.status.SvnStatusCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.remove.SvnRemoveCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.export.SvnExeExportCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.diff.SvnDiffCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.checkout.SvnCheckOutCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.checkin.SvnCheckInCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.changelog.SvnChangeLogCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.branch.SvnBranchCommand;
import org.apache.maven.scm.provider.svn.svnexe.command.add.SvnAddCommand;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.provider.svn.AbstractSvnScmProvider;

public class SvnExeScmProvider extends AbstractSvnScmProvider
{
    @Override
    protected SvnCommand getAddCommand() {
        return new SvnAddCommand();
    }
    
    @Override
    protected SvnCommand getBranchCommand() {
        return new SvnBranchCommand();
    }
    
    @Override
    protected SvnCommand getChangeLogCommand() {
        return new SvnChangeLogCommand();
    }
    
    @Override
    protected SvnCommand getCheckInCommand() {
        return new SvnCheckInCommand();
    }
    
    @Override
    protected SvnCommand getCheckOutCommand() {
        return new SvnCheckOutCommand();
    }
    
    @Override
    protected SvnCommand getDiffCommand() {
        return new SvnDiffCommand();
    }
    
    @Override
    protected SvnCommand getExportCommand() {
        return new SvnExeExportCommand();
    }
    
    @Override
    protected SvnCommand getRemoveCommand() {
        return new SvnRemoveCommand();
    }
    
    @Override
    protected SvnCommand getStatusCommand() {
        return new SvnStatusCommand();
    }
    
    @Override
    protected SvnCommand getTagCommand() {
        return new SvnTagCommand();
    }
    
    @Override
    protected SvnCommand getUpdateCommand() {
        return new SvnUpdateCommand();
    }
    
    @Override
    protected SvnCommand getListCommand() {
        return new SvnListCommand();
    }
    
    public SvnCommand getInfoCommand() {
        return new SvnInfoCommand();
    }
    
    @Override
    protected SvnCommand getBlameCommand() {
        return new SvnBlameCommand();
    }
    
    @Override
    protected SvnCommand getMkdirCommand() {
        return new SvnMkdirCommand();
    }
    
    @Override
    protected String getRepositoryURL(final File path) throws ScmException {
        final SvnInfoCommand infoCmd = (SvnInfoCommand)this.getInfoCommand();
        infoCmd.setLogger(this.getLogger());
        final InfoScmResult result = infoCmd.executeInfoCommand(null, new ScmFileSet(new File(""), path), null, false, null);
        if (result.getInfoItems().size() != 1) {
            throw new ScmRepositoryException("Cannot find URL: " + ((result.getInfoItems().size() == 0) ? "no" : "multiple") + " items returned by the info command");
        }
        return result.getInfoItems().get(0).getURL();
    }
    
    @Override
    public RemoteInfoScmResult remoteInfo(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final SvnRemoteInfoCommand svnRemoteInfoCommand = new SvnRemoteInfoCommand();
        return svnRemoteInfoCommand.executeRemoteInfoCommand(repository, fileSet, parameters);
    }
    
    @Override
    public boolean remoteUrlExist(final ScmProviderRepository repository, final CommandParameters parameters) throws ScmException {
        final SvnRemoteInfoCommand svnRemoteInfoCommand = new SvnRemoteInfoCommand();
        return svnRemoteInfoCommand.remoteUrlExist(repository, parameters);
    }
}
