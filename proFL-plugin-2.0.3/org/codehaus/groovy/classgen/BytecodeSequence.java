// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.util.Iterator;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.stmt.Statement;

public class BytecodeSequence extends Statement
{
    private final List<BytecodeInstruction> instructions;
    
    public BytecodeSequence(final List instructions) {
        this.instructions = (List<BytecodeInstruction>)instructions;
    }
    
    public BytecodeSequence(final BytecodeInstruction instruction) {
        (this.instructions = new ArrayList<BytecodeInstruction>(1)).add(instruction);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        if (visitor instanceof ClassGenerator) {
            final ClassGenerator gen = (ClassGenerator)visitor;
            gen.visitBytecodeSequence(this);
            return;
        }
        for (final Object part : this.instructions) {
            if (part instanceof ASTNode) {
                ((ASTNode)part).visit(visitor);
            }
        }
    }
    
    public List getInstructions() {
        return this.instructions;
    }
}
