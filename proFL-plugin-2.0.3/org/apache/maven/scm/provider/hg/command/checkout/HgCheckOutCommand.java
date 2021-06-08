// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.checkout;

import org.apache.maven.scm.ScmResult;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.hg.repository.HgScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class HgCheckOutCommand extends AbstractCheckOutCommand implements Command
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion scmVersion, final boolean recursive) throws ScmException {
        final HgScmProviderRepository repository = (HgScmProviderRepository)repo;
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
        final List<String> cmdList = new ArrayList<String>();
        if (repo.isPushChanges()) {
            cmdList.add("clone");
        }
        else {
            cmdList.add("update");
        }
        if (scmVersion != null && !StringUtils.isEmpty(scmVersion.getName())) {
            cmdList.add("-r");
            cmdList.add(scmVersion.getName());
        }
        if (!repo.isPushChanges()) {
            cmdList.add("-c");
        }
        cmdList.add(url);
        cmdList.add(checkoutDir.getAbsolutePath());
        final String[] checkoutCmd = cmdList.toArray(new String[0]);
        final HgConsumer checkoutConsumer = new HgConsumer(this.getLogger());
        HgUtils.execute(checkoutConsumer, this.getLogger(), checkoutDir.getParentFile(), checkoutCmd);
        final String[] inventoryCmd = { "locate" };
        final HgCheckOutConsumer consumer = new HgCheckOutConsumer(this.getLogger(), checkoutDir);
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), checkoutDir, inventoryCmd);
        return new CheckOutScmResult(consumer.getCheckedOutFiles(), result);
    }
}
