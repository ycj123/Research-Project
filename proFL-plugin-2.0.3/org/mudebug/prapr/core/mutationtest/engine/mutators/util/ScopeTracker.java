// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import java.util.Iterator;
import java.util.ArrayList;
import org.pitest.reloc.asm.Label;
import java.util.List;

public class ScopeTracker
{
    private final List<LocalVarInfo> localsInfo;
    private final Indexer<Label> labelIndexer;
    public final List<LocalVarInfo> visibleLocals;
    
    public ScopeTracker(final List<LocalVarInfo> localsInfo) {
        this.localsInfo = localsInfo;
        this.labelIndexer = new Indexer<Label>();
        this.visibleLocals = new ArrayList<LocalVarInfo>();
    }
    
    private void kill(final int labelIndex) {
        final Iterator<LocalVarInfo> lvit = this.visibleLocals.iterator();
        while (lvit.hasNext()) {
            if (lvit.next().endsAt == labelIndex) {
                lvit.remove();
            }
        }
    }
    
    private void gen(final int labelIndex) {
        for (final LocalVarInfo lvi : this.localsInfo) {
            if (lvi.startsAt == labelIndex) {
                this.visibleLocals.add(lvi);
            }
        }
    }
    
    public void transfer(final Label label) {
        final int index = this.labelIndexer.index(label);
        this.kill(index);
        this.gen(index);
    }
    
    public LocalVarInfo find(final int index) {
        for (final LocalVarInfo lvi : this.visibleLocals) {
            if (lvi.index == index) {
                return lvi;
            }
        }
        return null;
    }
}
