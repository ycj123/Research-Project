// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import java.util.Iterator;
import org.mudebug.prapr.core.commons.ImmutablePair;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.PraPRMethodInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

class FieldAccessToMethodCallMethodVisitor extends MethodVisitor
{
    private final Map<String, List<PraPRMethodInfo>> mutatedClassMethodsInfo;
    private final FieldAccessToMethodCallMutator variant;
    private final MethodInfo mutatedMethodInfo;
    private final ClassByteArraySource cba;
    private final MutationContext context;
    private final boolean isInterface;
    private final ClassName owningClassName;
    
    FieldAccessToMethodCallMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final FieldAccessToMethodCallMutator variant, final ClassByteArraySource cba, final boolean itf) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
        this.mutatedMethodInfo = methodInfo;
        this.mutatedClassMethodsInfo = cci.methodsInfo;
        this.cba = cba;
        this.isInterface = itf;
        this.owningClassName = Commons.getOwningClassName(methodInfo);
    }
    
    private ImmutablePair<String, PraPRMethodInfo> pickGetterMethod(final Type returnType, final boolean staticGetter, final Map<String, List<PraPRMethodInfo>> methodsInfo) {
        int count = 0;
        for (final Map.Entry<String, List<PraPRMethodInfo>> ent : methodsInfo.entrySet()) {
            final String desc = ent.getKey();
            if (Type.getReturnType(desc).equals(returnType) && Type.getArgumentTypes(desc).length == 0) {
                final List<PraPRMethodInfo> smil = ent.getValue();
                for (final PraPRMethodInfo smi : smil) {
                    if ((this.owningClassName.equals(smi.owningClassName) || smi.isPublic) && (!this.mutatedMethodInfo.isStatic() || smi.isStatic) && staticGetter == smi.isStatic) {
                        if (count == this.variant.ordinal()) {
                            return new ImmutablePair<String, PraPRMethodInfo>(desc, smi);
                        }
                        ++count;
                    }
                }
            }
        }
        return null;
    }
    
    private ImmutablePair<String, PraPRMethodInfo> pickSetterMethod(final Type paramType, final boolean staticSetter, final Map<String, List<PraPRMethodInfo>> methodsInfo) {
        int count = 0;
        for (final Map.Entry<String, List<PraPRMethodInfo>> ent : methodsInfo.entrySet()) {
            final String desc = ent.getKey();
            final Type[] argTypes = Type.getArgumentTypes(desc);
            if (Type.getReturnType(desc).getSort() == 0 && argTypes.length == 1 && argTypes[0].equals(paramType)) {
                final List<PraPRMethodInfo> smil = ent.getValue();
                for (final PraPRMethodInfo smi : smil) {
                    if (!smi.name.equals("<init>") && (this.owningClassName.equals(smi.owningClassName) || smi.isPublic) && (!this.mutatedMethodInfo.isStatic() || smi.isStatic) && (!staticSetter || smi.isStatic)) {
                        if (count == this.variant.ordinal()) {
                            return new ImmutablePair<String, PraPRMethodInfo>(desc, smi);
                        }
                        ++count;
                    }
                }
            }
        }
        return null;
    }
    
    private static boolean isStatic(final int opcode) {
        switch (opcode) {
            case 178:
            case 179: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean reading(final int opcode) {
        switch (opcode) {
            case 178:
            case 180: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public void visitFieldInsn(final int opcode, final String ownerInternalName, final String name, final String desc) {
        final Type expectedRetType = Type.getType(desc);
        ImmutablePair<String, PraPRMethodInfo> descSmi;
        boolean itf;
        if (this.reading(opcode)) {
            if (ownerInternalName.equals(this.owningClassName.asInternalName())) {
                descSmi = this.pickGetterMethod(expectedRetType, isStatic(opcode), this.mutatedClassMethodsInfo);
                itf = this.isInterface;
            }
            else {
                final CollectedClassInfo cci = ClassInfoCollector.collect(this.cba, ownerInternalName);
                descSmi = this.pickGetterMethod(expectedRetType, isStatic(opcode), cci.methodsInfo);
                itf = cci.isInterface();
            }
        }
        else if (ownerInternalName.equals(this.owningClassName.asInternalName())) {
            descSmi = this.pickSetterMethod(expectedRetType, isStatic(opcode), this.mutatedClassMethodsInfo);
            itf = this.isInterface;
        }
        else {
            final CollectedClassInfo cci = ClassInfoCollector.collect(this.cba, ownerInternalName);
            descSmi = this.pickSetterMethod(expectedRetType, isStatic(opcode), cci.methodsInfo);
            itf = cci.isInterface();
        }
        if (descSmi != null) {
            final String methodDesc = descSmi.getFirst();
            final PraPRMethodInfo smi = descSmi.getSecond();
            final String md = String.format("the access to field %s.%s is replaced by the call to %s::%s%s", ownerInternalName.replace('/', '.'), name, smi.owningClassName.asJavaName(), smi.name, methodDesc);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, md);
            if (this.context.shouldMutate(newId)) {
                if (smi.isStatic) {
                    super.visitMethodInsn(184, smi.owningClassName.asInternalName(), smi.name, methodDesc, itf);
                }
                else if (smi.isPublic || smi.isProtected) {
                    if (itf) {
                        super.visitMethodInsn(185, smi.owningClassName.asInternalName(), smi.name, methodDesc, true);
                    }
                    else {
                        super.visitMethodInsn(182, smi.owningClassName.asInternalName(), smi.name, methodDesc, false);
                    }
                }
                else if (smi.isPrivate) {
                    super.visitMethodInsn(183, smi.owningClassName.asInternalName(), smi.name, methodDesc, false);
                }
                else {
                    super.visitFieldInsn(opcode, ownerInternalName, name, desc);
                }
            }
            else {
                super.visitFieldInsn(opcode, ownerInternalName, name, desc);
            }
        }
        else {
            super.visitFieldInsn(opcode, ownerInternalName, name, desc);
        }
    }
}
