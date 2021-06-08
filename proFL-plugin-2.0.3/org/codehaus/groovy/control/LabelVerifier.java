// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import java.util.LinkedList;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class LabelVerifier extends ClassCodeVisitorSupport
{
    private SourceUnit source;
    private LinkedList visitedLabels;
    private LinkedList continueLabels;
    private LinkedList breakLabels;
    boolean inLoop;
    boolean inSwitch;
    
    public LabelVerifier(final SourceUnit src) {
        this.inLoop = false;
        this.inSwitch = false;
        this.source = src;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    private void init() {
        this.visitedLabels = new LinkedList();
        this.continueLabels = new LinkedList();
        this.breakLabels = new LinkedList();
        this.inLoop = false;
        this.inSwitch = false;
    }
    
    @Override
    protected void visitClassCodeContainer(final Statement code) {
        this.init();
        super.visitClassCodeContainer(code);
        this.assertNoLabelsMissed();
    }
    
    public void visitStatement(final Statement statement) {
        final String label = statement.getStatementLabel();
        if (label != null) {
            Iterator iter = this.breakLabels.iterator();
            while (iter.hasNext()) {
                final BreakStatement element = iter.next();
                if (element.getLabel().equals(label)) {
                    iter.remove();
                }
            }
            iter = this.continueLabels.iterator();
            while (iter.hasNext()) {
                final ContinueStatement element2 = iter.next();
                if (element2.getLabel().equals(label)) {
                    iter.remove();
                }
            }
            this.visitedLabels.add(label);
        }
        super.visitStatement(statement);
    }
    
    @Override
    public void visitForLoop(final ForStatement forLoop) {
        final boolean oldInLoop = this.inLoop;
        this.inLoop = true;
        super.visitForLoop(forLoop);
        this.inLoop = oldInLoop;
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement loop) {
        final boolean oldInLoop = this.inLoop;
        this.inLoop = true;
        super.visitDoWhileLoop(loop);
        this.inLoop = oldInLoop;
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement loop) {
        final boolean oldInLoop = this.inLoop;
        this.inLoop = true;
        super.visitWhileLoop(loop);
        this.inLoop = oldInLoop;
    }
    
    @Override
    public void visitBreakStatement(final BreakStatement statement) {
        final String label = statement.getLabel();
        final boolean hasNamedLabel = label != null;
        if (!hasNamedLabel && !this.inLoop && !this.inSwitch) {
            this.addError("the break statement is only allowed inside loops or switches", statement);
        }
        else if (hasNamedLabel && !this.inLoop) {
            this.addError("the break statement with named label is only allowed inside loops", statement);
        }
        if (label != null) {
            boolean found = false;
            for (final String element : this.visitedLabels) {
                if (element.equals(label)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.breakLabels.add(statement);
            }
        }
        super.visitBreakStatement(statement);
    }
    
    @Override
    public void visitContinueStatement(final ContinueStatement statement) {
        final String label = statement.getLabel();
        final boolean hasNamedLabel = label != null;
        if (!hasNamedLabel && !this.inLoop) {
            this.addError("the continue statement is only allowed inside loops", statement);
        }
        if (label != null) {
            boolean found = false;
            for (final String element : this.visitedLabels) {
                if (element.equals(label)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.continueLabels.add(statement);
            }
        }
        super.visitContinueStatement(statement);
    }
    
    protected void assertNoLabelsMissed() {
        for (final ContinueStatement element : this.continueLabels) {
            this.addError("continue to missing label", element);
        }
        for (final BreakStatement element2 : this.breakLabels) {
            this.addError("break to missing label", element2);
        }
    }
    
    @Override
    public void visitSwitch(final SwitchStatement statement) {
        final boolean oldInSwitch = this.inSwitch;
        this.inSwitch = true;
        super.visitSwitch(statement);
        this.inSwitch = oldInSwitch;
    }
}
