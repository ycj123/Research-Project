// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.tag;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.hg.command.inventory.HgListConsumer;
import org.apache.maven.scm.provider.hg.repository.HgScmProviderRepository;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class HgTagCommand extends AbstractTagCommand implements Command
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(scmProviderRepository, fileSet, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (tag == null || StringUtils.isEmpty(tag.trim())) {
            throw new ScmException("tag must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support tagging subsets of a directory : " + fileSet.getFileList());
        }
        final File workingDir = fileSet.getBasedir();
        final String[] tagCmd = { "tag", "--message", scmTagParameters.getMessage(), tag };
        final StringBuilder cmd = this.joinCmd(tagCmd);
        final HgTagConsumer consumer = new HgTagConsumer(this.getLogger());
        ScmResult result = HgUtils.execute(consumer, this.getLogger(), workingDir, tagCmd);
        final HgScmProviderRepository repository = (HgScmProviderRepository)scmProviderRepository;
        if (!result.isSuccess()) {
            throw new ScmException("Error while executing command " + cmd.toString());
        }
        if (repository.isPushChanges() && !repository.getURI().equals(fileSet.getBasedir().getAbsolutePath())) {
            final String branchName = HgUtils.getCurrentBranchName(this.getLogger(), workingDir);
            final boolean differentOutgoingBranch = HgUtils.differentOutgoingBranchFound(this.getLogger(), workingDir, branchName);
            final String[] pushCmd = { "push", differentOutgoingBranch ? ("-r" + branchName) : null, repository.getURI() };
            result = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), pushCmd);
        }
        final String[] listCmd = { "locate" };
        final HgListConsumer listconsumer = new HgListConsumer(this.getLogger());
        result = HgUtils.execute(listconsumer, this.getLogger(), fileSet.getBasedir(), listCmd);
        if (result.isSuccess()) {
            final List<ScmFile> files = listconsumer.getFiles();
            final List<ScmFile> fileList = new ArrayList<ScmFile>();
            for (final ScmFile f : files) {
                if (!f.getPath().endsWith(".hgtags")) {
                    fileList.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
                }
            }
            return new TagScmResult(fileList, result);
        }
        throw new ScmException("Error while executing command " + cmd.toString());
    }
    
    private StringBuilder joinCmd(final String[] cmd) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < cmd.length; ++i) {
            final String s = cmd[i];
            result.append(s);
            if (i < cmd.length - 1) {
                result.append(" ");
            }
        }
        return result;
    }
}
