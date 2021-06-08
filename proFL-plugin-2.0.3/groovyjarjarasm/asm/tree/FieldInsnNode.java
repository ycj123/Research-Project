// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.tree;

import java.util.Map;
import groovyjarjarasm.asm.MethodVisitor;

public class FieldInsnNode extends AbstractInsnNode
{
    public String owner;
    public String name;
    public String desc;
    
    public FieldInsnNode(final int n, final String owner, final String name, final String desc) {
        super(n);
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    public int getType() {
        return 4;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitFieldInsn(this.opcode, this.owner, this.name, this.desc);
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new FieldInsnNode(this.opcode, this.owner, this.name, this.desc);
    }
}
