// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import java.util.Arrays;
import java.util.Iterator;
import org.mudebug.prapr.core.commons.ImmutablePair;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.classinfo.ClassName;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.PraPRMethodInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

class FactoryMethodMutatorMethodVisitor extends MethodVisitor
{
    private final Map<String, List<PraPRMethodInfo>> mutatedClassMethodsInfo;
    private final FactoryMethodMutator variant;
    private final ClassName mutatedClassName;
    private final ClassByteArraySource cba;
    private final MutationContext context;
    protected LocalVariablesSorter lvs;
    private final GlobalInfo ch;
    
    FactoryMethodMutatorMethodVisitor(final MutationContext context, final MethodVisitor methodVisitor, final MethodInfo methodInfo, final CollectedClassInfo cci, final ClassByteArraySource cache, final FactoryMethodMutator variant, final GlobalInfo classHierarchy) {
        super(393216, methodVisitor);
        this.context = context;
        this.mutatedClassMethodsInfo = cci.methodsInfo;
        this.cba = cache;
        this.variant = variant;
        this.mutatedClassName = Commons.getOwningClassName(methodInfo);
        this.ch = classHierarchy;
    }
    
    private boolean isNonArrayObjectType(final Type type) {
        return type.getSort() == 10;
    }
    
    private ClassName pickClass(final String superName) {
        final int o = this.variant.getOrdinal() - 1;
        if (o < 0) {
            return ClassName.fromString(superName);
        }
        final String[] subClasses = this.ch.subclassesOf(superName);
        if (subClasses != null && o < subClasses.length) {
            return ClassName.fromString(subClasses[o]);
        }
        return null;
    }
    
    private ImmutablePair<String, PraPRMethodInfo> pickConstructor(final boolean isPublic, final Map<String, List<PraPRMethodInfo>> methInfo) {
        int count = 0;
        for (final Map.Entry<String, List<PraPRMethodInfo>> ent : methInfo.entrySet()) {
            final String desc = ent.getKey();
            if (Type.getReturnType(desc).getSort() == 0) {
                for (final PraPRMethodInfo mi : ent.getValue()) {
                    if ((!isPublic || mi.isPublic) && mi.name.equals("<init>")) {
                        if (count == this.variant.getPreferenceOrdinal()) {
                            return new ImmutablePair<String, PraPRMethodInfo>(desc, mi);
                        }
                        ++count;
                    }
                }
            }
        }
        return null;
    }
    
    private boolean isClassObject(final Type type) {
        return type.getSort() == 10 && type.getInternalName().equals("java/lang/Object");
    }
    
    private int firstMatch(final Type[] asis, final boolean[] used, final Type cat) {
        for (int i = 0; i < asis.length; ++i) {
            if ((asis[i].equals(cat) || this.isClassObject(asis[i])) && !used[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private void prepareStack(final Type[] asis, final int[] tempLocals, final Type[] tobe) {
        final boolean[] used = new boolean[asis.length];
        Arrays.fill(used, false);
        for (final Type cat : tobe) {
            final int argIndex = this.firstMatch(asis, used, cat);
            int localIndex;
            Type localType;
            if (argIndex >= 0) {
                used[argIndex] = true;
                localIndex = tempLocals[argIndex];
                localType = asis[argIndex];
            }
            else {
                localIndex = -1;
                localType = null;
            }
            if (localIndex < 0) {
                Commons.injectDefaultValue(this.mv, cat);
            }
            else if (this.isClassObject(localType) && cat.getSort() != 10) {
                Commons.injectDefaultValue(this.mv, cat);
            }
            else {
                Commons.injectLocalValue(this.mv, localIndex, cat);
            }
        }
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
        final Type returnType = Type.getReturnType(desc);
        if (this.isNonArrayObjectType(returnType)) {
            final ClassName returnTypeClassName = this.pickClass(returnType.getInternalName());
            if (returnTypeClassName != null) {
                Map<String, List<PraPRMethodInfo>> methInfo;
                boolean isPublic;
                if (this.mutatedClassName.equals(returnTypeClassName)) {
                    methInfo = this.mutatedClassMethodsInfo;
                    isPublic = false;
                }
                else {
                    final CollectedClassInfo cci = ClassInfoCollector.collect(this.cba, returnTypeClassName.asInternalName());
                    methInfo = cci.methodsInfo;
                    isPublic = true;
                }
                final ImmutablePair<String, PraPRMethodInfo> ctor = this.pickConstructor(isPublic, methInfo);
                if (ctor != null) {
                    final String msg = String.format("the call to factory method %s.%s%s is replaced by an instantiation of type %s using %s", owner.replace('/', '.'), name, desc, returnTypeClassName.asJavaName(), ctor.getFirst());
                    final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
                    if (this.context.shouldMutate(newId)) {
                        final Type[] asis = Type.getArgumentTypes(desc);
                        final int[] tempLocals = Commons.createTempLocals(this.lvs, asis);
                        Commons.storeValues(this.mv, asis, tempLocals);
                        if (!Commons.isStaticCall(opcode)) {
                            super.visitInsn(87);
                        }
                        final String toBeInstantiatedInternalName = ctor.getSecond().owningClassName.asInternalName();
                        super.visitTypeInsn(187, toBeInstantiatedInternalName);
                        super.visitInsn(89);
                        final Type[] tobe = Type.getArgumentTypes(ctor.getFirst());
                        this.prepareStack(asis, tempLocals, tobe);
                        super.visitMethodInsn(183, toBeInstantiatedInternalName, "<init>", ctor.getFirst(), false);
                    }
                    else {
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }
                }
                else {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                }
            }
            else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
        else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
