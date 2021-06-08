// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import java.util.Iterator;
import org.codehaus.groovy.syntax.RuntimeParserException;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.stmt.ForStatement;
import groovyjarjarasm.asm.Opcodes;
import org.codehaus.groovy.ast.CodeVisitorSupport;

public class VerifierCodeVisitor extends CodeVisitorSupport implements Opcodes
{
    private Verifier verifier;
    
    VerifierCodeVisitor(final Verifier verifier) {
        this.verifier = verifier;
    }
    
    @Override
    public void visitForLoop(final ForStatement expression) {
        assertValidIdentifier(expression.getVariable().getName(), "for loop variable name", expression);
        super.visitForLoop(expression);
    }
    
    @Override
    public void visitFieldExpression(final FieldExpression expression) {
        if (!expression.getField().isSynthetic()) {
            assertValidIdentifier(expression.getFieldName(), "field name", expression);
        }
        super.visitFieldExpression(expression);
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        assertValidIdentifier(expression.getName(), "variable name", expression);
        super.visitVariableExpression(expression);
    }
    
    @Override
    public void visitListExpression(final ListExpression expression) {
        for (final Expression element : expression.getExpressions()) {
            if (element instanceof MapEntryExpression) {
                throw new RuntimeParserException("No map entry allowed at this place", element);
            }
        }
        super.visitListExpression(expression);
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        final ClassNode callType = call.getType();
        if (callType.isEnum() && !callType.equals(this.verifier.getClassNode())) {
            throw new RuntimeParserException("Enum constructor calls are only allowed inside the enum class", call);
        }
    }
    
    public static void assertValidIdentifier(final String name, final String message, final ASTNode node) {
        final int size = name.length();
        if (size <= 0) {
            throw new RuntimeParserException("Invalid " + message + ". Identifier must not be empty", node);
        }
        final char firstCh = name.charAt(0);
        if (size == 1 && firstCh == '$') {
            throw new RuntimeParserException("Invalid " + message + ". Must include a letter but only found: " + name, node);
        }
        if (!Character.isJavaIdentifierStart(firstCh)) {
            throw new RuntimeParserException("Invalid " + message + ". Must start with a letter but was: " + name, node);
        }
        for (int i = 1; i < size; ++i) {
            final char ch = name.charAt(i);
            if (!Character.isJavaIdentifierPart(ch)) {
                throw new RuntimeParserException("Invalid " + message + ". Invalid character at position: " + (i + 1) + " of value:  " + ch + " in name: " + name, node);
            }
        }
    }
}
