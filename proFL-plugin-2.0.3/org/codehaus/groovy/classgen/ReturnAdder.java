// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.MethodNode;

public class ReturnAdder
{
    public static void addReturnIfNeeded(final MethodNode node) {
        final Statement statement = node.getCode();
        if (!node.isVoidMethod()) {
            if (statement != null) {
                node.setCode(addReturnsIfNeeded(statement, node.getVariableScope()));
            }
        }
        else if (!node.isAbstract() && !(statement instanceof BytecodeSequence)) {
            final BlockStatement newBlock = new BlockStatement();
            final Statement code = node.getCode();
            if (code instanceof BlockStatement) {
                newBlock.setVariableScope(((BlockStatement)code).getVariableScope());
            }
            if (statement instanceof BlockStatement) {
                newBlock.addStatements(((BlockStatement)statement).getStatements());
            }
            else {
                newBlock.addStatement(statement);
            }
            newBlock.addStatement(ReturnStatement.RETURN_NULL_OR_VOID);
            newBlock.setSourcePosition(statement);
            node.setCode(newBlock);
        }
    }
    
    private static Statement addReturnsIfNeeded(final Statement statement, final VariableScope scope) {
        if (statement instanceof ReturnStatement || statement instanceof BytecodeSequence || statement instanceof ThrowStatement) {
            return statement;
        }
        if (statement instanceof EmptyStatement) {
            return new ReturnStatement(ConstantExpression.NULL);
        }
        if (statement instanceof ExpressionStatement) {
            final ExpressionStatement expStmt = (ExpressionStatement)statement;
            final Expression expr = expStmt.getExpression();
            final ReturnStatement ret = new ReturnStatement(expr);
            ret.setSourcePosition(expr);
            ret.setStatementLabel(statement.getStatementLabel());
            return ret;
        }
        if (statement instanceof SynchronizedStatement) {
            final SynchronizedStatement sync = (SynchronizedStatement)statement;
            sync.setCode(addReturnsIfNeeded(sync.getCode(), scope));
            return sync;
        }
        if (statement instanceof IfStatement) {
            final IfStatement ifs = (IfStatement)statement;
            ifs.setIfBlock(addReturnsIfNeeded(ifs.getIfBlock(), scope));
            ifs.setElseBlock(addReturnsIfNeeded(ifs.getElseBlock(), scope));
            return ifs;
        }
        if (statement instanceof SwitchStatement) {
            final SwitchStatement swi = (SwitchStatement)statement;
            for (final CaseStatement caseStatement : swi.getCaseStatements()) {
                caseStatement.setCode(adjustSwitchCaseCode(caseStatement.getCode(), scope, false));
            }
            swi.setDefaultStatement(adjustSwitchCaseCode(swi.getDefaultStatement(), scope, true));
            return swi;
        }
        if (statement instanceof TryCatchStatement) {
            final TryCatchStatement trys = (TryCatchStatement)statement;
            trys.setTryStatement(addReturnsIfNeeded(trys.getTryStatement(), scope));
            for (int len = trys.getCatchStatements().size(), i = 0; i != len; ++i) {
                final CatchStatement catchStatement = trys.getCatchStatement(i);
                catchStatement.setCode(addReturnsIfNeeded(catchStatement.getCode(), scope));
            }
            return trys;
        }
        if (statement instanceof BlockStatement) {
            final BlockStatement block = (BlockStatement)statement;
            final List list = block.getStatements();
            if (!list.isEmpty()) {
                final int idx = list.size() - 1;
                final Statement last = addReturnsIfNeeded(list.get(idx), block.getVariableScope());
                list.set(idx, last);
                if (!statementReturns(last)) {
                    list.add(new ReturnStatement(ConstantExpression.NULL));
                }
                final BlockStatement newBlock = new BlockStatement(list, block.getVariableScope());
                newBlock.setSourcePosition(block);
                return newBlock;
            }
            final ReturnStatement ret = new ReturnStatement(ConstantExpression.NULL);
            ret.setSourcePosition(block);
            return ret;
        }
        else {
            if (statement == null) {
                return new ReturnStatement(ConstantExpression.NULL);
            }
            final List list2 = new ArrayList();
            list2.add(statement);
            list2.add(new ReturnStatement(ConstantExpression.NULL));
            final BlockStatement newBlock2 = new BlockStatement(list2, new VariableScope(scope));
            newBlock2.setSourcePosition(statement);
            return newBlock2;
        }
    }
    
    private static Statement adjustSwitchCaseCode(final Statement statement, final VariableScope scope, final boolean defaultCase) {
        if (statement instanceof BlockStatement) {
            final List list = ((BlockStatement)statement).getStatements();
            if (!list.isEmpty()) {
                final int idx = list.size() - 1;
                final Statement last = list.get(idx);
                if (last instanceof BreakStatement) {
                    list.remove(idx);
                    return addReturnsIfNeeded(statement, scope);
                }
                if (defaultCase) {
                    return addReturnsIfNeeded(statement, scope);
                }
            }
        }
        return statement;
    }
    
    private static boolean statementReturns(final Statement last) {
        return last instanceof ReturnStatement || last instanceof BlockStatement || last instanceof IfStatement || last instanceof ExpressionStatement || last instanceof EmptyStatement || last instanceof TryCatchStatement || last instanceof BytecodeSequence || last instanceof ThrowStatement || last instanceof SynchronizedStatement;
    }
}
