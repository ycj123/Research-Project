// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.branch;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.hg.command.inventory.HgListConsumer;
import org.apache.maven.scm.provider.hg.repository.HgScmProviderRepository;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class HgBranchCommand extends AbstractBranchCommand implements Command
{
    @Override
    protected ScmResult executeBranchCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String branch, final String message) throws ScmException {
        return this.executeBranchCommand(scmProviderRepository, fileSet, branch, new ScmBranchParameters(message));
    }
    
    @Override
    protected ScmResult executeBranchCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String branch, final ScmBranchParameters scmBranchParameters) throws ScmException {
        if (StringUtils.isBlank(branch)) {
            throw new ScmException("branch must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support branchging subsets of a directory");
        }
        final File workingDir = fileSet.getBasedir();
        final String[] branchCmd = { "branch", branch };
        final HgConsumer branchConsumer = new HgConsumer(this.getLogger()) {
            @Override
            public void doConsume(final ScmFileStatus status, final String trimmedLine) {
            }
        };
        ScmResult result = HgUtils.execute(branchConsumer, this.getLogger(), workingDir, branchCmd);
        final HgScmProviderRepository repository = (HgScmProviderRepository)scmProviderRepository;
        if (!result.isSuccess()) {
            throw new ScmException("Error while executing command " + this.joinCmd(branchCmd));
        }
        final String[] commitCmd = { "commit", "--message", scmBranchParameters.getMessage() };
        result = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), workingDir, commitCmd);
        if (!result.isSuccess()) {
            throw new ScmException("Error while executing command " + this.joinCmd(commitCmd));
        }
        if (repository.isPushChanges() && !repository.getURI().equals(fileSet.getBasedir().getAbsolutePath())) {
            final String[] pushCmd = { "push", "--new-branch", repository.getURI() };
            result = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), pushCmd);
            if (!result.isSuccess()) {
                throw new ScmException("Error while executing command " + this.joinCmd(pushCmd));
            }
        }
        final String[] listCmd = { "locate" };
        final HgListConsumer listconsumer = new HgListConsumer(this.getLogger());
        result = HgUtils.execute(listconsumer, this.getLogger(), fileSet.getBasedir(), listCmd);
        if (!result.isSuccess()) {
            throw new ScmException("Error while executing command " + this.joinCmd(listCmd));
        }
        final List<ScmFile> files = listconsumer.getFiles();
        final List<ScmFile> fileList = new ArrayList<ScmFile>();
        for (final ScmFile f : files) {
            fileList.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
        }
        return new BranchScmResult(fileList, result);
    }
    
    private String joinCmd(final String[] cmd) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < cmd.length; ++i) {
            final String s = cmd[i];
            result.append(s);
            if (i < cmd.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
