// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import java.lang.reflect.Field;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.reloc.asm.ClassVisitor;
import org.pitest.reloc.asm.ClassReader;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.reloc.asm.Type;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import org.pitest.reloc.asm.MethodVisitor;

public final class Commons
{
    private Commons() {
    }
    
    public static MethodVisitor dummyMethodVisitor(final MethodVisitor mv) {
        return new MethodVisitor(393216, mv) {};
    }
    
    public static LocalVarInfo pickLocalVariable(final List<LocalVarInfo> visibleLocals, final String desc, final int skip, final int index) {
        int count = skip;
        for (final LocalVarInfo lvi : visibleLocals) {
            if (lvi.typeDescriptor.equals(desc)) {
                if (index == count) {
                    return lvi;
                }
                ++count;
            }
        }
        return null;
    }
    
    public static FieldInfo pickField(final Map<String, List<FieldInfo>> fieldsInfo, final String desc, final int skip, final int index, final boolean accessedInStaticMeth) {
        final List<FieldInfo> fil = fieldsInfo.get(desc);
        int count = skip;
        if (fil != null) {
            if (accessedInStaticMeth) {
                for (final FieldInfo fi : fil) {
                    if (fi.isStatic) {
                        if (count == index) {
                            return fi;
                        }
                        ++count;
                    }
                }
            }
            else {
                for (final FieldInfo fi : fil) {
                    if (count == index) {
                        return fi;
                    }
                    ++count;
                }
            }
        }
        return null;
    }
    
    public static int[] createTempLocals(final LocalVariablesSorter lvs, final Type... types) {
        final int[] tempLocals = new int[types.length];
        for (int i = 0; i < types.length; ++i) {
            tempLocals[i] = lvs.newLocal(types[i]);
        }
        return tempLocals;
    }
    
    public static void storeValues(final MethodVisitor mv, final Type[] args, final int[] tempLocals) {
        for (int i = tempLocals.length - 1; i >= 0; --i) {
            final int tempLocal = tempLocals[i];
            switch (args[i].getSort()) {
                case 9:
                case 10:
                case 11: {
                    mv.visitVarInsn(58, tempLocal);
                    break;
                }
                case 6: {
                    mv.visitVarInsn(56, tempLocal);
                    break;
                }
                case 7: {
                    mv.visitVarInsn(55, tempLocal);
                    break;
                }
                case 8: {
                    mv.visitVarInsn(57, tempLocal);
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    mv.visitVarInsn(54, tempLocal);
                    break;
                }
                default: {
                    throw new RuntimeException();
                }
            }
        }
    }
    
    public static void restoreValues(final MethodVisitor mv, final int[] tempLocals, final Type[] args) {
        for (int i = 0; i < args.length; ++i) {
            final int tempLocal = tempLocals[i];
            switch (args[i].getSort()) {
                case 9:
                case 10:
                case 11: {
                    mv.visitVarInsn(25, tempLocal);
                    break;
                }
                case 6: {
                    mv.visitVarInsn(23, tempLocal);
                    break;
                }
                case 7: {
                    mv.visitVarInsn(22, tempLocal);
                    break;
                }
                case 8: {
                    mv.visitVarInsn(24, tempLocal);
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    mv.visitVarInsn(21, tempLocal);
                    break;
                }
                default: {
                    throw new RuntimeException();
                }
            }
        }
    }
    
    public static void injectFieldValue(final MethodVisitor mv, final int baseIndex, final FieldInfo fi, final Type expectedType) {
        final String desc = expectedType.getDescriptor();
        final String ownerInternalName = fi.owningClassName.asInternalName();
        final String name = fi.name;
        if (fi.isStatic) {
            mv.visitFieldInsn(178, ownerInternalName, name, desc);
        }
        else {
            mv.visitVarInsn(25, baseIndex);
            mv.visitFieldInsn(180, ownerInternalName, name, desc);
        }
    }
    
    public static void injectLocalValue(final MethodVisitor mv, final int local, final Type expectedType) {
        switch (expectedType.getSort()) {
            case 9:
            case 10:
            case 11: {
                mv.visitVarInsn(25, local);
                break;
            }
            case 6: {
                mv.visitVarInsn(23, local);
                break;
            }
            case 7: {
                mv.visitVarInsn(22, local);
                break;
            }
            case 8: {
                mv.visitVarInsn(24, local);
                break;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                mv.visitVarInsn(21, local);
                break;
            }
            default: {
                throw new RuntimeException();
            }
        }
    }
    
    public static void injectDefaultValue(final MethodVisitor mv, final Type expectedType) {
        switch (expectedType.getSort()) {
            case 9:
            case 10:
            case 11: {
                mv.visitInsn(1);
                break;
            }
            case 6: {
                mv.visitInsn(11);
                break;
            }
            case 7: {
                mv.visitInsn(9);
                break;
            }
            case 8: {
                mv.visitInsn(14);
                break;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                mv.visitInsn(3);
                break;
            }
            default: {
                throw new RuntimeException();
            }
        }
    }
    
    public static void injectReturnStmt(final MethodVisitor mv, final Type returnType, final LocalVarInfo lvi, final FieldInfo fi) {
        final int mutatedMethodReturnSort = returnType.getSort();
        if (mutatedMethodReturnSort == 0) {
            mv.visitInsn(177);
        }
        else {
            if (lvi == null && fi == null) {
                injectDefaultValue(mv, returnType);
            }
            else if (fi == null) {
                injectLocalValue(mv, lvi.index, returnType);
            }
            else {
                injectFieldValue(mv, 0, fi, returnType);
            }
            switch (mutatedMethodReturnSort) {
                case 9:
                case 10:
                case 11: {
                    mv.visitInsn(176);
                    break;
                }
                case 6: {
                    mv.visitInsn(174);
                    break;
                }
                case 7: {
                    mv.visitInsn(173);
                    break;
                }
                case 8: {
                    mv.visitInsn(175);
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    mv.visitInsn(172);
                    break;
                }
                default: {
                    throw new RuntimeException();
                }
            }
        }
    }
    
    public static String defValString(final Type type) {
        switch (type.getSort()) {
            case 9:
            case 10:
            case 11: {
                return "null";
            }
            case 6: {
                return "0.F";
            }
            case 7: {
                return "0L";
            }
            case 8: {
                return "0.D";
            }
            case 3: {
                return "0";
            }
            case 1: {
                return "false";
            }
            case 2: {
                return "'\\0'";
            }
            case 4: {
                return "0";
            }
            case 5: {
                return "0";
            }
            default: {
                throw new RuntimeException();
            }
        }
    }
    
    public static boolean isVirtualCall(final int opcode) {
        switch (opcode) {
            case 182:
            case 183:
            case 185: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isStaticCall(final int opcode) {
        return opcode == 184;
    }
    
    public static String getSupertype(final ClassByteArraySource cache, final String typeInternalName) {
        final byte[] bytes = cache.getBytes(typeInternalName).value();
        final SimpleClassVisitor cv = new SimpleClassVisitor();
        final ClassReader cr = new ClassReader(bytes);
        cr.accept(cv, 8);
        return cv.getSuperName();
    }
    
    public static ClassName getOwningClassName(final MethodInfo methodInfo) {
        final String methodName = methodInfo.getDescription();
        final int indexOfColon = methodName.indexOf(58);
        return ClassName.fromString(methodName.substring(0, indexOfColon));
    }
    
    public static int getMethodAccess(final MethodInfo methodInfo) {
        try {
            final Field accessField = MethodInfo.class.getDeclaredField("access");
            accessField.setAccessible(true);
            return accessField.getInt(methodInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    
    private static class SimpleClassVisitor extends ClassVisitor
    {
        private String superName;
        
        public SimpleClassVisitor() {
            super(393216);
        }
        
        @Override
        public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
            super.visit(version, access, name, signature, this.superName = superName, interfaces);
        }
        
        public String getSuperName() {
            return this.superName;
        }
    }
}
