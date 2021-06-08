// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.export;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractExportCommand extends AbstractCommand
{
    protected abstract ExportScmResult executeExportCommand(final ScmProviderRepository p0, final ScmFileSet p1, final ScmVersion p2, final String p3) throws ScmException;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        final String outputDirectory = parameters.getString(CommandParameter.OUTPUT_DIRECTORY, null);
        return this.executeExportCommand(repository, fileSet, scmVersion, outputDirectory);
    }
}
