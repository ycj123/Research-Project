// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.checkout;

import org.apache.maven.scm.ScmResult;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.bazaar.repository.BazaarScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class BazaarCheckOutCommand extends AbstractCheckOutCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        final BazaarScmProviderRepository repository = (BazaarScmProviderRepository)repo;
        final String url = repository.getURI();
        final File checkoutDir = fileSet.getBasedir();
        try {
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("Removing " + checkoutDir);
            }
            FileUtils.deleteDirectory(checkoutDir);
        }
        catch (IOException e) {
            throw new ScmException("Cannot remove " + checkoutDir);
        }
        final List<String> checkoutCmd = new ArrayList<String>();
        checkoutCmd.add("branch");
        checkoutCmd.add(url);
        checkoutCmd.add(checkoutDir.getAbsolutePath());
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            checkoutCmd.add("--revision");
            checkoutCmd.add("tag:" + version.getName());
        }
        final BazaarConsumer checkoutConsumer = new BazaarConsumer(this.getLogger());
        BazaarUtils.execute(checkoutConsumer, this.getLogger(), checkoutDir.getParentFile(), checkoutCmd.toArray(new String[0]));
        final String[] inventoryCmd = { "inventory" };
        final BazaarCheckOutConsumer consumer = new BazaarCheckOutConsumer(this.getLogger(), checkoutDir);
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), checkoutDir, inventoryCmd);
        if (!result.isSuccess()) {
            throw new ScmException(result.getProviderMessage());
        }
        return new CheckOutScmResult(consumer.getCheckedOutFiles(), result);
    }
}
