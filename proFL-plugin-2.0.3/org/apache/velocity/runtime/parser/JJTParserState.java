// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

import org.apache.velocity.runtime.parser.node.Node;
import java.util.Stack;

class JJTParserState
{
    private Stack nodes;
    private Stack marks;
    private int sp;
    private int mk;
    private boolean node_created;
    
    JJTParserState() {
        this.nodes = new Stack();
        this.marks = new Stack();
        this.sp = 0;
        this.mk = 0;
    }
    
    boolean nodeCreated() {
        return this.node_created;
    }
    
    void reset() {
        this.nodes.removeAllElements();
        this.marks.removeAllElements();
        this.sp = 0;
        this.mk = 0;
    }
    
    Node rootNode() {
        return (Node)this.nodes.elementAt(0);
    }
    
    void pushNode(final Node n) {
        this.nodes.push(n);
        ++this.sp;
    }
    
    Node popNode() {
        final int sp = this.sp - 1;
        this.sp = sp;
        if (sp < this.mk) {
            this.mk = this.marks.pop();
        }
        return this.nodes.pop();
    }
    
    Node peekNode() {
        return this.nodes.peek();
    }
    
    int nodeArity() {
        return this.sp - this.mk;
    }
    
    void clearNodeScope(final Node n) {
        while (this.sp > this.mk) {
            this.popNode();
        }
        this.mk = this.marks.pop();
    }
    
    void openNodeScope(final Node n) {
        this.marks.push(new Integer(this.mk));
        this.mk = this.sp;
        n.jjtOpen();
    }
    
    void closeNodeScope(final Node n, int num) {
        this.mk = this.marks.pop();
        while (num-- > 0) {
            final Node c = this.popNode();
            c.jjtSetParent(n);
            n.jjtAddChild(c, num);
        }
        n.jjtClose();
        this.pushNode(n);
        this.node_created = true;
    }
    
    void closeNodeScope(final Node n, final boolean condition) {
        if (condition) {
            int a = this.nodeArity();
            this.mk = this.marks.pop();
            while (a-- > 0) {
                final Node c = this.popNode();
                c.jjtSetParent(n);
                n.jjtAddChild(c, a);
            }
            n.jjtClose();
            this.pushNode(n);
            this.node_created = true;
        }
        else {
            this.mk = this.marks.pop();
            this.node_created = false;
        }
    }
}
