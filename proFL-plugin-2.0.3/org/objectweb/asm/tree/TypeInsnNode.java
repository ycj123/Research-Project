// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.tree;

import java.util.Map;
import org.objectweb.asm.MethodVisitor;

public class TypeInsnNode extends AbstractInsnNode
{
    public String desc;
    
    public TypeInsnNode(final int opcode, final String desc) {
        super(opcode);
        this.desc = desc;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    @Override
    public int getType() {
        return 3;
    }
    
    @Override
    public void accept(final MethodVisitor mv) {
        mv.visitTypeInsn(this.opcode, this.desc);
        this.acceptAnnotations(mv);
    }
    
    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> labels) {
        return new TypeInsnNode(this.opcode, this.desc).cloneAnnotations(this);
    }
}
