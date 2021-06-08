// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.visitor;

import org.apache.velocity.runtime.parser.node.ASTStop;
import org.apache.velocity.runtime.parser.node.ASTIntegerRange;
import org.apache.velocity.runtime.parser.node.ASTMap;
import org.apache.velocity.runtime.parser.node.ASTEscape;
import org.apache.velocity.runtime.parser.node.ASTEscapedDirective;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTSetDirective;
import org.apache.velocity.runtime.parser.node.ASTWord;
import org.apache.velocity.runtime.parser.node.ASTObjectArray;
import org.apache.velocity.runtime.parser.node.ASTComment;
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
import org.apache.velocity.runtime.parser.node.ASTFloatingPointLiteral;
import org.apache.velocity.runtime.parser.node.ASTIntegerLiteral;
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
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;

public abstract class BaseVisitor implements ParserVisitor
{
    protected InternalContextAdapter context;
    protected Writer writer;
    
    public void setWriter(final Writer writer) {
        this.writer = writer;
    }
    
    public void setContext(final InternalContextAdapter context) {
        this.context = context;
    }
    
    public Object visit(final SimpleNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTprocess node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTExpression node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTAssignment node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTOrNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTAndNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTEQNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTNENode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTLTNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTGTNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTLENode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTGENode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTAddNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTSubtractNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTMulNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTDivNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTModNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTNotNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTIntegerLiteral node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTFloatingPointLiteral node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTStringLiteral node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTIdentifier node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTMethod node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTReference node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTTrue node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTFalse node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTBlock node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTText node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTIfStatement node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTElseStatement node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTElseIfStatement node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTComment node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTObjectArray node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTWord node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTSetDirective node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTDirective node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTEscapedDirective node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTEscape node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTMap node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTIntegerRange node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(final ASTStop node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
}
