// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

public interface MutationVisitor
{
    void visitMutation(final String p0, final String p1, final String p2, final String p3, final String p4, final int p5, final String p6, final int p7, final String[] p8, final String[] p9, final double p10, final String p11);
    
    void visitFailureDescription(final String p0, final String p1);
}
