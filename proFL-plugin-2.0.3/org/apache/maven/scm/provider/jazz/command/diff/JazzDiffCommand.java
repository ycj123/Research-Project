// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.diff;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.Map;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import java.util.List;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.DebugLoggerConsumer;
import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.HashMap;
import org.apache.maven.scm.provider.jazz.command.status.JazzStatusCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class JazzDiffCommand extends AbstractDiffCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startRevision, final ScmVersion endRevision) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing diff command...");
        }
        final File baseDir = fileSet.getBasedir();
        final File parentFolder = (baseDir.getParentFile() != null) ? baseDir.getParentFile() : baseDir;
        final JazzStatusCommand statusCmd = new JazzStatusCommand();
        statusCmd.setLogger(this.getLogger());
        final StatusScmResult statusCmdResult = statusCmd.executeStatusCommand(repo, fileSet);
        final List<ScmFile> statusScmFiles = statusCmdResult.getChangedFiles();
        JazzScmCommand diffCmd = null;
        final StringBuilder patch = new StringBuilder();
        final Map<String, CharSequence> differences = new HashMap<String, CharSequence>();
        for (final ScmFile file : statusScmFiles) {
            if (file.getStatus() == ScmFileStatus.MODIFIED) {
                final File fullPath = new File(parentFolder, file.getPath());
                final String relativePath = fullPath.toString().substring(baseDir.toString().length());
                this.getLogger().debug("Full Path     : '" + fullPath + "'");
                this.getLogger().debug("Relative Path : '" + relativePath + "'");
                final DebugLoggerConsumer diffConsumer = new DebugLoggerConsumer(this.getLogger());
                final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
                diffCmd = this.createDiffCommand(repo, fileSet, relativePath);
                final int status = diffCmd.execute(diffConsumer, errConsumer);
                if (status != 0 || errConsumer.hasBeenFed()) {
                    return new DiffScmResult(diffCmd.toString(), "The scm diff command failed.", errConsumer.getOutput(), false);
                }
                patch.append(diffConsumer.getOutput());
                differences.put(relativePath, diffConsumer.getOutput());
            }
        }
        return new DiffScmResult(diffCmd.toString(), statusCmdResult.getChangedFiles(), differences, patch.toString());
    }
    
    public JazzScmCommand createDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String relativePath) {
        final JazzScmCommand command = new JazzScmCommand("diff", repo, fileSet, this.getLogger());
        command.addArgument("file");
        command.addArgument(relativePath);
        return command;
    }
}
