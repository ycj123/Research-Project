// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import org.pitest.reloc.asm.Type;
import java.util.LinkedList;
import org.pitest.reloc.asm.MethodVisitor;
import java.util.ArrayList;
import java.util.List;
import org.pitest.reloc.asm.FieldVisitor;
import org.pitest.classinfo.ClassName;
import org.pitest.reloc.asm.ClassVisitor;

class CollectorClassVisitor extends ClassVisitor
{
    private final CollectedClassInfo cci;
    private ClassName owningClassName;
    
    CollectorClassVisitor() {
        super(393216);
        this.cci = new CollectedClassInfo();
    }
    
    public CollectedClassInfo getCollectedClassInfo() {
        return this.cci;
    }
    
    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        this.cci.setInterface((access & 0x200) != 0x0);
        this.owningClassName = ClassName.fromString(name);
        super.visit(version, access, name, signature, superName, interfaces);
    }
    
    @Override
    public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
        List<FieldInfo> infoList = this.cci.fieldsInfo.get(desc);
        if (infoList == null) {
            infoList = new ArrayList<FieldInfo>();
            this.cci.fieldsInfo.put(desc, infoList);
        }
        infoList.add(new FieldInfo(access, name, this.owningClassName));
        return super.visitField(access, name, desc, signature, value);
    }
    
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final List<Integer> nullableParams = new LinkedList<Integer>();
        int argIndex = 0;
        if ((access & 0x8) == 0x0) {
            ++argIndex;
        }
        final Type[] arr$;
        final Type[] argTypes = arr$ = Type.getArgumentTypes(desc);
        for (final Type at : arr$) {
            switch (at.getSort()) {
                case 9:
                case 10:
                case 11: {
                    nullableParams.add(argIndex);
                    break;
                }
            }
            argIndex += at.getSize();
        }
        List<PraPRMethodInfo> infoList = this.cci.methodsInfo.get(desc);
        if (infoList == null) {
            infoList = new ArrayList<PraPRMethodInfo>();
            this.cci.methodsInfo.put(desc, infoList);
        }
        final MethodVisitor superMethodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        final CollectorMethodVisitor cmv = new CollectorMethodVisitor(superMethodVisitor);
        infoList.add(new PraPRMethodInfo(access, name, cmv.getLocalsInfo(), this.owningClassName, nullableParams));
        return cmv;
    }
}
