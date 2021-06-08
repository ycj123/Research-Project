// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.info;

import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.command.info.InfoItem;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitInfoConsumer extends AbstractConsumer
{
    private List<InfoItem> infoItems;
    private ScmFileSet scmFileSet;
    
    public GitInfoConsumer(final ScmLogger logger, final ScmFileSet scmFileSet) {
        super(logger);
        this.infoItems = new ArrayList<InfoItem>(1);
        this.scmFileSet = scmFileSet;
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("consume line " + line);
        }
        if (this.infoItems.isEmpty() && !StringUtils.isEmpty(line)) {
            final InfoItem infoItem = new InfoItem();
            infoItem.setRevision(StringUtils.trim(line));
            infoItem.setURL(this.scmFileSet.getBasedir().getPath());
            this.infoItems.add(infoItem);
        }
    }
    
    public List<InfoItem> getInfoItems() {
        return this.infoItems;
    }
}
