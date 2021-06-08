// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.Iterator;
import org.codehaus.groovy.GroovyBugError;
import java.util.Collection;
import java.util.HashMap;
import groovyjarjarasm.asm.MethodVisitor;
import java.util.LinkedList;
import java.util.Map;
import groovyjarjarasm.asm.Label;
import org.codehaus.groovy.ast.VariableScope;
import groovyjarjarasm.asm.Opcodes;

public class CompileStack implements Opcodes
{
    private boolean clear;
    private VariableScope scope;
    private Label continueLabel;
    private Label breakLabel;
    private Map stackVariables;
    private int currentVariableIndex;
    private int nextVariableIndex;
    private final LinkedList temporaryVariables;
    private final LinkedList usedVariables;
    private Map superBlockNamedLabels;
    private Map currentBlockNamedLabels;
    private LinkedList<BlockRecorder> finallyBlocks;
    private LinkedList<BlockRecorder> visitedBlocks;
    private Label thisStartLabel;
    private Label thisEndLabel;
    private MethodVisitor mv;
    private BytecodeHelper helper;
    private final LinkedList stateStack;
    private int localVariableOffset;
    private final Map namedLoopBreakLabel;
    private final Map namedLoopContinueLabel;
    private String className;
    private LinkedList<ExceptionTableEntry> typedExceptions;
    private LinkedList<ExceptionTableEntry> untypedExceptions;
    
    public CompileStack() {
        this.clear = true;
        this.stackVariables = new HashMap();
        this.currentVariableIndex = 1;
        this.nextVariableIndex = 1;
        this.temporaryVariables = new LinkedList();
        this.usedVariables = new LinkedList();
        this.superBlockNamedLabels = new HashMap();
        this.currentBlockNamedLabels = new HashMap();
        this.finallyBlocks = new LinkedList<BlockRecorder>();
        this.visitedBlocks = new LinkedList<BlockRecorder>();
        this.stateStack = new LinkedList();
        this.namedLoopBreakLabel = new HashMap();
        this.namedLoopContinueLabel = new HashMap();
        this.typedExceptions = new LinkedList<ExceptionTableEntry>();
        this.untypedExceptions = new LinkedList<ExceptionTableEntry>();
    }
    
    protected void pushState() {
        this.stateStack.add(new StateStackElement());
        this.stackVariables = new HashMap(this.stackVariables);
        this.finallyBlocks = new LinkedList<BlockRecorder>(this.finallyBlocks);
    }
    
    private void popState() {
        if (this.stateStack.size() == 0) {
            throw new GroovyBugError("Tried to do a pop on the compile stack without push.");
        }
        final StateStackElement element = this.stateStack.removeLast();
        this.scope = element.scope;
        this.continueLabel = element.continueLabel;
        this.breakLabel = element.breakLabel;
        this.currentVariableIndex = element.lastVariableIndex;
        this.stackVariables = element.stackVariables;
        this.nextVariableIndex = element.nextVariableIndex;
        this.finallyBlocks = element.finallyBlocks;
    }
    
    public Label getContinueLabel() {
        return this.continueLabel;
    }
    
    public Label getBreakLabel() {
        return this.breakLabel;
    }
    
    public void removeVar(final int tempIndex) {
        final Variable head = this.temporaryVariables.removeFirst();
        if (head.getIndex() != tempIndex) {
            throw new GroovyBugError("CompileStack#removeVar: tried to remove a temporary variable in wrong order");
        }
        this.currentVariableIndex = head.getPrevIndex();
        this.nextVariableIndex = tempIndex;
    }
    
    private void setEndLabels() {
        final Label endLabel = new Label();
        this.mv.visitLabel(endLabel);
        for (final Variable var : this.stackVariables.values()) {
            var.setEndLabel(endLabel);
        }
        this.thisEndLabel = endLabel;
    }
    
    public void pop() {
        this.setEndLabels();
        this.popState();
    }
    
    public VariableScope getScope() {
        return this.scope;
    }
    
    public int defineTemporaryVariable(final org.codehaus.groovy.ast.Variable var, final boolean store) {
        return this.defineTemporaryVariable(var.getName(), var.getType(), store);
    }
    
    public Variable getVariable(final String variableName) {
        return this.getVariable(variableName, true);
    }
    
    public Variable getVariable(final String variableName, final boolean mustExist) {
        if (variableName.equals("this")) {
            return Variable.THIS_VARIABLE;
        }
        if (variableName.equals("super")) {
            return Variable.SUPER_VARIABLE;
        }
        final Variable v = this.stackVariables.get(variableName);
        if (v == null && mustExist) {
            throw new GroovyBugError("tried to get a variable with the name " + variableName + " as stack variable, but a variable with this name was not created");
        }
        return v;
    }
    
    public int defineTemporaryVariable(final String name, final boolean store) {
        return this.defineTemporaryVariable(name, ClassHelper.DYNAMIC_TYPE, store);
    }
    
    public int defineTemporaryVariable(final String name, final ClassNode node, final boolean store) {
        final Variable answer = this.defineVar(name, node, false);
        this.temporaryVariables.addFirst(answer);
        this.usedVariables.removeLast();
        if (store) {
            this.mv.visitVarInsn(58, this.currentVariableIndex);
        }
        return answer.getIndex();
    }
    
    private void resetVariableIndex(final boolean isStatic) {
        if (!isStatic) {
            this.currentVariableIndex = 1;
            this.nextVariableIndex = 1;
        }
        else {
            this.currentVariableIndex = 0;
            this.nextVariableIndex = 0;
        }
    }
    
    public void clear() {
        if (this.stateStack.size() > 1) {
            final int size = this.stateStack.size() - 1;
            throw new GroovyBugError("the compile stack contains " + size + " more push instruction" + ((size == 1) ? "" : "s") + " than pops.");
        }
        this.clear = true;
        if (this.thisEndLabel == null) {
            this.setEndLabels();
        }
        if (!this.scope.isInStaticContext()) {
            this.mv.visitLocalVariable("this", this.className, null, this.thisStartLabel, this.thisEndLabel, 0);
        }
        for (final Variable v : this.usedVariables) {
            final String type = BytecodeHelper.getTypeDescription(v.getType());
            final Label start = v.getStartLabel();
            final Label end = v.getEndLabel();
            this.mv.visitLocalVariable(v.getName(), type, null, start, end, v.getIndex());
        }
        for (final ExceptionTableEntry ep : this.typedExceptions) {
            this.mv.visitTryCatchBlock(ep.start, ep.end, ep.goal, ep.sig);
        }
        for (final ExceptionTableEntry ep : this.untypedExceptions) {
            this.mv.visitTryCatchBlock(ep.start, ep.end, ep.goal, ep.sig);
        }
        this.pop();
        this.typedExceptions.clear();
        this.untypedExceptions.clear();
        this.stackVariables.clear();
        this.usedVariables.clear();
        this.scope = null;
        this.finallyBlocks.clear();
        this.mv = null;
        this.resetVariableIndex(false);
        this.superBlockNamedLabels.clear();
        this.currentBlockNamedLabels.clear();
        this.namedLoopBreakLabel.clear();
        this.namedLoopContinueLabel.clear();
        this.continueLabel = null;
        this.breakLabel = null;
        this.helper = null;
        this.thisStartLabel = null;
        this.thisEndLabel = null;
    }
    
    public void addExceptionBlock(final Label start, final Label end, final Label goal, final String sig) {
        final ExceptionTableEntry ep = new ExceptionTableEntry();
        ep.start = start;
        ep.end = end;
        ep.sig = sig;
        ep.goal = goal;
        if (sig == null) {
            this.untypedExceptions.add(ep);
        }
        else {
            this.typedExceptions.add(ep);
        }
    }
    
    protected void init(final VariableScope el, final Parameter[] parameters, final MethodVisitor mv, final ClassNode cn) {
        if (!this.clear) {
            throw new GroovyBugError("CompileStack#init called without calling clear before");
        }
        this.clear = false;
        this.pushVariableScope(el);
        this.mv = mv;
        this.helper = new BytecodeHelper(mv);
        this.defineMethodVariables(parameters, el.isInStaticContext());
        this.className = BytecodeHelper.getTypeDescription(cn);
    }
    
    protected void pushVariableScope(final VariableScope el) {
        this.pushState();
        this.scope = el;
        (this.superBlockNamedLabels = new HashMap(this.superBlockNamedLabels)).putAll(this.currentBlockNamedLabels);
        this.currentBlockNamedLabels = new HashMap();
    }
    
    protected void pushLoop(final VariableScope el, final String labelName) {
        this.pushVariableScope(el);
        this.initLoopLabels(labelName);
    }
    
    private void initLoopLabels(final String labelName) {
        this.continueLabel = new Label();
        this.breakLabel = new Label();
        if (labelName != null) {
            this.namedLoopBreakLabel.put(labelName, this.breakLabel);
            this.namedLoopContinueLabel.put(labelName, this.continueLabel);
        }
    }
    
    protected void pushLoop(final String labelName) {
        this.pushState();
        this.initLoopLabels(labelName);
    }
    
    protected Label getNamedBreakLabel(final String name) {
        Label label = this.getBreakLabel();
        Label endLabel = null;
        if (name != null) {
            endLabel = this.namedLoopBreakLabel.get(name);
        }
        if (endLabel != null) {
            label = endLabel;
        }
        return label;
    }
    
    protected Label getNamedContinueLabel(final String name) {
        Label label = this.getLabel(name);
        Label endLabel = null;
        if (name != null) {
            endLabel = this.namedLoopContinueLabel.get(name);
        }
        if (endLabel != null) {
            label = endLabel;
        }
        return label;
    }
    
    protected Label pushSwitch() {
        this.pushState();
        return this.breakLabel = new Label();
    }
    
    protected void pushBooleanExpression() {
        this.pushState();
    }
    
    private Variable defineVar(final String name, ClassNode type, final boolean methodParameterUsedInClosure) {
        final int prevCurrent = this.currentVariableIndex;
        this.makeNextVariableID(type);
        int index = this.currentVariableIndex;
        if (methodParameterUsedInClosure) {
            index = this.localVariableOffset++;
            type = ClassHelper.getWrapper(type);
        }
        final Variable answer = new Variable(index, type, name, prevCurrent);
        this.usedVariables.add(answer);
        answer.setHolder(methodParameterUsedInClosure);
        return answer;
    }
    
    private void makeLocalVariablesOffset(final Parameter[] paras, final boolean isInStaticContext) {
        this.resetVariableIndex(isInStaticContext);
        for (int i = 0; i < paras.length; ++i) {
            this.makeNextVariableID(paras[i].getType());
        }
        this.localVariableOffset = this.nextVariableIndex;
        this.resetVariableIndex(isInStaticContext);
    }
    
    private void defineMethodVariables(final Parameter[] paras, final boolean isInStaticContext) {
        final Label startLabel = new Label();
        this.thisStartLabel = startLabel;
        this.mv.visitLabel(startLabel);
        this.makeLocalVariablesOffset(paras, isInStaticContext);
        boolean hasHolder = false;
        for (int i = 0; i < paras.length; ++i) {
            final String name = paras[i].getName();
            final ClassNode type = paras[i].getType();
            Variable answer;
            if (paras[i].isClosureSharedVariable()) {
                answer = this.defineVar(name, type, true);
                this.helper.load(type, this.currentVariableIndex);
                this.helper.box(type);
                final Label newStart = new Label();
                this.mv.visitLabel(newStart);
                final Variable var = new Variable(this.currentVariableIndex, paras[i].getOriginType(), name, this.currentVariableIndex);
                var.setStartLabel(startLabel);
                var.setEndLabel(newStart);
                this.usedVariables.add(var);
                answer.setStartLabel(newStart);
                this.createReference(answer);
                hasHolder = true;
            }
            else {
                answer = this.defineVar(name, type, false);
                answer.setStartLabel(startLabel);
            }
            this.stackVariables.put(name, answer);
        }
        if (hasHolder) {
            this.nextVariableIndex = this.localVariableOffset;
        }
    }
    
    private void createReference(final Variable reference) {
        this.mv.visitTypeInsn(187, "groovy/lang/Reference");
        this.mv.visitInsn(90);
        this.mv.visitInsn(95);
        this.mv.visitMethodInsn(183, "groovy/lang/Reference", "<init>", "(Ljava/lang/Object;)V");
        this.mv.visitVarInsn(58, reference.getIndex());
    }
    
    public Variable defineVariable(final org.codehaus.groovy.ast.Variable v, final boolean initFromStack) {
        final String name = v.getName();
        final Variable answer = this.defineVar(name, v.getType(), false);
        if (v.isClosureSharedVariable()) {
            answer.setHolder(true);
        }
        this.stackVariables.put(name, answer);
        final Label startLabel = new Label();
        answer.setStartLabel(startLabel);
        if (answer.isHolder()) {
            if (!initFromStack) {
                this.mv.visitInsn(1);
            }
            this.createReference(answer);
        }
        else {
            if (!initFromStack) {
                this.mv.visitInsn(1);
            }
            this.mv.visitVarInsn(58, this.currentVariableIndex);
        }
        this.mv.visitLabel(startLabel);
        return answer;
    }
    
    public boolean containsVariable(final String name) {
        return this.stackVariables.containsKey(name);
    }
    
    private void makeNextVariableID(final ClassNode type) {
        this.currentVariableIndex = this.nextVariableIndex;
        if (type == ClassHelper.long_TYPE || type == ClassHelper.double_TYPE) {
            ++this.nextVariableIndex;
        }
        ++this.nextVariableIndex;
    }
    
    public Label getLabel(final String name) {
        if (name == null) {
            return null;
        }
        Label l = this.superBlockNamedLabels.get(name);
        if (l == null) {
            l = this.createLocalLabel(name);
        }
        return l;
    }
    
    public Label createLocalLabel(final String name) {
        Label l = this.currentBlockNamedLabels.get(name);
        if (l == null) {
            l = new Label();
            this.currentBlockNamedLabels.put(name, l);
        }
        return l;
    }
    
    public void applyFinallyBlocks(final Label label, final boolean isBreakLabel) {
        StateStackElement result = null;
        final ListIterator iter = this.stateStack.listIterator(this.stateStack.size());
        while (iter.hasPrevious()) {
            final StateStackElement element = iter.previous();
            if (!element.currentBlockNamedLabels.values().contains(label)) {
                if (isBreakLabel && element.breakLabel != label) {
                    result = element;
                    break;
                }
                if (!isBreakLabel && element.continueLabel != label) {
                    result = element;
                    break;
                }
                continue;
            }
        }
        List<BlockRecorder> blocksToRemove;
        if (result == null) {
            blocksToRemove = (List<BlockRecorder>)Collections.EMPTY_LIST;
        }
        else {
            blocksToRemove = result.finallyBlocks;
        }
        final List<BlockRecorder> blocks = new LinkedList<BlockRecorder>(this.finallyBlocks);
        blocks.removeAll(blocksToRemove);
        this.applyBlockRecorder(blocks);
    }
    
    private void applyBlockRecorder(final List<BlockRecorder> blocks) {
        if (blocks.size() == 0 || blocks.size() == this.visitedBlocks.size()) {
            return;
        }
        final Label end = new Label();
        this.mv.visitInsn(0);
        this.mv.visitLabel(end);
        final Label newStart = new Label();
        for (final BlockRecorder fb : blocks) {
            if (this.visitedBlocks.contains(fb)) {
                continue;
            }
            fb.closeRange(end);
            fb.excludedStatement.run();
            fb.startRange(newStart);
        }
        this.mv.visitInsn(0);
        this.mv.visitLabel(newStart);
    }
    
    public void applyBlockRecorder() {
        this.applyBlockRecorder(this.finallyBlocks);
    }
    
    public boolean hasBlockRecorder() {
        return !this.finallyBlocks.isEmpty();
    }
    
    public void pushBlockRecorder(final BlockRecorder recorder) {
        this.pushState();
        this.finallyBlocks.addFirst(recorder);
    }
    
    public void pushBlockRecorderVisit(final BlockRecorder finallyBlock) {
        this.visitedBlocks.add(finallyBlock);
    }
    
    public void popBlockRecorderVisit(final BlockRecorder finallyBlock) {
        this.visitedBlocks.remove(finallyBlock);
    }
    
    public void writeExceptionTable(final BlockRecorder block, final Label goal, final String sig) {
        if (block.isEmpty) {
            return;
        }
        for (final LabelRange range : block.ranges) {
            this.mv.visitTryCatchBlock(range.start, range.end, goal, sig);
        }
    }
    
    protected static class LabelRange
    {
        public Label start;
        public Label end;
    }
    
    protected static class BlockRecorder
    {
        private boolean isEmpty;
        public Runnable excludedStatement;
        public LinkedList<LabelRange> ranges;
        
        public BlockRecorder() {
            this.isEmpty = true;
            this.ranges = new LinkedList<LabelRange>();
        }
        
        public BlockRecorder(final Runnable excludedStatement) {
            this();
            this.excludedStatement = excludedStatement;
        }
        
        public void startRange(final Label start) {
            final LabelRange range = new LabelRange();
            range.start = start;
            this.ranges.add(range);
            this.isEmpty = false;
        }
        
        public void closeRange(final Label end) {
            this.ranges.getLast().end = end;
        }
    }
    
    private class ExceptionTableEntry
    {
        Label start;
        Label end;
        Label goal;
        String sig;
    }
    
    private class StateStackElement
    {
        final VariableScope scope;
        final Label continueLabel;
        final Label breakLabel;
        final int lastVariableIndex;
        final int nextVariableIndex;
        final Map stackVariables;
        final Map currentBlockNamedLabels;
        final LinkedList<BlockRecorder> finallyBlocks;
        
        StateStackElement() {
            this.scope = CompileStack.this.scope;
            this.continueLabel = CompileStack.this.continueLabel;
            this.breakLabel = CompileStack.this.breakLabel;
            this.lastVariableIndex = CompileStack.this.currentVariableIndex;
            this.stackVariables = CompileStack.this.stackVariables;
            this.nextVariableIndex = CompileStack.this.nextVariableIndex;
            this.currentBlockNamedLabels = CompileStack.this.currentBlockNamedLabels;
            this.finallyBlocks = CompileStack.this.finallyBlocks;
        }
    }
}
