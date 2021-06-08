// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import java.lang.constant.Constable;
import groovyjarjarasm.asm.Opcodes;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Type;
import groovyjarjarasm.asm.MethodAdapter;

public class LocalVariablesSorter extends MethodAdapter
{
    private static final Type OBJECT_TYPE;
    private int[] mapping;
    private Object[] newLocals;
    protected final int firstLocal;
    protected int nextLocal;
    private boolean changed;
    
    public LocalVariablesSorter(final int n, final String s, final MethodVisitor methodVisitor) {
        super(methodVisitor);
        this.mapping = new int[40];
        this.newLocals = new Object[20];
        final Type[] argumentTypes = Type.getArgumentTypes(s);
        this.nextLocal = (((0x8 & n) == 0x0) ? 1 : 0);
        for (int i = 0; i < argumentTypes.length; ++i) {
            this.nextLocal += argumentTypes[i].getSize();
        }
        this.firstLocal = this.nextLocal;
    }
    
    public void visitVarInsn(final int n, final int n2) {
        Type type = null;
        switch (n) {
            case 22:
            case 55: {
                type = Type.LONG_TYPE;
                break;
            }
            case 24:
            case 57: {
                type = Type.DOUBLE_TYPE;
                break;
            }
            case 23:
            case 56: {
                type = Type.FLOAT_TYPE;
                break;
            }
            case 21:
            case 54: {
                type = Type.INT_TYPE;
                break;
            }
            case 25:
            case 58: {
                type = LocalVariablesSorter.OBJECT_TYPE;
                break;
            }
            default: {
                type = Type.VOID_TYPE;
                break;
            }
        }
        this.mv.visitVarInsn(n, this.remap(n2, type));
    }
    
    public void visitIincInsn(final int n, final int n2) {
        this.mv.visitIincInsn(this.remap(n, Type.INT_TYPE), n2);
    }
    
    public void visitMaxs(final int n, final int n2) {
        this.mv.visitMaxs(n, this.nextLocal);
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        this.mv.visitLocalVariable(s, s2, s3, label, label2, this.remap(n, Type.getType(s2)));
    }
    
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
        if (n != -1) {
            throw new IllegalStateException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
        }
        if (!this.changed) {
            this.mv.visitFrame(n, n2, array, n3, array2);
            return;
        }
        final Object[] newLocals = new Object[this.newLocals.length];
        System.arraycopy(this.newLocals, 0, newLocals, 0, newLocals.length);
        int n4 = 0;
        for (final Object o : array) {
            final int n5 = (o == Opcodes.LONG || o == Opcodes.DOUBLE) ? 2 : 1;
            if (o != Opcodes.TOP) {
                this.setFrameLocal(this.remap(n4, n5), o);
            }
            n4 += n5;
        }
        int j = 0;
        int n6 = 0;
        int n7 = 0;
        while (j < this.newLocals.length) {
            final Object o2 = this.newLocals[j++];
            if (o2 != null && o2 != Opcodes.TOP) {
                this.newLocals[n7] = o2;
                n6 = n7 + 1;
                if (o2 == Opcodes.LONG || o2 == Opcodes.DOUBLE) {
                    ++j;
                }
            }
            else {
                this.newLocals[n7] = Opcodes.TOP;
            }
            ++n7;
        }
        this.mv.visitFrame(n, n6, this.newLocals, n3, array2);
        this.newLocals = newLocals;
    }
    
    public int newLocal(final Type type) {
        Constable constable = null;
        switch (type.getSort()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                constable = Opcodes.INTEGER;
                break;
            }
            case 6: {
                constable = Opcodes.FLOAT;
                break;
            }
            case 7: {
                constable = Opcodes.LONG;
                break;
            }
            case 8: {
                constable = Opcodes.DOUBLE;
                break;
            }
            case 9: {
                constable = type.getDescriptor();
                break;
            }
            default: {
                constable = type.getInternalName();
                break;
            }
        }
        final int nextLocal = this.nextLocal;
        this.nextLocal += type.getSize();
        this.setLocalType(nextLocal, type);
        this.setFrameLocal(nextLocal, constable);
        return nextLocal;
    }
    
    protected void setLocalType(final int n, final Type type) {
    }
    
    private void setFrameLocal(final int n, final Object o) {
        final int length = this.newLocals.length;
        if (n >= length) {
            final Object[] newLocals = new Object[Math.max(2 * length, n + 1)];
            System.arraycopy(this.newLocals, 0, newLocals, 0, length);
            this.newLocals = newLocals;
        }
        this.newLocals[n] = o;
    }
    
    private int remap(final int n, final Type type) {
        if (n < this.firstLocal) {
            return n;
        }
        final int n2 = 2 * n + type.getSize() - 1;
        final int length = this.mapping.length;
        if (n2 >= length) {
            final int[] mapping = new int[Math.max(2 * length, n2 + 1)];
            System.arraycopy(this.mapping, 0, mapping, 0, length);
            this.mapping = mapping;
        }
        int localMapping = this.mapping[n2];
        if (localMapping == 0) {
            localMapping = this.newLocalMapping(type);
            this.setLocalType(localMapping, type);
            this.mapping[n2] = localMapping + 1;
        }
        else {
            --localMapping;
        }
        if (localMapping != n) {
            this.changed = true;
        }
        return localMapping;
    }
    
    protected int newLocalMapping(final Type type) {
        final int nextLocal = this.nextLocal;
        this.nextLocal += type.getSize();
        return nextLocal;
    }
    
    private int remap(final int i, final int n) {
        if (i < this.firstLocal || !this.changed) {
            return i;
        }
        final int n2 = 2 * i + n - 1;
        final int n3 = (n2 < this.mapping.length) ? this.mapping[n2] : 0;
        if (n3 == 0) {
            throw new IllegalStateException("Unknown local variable " + i);
        }
        return n3 - 1;
    }
    
    static {
        OBJECT_TYPE = Type.getObjectType("java/lang/Object");
    }
}
