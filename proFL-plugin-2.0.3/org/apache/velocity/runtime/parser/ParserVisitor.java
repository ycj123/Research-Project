// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

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
import org.apache.velocity.runtime.parser.node.ASTStop;
import org.apache.velocity.runtime.parser.node.ASTSetDirective;
import org.apache.velocity.runtime.parser.node.ASTElseIfStatement;
import org.apache.velocity.runtime.parser.node.ASTElseStatement;
import org.apache.velocity.runtime.parser.node.ASTIfStatement;
import org.apache.velocity.runtime.parser.node.ASTText;
import org.apache.velocity.runtime.parser.node.ASTFalse;
import org.apache.velocity.runtime.parser.node.ASTTrue;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ASTMethod;
import org.apache.velocity.runtime.parser.node.ASTIntegerRange;
import org.apache.velocity.runtime.parser.node.ASTObjectArray;
import org.apache.velocity.runtime.parser.node.ASTMap;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTWord;
import org.apache.velocity.runtime.parser.node.ASTIdentifier;
import org.apache.velocity.runtime.parser.node.ASTStringLiteral;
import org.apache.velocity.runtime.parser.node.ASTIntegerLiteral;
import org.apache.velocity.runtime.parser.node.ASTFloatingPointLiteral;
import org.apache.velocity.runtime.parser.node.ASTComment;
import org.apache.velocity.runtime.parser.node.ASTEscape;
import org.apache.velocity.runtime.parser.node.ASTEscapedDirective;
import org.apache.velocity.runtime.parser.node.ASTprocess;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public interface ParserVisitor
{
    Object visit(final SimpleNode p0, final Object p1);
    
    Object visit(final ASTprocess p0, final Object p1);
    
    Object visit(final ASTEscapedDirective p0, final Object p1);
    
    Object visit(final ASTEscape p0, final Object p1);
    
    Object visit(final ASTComment p0, final Object p1);
    
    Object visit(final ASTFloatingPointLiteral p0, final Object p1);
    
    Object visit(final ASTIntegerLiteral p0, final Object p1);
    
    Object visit(final ASTStringLiteral p0, final Object p1);
    
    Object visit(final ASTIdentifier p0, final Object p1);
    
    Object visit(final ASTWord p0, final Object p1);
    
    Object visit(final ASTDirective p0, final Object p1);
    
    Object visit(final ASTBlock p0, final Object p1);
    
    Object visit(final ASTMap p0, final Object p1);
    
    Object visit(final ASTObjectArray p0, final Object p1);
    
    Object visit(final ASTIntegerRange p0, final Object p1);
    
    Object visit(final ASTMethod p0, final Object p1);
    
    Object visit(final ASTReference p0, final Object p1);
    
    Object visit(final ASTTrue p0, final Object p1);
    
    Object visit(final ASTFalse p0, final Object p1);
    
    Object visit(final ASTText p0, final Object p1);
    
    Object visit(final ASTIfStatement p0, final Object p1);
    
    Object visit(final ASTElseStatement p0, final Object p1);
    
    Object visit(final ASTElseIfStatement p0, final Object p1);
    
    Object visit(final ASTSetDirective p0, final Object p1);
    
    Object visit(final ASTStop p0, final Object p1);
    
    Object visit(final ASTExpression p0, final Object p1);
    
    Object visit(final ASTAssignment p0, final Object p1);
    
    Object visit(final ASTOrNode p0, final Object p1);
    
    Object visit(final ASTAndNode p0, final Object p1);
    
    Object visit(final ASTEQNode p0, final Object p1);
    
    Object visit(final ASTNENode p0, final Object p1);
    
    Object visit(final ASTLTNode p0, final Object p1);
    
    Object visit(final ASTGTNode p0, final Object p1);
    
    Object visit(final ASTLENode p0, final Object p1);
    
    Object visit(final ASTGENode p0, final Object p1);
    
    Object visit(final ASTAddNode p0, final Object p1);
    
    Object visit(final ASTSubtractNode p0, final Object p1);
    
    Object visit(final ASTMulNode p0, final Object p1);
    
    Object visit(final ASTDivNode p0, final Object p1);
    
    Object visit(final ASTModNode p0, final Object p1);
    
    Object visit(final ASTNotNode p0, final Object p1);
}
