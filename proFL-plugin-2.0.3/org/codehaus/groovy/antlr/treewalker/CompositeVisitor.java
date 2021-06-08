// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class CompositeVisitor implements Visitor
{
    final List visitors;
    final List backToFrontVisitors;
    private final Stack stack;
    
    public CompositeVisitor(final List visitors) {
        this.visitors = visitors;
        this.stack = new Stack();
        (this.backToFrontVisitors = new ArrayList()).addAll(visitors);
        Collections.reverse(this.backToFrontVisitors);
    }
    
    private Iterator itr(final int visit) {
        Iterator itr = this.visitors.iterator();
        if (visit == 4) {
            itr = this.backToFrontVisitors.iterator();
        }
        return itr;
    }
    
    public void setUp() {
        final Iterator itr = this.visitors.iterator();
        while (itr.hasNext()) {
            itr.next().setUp();
        }
    }
    
    public void visitAbstract(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAbstract(t, visit);
        }
    }
    
    public void visitAnnotation(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotation(t, visit);
        }
    }
    
    public void visitAnnotations(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotations(t, visit);
        }
    }
    
    public void visitAnnotationArrayInit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotationArrayInit(t, visit);
        }
    }
    
    public void visitAnnotationDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotationDef(t, visit);
        }
    }
    
    public void visitAnnotationFieldDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotationFieldDef(t, visit);
        }
    }
    
    public void visitAnnotationMemberValuePair(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAnnotationMemberValuePair(t, visit);
        }
    }
    
    public void visitArrayDeclarator(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitArrayDeclarator(t, visit);
        }
    }
    
    public void visitAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAssign(t, visit);
        }
    }
    
    public void visitAt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitAt(t, visit);
        }
    }
    
    public void visitBand(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBand(t, visit);
        }
    }
    
    public void visitBandAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBandAssign(t, visit);
        }
    }
    
    public void visitBigSuffix(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBigSuffix(t, visit);
        }
    }
    
    public void visitBlock(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBlock(t, visit);
        }
    }
    
    public void visitBnot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBnot(t, visit);
        }
    }
    
    public void visitBor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBor(t, visit);
        }
    }
    
    public void visitBorAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBorAssign(t, visit);
        }
    }
    
    public void visitBsr(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBsr(t, visit);
        }
    }
    
    public void visitBsrAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBsrAssign(t, visit);
        }
    }
    
    public void visitBxor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBxor(t, visit);
        }
    }
    
    public void visitBxorAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitBxorAssign(t, visit);
        }
    }
    
    public void visitCaseGroup(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitCaseGroup(t, visit);
        }
    }
    
    public void visitClassDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitClassDef(t, visit);
        }
    }
    
    public void visitClosedBlock(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitClosedBlock(t, visit);
        }
    }
    
    public void visitClosureList(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitClosureList(t, visit);
        }
    }
    
    public void visitClosureOp(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitClosureOp(t, visit);
        }
    }
    
    public void visitColon(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitColon(t, visit);
        }
    }
    
    public void visitComma(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitComma(t, visit);
        }
    }
    
    public void visitCompareTo(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitCompareTo(t, visit);
        }
    }
    
    public void visitCtorCall(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitCtorCall(t, visit);
        }
    }
    
    public void visitCtorIdent(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitCtorIdent(t, visit);
        }
    }
    
    public void visitDec(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDec(t, visit);
        }
    }
    
    public void visitDigit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDigit(t, visit);
        }
    }
    
    public void visitDiv(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDiv(t, visit);
        }
    }
    
    public void visitDivAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDivAssign(t, visit);
        }
    }
    
    public void visitDollar(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDollar(t, visit);
        }
    }
    
    public void visitDot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDot(t, visit);
        }
    }
    
    public void visitDynamicMember(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDynamicMember(t, visit);
        }
    }
    
    public void visitElist(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitElist(t, visit);
        }
    }
    
    public void visitEmptyStat(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEmptyStat(t, visit);
        }
    }
    
    public void visitEnumConstantDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEnumConstantDef(t, visit);
        }
    }
    
    public void visitEnumDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEnumDef(t, visit);
        }
    }
    
    public void visitEof(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEof(t, visit);
        }
    }
    
    public void visitEqual(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEqual(t, visit);
        }
    }
    
    public void visitEsc(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitEsc(t, visit);
        }
    }
    
    public void visitExponent(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitExponent(t, visit);
        }
    }
    
    public void visitExpr(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitExpr(t, visit);
        }
    }
    
    public void visitExtendsClause(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitExtendsClause(t, visit);
        }
    }
    
    public void visitFinal(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitFinal(t, visit);
        }
    }
    
    public void visitFloatSuffix(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitFloatSuffix(t, visit);
        }
    }
    
    public void visitForCondition(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitForCondition(t, visit);
        }
    }
    
    public void visitForEachClause(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitForEachClause(t, visit);
        }
    }
    
    public void visitForInit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitForInit(t, visit);
        }
    }
    
    public void visitForInIterable(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitForInIterable(t, visit);
        }
    }
    
    public void visitForIterator(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitForIterator(t, visit);
        }
    }
    
    public void visitGe(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitGe(t, visit);
        }
    }
    
    public void visitGt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitGt(t, visit);
        }
    }
    
    public void visitHexDigit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitHexDigit(t, visit);
        }
    }
    
    public void visitIdent(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitIdent(t, visit);
        }
    }
    
    public void visitImplementsClause(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitImplementsClause(t, visit);
        }
    }
    
    public void visitImplicitParameters(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitImplicitParameters(t, visit);
        }
    }
    
    public void visitImport(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitImport(t, visit);
        }
    }
    
    public void visitInc(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitInc(t, visit);
        }
    }
    
    public void visitIndexOp(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitIndexOp(t, visit);
        }
    }
    
    public void visitInstanceInit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitInstanceInit(t, visit);
        }
    }
    
    public void visitInterfaceDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitInterfaceDef(t, visit);
        }
    }
    
    public void visitLabeledArg(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLabeledArg(t, visit);
        }
    }
    
    public void visitLabeledStat(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLabeledStat(t, visit);
        }
    }
    
    public void visitLand(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLand(t, visit);
        }
    }
    
    public void visitLbrack(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLbrack(t, visit);
        }
    }
    
    public void visitLcurly(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLcurly(t, visit);
        }
    }
    
    public void visitLe(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLe(t, visit);
        }
    }
    
    public void visitLetter(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLetter(t, visit);
        }
    }
    
    public void visitListConstructor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitListConstructor(t, visit);
        }
    }
    
    public void visitLiteralAs(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralAs(t, visit);
        }
    }
    
    public void visitLiteralAssert(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralAssert(t, visit);
        }
    }
    
    public void visitLiteralBoolean(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralBoolean(t, visit);
        }
    }
    
    public void visitLiteralBreak(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralBreak(t, visit);
        }
    }
    
    public void visitLiteralByte(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralByte(t, visit);
        }
    }
    
    public void visitLiteralCase(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralCase(t, visit);
        }
    }
    
    public void visitLiteralCatch(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralCatch(t, visit);
        }
    }
    
    public void visitLiteralChar(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralChar(t, visit);
        }
    }
    
    public void visitLiteralClass(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralClass(t, visit);
        }
    }
    
    public void visitLiteralContinue(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralContinue(t, visit);
        }
    }
    
    public void visitLiteralDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralDef(t, visit);
        }
    }
    
    public void visitLiteralDefault(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralDefault(t, visit);
        }
    }
    
    public void visitLiteralDouble(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralDouble(t, visit);
        }
    }
    
    public void visitLiteralElse(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralElse(t, visit);
        }
    }
    
    public void visitLiteralEnum(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralEnum(t, visit);
        }
    }
    
    public void visitLiteralExtends(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralExtends(t, visit);
        }
    }
    
    public void visitLiteralFalse(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralFalse(t, visit);
        }
    }
    
    public void visitLiteralFinally(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralFinally(t, visit);
        }
    }
    
    public void visitLiteralFloat(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralFloat(t, visit);
        }
    }
    
    public void visitLiteralFor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralFor(t, visit);
        }
    }
    
    public void visitLiteralIf(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralIf(t, visit);
        }
    }
    
    public void visitLiteralImplements(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralImplements(t, visit);
        }
    }
    
    public void visitLiteralImport(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralImport(t, visit);
        }
    }
    
    public void visitLiteralIn(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralIn(t, visit);
        }
    }
    
    public void visitLiteralInstanceof(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralInstanceof(t, visit);
        }
    }
    
    public void visitLiteralInt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralInt(t, visit);
        }
    }
    
    public void visitLiteralInterface(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralInterface(t, visit);
        }
    }
    
    public void visitLiteralLong(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralLong(t, visit);
        }
    }
    
    public void visitLiteralNative(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralNative(t, visit);
        }
    }
    
    public void visitLiteralNew(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralNew(t, visit);
        }
    }
    
    public void visitLiteralNull(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralNull(t, visit);
        }
    }
    
    public void visitLiteralPackage(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralPackage(t, visit);
        }
    }
    
    public void visitLiteralPrivate(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralPrivate(t, visit);
        }
    }
    
    public void visitLiteralProtected(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralProtected(t, visit);
        }
    }
    
    public void visitLiteralPublic(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralPublic(t, visit);
        }
    }
    
    public void visitLiteralReturn(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralReturn(t, visit);
        }
    }
    
    public void visitLiteralShort(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralShort(t, visit);
        }
    }
    
    public void visitLiteralStatic(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralStatic(t, visit);
        }
    }
    
    public void visitLiteralSuper(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralSuper(t, visit);
        }
    }
    
    public void visitLiteralSwitch(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralSwitch(t, visit);
        }
    }
    
    public void visitLiteralSynchronized(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralSynchronized(t, visit);
        }
    }
    
    public void visitLiteralThis(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralThis(t, visit);
        }
    }
    
    public void visitLiteralThreadsafe(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralThreadsafe(t, visit);
        }
    }
    
    public void visitLiteralThrow(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralThrow(t, visit);
        }
    }
    
    public void visitLiteralThrows(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralThrows(t, visit);
        }
    }
    
    public void visitLiteralTransient(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralTransient(t, visit);
        }
    }
    
    public void visitLiteralTrue(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralTrue(t, visit);
        }
    }
    
    public void visitLiteralTry(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralTry(t, visit);
        }
    }
    
    public void visitLiteralVoid(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralVoid(t, visit);
        }
    }
    
    public void visitLiteralVolatile(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralVolatile(t, visit);
        }
    }
    
    public void visitLiteralWhile(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLiteralWhile(t, visit);
        }
    }
    
    public void visitLnot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLnot(t, visit);
        }
    }
    
    public void visitLor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLor(t, visit);
        }
    }
    
    public void visitLparen(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLparen(t, visit);
        }
    }
    
    public void visitLt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitLt(t, visit);
        }
    }
    
    public void visitMapConstructor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMapConstructor(t, visit);
        }
    }
    
    public void visitMemberPointer(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMemberPointer(t, visit);
        }
    }
    
    public void visitMethodCall(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMethodCall(t, visit);
        }
    }
    
    public void visitMethodDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMethodDef(t, visit);
        }
    }
    
    public void visitMinus(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMinus(t, visit);
        }
    }
    
    public void visitMinusAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMinusAssign(t, visit);
        }
    }
    
    public void visitMlComment(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMlComment(t, visit);
        }
    }
    
    public void visitMod(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitMod(t, visit);
        }
    }
    
    public void visitModifiers(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitModifiers(t, visit);
        }
    }
    
    public void visitModAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitModAssign(t, visit);
        }
    }
    
    public void visitNls(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNls(t, visit);
        }
    }
    
    public void visitNotEqual(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNotEqual(t, visit);
        }
    }
    
    public void visitNullTreeLookahead(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNullTreeLookahead(t, visit);
        }
    }
    
    public void visitNumBigDecimal(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumBigDecimal(t, visit);
        }
    }
    
    public void visitNumBigInt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumBigInt(t, visit);
        }
    }
    
    public void visitNumDouble(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumDouble(t, visit);
        }
    }
    
    public void visitNumFloat(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumFloat(t, visit);
        }
    }
    
    public void visitNumInt(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumInt(t, visit);
        }
    }
    
    public void visitNumLong(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitNumLong(t, visit);
        }
    }
    
    public void visitObjblock(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitObjblock(t, visit);
        }
    }
    
    public void visitOneNl(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitOneNl(t, visit);
        }
    }
    
    public void visitOptionalDot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitOptionalDot(t, visit);
        }
    }
    
    public void visitPackageDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitPackageDef(t, visit);
        }
    }
    
    public void visitParameters(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitParameters(t, visit);
        }
    }
    
    public void visitParameterDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitParameterDef(t, visit);
        }
    }
    
    public void visitPlus(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitPlus(t, visit);
        }
    }
    
    public void visitPlusAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitPlusAssign(t, visit);
        }
    }
    
    public void visitPostDec(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitPostDec(t, visit);
        }
    }
    
    public void visitPostInc(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitPostInc(t, visit);
        }
    }
    
    public void visitQuestion(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitQuestion(t, visit);
        }
    }
    
    public void visitRangeExclusive(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRangeExclusive(t, visit);
        }
    }
    
    public void visitRangeInclusive(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRangeInclusive(t, visit);
        }
    }
    
    public void visitRbrack(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRbrack(t, visit);
        }
    }
    
    public void visitRcurly(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRcurly(t, visit);
        }
    }
    
    public void visitRegexpCtorEnd(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRegexpCtorEnd(t, visit);
        }
    }
    
    public void visitRegexpLiteral(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRegexpLiteral(t, visit);
        }
    }
    
    public void visitRegexpSymbol(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRegexpSymbol(t, visit);
        }
    }
    
    public void visitRegexFind(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRegexFind(t, visit);
        }
    }
    
    public void visitRegexMatch(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRegexMatch(t, visit);
        }
    }
    
    public void visitRparen(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitRparen(t, visit);
        }
    }
    
    public void visitSelectSlot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSelectSlot(t, visit);
        }
    }
    
    public void visitSemi(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSemi(t, visit);
        }
    }
    
    public void visitShComment(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitShComment(t, visit);
        }
    }
    
    public void visitSl(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSl(t, visit);
        }
    }
    
    public void visitSlist(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSlist(t, visit);
        }
    }
    
    public void visitSlAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSlAssign(t, visit);
        }
    }
    
    public void visitSlComment(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSlComment(t, visit);
        }
    }
    
    public void visitSpreadArg(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSpreadArg(t, visit);
        }
    }
    
    public void visitSpreadDot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSpreadDot(t, visit);
        }
    }
    
    public void visitSpreadMapArg(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSpreadMapArg(t, visit);
        }
    }
    
    public void visitSr(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSr(t, visit);
        }
    }
    
    public void visitSrAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSrAssign(t, visit);
        }
    }
    
    public void visitStar(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStar(t, visit);
        }
    }
    
    public void visitStarAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStarAssign(t, visit);
        }
    }
    
    public void visitStarStar(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStarStar(t, visit);
        }
    }
    
    public void visitStarStarAssign(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStarStarAssign(t, visit);
        }
    }
    
    public void visitStaticImport(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStaticImport(t, visit);
        }
    }
    
    public void visitStaticInit(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStaticInit(t, visit);
        }
    }
    
    public void visitStrictfp(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStrictfp(t, visit);
        }
    }
    
    public void visitStringCh(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringCh(t, visit);
        }
    }
    
    public void visitStringConstructor(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringConstructor(t, visit);
        }
    }
    
    public void visitStringCtorEnd(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringCtorEnd(t, visit);
        }
    }
    
    public void visitStringCtorMiddle(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringCtorMiddle(t, visit);
        }
    }
    
    public void visitStringCtorStart(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringCtorStart(t, visit);
        }
    }
    
    public void visitStringLiteral(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringLiteral(t, visit);
        }
    }
    
    public void visitStringNl(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitStringNl(t, visit);
        }
    }
    
    public void visitSuperCtorCall(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitSuperCtorCall(t, visit);
        }
    }
    
    public void visitTripleDot(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTripleDot(t, visit);
        }
    }
    
    public void visitType(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitType(t, visit);
        }
    }
    
    public void visitTypecast(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypecast(t, visit);
        }
    }
    
    public void visitTypeArgument(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeArgument(t, visit);
        }
    }
    
    public void visitTypeArguments(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeArguments(t, visit);
        }
    }
    
    public void visitTypeLowerBounds(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeLowerBounds(t, visit);
        }
    }
    
    public void visitTypeParameter(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeParameter(t, visit);
        }
    }
    
    public void visitTypeParameters(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeParameters(t, visit);
        }
    }
    
    public void visitTypeUpperBounds(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitTypeUpperBounds(t, visit);
        }
    }
    
    public void visitUnaryMinus(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitUnaryMinus(t, visit);
        }
    }
    
    public void visitUnaryPlus(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitUnaryPlus(t, visit);
        }
    }
    
    public void visitUnusedConst(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitUnusedConst(t, visit);
        }
    }
    
    public void visitUnusedDo(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitUnusedDo(t, visit);
        }
    }
    
    public void visitUnusedGoto(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitUnusedGoto(t, visit);
        }
    }
    
    public void visitVariableDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitVariableDef(t, visit);
        }
    }
    
    public void visitVariableParameterDef(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitVariableParameterDef(t, visit);
        }
    }
    
    public void visitVocab(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitVocab(t, visit);
        }
    }
    
    public void visitWildcardType(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitWildcardType(t, visit);
        }
    }
    
    public void visitWs(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitWs(t, visit);
        }
    }
    
    public void visitDefault(final GroovySourceAST t, final int visit) {
        final Iterator itr = this.itr(visit);
        while (itr.hasNext()) {
            itr.next().visitDefault(t, visit);
        }
    }
    
    public void tearDown() {
        final Iterator itr = this.backToFrontVisitors.iterator();
        while (itr.hasNext()) {
            itr.next().tearDown();
        }
    }
    
    public void push(final GroovySourceAST t) {
        final Iterator itr = this.visitors.iterator();
        while (itr.hasNext()) {
            itr.next().push(t);
        }
    }
    
    public GroovySourceAST pop() {
        GroovySourceAST lastNodePopped = null;
        final Iterator itr = this.backToFrontVisitors.iterator();
        while (itr.hasNext()) {
            lastNodePopped = itr.next().pop();
        }
        return lastNodePopped;
    }
}
