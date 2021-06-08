// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.add;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class HgAddCommand extends AbstractAddCommand implements Command
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        String[] addCmd = { "add", "--verbose" };
        addCmd = HgUtils.expandCommandLine(addCmd, fileSet);
        final File workingDir = fileSet.getBasedir();
        final HgAddConsumer consumer = new HgAddConsumer(this.getLogger(), workingDir);
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), workingDir, addCmd);
        final AddScmResult addScmResult = new AddScmResult(consumer.getAddedFiles(), result);
        for (final File workingFile : fileSet.getFileList()) {
            final File file = new File(workingDir + "/" + workingFile.getPath());
            if (file.isDirectory() && file.listFiles().length == 0) {
                addScmResult.getAddedFiles().add(new ScmFile(workingFile.getPath(), ScmFileStatus.ADDED));
            }
        }
        return addScmResult;
    }
}
