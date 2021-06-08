// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.MutationResult;
import java.util.List;

public class MutationGrouping
{
    private final int id;
    private final String title;
    private final List<MutationResult> mutations;
    
    public MutationGrouping(final int id, final String title, final List<MutationResult> mutations) {
        this.title = title;
        this.mutations = mutations;
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public List<MutationResult> getMutations() {
        return this.mutations;
    }
    
    public int getId() {
        return this.id;
    }
}
