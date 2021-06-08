// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.bytecode.analysis;

import org.objectweb.asm.tree.MethodInsnNode;
import org.pitest.classinfo.ClassName;
import org.pitest.sequence.Slot;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.pitest.sequence.SlotWrite;
import org.pitest.functional.F;
import org.pitest.functional.prelude.Prelude;
import org.objectweb.asm.tree.IincInsnNode;
import org.pitest.sequence.SlotRead;
import org.pitest.sequence.Context;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.pitest.sequence.Match;

public class InstructionMatchers
{
    public static Match<AbstractInsnNode> anyInstruction() {
        return Match.always();
    }
    
    public static Match<AbstractInsnNode> opCode(final int opcode) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode a) {
                return a.getOpcode() == opcode;
            }
        };
    }
    
    public static <T extends AbstractInsnNode> Match<AbstractInsnNode> isA(final Class<T> cls) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode a) {
                return a.getClass().isAssignableFrom(cls);
            }
        };
    }
    
    public static Match<AbstractInsnNode> incrementsVariable(final SlotRead<Integer> counterVariable) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> context, final AbstractInsnNode a) {
                return a instanceof IincInsnNode && context.retrieve((SlotRead<Object>)counterVariable).contains((F<Object, Boolean>)Prelude.isEqualTo(((IincInsnNode)a).var));
            }
        };
    }
    
    public static Match<AbstractInsnNode> anIStore(final SlotWrite<Integer> counterVariable) {
        return opCode(54).and(aVariableAccess(counterVariable));
    }
    
    public static Match<AbstractInsnNode> aVariableAccess(final SlotWrite<Integer> counterVariable) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                return t instanceof VarInsnNode && c.store(counterVariable, ((VarInsnNode)t).var);
            }
        };
    }
    
    public static Match<AbstractInsnNode> anIStoreTo(final SlotRead<Integer> counterVariable) {
        return opCode(54).and(variableMatches(counterVariable));
    }
    
    public static Match<AbstractInsnNode> anILoadOf(final SlotRead<Integer> counterVariable) {
        return opCode(21).and(variableMatches(counterVariable));
    }
    
    public static Match<AbstractInsnNode> variableMatches(final SlotRead<Integer> counterVariable) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                return t instanceof VarInsnNode && c.retrieve((SlotRead<Object>)counterVariable).contains((F<Object, Boolean>)Prelude.isEqualTo(((VarInsnNode)t).var));
            }
        };
    }
    
    public static Match<AbstractInsnNode> anIntegerConstant() {
        return opCode(2).or(opCode(3)).or(opCode(4)).or(opCode(5)).or(opCode(6)).or(opCode(7)).or(opCode(8));
    }
    
    public static Match<AbstractInsnNode> aLabelNode(final SlotWrite<LabelNode> slot) {
        return isA(LabelNode.class).and(writeNodeToSlot(slot, LabelNode.class));
    }
    
    public static Match<AbstractInsnNode> aJump() {
        return isA(JumpInsnNode.class);
    }
    
    public static Match<AbstractInsnNode> aConditionalJump() {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                return t instanceof JumpInsnNode && t.getOpcode() != 167 && t.getOpcode() != 168;
            }
        };
    }
    
    public static Match<AbstractInsnNode> aConditionalJumpTo(final Slot<LabelNode> label) {
        return jumpsTo(label.read()).and(aConditionalJump());
    }
    
    public static <T extends AbstractInsnNode> Match<AbstractInsnNode> writeNodeToSlot(final SlotWrite<T> slot, final Class<T> clazz) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                if (clazz.isAssignableFrom(t.getClass())) {
                    c.store(slot, clazz.cast(t));
                    return true;
                }
                return false;
            }
        };
    }
    
    public static Match<AbstractInsnNode> methodCallThatReturns(final ClassName type) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                return t instanceof MethodInsnNode && ((MethodInsnNode)t).desc.endsWith(type.asInternalName() + ";");
            }
        };
    }
    
    public static Match<AbstractInsnNode> methodCall() {
        return isA(MethodInsnNode.class);
    }
    
    public static Match<AbstractInsnNode> methodCallTo(final ClassName owner, final String name) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                if (t instanceof MethodInsnNode) {
                    final MethodInsnNode call = (MethodInsnNode)t;
                    return call.name.equals(name) && call.owner.equals(owner.asInternalName());
                }
                return false;
            }
        };
    }
    
    public static Match<AbstractInsnNode> isInstruction(final SlotRead<AbstractInsnNode> target) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                return c.retrieve((SlotRead<Object>)target).value() == t;
            }
        };
    }
    
    public static Match<AbstractInsnNode> recordTarget(final SlotRead<AbstractInsnNode> target, final SlotWrite<Boolean> found) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                if (c.retrieve((SlotRead<Object>)target).value() == t) {
                    c.store(found, true);
                }
                return true;
            }
        };
    }
    
    private static Match<AbstractInsnNode> storeJumpTarget(final SlotWrite<LabelNode> label) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                if (t instanceof JumpInsnNode) {
                    c.store(label, ((JumpInsnNode)t).label);
                    return true;
                }
                return false;
            }
        };
    }
    
    public static Match<AbstractInsnNode> jumpsTo(final SlotRead<LabelNode> loopStart) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> context, final AbstractInsnNode a) {
                if (!(a instanceof JumpInsnNode)) {
                    return false;
                }
                final JumpInsnNode jump = (JumpInsnNode)a;
                return context.retrieve((SlotRead<Object>)loopStart).contains((F<Object, Boolean>)Prelude.isEqualTo(jump.label));
            }
        };
    }
    
    public static Match<AbstractInsnNode> jumpsTo(final SlotWrite<LabelNode> label) {
        return storeJumpTarget(label);
    }
    
    public static Match<AbstractInsnNode> gotoLabel(final SlotWrite<LabelNode> loopEnd) {
        return opCode(167).and(storeJumpTarget(loopEnd));
    }
    
    public static Match<AbstractInsnNode> labelNode(final SlotRead<LabelNode> loopEnd) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> c, final AbstractInsnNode t) {
                if (!(t instanceof LabelNode)) {
                    return false;
                }
                final LabelNode l = (LabelNode)t;
                return c.retrieve((SlotRead<Object>)loopEnd).contains((F<Object, Boolean>)Prelude.isEqualTo(l));
            }
        };
    }
    
    public static Match<AbstractInsnNode> debug(final String msg) {
        return new Match<AbstractInsnNode>() {
            @Override
            public boolean test(final Context<AbstractInsnNode> context, final AbstractInsnNode a) {
                context.debug(msg);
                return true;
            }
        };
    }
}
