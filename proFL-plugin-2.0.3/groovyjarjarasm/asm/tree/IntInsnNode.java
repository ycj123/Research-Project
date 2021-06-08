// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.tree;

import java.util.Map;
import groovyjarjarasm.asm.MethodVisitor;

public class IntInsnNode extends AbstractInsnNode
{
    public int operand;
    
    public IntInsnNode(final int n, final int operand) {
        super(n);
        this.operand = operand;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    public int getType() {
        return 1;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitIntInsn(this.opcode, this.operand);
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new IntInsnNode(this.opcode, this.operand);
    }
}
