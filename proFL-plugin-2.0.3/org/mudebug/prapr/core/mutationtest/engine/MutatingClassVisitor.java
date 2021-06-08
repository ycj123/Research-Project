// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine;

import org.pitest.mutationtest.engine.gregor.AvoidAssertsMethodAdapter;
import org.pitest.mutationtest.engine.gregor.AvoidStringSwitchedMethodAdapter;
import org.pitest.mutationtest.engine.gregor.LineTrackingMethodVisitor;
import org.pitest.mutationtest.engine.gregor.blocks.BlockCounter;
import org.pitest.mutationtest.engine.gregor.blocks.BlockTrackingMethodDecorator;
import org.pitest.mutationtest.engine.gregor.ClassInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import java.util.Iterator;
import org.pitest.mutationtest.engine.gregor.analysis.InstructionCounter;
import org.pitest.mutationtest.engine.gregor.analysis.InstructionTrackingMethodVisitor;
import org.pitest.mutationtest.engine.gregor.PraPRMethodMutationContext;
import org.pitest.mutationtest.engine.Location;
import org.pitest.mutationtest.engine.MethodName;
import org.pitest.classinfo.ClassName;
import org.pitest.reloc.asm.MethodVisitor;
import java.util.HashSet;
import java.util.Collection;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Set;
import org.pitest.mutationtest.engine.gregor.PraPRMutaterClassContext;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.functional.F;
import org.pitest.reloc.asm.ClassVisitor;

class MutatingClassVisitor extends ClassVisitor
{
    private final F<MethodInfo, Boolean> filter;
    private final PraPRMutaterClassContext context;
    private final Set<MethodMutatorFactory> methodMutators;
    private final CollectedClassInfo collectedClassInfo;
    private final ClassByteArraySource cache;
    private final GlobalInfo classHierarchy;
    
    MutatingClassVisitor(final ClassVisitor delegateClassVisitor, final PraPRMutaterClassContext context, final F<MethodInfo, Boolean> filter, final Collection<MethodMutatorFactory> mutators, final CollectedClassInfo collectedClassInfo, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        super(393216, delegateClassVisitor);
        this.context = context;
        this.filter = filter;
        this.methodMutators = new HashSet<MethodMutatorFactory>(mutators);
        this.collectedClassInfo = collectedClassInfo;
        this.cache = cache;
        this.classHierarchy = classHierarchy;
    }
    
    @Override
    public MethodVisitor visitMethod(final int access, final String methodName, final String methodDescriptor, final String signature, final String[] exceptions) {
        final PraPRMethodMutationContext methodContext = new PraPRMethodMutationContext(this.context, Location.location(ClassName.fromString(this.context.getClassInfo().getName()), MethodName.fromString(methodName), methodDescriptor));
        final MethodVisitor methodVisitor = this.cv.visitMethod(access, methodName, methodDescriptor, signature, exceptions);
        final MethodInfo info = new MethodInfo().withOwner(this.context.getClassInfo()).withAccess(access).withMethodName(methodName).withMethodDescriptor(methodDescriptor);
        if (this.filter.apply(info)) {
            return this.visitMethodForMutation(methodContext, info, methodVisitor);
        }
        return methodVisitor;
    }
    
    private MethodVisitor visitMethodForMutation(final PraPRMethodMutationContext methodContext, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        MethodVisitor next = methodVisitor;
        for (final MethodMutatorFactory each : this.methodMutators) {
            next = this.getMethodVisitor(each, methodContext, methodInfo, next);
        }
        return new InstructionTrackingMethodVisitor(wrapWithDecorators(methodContext, this.wrapWithFilters(methodContext, next)), methodContext);
    }
    
    private MethodVisitor getMethodVisitor(final MethodMutatorFactory methodMutatorFactory, final PraPRMethodMutationContext methodContext, final MethodInfo methodInfo, final MethodVisitor next) {
        if (methodMutatorFactory instanceof PraPRMethodMutatorFactory) {
            return ((PraPRMethodMutatorFactory)methodMutatorFactory).create(methodContext, methodInfo, next, this.collectedClassInfo, this.cache, this.classHierarchy);
        }
        return methodMutatorFactory.create(methodContext, methodInfo, next);
    }
    
    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.context.registerClass(new ClassInfo(version, access, name, signature, superName, interfaces));
    }
    
    @Override
    public void visitSource(final String source, final String debug) {
        super.visitSource(source, debug);
        this.context.registerSourceFile(source);
    }
    
    private static MethodVisitor wrapWithDecorators(final PraPRMethodMutationContext methodContext, final MethodVisitor mv) {
        return wrapWithBlockTracker(methodContext, wrapWithLineTracker(methodContext, mv));
    }
    
    private static MethodVisitor wrapWithBlockTracker(final PraPRMethodMutationContext methodContext, final MethodVisitor mv) {
        return new BlockTrackingMethodDecorator(methodContext, mv);
    }
    
    private static MethodVisitor wrapWithLineTracker(final PraPRMethodMutationContext methodContext, final MethodVisitor mv) {
        return new LineTrackingMethodVisitor(methodContext, mv);
    }
    
    private MethodVisitor wrapWithFilters(final PraPRMethodMutationContext methodContext, final MethodVisitor wrappedMethodVisitor) {
        return wrapWithStringSwitchFilter(methodContext, wrapWithAssertFilter(methodContext, wrappedMethodVisitor));
    }
    
    private static MethodVisitor wrapWithStringSwitchFilter(final PraPRMethodMutationContext methodContext, final MethodVisitor wrappedMethodVisitor) {
        return new AvoidStringSwitchedMethodAdapter(methodContext, wrappedMethodVisitor);
    }
    
    private static MethodVisitor wrapWithAssertFilter(final PraPRMethodMutationContext methodContext, final MethodVisitor wrappedMethodVisitor) {
        return new AvoidAssertsMethodAdapter(methodContext, wrappedMethodVisitor);
    }
}
