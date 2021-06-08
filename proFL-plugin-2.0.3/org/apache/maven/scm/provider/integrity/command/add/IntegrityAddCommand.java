// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.add;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class IntegrityAddCommand extends AbstractAddCommand
{
    public AddScmResult executeAddCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        this.getLogger().info("Attempting to add new files from directory " + fileSet.getBasedir().getAbsolutePath());
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final Sandbox siSandbox = iRepo.getSandbox();
        final String excludes = Sandbox.formatFilePatterns(fileSet.getExcludes());
        final String includes = Sandbox.formatFilePatterns(fileSet.getIncludes());
        final String msg = (null == message || message.length() == 0) ? System.getProperty("message") : message;
        final List<ScmFile> addedFiles = siSandbox.addNonMembers(excludes, includes, msg);
        if (siSandbox.getOverallAddSuccess()) {
            return new AddScmResult("si add", addedFiles);
        }
        return new AddScmResult(addedFiles, new ScmResult("si add", "There was a problem adding files to the repository", "", false));
    }
}
