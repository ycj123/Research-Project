// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import groovyjarjarantlr.collections.AST;
import java.util.ArrayList;
import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.List;
import org.codehaus.groovy.antlr.AntlrASTProcessor;

public abstract class TraversalHelper implements AntlrASTProcessor
{
    protected List<GroovySourceAST> unvisitedNodes;
    private final Visitor v;
    
    public TraversalHelper(final Visitor visitor) {
        this.unvisitedNodes = new ArrayList<GroovySourceAST>();
        this.v = visitor;
    }
    
    protected void setUp(final GroovySourceAST ast) {
        this.v.setUp();
    }
    
    protected void tearDown(final GroovySourceAST ast) {
        this.v.tearDown();
    }
    
    protected void push(final GroovySourceAST ast) {
        this.v.push(ast);
    }
    
    protected GroovySourceAST pop() {
        return this.v.pop();
    }
    
    protected void visitNode(final GroovySourceAST ast, final int n) {
        if (ast != null) {
            switch (ast.getType()) {
                case 38: {
                    this.v.visitAbstract(ast, n);
                    break;
                }
                case 65: {
                    this.v.visitAnnotation(ast, n);
                    break;
                }
                case 64: {
                    this.v.visitAnnotations(ast, n);
                    break;
                }
                case 68: {
                    this.v.visitAnnotationArrayInit(ast, n);
                    break;
                }
                case 63: {
                    this.v.visitAnnotationDef(ast, n);
                    break;
                }
                case 67: {
                    this.v.visitAnnotationFieldDef(ast, n);
                    break;
                }
                case 66: {
                    this.v.visitAnnotationMemberValuePair(ast, n);
                    break;
                }
                case 16: {
                    this.v.visitArrayDeclarator(ast, n);
                    break;
                }
                case 120: {
                    this.v.visitAssign(ast, n);
                    break;
                }
                case 92: {
                    this.v.visitAt(ast, n);
                    break;
                }
                case 121: {
                    this.v.visitBand(ast, n);
                    break;
                }
                case 165: {
                    this.v.visitBandAssign(ast, n);
                    break;
                }
                case 219: {
                    this.v.visitBigSuffix(ast, n);
                    break;
                }
                case 4: {
                    this.v.visitBlock(ast, n);
                    break;
                }
                case 191: {
                    this.v.visitBnot(ast, n);
                    break;
                }
                case 172: {
                    this.v.visitBor(ast, n);
                    break;
                }
                case 167: {
                    this.v.visitBorAssign(ast, n);
                    break;
                }
                case 99: {
                    this.v.visitBsr(ast, n);
                    break;
                }
                case 163: {
                    this.v.visitBsrAssign(ast, n);
                    break;
                }
                case 173: {
                    this.v.visitBxor(ast, n);
                    break;
                }
                case 166: {
                    this.v.visitBxorAssign(ast, n);
                    break;
                }
                case 31: {
                    this.v.visitCaseGroup(ast, n);
                    break;
                }
                case 13: {
                    this.v.visitClassDef(ast, n);
                    break;
                }
                case 49: {
                    this.v.visitClosedBlock(ast, n);
                    break;
                }
                case 130: {
                    this.v.visitClosureOp(ast, n);
                    break;
                }
                case 76: {
                    this.v.visitClosureList(ast, n);
                    break;
                }
                case 131: {
                    this.v.visitColon(ast, n);
                    break;
                }
                case 96: {
                    this.v.visitComma(ast, n);
                    break;
                }
                case 180: {
                    this.v.visitCompareTo(ast, n);
                    break;
                }
                case 44: {
                    this.v.visitCtorCall(ast, n);
                    break;
                }
                case 45: {
                    this.v.visitCtorIdent(ast, n);
                    break;
                }
                case 189: {
                    this.v.visitDec(ast, n);
                    break;
                }
                case 216: {
                    this.v.visitDigit(ast, n);
                    break;
                }
                case 187: {
                    this.v.visitDiv(ast, n);
                    break;
                }
                case 160: {
                    this.v.visitDivAssign(ast, n);
                    break;
                }
                case 202: {
                    this.v.visitDollar(ast, n);
                    break;
                }
                case 87: {
                    this.v.visitDot(ast, n);
                    break;
                }
                case 52: {
                    this.v.visitDynamicMember(ast, n);
                    break;
                }
                case 32: {
                    this.v.visitElist(ast, n);
                    break;
                }
                case 36: {
                    this.v.visitEmptyStat(ast, n);
                    break;
                }
                case 61: {
                    this.v.visitEnumConstantDef(ast, n);
                    break;
                }
                case 60: {
                    this.v.visitEnumDef(ast, n);
                    break;
                }
                case 1: {
                    this.v.visitEof(ast, n);
                    break;
                }
                case 177: {
                    this.v.visitEqual(ast, n);
                    break;
                }
                case 211: {
                    this.v.visitEsc(ast, n);
                    break;
                }
                case 217: {
                    this.v.visitExponent(ast, n);
                    break;
                }
                case 27: {
                    this.v.visitExpr(ast, n);
                    break;
                }
                case 17: {
                    this.v.visitExtendsClause(ast, n);
                    break;
                }
                case 37: {
                    this.v.visitFinal(ast, n);
                    break;
                }
                case 218: {
                    this.v.visitFloatSuffix(ast, n);
                    break;
                }
                case 34: {
                    this.v.visitForCondition(ast, n);
                    break;
                }
                case 62: {
                    this.v.visitForEachClause(ast, n);
                    break;
                }
                case 33: {
                    this.v.visitForInit(ast, n);
                    break;
                }
                case 58: {
                    this.v.visitForInIterable(ast, n);
                    break;
                }
                case 35: {
                    this.v.visitForIterator(ast, n);
                    break;
                }
                case 182: {
                    this.v.visitGe(ast, n);
                    break;
                }
                case 97: {
                    this.v.visitGt(ast, n);
                    break;
                }
                case 213: {
                    this.v.visitHexDigit(ast, n);
                    break;
                }
                case 84: {
                    this.v.visitIdent(ast, n);
                    break;
                }
                case 18: {
                    this.v.visitImplementsClause(ast, n);
                    break;
                }
                case 50: {
                    this.v.visitImplicitParameters(ast, n);
                    break;
                }
                case 28: {
                    this.v.visitImport(ast, n);
                    break;
                }
                case 186: {
                    this.v.visitInc(ast, n);
                    break;
                }
                case 23: {
                    this.v.visitIndexOp(ast, n);
                    break;
                }
                case 10: {
                    this.v.visitInstanceInit(ast, n);
                    break;
                }
                case 14: {
                    this.v.visitInterfaceDef(ast, n);
                    break;
                }
                case 53: {
                    this.v.visitLabeledArg(ast, n);
                    break;
                }
                case 21: {
                    this.v.visitLabeledStat(ast, n);
                    break;
                }
                case 171: {
                    this.v.visitLand(ast, n);
                    break;
                }
                case 82: {
                    this.v.visitLbrack(ast, n);
                    break;
                }
                case 122: {
                    this.v.visitLcurly(ast, n);
                    break;
                }
                case 181: {
                    this.v.visitLe(ast, n);
                    break;
                }
                case 215: {
                    this.v.visitLetter(ast, n);
                    break;
                }
                case 56: {
                    this.v.visitListConstructor(ast, n);
                    break;
                }
                case 110: {
                    this.v.visitLiteralAs(ast, n);
                    break;
                }
                case 142: {
                    this.v.visitLiteralAssert(ast, n);
                    break;
                }
                case 101: {
                    this.v.visitLiteralBoolean(ast, n);
                    break;
                }
                case 139: {
                    this.v.visitLiteralBreak(ast, n);
                    break;
                }
                case 102: {
                    this.v.visitLiteralByte(ast, n);
                    break;
                }
                case 145: {
                    this.v.visitLiteralCase(ast, n);
                    break;
                }
                case 148: {
                    this.v.visitLiteralCatch(ast, n);
                    break;
                }
                case 103: {
                    this.v.visitLiteralChar(ast, n);
                    break;
                }
                case 89: {
                    this.v.visitLiteralClass(ast, n);
                    break;
                }
                case 140: {
                    this.v.visitLiteralContinue(ast, n);
                    break;
                }
                case 81: {
                    this.v.visitLiteralDef(ast, n);
                    break;
                }
                case 125: {
                    this.v.visitLiteralDefault(ast, n);
                    break;
                }
                case 108: {
                    this.v.visitLiteralDouble(ast, n);
                    break;
                }
                case 133: {
                    this.v.visitLiteralElse(ast, n);
                    break;
                }
                case 91: {
                    this.v.visitLiteralEnum(ast, n);
                    break;
                }
                case 94: {
                    this.v.visitLiteralExtends(ast, n);
                    break;
                }
                case 152: {
                    this.v.visitLiteralFalse(ast, n);
                    break;
                }
                case 147: {
                    this.v.visitLiteralFinally(ast, n);
                    break;
                }
                case 106: {
                    this.v.visitLiteralFloat(ast, n);
                    break;
                }
                case 136: {
                    this.v.visitLiteralFor(ast, n);
                    break;
                }
                case 132: {
                    this.v.visitLiteralIf(ast, n);
                    break;
                }
                case 127: {
                    this.v.visitLiteralImplements(ast, n);
                    break;
                }
                case 79: {
                    this.v.visitLiteralImport(ast, n);
                    break;
                }
                case 137: {
                    this.v.visitLiteralIn(ast, n);
                    break;
                }
                case 153: {
                    this.v.visitLiteralInstanceof(ast, n);
                    break;
                }
                case 105: {
                    this.v.visitLiteralInt(ast, n);
                    break;
                }
                case 90: {
                    this.v.visitLiteralInterface(ast, n);
                    break;
                }
                case 107: {
                    this.v.visitLiteralLong(ast, n);
                    break;
                }
                case 115: {
                    this.v.visitLiteralNative(ast, n);
                    break;
                }
                case 154: {
                    this.v.visitLiteralNew(ast, n);
                    break;
                }
                case 155: {
                    this.v.visitLiteralNull(ast, n);
                    break;
                }
                case 78: {
                    this.v.visitLiteralPackage(ast, n);
                    break;
                }
                case 111: {
                    this.v.visitLiteralPrivate(ast, n);
                    break;
                }
                case 113: {
                    this.v.visitLiteralProtected(ast, n);
                    break;
                }
                case 112: {
                    this.v.visitLiteralPublic(ast, n);
                    break;
                }
                case 138: {
                    this.v.visitLiteralReturn(ast, n);
                    break;
                }
                case 104: {
                    this.v.visitLiteralShort(ast, n);
                    break;
                }
                case 80: {
                    this.v.visitLiteralStatic(ast, n);
                    break;
                }
                case 95: {
                    this.v.visitLiteralSuper(ast, n);
                    break;
                }
                case 135: {
                    this.v.visitLiteralSwitch(ast, n);
                    break;
                }
                case 117: {
                    this.v.visitLiteralSynchronized(ast, n);
                    break;
                }
                case 128: {
                    this.v.visitLiteralThis(ast, n);
                    break;
                }
                case 116: {
                    this.v.visitLiteralThreadsafe(ast, n);
                    break;
                }
                case 141: {
                    this.v.visitLiteralThrow(ast, n);
                    break;
                }
                case 126: {
                    this.v.visitLiteralThrows(ast, n);
                    break;
                }
                case 114: {
                    this.v.visitLiteralTransient(ast, n);
                    break;
                }
                case 156: {
                    this.v.visitLiteralTrue(ast, n);
                    break;
                }
                case 146: {
                    this.v.visitLiteralTry(ast, n);
                    break;
                }
                case 100: {
                    this.v.visitLiteralVoid(ast, n);
                    break;
                }
                case 118: {
                    this.v.visitLiteralVolatile(ast, n);
                    break;
                }
                case 134: {
                    this.v.visitLiteralWhile(ast, n);
                    break;
                }
                case 192: {
                    this.v.visitLnot(ast, n);
                    break;
                }
                case 170: {
                    this.v.visitLor(ast, n);
                    break;
                }
                case 88: {
                    this.v.visitLparen(ast, n);
                    break;
                }
                case 86: {
                    this.v.visitLt(ast, n);
                    break;
                }
                case 57: {
                    this.v.visitMapConstructor(ast, n);
                    break;
                }
                case 151: {
                    this.v.visitMemberPointer(ast, n);
                    break;
                }
                case 26: {
                    this.v.visitMethodCall(ast, n);
                    break;
                }
                case 8: {
                    this.v.visitMethodDef(ast, n);
                    break;
                }
                case 144: {
                    this.v.visitMinus(ast, n);
                    break;
                }
                case 158: {
                    this.v.visitMinusAssign(ast, n);
                    break;
                }
                case 206: {
                    this.v.visitMlComment(ast, n);
                    break;
                }
                case 188: {
                    this.v.visitMod(ast, n);
                    break;
                }
                case 5: {
                    this.v.visitModifiers(ast, n);
                    break;
                }
                case 161: {
                    this.v.visitModAssign(ast, n);
                    break;
                }
                case 201: {
                    this.v.visitNls(ast, n);
                    break;
                }
                case 176: {
                    this.v.visitNotEqual(ast, n);
                    break;
                }
                case 3: {
                    this.v.visitNullTreeLookahead(ast, n);
                    break;
                }
                case 200: {
                    this.v.visitNumBigDecimal(ast, n);
                    break;
                }
                case 199: {
                    this.v.visitNumBigInt(ast, n);
                    break;
                }
                case 198: {
                    this.v.visitNumDouble(ast, n);
                    break;
                }
                case 196: {
                    this.v.visitNumFloat(ast, n);
                    break;
                }
                case 195: {
                    this.v.visitNumInt(ast, n);
                    break;
                }
                case 197: {
                    this.v.visitNumLong(ast, n);
                    break;
                }
                case 6: {
                    this.v.visitObjblock(ast, n);
                    break;
                }
                case 204: {
                    this.v.visitOneNl(ast, n);
                    break;
                }
                case 150: {
                    this.v.visitOptionalDot(ast, n);
                    break;
                }
                case 15: {
                    this.v.visitPackageDef(ast, n);
                    break;
                }
                case 19: {
                    this.v.visitParameters(ast, n);
                    break;
                }
                case 20: {
                    this.v.visitParameterDef(ast, n);
                    break;
                }
                case 143: {
                    this.v.visitPlus(ast, n);
                    break;
                }
                case 157: {
                    this.v.visitPlusAssign(ast, n);
                    break;
                }
                case 25: {
                    this.v.visitPostDec(ast, n);
                    break;
                }
                case 24: {
                    this.v.visitPostInc(ast, n);
                    break;
                }
                case 93: {
                    this.v.visitQuestion(ast, n);
                    break;
                }
                case 185: {
                    this.v.visitRangeExclusive(ast, n);
                    break;
                }
                case 184: {
                    this.v.visitRangeInclusive(ast, n);
                    break;
                }
                case 83: {
                    this.v.visitRbrack(ast, n);
                    break;
                }
                case 123: {
                    this.v.visitRcurly(ast, n);
                    break;
                }
                case 209: {
                    this.v.visitRegexpCtorEnd(ast, n);
                    break;
                }
                case 208: {
                    this.v.visitRegexpLiteral(ast, n);
                    break;
                }
                case 210: {
                    this.v.visitRegexpSymbol(ast, n);
                    break;
                }
                case 174: {
                    this.v.visitRegexFind(ast, n);
                    break;
                }
                case 175: {
                    this.v.visitRegexMatch(ast, n);
                    break;
                }
                case 119: {
                    this.v.visitRparen(ast, n);
                    break;
                }
                case 51: {
                    this.v.visitSelectSlot(ast, n);
                    break;
                }
                case 124: {
                    this.v.visitSemi(ast, n);
                    break;
                }
                case 77: {
                    this.v.visitShComment(ast, n);
                    break;
                }
                case 183: {
                    this.v.visitSl(ast, n);
                    break;
                }
                case 7: {
                    this.v.visitSlist(ast, n);
                    break;
                }
                case 164: {
                    this.v.visitSlAssign(ast, n);
                    break;
                }
                case 205: {
                    this.v.visitSlComment(ast, n);
                    break;
                }
                case 54: {
                    this.v.visitSpreadArg(ast, n);
                    break;
                }
                case 149: {
                    this.v.visitSpreadDot(ast, n);
                    break;
                }
                case 55: {
                    this.v.visitSpreadMapArg(ast, n);
                    break;
                }
                case 98: {
                    this.v.visitSr(ast, n);
                    break;
                }
                case 162: {
                    this.v.visitSrAssign(ast, n);
                    break;
                }
                case 109: {
                    this.v.visitStar(ast, n);
                    break;
                }
                case 159: {
                    this.v.visitStarAssign(ast, n);
                    break;
                }
                case 190: {
                    this.v.visitStarStar(ast, n);
                    break;
                }
                case 168: {
                    this.v.visitStarStarAssign(ast, n);
                    break;
                }
                case 59: {
                    this.v.visitStaticImport(ast, n);
                    break;
                }
                case 11: {
                    this.v.visitStaticInit(ast, n);
                    break;
                }
                case 42: {
                    this.v.visitStrictfp(ast, n);
                    break;
                }
                case 207: {
                    this.v.visitStringCh(ast, n);
                    break;
                }
                case 47: {
                    this.v.visitStringConstructor(ast, n);
                    break;
                }
                case 194: {
                    this.v.visitStringCtorEnd(ast, n);
                    break;
                }
                case 48: {
                    this.v.visitStringCtorMiddle(ast, n);
                    break;
                }
                case 193: {
                    this.v.visitStringCtorStart(ast, n);
                    break;
                }
                case 85: {
                    this.v.visitStringLiteral(ast, n);
                    break;
                }
                case 212: {
                    this.v.visitStringNl(ast, n);
                    break;
                }
                case 43: {
                    this.v.visitSuperCtorCall(ast, n);
                    break;
                }
                case 129: {
                    this.v.visitTripleDot(ast, n);
                    break;
                }
                case 12: {
                    this.v.visitType(ast, n);
                    break;
                }
                case 22: {
                    this.v.visitTypecast(ast, n);
                    break;
                }
                case 70: {
                    this.v.visitTypeArgument(ast, n);
                    break;
                }
                case 69: {
                    this.v.visitTypeArguments(ast, n);
                    break;
                }
                case 75: {
                    this.v.visitTypeLowerBounds(ast, n);
                    break;
                }
                case 72: {
                    this.v.visitTypeParameter(ast, n);
                    break;
                }
                case 71: {
                    this.v.visitTypeParameters(ast, n);
                    break;
                }
                case 74: {
                    this.v.visitTypeUpperBounds(ast, n);
                    break;
                }
                case 29: {
                    this.v.visitUnaryMinus(ast, n);
                    break;
                }
                case 30: {
                    this.v.visitUnaryPlus(ast, n);
                    break;
                }
                case 40: {
                    this.v.visitUnusedConst(ast, n);
                    break;
                }
                case 41: {
                    this.v.visitUnusedDo(ast, n);
                    break;
                }
                case 39: {
                    this.v.visitUnusedGoto(ast, n);
                    break;
                }
                case 9: {
                    this.v.visitVariableDef(ast, n);
                    break;
                }
                case 46: {
                    this.v.visitVariableParameterDef(ast, n);
                    break;
                }
                case 214: {
                    this.v.visitVocab(ast, n);
                    break;
                }
                case 73: {
                    this.v.visitWildcardType(ast, n);
                    break;
                }
                case 203: {
                    this.v.visitWs(ast, n);
                    break;
                }
                default: {
                    this.v.visitDefault(ast, n);
                    break;
                }
            }
        }
        else {
            this.v.visitDefault(null, n);
        }
    }
    
    protected abstract void accept(final GroovySourceAST p0);
    
    protected void accept_v_FirstChildsFirstChild_v_Child2_Child3_v_Child4_v___v_LastChild(final GroovySourceAST t) {
        this.openingVisit(t);
        final GroovySourceAST expr2 = t.childAt(0);
        this.skip(expr2);
        this.accept(expr2.childAt(0));
        this.closingVisit(t);
        GroovySourceAST sibling = (GroovySourceAST)expr2.getNextSibling();
        boolean firstSList = true;
        while (sibling != null) {
            if (!firstSList) {
                this.subsequentVisit(t);
            }
            firstSList = false;
            this.accept(sibling);
            sibling = (GroovySourceAST)sibling.getNextSibling();
        }
    }
    
    protected void accept_v_FirstChildsFirstChild_v_RestOfTheChildren(final GroovySourceAST t) {
        this.openingVisit(t);
        final GroovySourceAST expr = t.childAt(0);
        this.skip(expr);
        this.accept(expr.childAt(0));
        this.closingVisit(t);
        this.acceptSiblings(expr);
    }
    
    protected void accept_FirstChild_v_SecondChild(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.subsequentVisit(t);
        this.accept(t.childAt(1));
    }
    
    protected void accept_FirstChild_v_SecondChild_v(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.openingVisit(t);
        this.accept(t.childAt(1));
        this.closingVisit(t);
    }
    
    protected void accept_SecondChild_v_ThirdChild_v(final GroovySourceAST t) {
        this.accept(t.childAt(1));
        this.openingVisit(t);
        this.accept(t.childAt(2));
        this.closingVisit(t);
    }
    
    protected void accept_FirstChild_v_SecondChildsChildren_v(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.openingVisit(t);
        final GroovySourceAST secondChild = t.childAt(1);
        if (secondChild != null) {
            this.acceptChildren(secondChild);
        }
        this.closingVisit(t);
    }
    
    protected void accept_v_FirstChild_SecondChild_v_ThirdChild_v(final GroovySourceAST t) {
        this.openingVisit(t);
        this.accept(t.childAt(0));
        this.accept(t.childAt(1));
        this.subsequentVisit(t);
        this.accept(t.childAt(2));
        this.closingVisit(t);
    }
    
    protected void accept_FirstChild_v_SecondChild_v_ThirdChild_v(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.openingVisit(t);
        this.accept(t.childAt(1));
        this.subsequentVisit(t);
        this.accept(t.childAt(2));
        this.closingVisit(t);
    }
    
    protected void accept_FirstSecondAndThirdChild_v_v_ForthChild(final GroovySourceAST t) {
        final GroovySourceAST child1 = (GroovySourceAST)t.getFirstChild();
        if (child1 != null) {
            this.accept(child1);
            final GroovySourceAST child2 = (GroovySourceAST)child1.getNextSibling();
            if (child2 != null) {
                this.accept(child2);
                final GroovySourceAST child3 = (GroovySourceAST)child2.getNextSibling();
                if (child3 != null) {
                    this.accept(child3);
                    this.openingVisit(t);
                    final GroovySourceAST child4 = (GroovySourceAST)child3.getNextSibling();
                    if (child4 != null) {
                        this.subsequentVisit(t);
                        this.accept(child4);
                    }
                }
            }
        }
    }
    
    protected void accept_v_FirstChild_2ndv_SecondChild_v___LastChild_v(final GroovySourceAST t) {
        this.openingVisit(t);
        final GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
        if (child != null) {
            this.accept(child);
            GroovySourceAST sibling = (GroovySourceAST)child.getNextSibling();
            if (sibling != null) {
                this.secondVisit(t);
                this.accept(sibling);
                for (sibling = (GroovySourceAST)sibling.getNextSibling(); sibling != null; sibling = (GroovySourceAST)sibling.getNextSibling()) {
                    this.subsequentVisit(t);
                    this.accept(sibling);
                }
            }
        }
        this.closingVisit(t);
    }
    
    protected void accept_v_FirstChild_v_SecondChild_v___LastChild_v(final GroovySourceAST t) {
        this.openingVisit(t);
        final GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
        if (child != null) {
            this.accept(child);
            for (GroovySourceAST sibling = (GroovySourceAST)child.getNextSibling(); sibling != null; sibling = (GroovySourceAST)sibling.getNextSibling()) {
                this.subsequentVisit(t);
                this.accept(sibling);
            }
        }
        this.closingVisit(t);
    }
    
    protected void accept_v_FirstChild_v(final GroovySourceAST t) {
        this.openingVisit(t);
        this.accept(t.childAt(0));
        this.closingVisit(t);
    }
    
    protected void accept_v_Siblings_v(final GroovySourceAST t) {
        this.openingVisit(t);
        this.acceptSiblings(t);
        this.closingVisit(t);
    }
    
    protected void accept_v_AllChildren_v_Siblings(final GroovySourceAST t) {
        this.openingVisit(t);
        this.acceptChildren(t);
        this.closingVisit(t);
        this.acceptSiblings(t);
    }
    
    protected void accept_v_AllChildren_v(final GroovySourceAST t) {
        this.openingVisit(t);
        this.acceptChildren(t);
        this.closingVisit(t);
    }
    
    protected void accept_FirstChild_v_RestOfTheChildren(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.openingVisit(t);
        this.closingVisit(t);
        this.acceptSiblings(t.childAt(0));
    }
    
    protected void accept_FirstChild_v_RestOfTheChildren_v_LastChild(final GroovySourceAST t) {
        int count = 0;
        this.accept(t.childAt(0));
        ++count;
        this.openingVisit(t);
        if (t.childAt(0) != null) {
            for (GroovySourceAST sibling = (GroovySourceAST)t.childAt(0).getNextSibling(); sibling != null; sibling = (GroovySourceAST)sibling.getNextSibling()) {
                if (count == t.getNumberOfChildren() - 1) {
                    this.closingVisit(t);
                }
                this.accept(sibling);
                ++count;
            }
        }
    }
    
    protected void accept_FirstChild_v_RestOfTheChildren_v(final GroovySourceAST t) {
        this.accept(t.childAt(0));
        this.openingVisit(t);
        this.acceptSiblings(t.childAt(0));
        this.closingVisit(t);
    }
    
    protected void accept_v_FirstChild_v_RestOfTheChildren(final GroovySourceAST t) {
        this.accept_v_FirstChild_v(t);
        this.acceptSiblings(t.childAt(0));
    }
    
    protected void accept_v_FirstChild_v_RestOfTheChildren_v(final GroovySourceAST t) {
        this.openingVisit(t);
        this.accept(t.childAt(0));
        this.subsequentVisit(t);
        this.acceptSiblings(t.childAt(0));
        this.closingVisit(t);
    }
    
    protected void acceptSiblings(final GroovySourceAST t) {
        if (t != null) {
            for (GroovySourceAST sibling = (GroovySourceAST)t.getNextSibling(); sibling != null; sibling = (GroovySourceAST)sibling.getNextSibling()) {
                this.accept(sibling);
            }
        }
    }
    
    protected void acceptChildren(final GroovySourceAST t) {
        if (t != null) {
            final GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
            if (child != null) {
                this.accept(child);
                this.acceptSiblings(child);
            }
        }
    }
    
    protected void skip(final GroovySourceAST expr) {
        this.unvisitedNodes.remove(expr);
    }
    
    protected void openingVisit(final GroovySourceAST t) {
        this.unvisitedNodes.remove(t);
        final int n = 1;
        this.visitNode(t, n);
    }
    
    protected void secondVisit(final GroovySourceAST t) {
        final int n = 2;
        this.visitNode(t, n);
    }
    
    protected void subsequentVisit(final GroovySourceAST t) {
        final int n = 3;
        this.visitNode(t, n);
    }
    
    protected void closingVisit(final GroovySourceAST t) {
        final int n = 4;
        this.visitNode(t, n);
    }
    
    public AST process(final AST t) {
        final GroovySourceAST node = (GroovySourceAST)t;
        this.setUp(node);
        this.accept(node);
        this.acceptSiblings(node);
        this.tearDown(node);
        return null;
    }
}
