// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine;

import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;

public interface PraPRMethodMutatorFactory extends MethodMutatorFactory
{
    MethodVisitor create(final MutationContext p0, final MethodInfo p1, final MethodVisitor p2, final CollectedClassInfo p3, final ClassByteArraySource p4, final GlobalInfo p5);
}
