// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.info;

import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.info.InfoItem;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgInfoConsumer extends HgConsumer
{
    private List<InfoItem> infoItems;
    
    public HgInfoConsumer(final ScmLogger scmLogger) {
        super(scmLogger);
        this.infoItems = new ArrayList<InfoItem>(1);
    }
    
    @Override
    public void consumeLine(final String line) {
        final InfoItem infoItem = new InfoItem();
        infoItem.setRevision(line);
        this.infoItems.add(infoItem);
    }
    
    public List<InfoItem> getInfoItems() {
        return this.infoItems;
    }
}
