// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.Stack;
import java.io.PrintStream;

public class SourcePrinter extends VisitorAdapter
{
    private final String[] tokenNames;
    private int tabLevel;
    private int lastLinePrinted;
    private final boolean newLines;
    protected final PrintStream out;
    private String className;
    private final Stack stack;
    private int stringConstructorCounter;
    
    public SourcePrinter(final PrintStream out, final String[] tokenNames) {
        this(out, tokenNames, true);
    }
    
    public SourcePrinter(final PrintStream out, final String[] tokenNames, final boolean newLines) {
        this.tokenNames = tokenNames;
        this.tabLevel = 0;
        this.lastLinePrinted = 0;
        this.out = out;
        this.newLines = newLines;
        this.stack = new Stack();
    }
    
    @Override
    public void visitAbstract(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "abstract ", null, null);
    }
    
    @Override
    public void visitAnnotation(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.print(t, visit, "@");
        }
        if (visit == 2) {
            this.print(t, visit, "(");
        }
        if (visit == 3) {
            this.print(t, visit, ", ");
        }
        if (visit == 4) {
            if (t.getNumberOfChildren() > 1) {
                this.print(t, visit, ") ");
            }
            else {
                this.print(t, visit, " ");
            }
        }
    }
    
    @Override
    public void visitAnnotations(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitAnnotationDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "@interface ", null, null);
    }
    
    @Override
    public void visitAnnotationFieldDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "() ", "default ", null);
    }
    
    @Override
    public void visitAnnotationMemberValuePair(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " = ", null, null);
    }
    
    @Override
    public void visitArrayDeclarator(final GroovySourceAST t, final int visit) {
        if (this.getParentNode().getType() == 12 || this.getParentNode().getType() == 22) {
            this.print(t, visit, null, null, "[]");
        }
        else {
            this.print(t, visit, "[", null, "]");
        }
    }
    
    @Override
    public void visitAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " = ", null, null);
    }
    
    @Override
    public void visitBand(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " & ", null, null);
    }
    
    @Override
    public void visitBandAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " &= ", null, null);
    }
    
    @Override
    public void visitBnot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "~", null, null);
    }
    
    @Override
    public void visitBor(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " | ", null, null);
    }
    
    @Override
    public void visitBorAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " |= ", null, null);
    }
    
    @Override
    public void visitBsr(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " >>> ", null, null);
    }
    
    @Override
    public void visitBsrAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " >>>= ", null, null);
    }
    
    @Override
    public void visitBxor(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " ^ ", null, null);
    }
    
    @Override
    public void visitBxorAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " ^= ", null, null);
    }
    
    @Override
    public void visitCaseGroup(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            ++this.tabLevel;
        }
        if (visit == 4) {
            --this.tabLevel;
        }
    }
    
    @Override
    public void visitClassDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "class ", null, null);
        if (visit == 1) {
            this.className = t.childOfType(84).getText();
        }
    }
    
    @Override
    public void visitClosedBlock(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "{", "-> ", "}");
    }
    
    @Override
    public void visitClosureList(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "(", "; ", ")");
    }
    
    @Override
    public void visitCompareTo(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " <=> ", null, null);
    }
    
    @Override
    public void visitCtorCall(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "this(", " ", ")");
    }
    
    @Override
    public void visitCtorIdent(final GroovySourceAST t, final int visit) {
        this.print(t, visit, this.className, null, null);
    }
    
    @Override
    public void visitDec(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "--", null, null);
    }
    
    @Override
    public void visitDiv(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " / ", null, null);
    }
    
    @Override
    public void visitDivAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " /= ", null, null);
    }
    
    @Override
    public void visitDot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, ".", null, null);
    }
    
    @Override
    public void visitDynamicMember(final GroovySourceAST t, final int visit) {
        if (t.childOfType(47) == null) {
            this.printUpdatingTabLevel(t, visit, "(", null, ")");
        }
    }
    
    @Override
    public void visitElist(final GroovySourceAST t, final int visit) {
        if (this.getParentNode().getType() == 61) {
            this.print(t, visit, "(", ", ", ")");
        }
        else {
            this.print(t, visit, null, ", ", null);
        }
    }
    
    @Override
    public void visitEnumConstantDef(final GroovySourceAST t, final int visit) {
        final GroovySourceAST sibling = (GroovySourceAST)t.getNextSibling();
        if (sibling != null && sibling.getType() == 61) {
            this.print(t, visit, null, null, ", ");
        }
    }
    
    @Override
    public void visitEnumDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "enum ", null, null);
    }
    
    @Override
    public void visitEqual(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " == ", null, null);
    }
    
    @Override
    public void visitExpr(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitExtendsClause(final GroovySourceAST t, final int visit) {
        if (visit == 1 && t.getNumberOfChildren() != 0) {
            this.print(t, visit, " extends ");
        }
    }
    
    @Override
    public void visitFinal(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "final ", null, null);
    }
    
    @Override
    public void visitForCondition(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " ; ", null, null);
    }
    
    @Override
    public void visitForInit(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "(", null, null);
    }
    
    @Override
    public void visitForInIterable(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "(", " in ", ") ");
    }
    
    @Override
    public void visitForIterator(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " ; ", null, ")");
    }
    
    @Override
    public void visitGe(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " >= ", null, null);
    }
    
    @Override
    public void visitGt(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " > ", null, null);
    }
    
    @Override
    public void visitIdent(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitImplementsClause(final GroovySourceAST t, final int visit) {
        if (visit == 1 && t.getNumberOfChildren() != 0) {
            this.print(t, visit, " implements ");
        }
        if (visit == 4) {
            this.print(t, visit, " ");
        }
    }
    
    @Override
    public void visitImplicitParameters(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitImport(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "import ", null, null);
    }
    
    @Override
    public void visitInc(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "++", null, null);
    }
    
    @Override
    public void visitIndexOp(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "[", null, "]");
    }
    
    @Override
    public void visitInterfaceDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "interface ", null, null);
    }
    
    @Override
    public void visitInstanceInit(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitLabeledArg(final GroovySourceAST t, final int visit) {
        this.print(t, visit, ":", null, null);
    }
    
    @Override
    public void visitLabeledStat(final GroovySourceAST t, final int visit) {
        this.print(t, visit, ":", null, null);
    }
    
    @Override
    public void visitLand(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " && ", null, null);
    }
    
    @Override
    public void visitLe(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " <= ", null, null);
    }
    
    @Override
    public void visitListConstructor(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "[", null, "]");
    }
    
    @Override
    public void visitLiteralAs(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " as ", null, null);
    }
    
    @Override
    public void visitLiteralAssert(final GroovySourceAST t, final int visit) {
        if (t.getNumberOfChildren() > 1) {
            this.print(t, visit, "assert ", null, " : ");
        }
        else {
            this.print(t, visit, "assert ", null, null);
        }
    }
    
    @Override
    public void visitLiteralBoolean(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "boolean", null, null);
    }
    
    @Override
    public void visitLiteralBreak(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "break ", null, null);
    }
    
    @Override
    public void visitLiteralByte(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "byte", null, null);
    }
    
    @Override
    public void visitLiteralCase(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "case ", null, ":");
    }
    
    @Override
    public void visitLiteralCatch(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, " catch (", null, ") ");
    }
    
    @Override
    public void visitLiteralChar(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "char", null, null);
    }
    
    @Override
    public void visitLiteralContinue(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "continue ", null, null);
    }
    
    @Override
    public void visitLiteralDefault(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "default", null, ":");
    }
    
    @Override
    public void visitLiteralDouble(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "double", null, null);
    }
    
    @Override
    public void visitLiteralFalse(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "false", null, null);
    }
    
    @Override
    public void visitLiteralFinally(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "finally ", null, null);
    }
    
    @Override
    public void visitLiteralFloat(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "float", null, null);
    }
    
    @Override
    public void visitLiteralFor(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "for ", null, null);
    }
    
    @Override
    public void visitLiteralIf(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "if (", " else ", ") ");
    }
    
    @Override
    public void visitLiteralIn(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " in ", null, null);
    }
    
    @Override
    public void visitLiteralInstanceof(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " instanceof ", null, null);
    }
    
    @Override
    public void visitLiteralInt(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "int", null, null);
    }
    
    @Override
    public void visitLiteralLong(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "long", null, null);
    }
    
    @Override
    public void visitLiteralNative(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "native ", null, null);
    }
    
    @Override
    public void visitLiteralNew(final GroovySourceAST t, final int visit) {
        if (t.childOfType(16) == null) {
            this.print(t, visit, "new ", "(", ")");
        }
        else {
            this.print(t, visit, "new ", null, null);
        }
    }
    
    @Override
    public void visitLiteralNull(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "null", null, null);
    }
    
    @Override
    public void visitLiteralPrivate(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "private ", null, null);
    }
    
    @Override
    public void visitLiteralProtected(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "protected ", null, null);
    }
    
    @Override
    public void visitLiteralPublic(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "public ", null, null);
    }
    
    @Override
    public void visitLiteralReturn(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "return ", null, null);
    }
    
    @Override
    public void visitLiteralShort(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "short", null, null);
    }
    
    @Override
    public void visitLiteralStatic(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "static ", null, null);
    }
    
    @Override
    public void visitLiteralSuper(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "super", null, null);
    }
    
    @Override
    public void visitLiteralSwitch(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.print(t, visit, "switch (");
            ++this.tabLevel;
        }
        if (visit == 3) {
            this.print(t, visit, ") {");
        }
        if (visit == 4) {
            --this.tabLevel;
            this.print(t, visit, "}");
        }
    }
    
    @Override
    public void visitLiteralSynchronized(final GroovySourceAST t, final int visit) {
        if (t.getNumberOfChildren() > 0) {
            this.print(t, visit, "synchronized (", null, ") ");
        }
        else {
            this.print(t, visit, "synchronized ", null, null);
        }
    }
    
    @Override
    public void visitLiteralThis(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "this", null, null);
    }
    
    @Override
    public void visitLiteralThreadsafe(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "threadsafe ", null, null);
    }
    
    @Override
    public void visitLiteralThrow(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "throw ", null, null);
    }
    
    @Override
    public void visitLiteralThrows(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "throws ", null, null);
    }
    
    @Override
    public void visitLiteralTransient(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "transient ", null, null);
    }
    
    @Override
    public void visitLiteralTrue(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "true", null, null);
    }
    
    @Override
    public void visitLiteralTry(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "try ", null, null);
    }
    
    @Override
    public void visitLiteralVoid(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "void", null, null);
    }
    
    @Override
    public void visitLiteralVolatile(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "volatile ", null, null);
    }
    
    @Override
    public void visitLiteralWhile(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "while (", null, ") ");
    }
    
    @Override
    public void visitLnot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "!", null, null);
    }
    
    @Override
    public void visitLor(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " || ", null, null);
    }
    
    @Override
    public void visitLt(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " < ", null, null);
    }
    
    @Override
    public void visitMapConstructor(final GroovySourceAST t, final int visit) {
        if (t.getNumberOfChildren() == 0) {
            this.print(t, visit, "[:]", null, null);
        }
        else {
            this.printUpdatingTabLevel(t, visit, "[", null, "]");
        }
    }
    
    @Override
    public void visitMemberPointer(final GroovySourceAST t, final int visit) {
        this.print(t, visit, ".&", null, null);
    }
    
    @Override
    public void visitMethodCall(final GroovySourceAST t, final int visit) {
        if ("<command>".equals(t.getText())) {
            this.printUpdatingTabLevel(t, visit, " ", " ", null);
        }
        else {
            this.printUpdatingTabLevel(t, visit, "(", " ", ")");
        }
    }
    
    @Override
    public void visitMethodDef(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitMinus(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " - ", null, null);
    }
    
    @Override
    public void visitMinusAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " -= ", null, null);
    }
    
    @Override
    public void visitMod(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " % ", null, null);
    }
    
    @Override
    public void visitModifiers(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitModAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " %= ", null, null);
    }
    
    @Override
    public void visitNotEqual(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " != ", null, null);
    }
    
    @Override
    public void visitNumBigDecimal(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitNumBigInt(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitNumDouble(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitNumInt(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitNumFloat(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitNumLong(final GroovySourceAST t, final int visit) {
        this.print(t, visit, t.getText(), null, null);
    }
    
    @Override
    public void visitObjblock(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            ++this.tabLevel;
            this.print(t, visit, "{");
        }
        else {
            --this.tabLevel;
            this.print(t, visit, "}");
        }
    }
    
    @Override
    public void visitOptionalDot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "?.", null, null);
    }
    
    @Override
    public void visitPackageDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "package ", null, null);
    }
    
    @Override
    public void visitParameterDef(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitParameters(final GroovySourceAST t, final int visit) {
        if (this.getParentNode().getType() == 49) {
            this.printUpdatingTabLevel(t, visit, null, ",", " ");
        }
        else {
            this.printUpdatingTabLevel(t, visit, "(", ", ", ") ");
        }
    }
    
    @Override
    public void visitPlus(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " + ", null, null);
    }
    
    @Override
    public void visitPlusAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " += ", null, null);
    }
    
    @Override
    public void visitPostDec(final GroovySourceAST t, final int visit) {
        this.print(t, visit, null, null, "--");
    }
    
    @Override
    public void visitPostInc(final GroovySourceAST t, final int visit) {
        this.print(t, visit, null, null, "++");
    }
    
    @Override
    public void visitQuestion(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "?", ":", null);
    }
    
    @Override
    public void visitRangeExclusive(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "..<", null, null);
    }
    
    @Override
    public void visitRangeInclusive(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "..", null, null);
    }
    
    @Override
    public void visitRegexFind(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " =~ ", null, null);
    }
    
    @Override
    public void visitRegexMatch(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " ==~ ", null, null);
    }
    
    @Override
    public void visitSelectSlot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "@", null, null);
    }
    
    @Override
    public void visitSl(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " << ", null, null);
    }
    
    @Override
    public void visitSlAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " <<= ", null, null);
    }
    
    @Override
    public void visitSlist(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            ++this.tabLevel;
            this.print(t, visit, "{");
        }
        else {
            --this.tabLevel;
            this.print(t, visit, "}");
        }
    }
    
    @Override
    public void visitSpreadArg(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "*", null, null);
    }
    
    @Override
    public void visitSpreadDot(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "*.", null, null);
    }
    
    @Override
    public void visitSpreadMapArg(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "*:", null, null);
    }
    
    @Override
    public void visitSr(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " >> ", null, null);
    }
    
    @Override
    public void visitSrAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " >>= ", null, null);
    }
    
    @Override
    public void visitStar(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "*", null, null);
    }
    
    @Override
    public void visitStarAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " *= ", null, null);
    }
    
    @Override
    public void visitStarStar(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "**", null, null);
    }
    
    @Override
    public void visitStarStarAssign(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " **= ", null, null);
    }
    
    @Override
    public void visitStaticInit(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "static ", null, null);
    }
    
    @Override
    public void visitStaticImport(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "import static ", null, null);
    }
    
    @Override
    public void visitStrictfp(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "strictfp ", null, null);
    }
    
    @Override
    public void visitStringConstructor(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.stringConstructorCounter = 0;
            this.print(t, visit, "\"");
        }
        if (visit == 3) {
            if (this.stringConstructorCounter % 2 == 0) {
                this.print(t, visit, "$");
            }
            ++this.stringConstructorCounter;
        }
        if (visit == 4) {
            this.print(t, visit, "\"");
        }
    }
    
    @Override
    public void visitStringLiteral(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            String theString = this.escape(t.getText());
            if (this.getParentNode().getType() != 53 && this.getParentNode().getType() != 47) {
                theString = "\"" + theString + "\"";
            }
            this.print(t, visit, theString);
        }
    }
    
    private String escape(String literal) {
        literal = literal.replaceAll("\n", "\\\\<<REMOVE>>n");
        literal = literal.replaceAll("<<REMOVE>>", "");
        return literal;
    }
    
    @Override
    public void visitSuperCtorCall(final GroovySourceAST t, final int visit) {
        this.printUpdatingTabLevel(t, visit, "super(", " ", ")");
    }
    
    @Override
    public void visitType(final GroovySourceAST t, final int visit) {
        final GroovySourceAST parent = this.getParentNode();
        final GroovySourceAST modifiers = parent.childOfType(5);
        if (modifiers == null || modifiers.getNumberOfChildren() == 0) {
            if (visit == 1 && t.getNumberOfChildren() == 0 && parent.getType() != 20) {
                this.print(t, visit, "def");
            }
            if (visit == 4 && (parent.getType() == 9 || parent.getType() == 8 || parent.getType() == 67 || (parent.getType() == 20 && t.getNumberOfChildren() != 0))) {
                this.print(t, visit, " ");
            }
        }
        else if (visit == 4 && t.getNumberOfChildren() != 0) {
            this.print(t, visit, " ");
        }
    }
    
    @Override
    public void visitTypeArgument(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitTypeArguments(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "<", ", ", ">");
    }
    
    @Override
    public void visitTypecast(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "(", null, ")");
    }
    
    @Override
    public void visitTypeLowerBounds(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " super ", " & ", null);
    }
    
    @Override
    public void visitTypeParameter(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitTypeParameters(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "<", ", ", ">");
    }
    
    @Override
    public void visitTypeUpperBounds(final GroovySourceAST t, final int visit) {
        this.print(t, visit, " extends ", " & ", null);
    }
    
    @Override
    public void visitUnaryMinus(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "-", null, null);
    }
    
    @Override
    public void visitUnaryPlus(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "+", null, null);
    }
    
    @Override
    public void visitVariableDef(final GroovySourceAST t, final int visit) {
    }
    
    @Override
    public void visitVariableParameterDef(final GroovySourceAST t, final int visit) {
        this.print(t, visit, null, "... ", null);
    }
    
    @Override
    public void visitWildcardType(final GroovySourceAST t, final int visit) {
        this.print(t, visit, "?", null, null);
    }
    
    @Override
    public void visitDefault(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.print(t, visit, "<" + this.tokenNames[t.getType()] + ">");
        }
        else {
            this.print(t, visit, "</" + this.tokenNames[t.getType()] + ">");
        }
    }
    
    protected void printUpdatingTabLevel(final GroovySourceAST t, final int visit, final String opening, final String subsequent, final String closing) {
        if (visit == 1 && opening != null) {
            this.print(t, visit, opening);
            ++this.tabLevel;
        }
        if (visit == 3 && subsequent != null) {
            this.print(t, visit, subsequent);
        }
        if (visit == 4 && closing != null) {
            --this.tabLevel;
            this.print(t, visit, closing);
        }
    }
    
    protected void print(final GroovySourceAST t, final int visit, final String opening, final String subsequent, final String closing) {
        if (visit == 1 && opening != null) {
            this.print(t, visit, opening);
        }
        if (visit == 3 && subsequent != null) {
            this.print(t, visit, subsequent);
        }
        if (visit == 4 && closing != null) {
            this.print(t, visit, closing);
        }
    }
    
    protected void print(final GroovySourceAST t, final int visit, final String value) {
        if (visit == 1) {
            this.printNewlineAndIndent(t, visit);
        }
        if (visit == 4) {
            this.printNewlineAndIndent(t, visit);
        }
        this.out.print(value);
    }
    
    protected void printNewlineAndIndent(final GroovySourceAST t, final int visit) {
        final int currentLine = t.getLine();
        if (this.lastLinePrinted == 0) {
            this.lastLinePrinted = currentLine;
        }
        if (this.lastLinePrinted != currentLine) {
            if (this.newLines && (visit != 1 || t.getType() != 7)) {
                for (int i = this.lastLinePrinted; i < currentLine; ++i) {
                    this.out.println();
                }
                if (this.lastLinePrinted > currentLine) {
                    this.out.println();
                    this.lastLinePrinted = currentLine;
                }
                if (visit == 1 || (visit == 4 && this.lastLinePrinted > currentLine)) {
                    for (int i = 0; i < this.tabLevel; ++i) {
                        this.out.print("    ");
                    }
                }
            }
            this.lastLinePrinted = Math.max(currentLine, this.lastLinePrinted);
        }
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
}
