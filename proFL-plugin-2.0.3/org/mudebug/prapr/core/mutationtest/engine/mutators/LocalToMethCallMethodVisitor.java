// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import org.mudebug.prapr.core.commons.ImmutablePair;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.PraPRMethodInfo;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.reloc.asm.MethodVisitor;

class LocalToMethCallMethodVisitor extends MethodVisitor
{
    private final LocalToMethodCallMutator variant;
    private final String mutatedClassInternalName;
    private final ScopeTracker scopeTracker;
    private final ClassByteArraySource cache;
    private final MutationContext context;
    private final boolean isInterface;
    private final boolean mustStatic;
    
    LocalToMethCallMethodVisitor(final LocalToMethodCallMutator variant, final MutationContext context, final MethodVisitor methodVisitor, final MethodInfo methodInfo, final CollectedClassInfo cci, final ClassByteArraySource cache) {
        super(393216, methodVisitor);
        this.variant = variant;
        this.context = context;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.cache = cache;
        this.isInterface = cci.isInterface();
        this.mutatedClassInternalName = Commons.getOwningClassName(methodInfo).asInternalName();
        this.mustStatic = methodInfo.isStatic();
    }
    
    private LocalVarInfo getLocal(final int index) {
        for (final LocalVarInfo lvi : this.scopeTracker.visibleLocals) {
            if (lvi.index == index) {
                return lvi;
            }
        }
        return null;
    }
    
    private ImmutablePair<String, PraPRMethodInfo> pickMethod(final Map<String, List<PraPRMethodInfo>> methodsInfo, final Type returnType, final boolean mustPublic) {
        int count = 0;
        for (final Map.Entry<String, List<PraPRMethodInfo>> ent : methodsInfo.entrySet()) {
            final String desc = ent.getKey();
            if (Type.getReturnType(desc).equals(returnType)) {
                for (final PraPRMethodInfo methodInfo : ent.getValue()) {
                    if (this.isUseless(methodInfo.name)) {
                        continue;
                    }
                    if (mustPublic && !methodInfo.isPublic) {
                        continue;
                    }
                    final Type[] argTypes = Type.getArgumentTypes(desc);
                    if (methodInfo.isStatic) {
                        if (argTypes.length != 1 || !argTypes[0].equals(returnType)) {
                            continue;
                        }
                        if (count == this.variant.ordinal()) {
                            return new ImmutablePair<String, PraPRMethodInfo>(desc, methodInfo);
                        }
                        ++count;
                    }
                    else {
                        if (this.mustStatic || argTypes.length != 0) {
                            continue;
                        }
                        if (count == this.variant.ordinal()) {
                            return new ImmutablePair<String, PraPRMethodInfo>(desc, methodInfo);
                        }
                        ++count;
                    }
                }
            }
        }
        return null;
    }
    
    private boolean isUseless(final String methodName) {
        return methodName.matches("(equals|hashCode|toString|clone)");
    }
    
    @Override
    public void visitVarInsn(final int opcode, final int var) {
        if (opcode == 25) {
            final LocalVarInfo thisLocal = this.getLocal(var);
            if (thisLocal != null) {
                final Type localType = Type.getType(thisLocal.typeDescriptor);
                if (localType.getSort() != 10) {
                    super.visitVarInsn(opcode, var);
                    return;
                }
                final String varTypeInternalName = localType.getInternalName();
                final CollectedClassInfo cci = ClassInfoCollector.collect(this.cache, varTypeInternalName);
                boolean mustPublic;
                boolean itf;
                if (this.mutatedClassInternalName.equals(varTypeInternalName)) {
                    mustPublic = false;
                    itf = this.isInterface;
                }
                else {
                    mustPublic = true;
                    itf = cci.isInterface();
                }
                final ImmutablePair<String, PraPRMethodInfo> smiPair = this.pickMethod(cci.methodsInfo, localType, mustPublic);
                if (smiPair != null) {
                    final String methodDesc = smiPair.getFirst();
                    final PraPRMethodInfo methodInfo = smiPair.getSecond();
                    String msg;
                    if (methodInfo.isStatic) {
                        msg = String.format("the access to the local %s is replaced a call %s(%s)", thisLocal.name, methodInfo.name, thisLocal.name);
                    }
                    else {
                        msg = String.format("the access to the local %s is replaced a call %s.%s()", thisLocal.name, thisLocal.name, methodInfo.name);
                    }
                    final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
                    if (this.context.shouldMutate(newId)) {
                        final String ownerClassInternalName = methodInfo.owningClassName.asInternalName();
                        super.visitVarInsn(opcode, var);
                        if (methodInfo.isStatic) {
                            super.visitMethodInsn(184, ownerClassInternalName, methodInfo.name, methodDesc, itf);
                        }
                        else if (methodInfo.isPublic || methodInfo.isProtected) {
                            if (itf) {
                                super.visitMethodInsn(185, ownerClassInternalName, methodInfo.name, methodDesc, true);
                            }
                            else {
                                super.visitMethodInsn(182, ownerClassInternalName, methodInfo.name, methodDesc, false);
                            }
                        }
                        else if (methodInfo.isPrivate) {
                            super.visitMethodInsn(183, ownerClassInternalName, methodInfo.name, methodDesc, false);
                        }
                        else {
                            super.visitVarInsn(opcode, var);
                        }
                    }
                    else {
                        super.visitVarInsn(opcode, var);
                    }
                }
                else {
                    super.visitVarInsn(opcode, var);
                }
            }
            else {
                super.visitVarInsn(opcode, var);
            }
        }
        else {
            super.visitVarInsn(opcode, var);
        }
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
}
