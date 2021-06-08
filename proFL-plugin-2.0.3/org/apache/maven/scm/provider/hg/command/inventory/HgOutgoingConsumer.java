// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.inventory;

import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgOutgoingConsumer extends HgConsumer
{
    private List<HgChangeSet> changes;
    private static final String BRANCH = "branch";
    
    public HgOutgoingConsumer(final ScmLogger logger) {
        super(logger);
        this.changes = new ArrayList<HgChangeSet>();
    }
    
    @Override
    public void consumeLine(final String line) {
        String branch = null;
        if (line.startsWith("branch")) {
            branch = line.substring("branch".length() + 7);
        }
        this.changes.add(new HgChangeSet(branch));
    }
    
    public List<HgChangeSet> getChanges() {
        return this.changes;
    }
}
