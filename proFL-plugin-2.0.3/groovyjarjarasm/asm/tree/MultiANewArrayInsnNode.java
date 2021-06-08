// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.tree;

import java.util.Map;
import groovyjarjarasm.asm.MethodVisitor;

public class MultiANewArrayInsnNode extends AbstractInsnNode
{
    public String desc;
    public int dims;
    
    public MultiANewArrayInsnNode(final String desc, final int dims) {
        super(197);
        this.desc = desc;
        this.dims = dims;
    }
    
    public int getType() {
        return 12;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitMultiANewArrayInsn(this.desc, this.dims);
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new MultiANewArrayInsnNode(this.desc, this.dims);
    }
}
