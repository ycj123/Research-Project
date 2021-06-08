// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.visitor;

import org.apache.velocity.runtime.parser.node.ASTStop;
import org.apache.velocity.runtime.parser.node.ASTIntegerRange;
import org.apache.velocity.runtime.parser.node.ASTMap;
import org.apache.velocity.runtime.parser.node.ASTEscape;
import org.apache.velocity.runtime.parser.node.ASTEscapedDirective;
import org.apache.velocity.runtime.parser.node.ASTSetDirective;
import org.apache.velocity.runtime.parser.node.ASTWord;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTObjectArray;
import org.apache.velocity.runtime.parser.node.ASTElseIfStatement;
import org.apache.velocity.runtime.parser.node.ASTElseStatement;
import org.apache.velocity.runtime.parser.node.ASTIfStatement;
import org.apache.velocity.runtime.parser.node.ASTText;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.ASTFalse;
import org.apache.velocity.runtime.parser.node.ASTTrue;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ASTMethod;
import org.apache.velocity.runtime.parser.node.ASTIdentifier;
import org.apache.velocity.runtime.parser.node.ASTStringLiteral;
import org.apache.velocity.runtime.parser.node.ASTIntegerLiteral;
import org.apache.velocity.runtime.parser.node.ASTFloatingPointLiteral;
import org.apache.velocity.runtime.parser.node.ASTNotNode;
import org.apache.velocity.runtime.parser.node.ASTModNode;
import org.apache.velocity.runtime.parser.node.ASTDivNode;
import org.apache.velocity.runtime.parser.node.ASTMulNode;
import org.apache.velocity.runtime.parser.node.ASTSubtractNode;
import org.apache.velocity.runtime.parser.node.ASTAddNode;
import org.apache.velocity.runtime.parser.node.ASTGENode;
import org.apache.velocity.runtime.parser.node.ASTLENode;
import org.apache.velocity.runtime.parser.node.ASTGTNode;
import org.apache.velocity.runtime.parser.node.ASTLTNode;
import org.apache.velocity.runtime.parser.node.ASTNENode;
import org.apache.velocity.runtime.parser.node.ASTEQNode;
import org.apache.velocity.runtime.parser.node.ASTAndNode;
import org.apache.velocity.runtime.parser.node.ASTOrNode;
import org.apache.velocity.runtime.parser.node.ASTAssignment;
import org.apache.velocity.runtime.parser.node.ASTExpression;
import org.apache.velocity.runtime.parser.node.ASTprocess;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.node.Node;

public class NodeViewMode extends BaseVisitor
{
    private int indent;
    private boolean showTokens;
    
    public NodeViewMode() {
        this.indent = 0;
        this.showTokens = true;
    }
    
    private String indentString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.indent; ++i) {
            sb.append("  ");
        }
        return sb.toString();
    }
    
    private Object showNode(final Node node, Object data) {
        String tokens = "";
        String special = "";
        if (this.showTokens) {
            final Token t = node.getFirstToken();
            if (t.specialToken != null && !t.specialToken.image.startsWith("##")) {
                special = t.specialToken.image;
            }
            tokens = " -> " + special + t.image;
        }
        System.out.println(this.indentString() + node + tokens);
        ++this.indent;
        data = node.childrenAccept(this, data);
        --this.indent;
        return data;
    }
    
    public Object visit(final SimpleNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTprocess node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTExpression node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTAssignment node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTOrNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTAndNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTEQNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTNENode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTLTNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTGTNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTLENode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTGENode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTAddNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTSubtractNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTMulNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTDivNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTModNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTNotNode node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTFloatingPointLiteral node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTIntegerLiteral node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTStringLiteral node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTIdentifier node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTMethod node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTReference node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTTrue node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTFalse node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTBlock node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTText node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTIfStatement node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTElseStatement node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTElseIfStatement node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTObjectArray node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTDirective node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTWord node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTSetDirective node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTEscapedDirective node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTEscape node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTMap node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTIntegerRange node, final Object data) {
        return this.showNode(node, data);
    }
    
    public Object visit(final ASTStop node, final Object data) {
        return this.showNode(node, data);
    }
}
