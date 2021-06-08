// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.DetectionStatus;
import org.pitest.functional.Option;
import java.util.Comparator;
import java.util.Collections;
import org.pitest.mutationtest.MutationResult;
import java.util.List;

public class Line
{
    private final long number;
    private final String text;
    private final LineStatus lineCovered;
    private final List<MutationResult> mutations;
    
    public Line(final long number, final String text, final LineStatus lineCovered, final List<MutationResult> mutations) {
        this.number = number;
        this.text = text;
        this.lineCovered = lineCovered;
        Collections.sort(this.mutations = mutations, new ResultComparator());
    }
    
    public long getNumber() {
        return this.number;
    }
    
    public String getText() {
        return this.text;
    }
    
    public LineStatus getLineCovered() {
        return this.lineCovered;
    }
    
    public List<MutationResult> getMutations() {
        return this.mutations;
    }
    
    public Option<DetectionStatus> detectionStatus() {
        if (this.mutations.isEmpty()) {
            return (Option<DetectionStatus>)Option.none();
        }
        return Option.some(this.mutations.get(0).getStatus());
    }
    
    public int getNumberOfMutations() {
        return this.mutations.size();
    }
    
    public String getNumberOfMutationsForDisplay() {
        if (this.getNumberOfMutations() > 0) {
            return "" + this.getNumberOfMutations();
        }
        return "";
    }
    
    public LineStyle getStyles() {
        return new LineStyle(this);
    }
}
