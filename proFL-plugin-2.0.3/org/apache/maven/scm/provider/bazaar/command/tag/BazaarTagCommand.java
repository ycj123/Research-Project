// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.tag;

import java.io.File;
import org.apache.maven.scm.provider.bazaar.repository.BazaarScmProviderRepository;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class BazaarTagCommand extends AbstractTagCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tagName, final ScmTagParameters scmTagParameters) throws ScmException {
        if (tagName == null || StringUtils.isEmpty(tagName.trim())) {
            throw new ScmException("tag name must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("tagging specific files is not allowed");
        }
        final File bazaarRoot = fileSet.getBasedir();
        final BazaarConsumer consumer = new BazaarConsumer(this.getLogger());
        final String[] tagCmd = { "tag", tagName };
        final ScmResult tagResult = BazaarUtils.execute(consumer, this.getLogger(), bazaarRoot, tagCmd);
        if (!tagResult.isSuccess()) {
            return new TagScmResult(null, tagResult);
        }
        final BazaarLsConsumer lsConsumer = new BazaarLsConsumer(this.getLogger(), bazaarRoot, ScmFileStatus.TAGGED);
        final String[] lsCmd = { "ls", "--recursive", "--revision", "tag:" + tagName };
        final ScmResult lsResult = BazaarUtils.execute(lsConsumer, this.getLogger(), bazaarRoot, lsCmd);
        if (!lsResult.isSuccess()) {
            return new TagScmResult(null, lsResult);
        }
        final BazaarScmProviderRepository bazaarRepository = (BazaarScmProviderRepository)repository;
        if (!bazaarRepository.getURI().equals(fileSet.getBasedir().getAbsolutePath()) && repository.isPushChanges()) {
            final String[] pushCmd = { "push", bazaarRepository.getURI() };
            final ScmResult pushResult = BazaarUtils.execute(new BazaarConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), pushCmd);
            if (!pushResult.isSuccess()) {
                return new TagScmResult(null, pushResult);
            }
        }
        return new TagScmResult(lsConsumer.getListedFiles(), tagResult);
    }
}
