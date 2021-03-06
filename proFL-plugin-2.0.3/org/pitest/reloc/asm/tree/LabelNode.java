// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.asm.tree;

import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.reloc.asm.Label;

public class LabelNode extends AbstractInsnNode
{
    private Label label;
    
    public LabelNode() {
        super(-1);
    }
    
    public LabelNode(final Label label) {
        super(-1);
        this.label = label;
    }
    
    @Override
    public int getType() {
        return 8;
    }
    
    public Label getLabel() {
        if (this.label == null) {
            this.label = new Label();
        }
        return this.label;
    }
    
    @Override
    public void accept(final MethodVisitor cv) {
        cv.visitLabel(this.getLabel());
    }
    
    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> labels) {
        return labels.get(this);
    }
    
    public void resetLabel() {
        this.label = null;
    }
}
