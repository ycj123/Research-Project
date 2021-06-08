// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.text.similarity;

public class JaccardDistance implements EditDistance<Double>
{
    private final JaccardSimilarity jaccardSimilarity;
    
    public JaccardDistance() {
        this.jaccardSimilarity = new JaccardSimilarity();
    }
    
    @Override
    public Double apply(final CharSequence left, final CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Math.round((1.0 - this.jaccardSimilarity.apply(left, right)) * 100.0) / 100.0;
    }
}
