// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.text.similarity;

public interface EditDistance<R> extends SimilarityScore<R>
{
    R apply(final CharSequence p0, final CharSequence p1);
}
