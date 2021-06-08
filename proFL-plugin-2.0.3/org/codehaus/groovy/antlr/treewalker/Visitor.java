// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;

public interface Visitor
{
    public static final int OPENING_VISIT = 1;
    public static final int SECOND_VISIT = 2;
    public static final int SUBSEQUENT_VISIT = 3;
    public static final int CLOSING_VISIT = 4;
    
    void setUp();
    
    void visitAbstract(final GroovySourceAST p0, final int p1);
    
    void visitAnnotation(final GroovySourceAST p0, final int p1);
    
    void visitAnnotations(final GroovySourceAST p0, final int p1);
    
    void visitAnnotationArrayInit(final GroovySourceAST p0, final int p1);
    
    void visitAnnotationDef(final GroovySourceAST p0, final int p1);
    
    void visitAnnotationFieldDef(final GroovySourceAST p0, final int p1);
    
    void visitAnnotationMemberValuePair(final GroovySourceAST p0, final int p1);
    
    void visitArrayDeclarator(final GroovySourceAST p0, final int p1);
    
    void visitAssign(final GroovySourceAST p0, final int p1);
    
    void visitAt(final GroovySourceAST p0, final int p1);
    
    void visitBand(final GroovySourceAST p0, final int p1);
    
    void visitBandAssign(final GroovySourceAST p0, final int p1);
    
    void visitBigSuffix(final GroovySourceAST p0, final int p1);
    
    void visitBlock(final GroovySourceAST p0, final int p1);
    
    void visitBnot(final GroovySourceAST p0, final int p1);
    
    void visitBor(final GroovySourceAST p0, final int p1);
    
    void visitBorAssign(final GroovySourceAST p0, final int p1);
    
    void visitBsr(final GroovySourceAST p0, final int p1);
    
    void visitBsrAssign(final GroovySourceAST p0, final int p1);
    
    void visitBxor(final GroovySourceAST p0, final int p1);
    
    void visitBxorAssign(final GroovySourceAST p0, final int p1);
    
    void visitCaseGroup(final GroovySourceAST p0, final int p1);
    
    void visitClassDef(final GroovySourceAST p0, final int p1);
    
    void visitClosedBlock(final GroovySourceAST p0, final int p1);
    
    void visitClosureList(final GroovySourceAST p0, final int p1);
    
    void visitClosureOp(final GroovySourceAST p0, final int p1);
    
    void visitColon(final GroovySourceAST p0, final int p1);
    
    void visitComma(final GroovySourceAST p0, final int p1);
    
    void visitCompareTo(final GroovySourceAST p0, final int p1);
    
    void visitCtorCall(final GroovySourceAST p0, final int p1);
    
    void visitCtorIdent(final GroovySourceAST p0, final int p1);
    
    void visitDec(final GroovySourceAST p0, final int p1);
    
    void visitDigit(final GroovySourceAST p0, final int p1);
    
    void visitDiv(final GroovySourceAST p0, final int p1);
    
    void visitDivAssign(final GroovySourceAST p0, final int p1);
    
    void visitDollar(final GroovySourceAST p0, final int p1);
    
    void visitDot(final GroovySourceAST p0, final int p1);
    
    void visitDynamicMember(final GroovySourceAST p0, final int p1);
    
    void visitElist(final GroovySourceAST p0, final int p1);
    
    void visitEmptyStat(final GroovySourceAST p0, final int p1);
    
    void visitEnumConstantDef(final GroovySourceAST p0, final int p1);
    
    void visitEnumDef(final GroovySourceAST p0, final int p1);
    
    void visitEof(final GroovySourceAST p0, final int p1);
    
    void visitEqual(final GroovySourceAST p0, final int p1);
    
    void visitEsc(final GroovySourceAST p0, final int p1);
    
    void visitExponent(final GroovySourceAST p0, final int p1);
    
    void visitExpr(final GroovySourceAST p0, final int p1);
    
    void visitExtendsClause(final GroovySourceAST p0, final int p1);
    
    void visitFinal(final GroovySourceAST p0, final int p1);
    
    void visitFloatSuffix(final GroovySourceAST p0, final int p1);
    
    void visitForCondition(final GroovySourceAST p0, final int p1);
    
    void visitForEachClause(final GroovySourceAST p0, final int p1);
    
    void visitForInit(final GroovySourceAST p0, final int p1);
    
    void visitForInIterable(final GroovySourceAST p0, final int p1);
    
    void visitForIterator(final GroovySourceAST p0, final int p1);
    
    void visitGe(final GroovySourceAST p0, final int p1);
    
    void visitGt(final GroovySourceAST p0, final int p1);
    
    void visitHexDigit(final GroovySourceAST p0, final int p1);
    
    void visitIdent(final GroovySourceAST p0, final int p1);
    
    void visitImplementsClause(final GroovySourceAST p0, final int p1);
    
    void visitImplicitParameters(final GroovySourceAST p0, final int p1);
    
    void visitImport(final GroovySourceAST p0, final int p1);
    
    void visitInc(final GroovySourceAST p0, final int p1);
    
    void visitIndexOp(final GroovySourceAST p0, final int p1);
    
    void visitInstanceInit(final GroovySourceAST p0, final int p1);
    
    void visitInterfaceDef(final GroovySourceAST p0, final int p1);
    
    void visitLabeledArg(final GroovySourceAST p0, final int p1);
    
    void visitLabeledStat(final GroovySourceAST p0, final int p1);
    
    void visitLand(final GroovySourceAST p0, final int p1);
    
    void visitLbrack(final GroovySourceAST p0, final int p1);
    
    void visitLcurly(final GroovySourceAST p0, final int p1);
    
    void visitLe(final GroovySourceAST p0, final int p1);
    
    void visitLetter(final GroovySourceAST p0, final int p1);
    
    void visitListConstructor(final GroovySourceAST p0, final int p1);
    
    void visitLiteralAs(final GroovySourceAST p0, final int p1);
    
    void visitLiteralAssert(final GroovySourceAST p0, final int p1);
    
    void visitLiteralBoolean(final GroovySourceAST p0, final int p1);
    
    void visitLiteralBreak(final GroovySourceAST p0, final int p1);
    
    void visitLiteralByte(final GroovySourceAST p0, final int p1);
    
    void visitLiteralCase(final GroovySourceAST p0, final int p1);
    
    void visitLiteralCatch(final GroovySourceAST p0, final int p1);
    
    void visitLiteralChar(final GroovySourceAST p0, final int p1);
    
    void visitLiteralClass(final GroovySourceAST p0, final int p1);
    
    void visitLiteralContinue(final GroovySourceAST p0, final int p1);
    
    void visitLiteralDef(final GroovySourceAST p0, final int p1);
    
    void visitLiteralDefault(final GroovySourceAST p0, final int p1);
    
    void visitLiteralDouble(final GroovySourceAST p0, final int p1);
    
    void visitLiteralElse(final GroovySourceAST p0, final int p1);
    
    void visitLiteralEnum(final GroovySourceAST p0, final int p1);
    
    void visitLiteralExtends(final GroovySourceAST p0, final int p1);
    
    void visitLiteralFalse(final GroovySourceAST p0, final int p1);
    
    void visitLiteralFinally(final GroovySourceAST p0, final int p1);
    
    void visitLiteralFloat(final GroovySourceAST p0, final int p1);
    
    void visitLiteralFor(final GroovySourceAST p0, final int p1);
    
    void visitLiteralIf(final GroovySourceAST p0, final int p1);
    
    void visitLiteralImplements(final GroovySourceAST p0, final int p1);
    
    void visitLiteralImport(final GroovySourceAST p0, final int p1);
    
    void visitLiteralIn(final GroovySourceAST p0, final int p1);
    
    void visitLiteralInstanceof(final GroovySourceAST p0, final int p1);
    
    void visitLiteralInt(final GroovySourceAST p0, final int p1);
    
    void visitLiteralInterface(final GroovySourceAST p0, final int p1);
    
    void visitLiteralLong(final GroovySourceAST p0, final int p1);
    
    void visitLiteralNative(final GroovySourceAST p0, final int p1);
    
    void visitLiteralNew(final GroovySourceAST p0, final int p1);
    
    void visitLiteralNull(final GroovySourceAST p0, final int p1);
    
    void visitLiteralPackage(final GroovySourceAST p0, final int p1);
    
    void visitLiteralPrivate(final GroovySourceAST p0, final int p1);
    
    void visitLiteralProtected(final GroovySourceAST p0, final int p1);
    
    void visitLiteralPublic(final GroovySourceAST p0, final int p1);
    
    void visitLiteralReturn(final GroovySourceAST p0, final int p1);
    
    void visitLiteralShort(final GroovySourceAST p0, final int p1);
    
    void visitLiteralStatic(final GroovySourceAST p0, final int p1);
    
    void visitLiteralSuper(final GroovySourceAST p0, final int p1);
    
    void visitLiteralSwitch(final GroovySourceAST p0, final int p1);
    
    void visitLiteralSynchronized(final GroovySourceAST p0, final int p1);
    
    void visitLiteralThis(final GroovySourceAST p0, final int p1);
    
    void visitLiteralThreadsafe(final GroovySourceAST p0, final int p1);
    
    void visitLiteralThrow(final GroovySourceAST p0, final int p1);
    
    void visitLiteralThrows(final GroovySourceAST p0, final int p1);
    
    void visitLiteralTransient(final GroovySourceAST p0, final int p1);
    
    void visitLiteralTrue(final GroovySourceAST p0, final int p1);
    
    void visitLiteralTry(final GroovySourceAST p0, final int p1);
    
    void visitLiteralVoid(final GroovySourceAST p0, final int p1);
    
    void visitLiteralVolatile(final GroovySourceAST p0, final int p1);
    
    void visitLiteralWhile(final GroovySourceAST p0, final int p1);
    
    void visitLnot(final GroovySourceAST p0, final int p1);
    
    void visitLor(final GroovySourceAST p0, final int p1);
    
    void visitLparen(final GroovySourceAST p0, final int p1);
    
    void visitLt(final GroovySourceAST p0, final int p1);
    
    void visitMapConstructor(final GroovySourceAST p0, final int p1);
    
    void visitMemberPointer(final GroovySourceAST p0, final int p1);
    
    void visitMethodCall(final GroovySourceAST p0, final int p1);
    
    void visitMethodDef(final GroovySourceAST p0, final int p1);
    
    void visitMinus(final GroovySourceAST p0, final int p1);
    
    void visitMinusAssign(final GroovySourceAST p0, final int p1);
    
    void visitMlComment(final GroovySourceAST p0, final int p1);
    
    void visitMod(final GroovySourceAST p0, final int p1);
    
    void visitModifiers(final GroovySourceAST p0, final int p1);
    
    void visitModAssign(final GroovySourceAST p0, final int p1);
    
    void visitNls(final GroovySourceAST p0, final int p1);
    
    void visitNotEqual(final GroovySourceAST p0, final int p1);
    
    void visitNullTreeLookahead(final GroovySourceAST p0, final int p1);
    
    void visitNumBigDecimal(final GroovySourceAST p0, final int p1);
    
    void visitNumBigInt(final GroovySourceAST p0, final int p1);
    
    void visitNumDouble(final GroovySourceAST p0, final int p1);
    
    void visitNumFloat(final GroovySourceAST p0, final int p1);
    
    void visitNumInt(final GroovySourceAST p0, final int p1);
    
    void visitNumLong(final GroovySourceAST p0, final int p1);
    
    void visitObjblock(final GroovySourceAST p0, final int p1);
    
    void visitOneNl(final GroovySourceAST p0, final int p1);
    
    void visitOptionalDot(final GroovySourceAST p0, final int p1);
    
    void visitPackageDef(final GroovySourceAST p0, final int p1);
    
    void visitParameters(final GroovySourceAST p0, final int p1);
    
    void visitParameterDef(final GroovySourceAST p0, final int p1);
    
    void visitPlus(final GroovySourceAST p0, final int p1);
    
    void visitPlusAssign(final GroovySourceAST p0, final int p1);
    
    void visitPostDec(final GroovySourceAST p0, final int p1);
    
    void visitPostInc(final GroovySourceAST p0, final int p1);
    
    void visitQuestion(final GroovySourceAST p0, final int p1);
    
    void visitRangeExclusive(final GroovySourceAST p0, final int p1);
    
    void visitRangeInclusive(final GroovySourceAST p0, final int p1);
    
    void visitRbrack(final GroovySourceAST p0, final int p1);
    
    void visitRcurly(final GroovySourceAST p0, final int p1);
    
    void visitRegexpCtorEnd(final GroovySourceAST p0, final int p1);
    
    void visitRegexpLiteral(final GroovySourceAST p0, final int p1);
    
    void visitRegexpSymbol(final GroovySourceAST p0, final int p1);
    
    void visitRegexFind(final GroovySourceAST p0, final int p1);
    
    void visitRegexMatch(final GroovySourceAST p0, final int p1);
    
    void visitRparen(final GroovySourceAST p0, final int p1);
    
    void visitSelectSlot(final GroovySourceAST p0, final int p1);
    
    void visitSemi(final GroovySourceAST p0, final int p1);
    
    void visitShComment(final GroovySourceAST p0, final int p1);
    
    void visitSl(final GroovySourceAST p0, final int p1);
    
    void visitSlist(final GroovySourceAST p0, final int p1);
    
    void visitSlAssign(final GroovySourceAST p0, final int p1);
    
    void visitSlComment(final GroovySourceAST p0, final int p1);
    
    void visitSpreadArg(final GroovySourceAST p0, final int p1);
    
    void visitSpreadDot(final GroovySourceAST p0, final int p1);
    
    void visitSpreadMapArg(final GroovySourceAST p0, final int p1);
    
    void visitSr(final GroovySourceAST p0, final int p1);
    
    void visitSrAssign(final GroovySourceAST p0, final int p1);
    
    void visitStar(final GroovySourceAST p0, final int p1);
    
    void visitStarAssign(final GroovySourceAST p0, final int p1);
    
    void visitStarStar(final GroovySourceAST p0, final int p1);
    
    void visitStarStarAssign(final GroovySourceAST p0, final int p1);
    
    void visitStaticImport(final GroovySourceAST p0, final int p1);
    
    void visitStaticInit(final GroovySourceAST p0, final int p1);
    
    void visitStrictfp(final GroovySourceAST p0, final int p1);
    
    void visitStringCh(final GroovySourceAST p0, final int p1);
    
    void visitStringConstructor(final GroovySourceAST p0, final int p1);
    
    void visitStringCtorEnd(final GroovySourceAST p0, final int p1);
    
    void visitStringCtorMiddle(final GroovySourceAST p0, final int p1);
    
    void visitStringCtorStart(final GroovySourceAST p0, final int p1);
    
    void visitStringLiteral(final GroovySourceAST p0, final int p1);
    
    void visitStringNl(final GroovySourceAST p0, final int p1);
    
    void visitSuperCtorCall(final GroovySourceAST p0, final int p1);
    
    void visitTripleDot(final GroovySourceAST p0, final int p1);
    
    void visitType(final GroovySourceAST p0, final int p1);
    
    void visitTypecast(final GroovySourceAST p0, final int p1);
    
    void visitTypeArgument(final GroovySourceAST p0, final int p1);
    
    void visitTypeArguments(final GroovySourceAST p0, final int p1);
    
    void visitTypeLowerBounds(final GroovySourceAST p0, final int p1);
    
    void visitTypeParameter(final GroovySourceAST p0, final int p1);
    
    void visitTypeParameters(final GroovySourceAST p0, final int p1);
    
    void visitTypeUpperBounds(final GroovySourceAST p0, final int p1);
    
    void visitUnaryMinus(final GroovySourceAST p0, final int p1);
    
    void visitUnaryPlus(final GroovySourceAST p0, final int p1);
    
    void visitUnusedConst(final GroovySourceAST p0, final int p1);
    
    void visitUnusedDo(final GroovySourceAST p0, final int p1);
    
    void visitUnusedGoto(final GroovySourceAST p0, final int p1);
    
    void visitVariableDef(final GroovySourceAST p0, final int p1);
    
    void visitVariableParameterDef(final GroovySourceAST p0, final int p1);
    
    void visitVocab(final GroovySourceAST p0, final int p1);
    
    void visitWildcardType(final GroovySourceAST p0, final int p1);
    
    void visitWs(final GroovySourceAST p0, final int p1);
    
    void visitDefault(final GroovySourceAST p0, final int p1);
    
    void tearDown();
    
    void push(final GroovySourceAST p0);
    
    GroovySourceAST pop();
}
