// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.coverage.analysis;

import java.util.ListIterator;
import org.pitest.reloc.asm.tree.LookupSwitchInsnNode;
import java.util.Collection;
import org.pitest.reloc.asm.tree.TableSwitchInsnNode;
import org.pitest.reloc.asm.tree.InsnList;
import org.pitest.reloc.asm.tree.JumpInsnNode;
import java.util.Iterator;
import org.pitest.reloc.asm.tree.TryCatchBlockNode;
import org.pitest.reloc.asm.tree.FrameNode;
import org.pitest.reloc.asm.tree.LabelNode;
import java.util.HashSet;
import org.pitest.reloc.asm.tree.AbstractInsnNode;
import java.util.Set;
import org.pitest.reloc.asm.tree.LineNumberNode;
import java.util.ArrayList;
import java.util.List;
import org.pitest.reloc.asm.tree.MethodNode;

public class ControlFlowAnalyser
{
    private static final int LIKELY_NUMBER_OF_LINES_PER_BLOCK = 7;
    
    public static List<Block> analyze(final MethodNode mn) {
        final List<Block> blocks = new ArrayList<Block>(mn.instructions.size());
        final Set<AbstractInsnNode> jumpTargets = findJumpTargets(mn.instructions);
        addtryCatchBoundaries(mn, jumpTargets);
        Set<Integer> blockLines = smallSet();
        int lastLine = Integer.MIN_VALUE;
        final int lastInstruction = mn.instructions.size() - 1;
        int blockStart = 0;
        for (int i = 0; i != mn.instructions.size(); ++i) {
            final AbstractInsnNode ins = mn.instructions.get(i);
            if (ins instanceof LineNumberNode) {
                final LineNumberNode lnn = (LineNumberNode)ins;
                blockLines.add(lnn.line);
                lastLine = lnn.line;
            }
            else if (jumpTargets.contains(ins) && blockStart != i) {
                blocks.add(new Block(blockStart, i - 1, blockLines));
                blockStart = i;
                blockLines = smallSet();
            }
            else if (endsBlock(ins)) {
                blocks.add(new Block(blockStart, i, blockLines));
                blockStart = i + 1;
                blockLines = smallSet();
            }
            else if (lastLine != Integer.MIN_VALUE && isInstruction(ins)) {
                blockLines.add(lastLine);
            }
        }
        if (blockStart != lastInstruction) {
            blocks.add(new Block(blockStart, lastInstruction, blockLines));
        }
        return blocks;
    }
    
    private static HashSet<Integer> smallSet() {
        return new HashSet<Integer>(7);
    }
    
    private static boolean isInstruction(final AbstractInsnNode ins) {
        return !(ins instanceof LabelNode) && !(ins instanceof FrameNode);
    }
    
    private static void addtryCatchBoundaries(final MethodNode mn, final Set<AbstractInsnNode> jumpTargets) {
        for (final Object each : mn.tryCatchBlocks) {
            final TryCatchBlockNode tcb = (TryCatchBlockNode)each;
            jumpTargets.add(tcb.handler);
        }
    }
    
    private static boolean endsBlock(final AbstractInsnNode ins) {
        return ins instanceof JumpInsnNode || isReturn(ins);
    }
    
    private static boolean isReturn(final AbstractInsnNode ins) {
        final int opcode = ins.getOpcode();
        switch (opcode) {
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 191: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static Set<AbstractInsnNode> findJumpTargets(final InsnList instructions) {
        final Set<AbstractInsnNode> jumpTargets = new HashSet<AbstractInsnNode>();
        for (final AbstractInsnNode o : instructions) {
            if (o instanceof JumpInsnNode) {
                jumpTargets.add(((JumpInsnNode)o).label);
            }
            else if (o instanceof TableSwitchInsnNode) {
                final TableSwitchInsnNode twn = (TableSwitchInsnNode)o;
                jumpTargets.add(twn.dflt);
                jumpTargets.addAll(twn.labels);
            }
            else {
                if (!(o instanceof LookupSwitchInsnNode)) {
                    continue;
                }
                final LookupSwitchInsnNode lsn = (LookupSwitchInsnNode)o;
                jumpTargets.add(lsn.dflt);
                jumpTargets.addAll(lsn.labels);
            }
        }
        return jumpTargets;
    }
}
