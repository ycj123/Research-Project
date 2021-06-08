// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.java;

import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.Stack;
import org.codehaus.groovy.antlr.treewalker.VisitorAdapter;

public class PreJava2GroovyConverter extends VisitorAdapter
{
    private String[] tokenNames;
    private Stack stack;
    
    public PreJava2GroovyConverter(final String[] tokenNames) {
        this.tokenNames = tokenNames;
        this.stack = new Stack();
    }
    
    @Override
    public void visitDefault(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            if (t.getType() == 114) {
                this.visitJavaLiteralDo(t);
            }
            else if (t.getType() == 28) {
                this.visitJavaArrayInit(t);
            }
        }
    }
    
    private void visitJavaLiteralDo(final GroovySourceAST t) {
        this.swapTwoChildren(t);
    }
    
    private void visitJavaArrayInit(final GroovySourceAST t) {
        if (this.stack.size() > 2) {
            final GroovySourceAST grandParent = this.getGrandParentNode();
            if (grandParent.getType() == 27) {
                grandParent.setType(28);
                grandParent.setFirstChild(t);
                t.setType(33);
            }
        }
    }
    
    public void swapTwoChildren(final GroovySourceAST t) {
        final GroovySourceAST a = (GroovySourceAST)t.getFirstChild();
        final GroovySourceAST b = (GroovySourceAST)a.getNextSibling();
        t.setFirstChild(b);
        a.setNextSibling(null);
        b.setNextSibling(a);
    }
    
    @Override
    public void push(final GroovySourceAST t) {
        this.stack.push(t);
    }
    
    @Override
    public GroovySourceAST pop() {
        if (!this.stack.empty()) {
            return this.stack.pop();
        }
        return null;
    }
    
    private GroovySourceAST getParentNode() {
        final Object currentNode = this.stack.pop();
        final Object parentNode = this.stack.peek();
        this.stack.push(currentNode);
        return (GroovySourceAST)parentNode;
    }
    
    private GroovySourceAST getGrandParentNode() {
        final Object currentNode = this.stack.pop();
        final Object parentNode = this.stack.pop();
        final Object grandParentNode = this.stack.peek();
        this.stack.push(parentNode);
        this.stack.push(currentNode);
        return (GroovySourceAST)grandParentNode;
    }
}
