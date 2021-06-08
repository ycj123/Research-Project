// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.List;
import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import javax.swing.tree.DefaultMutableTreeNode;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.ast.CodeVisitorSupport;

private class TreeNodeBuildingVisitor extends CodeVisitorSupport implements GroovyObject
{
    private DefaultMutableTreeNode currentNode;
    private Object adapter;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204251;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$FieldExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArrayExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$WhileStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$DoWhileStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BinaryExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$TupleExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClassExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$SpreadExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$GStringExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PostfixExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$AttributeExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$RegexExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$AssertStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$IfStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PrefixExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$classgen$BytecodeExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$CastExpression;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$RangeExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$BreakStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MapExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PropertyExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$NotExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$TernaryExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
    
    private TreeNodeBuildingVisitor(final Object adapter) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        if (!DefaultTypeTransformation.booleanUnbox(adapter)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: adapter");
        }
        this.adapter = adapter;
    }
    
    private void addNode(final Object node, final Class expectedSubclass, final Closure superMethod) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[1].call(expectedSubclass), $getCallSiteArray[2].call($getCallSiteArray[3].call(node)))) {
            if (ScriptBytecodeAdapter.compareEqual(this.currentNode, null)) {
                this.currentNode = (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(this.adapter, node), $get$$class$javax$swing$tree$DefaultMutableTreeNode()), $get$$class$javax$swing$tree$DefaultMutableTreeNode());
                $getCallSiteArray[5].call(superMethod, node);
            }
            else {
                final DefaultMutableTreeNode temp = this.currentNode;
                this.currentNode = (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(this.adapter, node), $get$$class$javax$swing$tree$DefaultMutableTreeNode()), $get$$class$javax$swing$tree$DefaultMutableTreeNode());
                $getCallSiteArray[7].call(temp, this.currentNode);
                ScriptBytecodeAdapter.setProperty(temp, $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), this.currentNode, "parent");
                $getCallSiteArray[8].call(superMethod, node);
                this.currentNode = (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType(temp, $get$$class$javax$swing$tree$DefaultMutableTreeNode());
            }
        }
        else {
            $getCallSiteArray[9].call(superMethod, node);
        }
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement node) {
        $getCallSiteArray()[10].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$BlockStatement(), new TreeNodeBuildingVisitor$_visitBlockStatement_closure1(this, this));
    }
    
    @Override
    public void visitForLoop(final ForStatement node) {
        $getCallSiteArray()[11].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$ForStatement(), new TreeNodeBuildingVisitor$_visitForLoop_closure2(this, this));
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement node) {
        $getCallSiteArray()[12].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$WhileStatement(), new TreeNodeBuildingVisitor$_visitWhileLoop_closure3(this, this));
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement node) {
        $getCallSiteArray()[13].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$DoWhileStatement(), new TreeNodeBuildingVisitor$_visitDoWhileLoop_closure4(this, this));
    }
    
    @Override
    public void visitIfElse(final IfStatement node) {
        $getCallSiteArray()[14].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$IfStatement(), new TreeNodeBuildingVisitor$_visitIfElse_closure5(this, this));
    }
    
    @Override
    public void visitExpressionStatement(final ExpressionStatement node) {
        $getCallSiteArray()[15].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$ExpressionStatement(), new TreeNodeBuildingVisitor$_visitExpressionStatement_closure6(this, this));
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement node) {
        $getCallSiteArray()[16].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$ReturnStatement(), new TreeNodeBuildingVisitor$_visitReturnStatement_closure7(this, this));
    }
    
    @Override
    public void visitAssertStatement(final AssertStatement node) {
        $getCallSiteArray()[17].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$AssertStatement(), new TreeNodeBuildingVisitor$_visitAssertStatement_closure8(this, this));
    }
    
    @Override
    public void visitTryCatchFinally(final TryCatchStatement node) {
        $getCallSiteArray()[18].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$TryCatchStatement(), new TreeNodeBuildingVisitor$_visitTryCatchFinally_closure9(this, this));
    }
    
    @Override
    protected void visitEmptyStatement(final EmptyStatement node) {
        $getCallSiteArray()[19].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$EmptyStatement(), new TreeNodeBuildingVisitor$_visitEmptyStatement_closure10(this, this));
    }
    
    @Override
    public void visitSwitch(final SwitchStatement node) {
        $getCallSiteArray()[20].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement(), new TreeNodeBuildingVisitor$_visitSwitch_closure11(this, this));
    }
    
    @Override
    public void visitCaseStatement(final CaseStatement node) {
        $getCallSiteArray()[21].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement(), new TreeNodeBuildingVisitor$_visitCaseStatement_closure12(this, this));
    }
    
    @Override
    public void visitBreakStatement(final BreakStatement node) {
        $getCallSiteArray()[22].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$BreakStatement(), new TreeNodeBuildingVisitor$_visitBreakStatement_closure13(this, this));
    }
    
    @Override
    public void visitContinueStatement(final ContinueStatement node) {
        $getCallSiteArray()[23].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$ContinueStatement(), new TreeNodeBuildingVisitor$_visitContinueStatement_closure14(this, this));
    }
    
    @Override
    public void visitSynchronizedStatement(final SynchronizedStatement node) {
        $getCallSiteArray()[24].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement(), new TreeNodeBuildingVisitor$_visitSynchronizedStatement_closure15(this, this));
    }
    
    @Override
    public void visitThrowStatement(final ThrowStatement node) {
        $getCallSiteArray()[25].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$ThrowStatement(), new TreeNodeBuildingVisitor$_visitThrowStatement_closure16(this, this));
    }
    
    @Override
    public void visitMethodCallExpression(final MethodCallExpression node) {
        $getCallSiteArray()[26].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$MethodCallExpression(), new TreeNodeBuildingVisitor$_visitMethodCallExpression_closure17(this, this));
    }
    
    @Override
    public void visitStaticMethodCallExpression(final StaticMethodCallExpression node) {
        $getCallSiteArray()[27].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression(), new TreeNodeBuildingVisitor$_visitStaticMethodCallExpression_closure18(this, this));
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression node) {
        $getCallSiteArray()[28].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression(), new TreeNodeBuildingVisitor$_visitConstructorCallExpression_closure19(this, this));
    }
    
    @Override
    public void visitBinaryExpression(final BinaryExpression node) {
        $getCallSiteArray()[29].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$BinaryExpression(), new TreeNodeBuildingVisitor$_visitBinaryExpression_closure20(this, this));
    }
    
    @Override
    public void visitTernaryExpression(final TernaryExpression node) {
        $getCallSiteArray()[30].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$TernaryExpression(), new TreeNodeBuildingVisitor$_visitTernaryExpression_closure21(this, this));
    }
    
    @Override
    public void visitShortTernaryExpression(final ElvisOperatorExpression node) {
        $getCallSiteArray()[31].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression(), new TreeNodeBuildingVisitor$_visitShortTernaryExpression_closure22(this, this));
    }
    
    @Override
    public void visitPostfixExpression(final PostfixExpression node) {
        $getCallSiteArray()[32].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$PostfixExpression(), new TreeNodeBuildingVisitor$_visitPostfixExpression_closure23(this, this));
    }
    
    @Override
    public void visitPrefixExpression(final PrefixExpression node) {
        $getCallSiteArray()[33].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$PrefixExpression(), new TreeNodeBuildingVisitor$_visitPrefixExpression_closure24(this, this));
    }
    
    @Override
    public void visitBooleanExpression(final BooleanExpression node) {
        $getCallSiteArray()[34].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), new TreeNodeBuildingVisitor$_visitBooleanExpression_closure25(this, this));
    }
    
    @Override
    public void visitNotExpression(final NotExpression node) {
        $getCallSiteArray()[35].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$NotExpression(), new TreeNodeBuildingVisitor$_visitNotExpression_closure26(this, this));
    }
    
    @Override
    public void visitClosureExpression(final ClosureExpression node) {
        $getCallSiteArray()[36].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression(), new TreeNodeBuildingVisitor$_visitClosureExpression_closure27(this, this));
    }
    
    public void visitParameter(final Parameter node) {
        final Parameter node2 = (Parameter)new Reference(node);
        $getCallSiteArray()[37].callCurrent(this, ((Reference<Object>)node2).get(), $get$$class$org$codehaus$groovy$ast$Parameter(), new TreeNodeBuildingVisitor$_visitParameter_closure28(this, this, (Reference<Object>)node2));
    }
    
    @Override
    public void visitTupleExpression(final TupleExpression node) {
        $getCallSiteArray()[38].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$TupleExpression(), new TreeNodeBuildingVisitor$_visitTupleExpression_closure29(this, this));
    }
    
    @Override
    public void visitListExpression(final ListExpression node) {
        $getCallSiteArray()[39].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ListExpression(), new TreeNodeBuildingVisitor$_visitListExpression_closure30(this, this));
    }
    
    @Override
    public void visitArrayExpression(final ArrayExpression node) {
        $getCallSiteArray()[40].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ArrayExpression(), new TreeNodeBuildingVisitor$_visitArrayExpression_closure31(this, this));
    }
    
    @Override
    public void visitMapExpression(final MapExpression node) {
        $getCallSiteArray()[41].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$MapExpression(), new TreeNodeBuildingVisitor$_visitMapExpression_closure32(this, this));
    }
    
    @Override
    public void visitMapEntryExpression(final MapEntryExpression node) {
        $getCallSiteArray()[42].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression(), new TreeNodeBuildingVisitor$_visitMapEntryExpression_closure33(this, this));
    }
    
    @Override
    public void visitRangeExpression(final RangeExpression node) {
        $getCallSiteArray()[43].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$RangeExpression(), new TreeNodeBuildingVisitor$_visitRangeExpression_closure34(this, this));
    }
    
    @Override
    public void visitSpreadExpression(final SpreadExpression node) {
        $getCallSiteArray()[44].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$SpreadExpression(), new TreeNodeBuildingVisitor$_visitSpreadExpression_closure35(this, this));
    }
    
    @Override
    public void visitSpreadMapExpression(final SpreadMapExpression node) {
        $getCallSiteArray()[45].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$SpreadMapExpression(), new TreeNodeBuildingVisitor$_visitSpreadMapExpression_closure36(this, this));
    }
    
    @Override
    public void visitMethodPointerExpression(final MethodPointerExpression node) {
        $getCallSiteArray()[46].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$MethodPointerExpression(), new TreeNodeBuildingVisitor$_visitMethodPointerExpression_closure37(this, this));
    }
    
    @Override
    public void visitUnaryMinusExpression(final UnaryMinusExpression node) {
        $getCallSiteArray()[47].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression(), new TreeNodeBuildingVisitor$_visitUnaryMinusExpression_closure38(this, this));
    }
    
    @Override
    public void visitUnaryPlusExpression(final UnaryPlusExpression node) {
        $getCallSiteArray()[48].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression(), new TreeNodeBuildingVisitor$_visitUnaryPlusExpression_closure39(this, this));
    }
    
    @Override
    public void visitBitwiseNegationExpression(final BitwiseNegationExpression node) {
        $getCallSiteArray()[49].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression(), new TreeNodeBuildingVisitor$_visitBitwiseNegationExpression_closure40(this, this));
    }
    
    @Override
    public void visitCastExpression(final CastExpression node) {
        $getCallSiteArray()[50].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$CastExpression(), new TreeNodeBuildingVisitor$_visitCastExpression_closure41(this, this));
    }
    
    @Override
    public void visitConstantExpression(final ConstantExpression node) {
        $getCallSiteArray()[51].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), new TreeNodeBuildingVisitor$_visitConstantExpression_closure42(this, this));
    }
    
    @Override
    public void visitClassExpression(final ClassExpression node) {
        $getCallSiteArray()[52].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ClassExpression(), new TreeNodeBuildingVisitor$_visitClassExpression_closure43(this, this));
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression node) {
        $getCallSiteArray()[53].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$VariableExpression(), new TreeNodeBuildingVisitor$_visitVariableExpression_closure44(this, this));
    }
    
    @Override
    public void visitDeclarationExpression(final DeclarationExpression node) {
        $getCallSiteArray()[54].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$DeclarationExpression(), new TreeNodeBuildingVisitor$_visitDeclarationExpression_closure45(this, this));
    }
    
    @Override
    public void visitPropertyExpression(final PropertyExpression node) {
        $getCallSiteArray()[55].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$PropertyExpression(), new TreeNodeBuildingVisitor$_visitPropertyExpression_closure46(this, this));
    }
    
    @Override
    public void visitAttributeExpression(final AttributeExpression node) {
        $getCallSiteArray()[56].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$AttributeExpression(), new TreeNodeBuildingVisitor$_visitAttributeExpression_closure47(this, this));
    }
    
    @Override
    public void visitFieldExpression(final FieldExpression node) {
        $getCallSiteArray()[57].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$FieldExpression(), new TreeNodeBuildingVisitor$_visitFieldExpression_closure48(this, this));
    }
    
    @Deprecated
    @Override
    public void visitRegexExpression(final RegexExpression node) {
        $getCallSiteArray()[58].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$RegexExpression(), new TreeNodeBuildingVisitor$_visitRegexExpression_closure49(this, this));
    }
    
    @Override
    public void visitGStringExpression(final GStringExpression node) {
        $getCallSiteArray()[59].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$GStringExpression(), new TreeNodeBuildingVisitor$_visitGStringExpression_closure50(this, this));
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement node) {
        $getCallSiteArray()[60].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement(), new TreeNodeBuildingVisitor$_visitCatchStatement_closure51(this, this));
    }
    
    @Override
    public void visitArgumentlistExpression(final ArgumentListExpression node) {
        $getCallSiteArray()[61].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression(), new TreeNodeBuildingVisitor$_visitArgumentlistExpression_closure52(this, this));
    }
    
    @Override
    public void visitClosureListExpression(final ClosureListExpression node) {
        $getCallSiteArray()[62].callCurrent(this, node, $get$$class$org$codehaus$groovy$ast$expr$ClosureListExpression(), new TreeNodeBuildingVisitor$_visitClosureListExpression_closure53(this, this));
    }
    
    @Override
    public void visitBytecodeExpression(final BytecodeExpression node) {
        $getCallSiteArray()[63].callCurrent(this, node, $get$$class$org$codehaus$groovy$classgen$BytecodeExpression(), new TreeNodeBuildingVisitor$_visitBytecodeExpression_closure54(this, this));
    }
    
    @Override
    protected void visitListOfExpressions(final List<? extends Expression> list) {
        $getCallSiteArray()[64].call(list, new TreeNodeBuildingVisitor$_visitListOfExpressions_closure55(this, this));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TreeNodeBuildingVisitor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TreeNodeBuildingVisitor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TreeNodeBuildingVisitor.__timeStamp__239_neverHappen1292524204251 = 0L;
        TreeNodeBuildingVisitor.__timeStamp = 1292524204251L;
    }
    
    public DefaultMutableTreeNode getCurrentNode() {
        return this.currentNode;
    }
    
    public void setCurrentNode(final DefaultMutableTreeNode currentNode) {
        this.currentNode = currentNode;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[65];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingVisitor.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingVisitor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingVisitor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
        if (($class$org$codehaus$groovy$ast$expr$MapEntryExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MapEntryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MapEntryExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MapEntryExpression = class$("org.codehaus.groovy.ast.expr.MapEntryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = TreeNodeBuildingVisitor.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (TreeNodeBuildingVisitor.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$VariableExpression() {
        Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
        if (($class$org$codehaus$groovy$ast$expr$VariableExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$VariableExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$VariableExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$VariableExpression = class$("org.codehaus.groovy.ast.expr.VariableExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$VariableExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$EmptyStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
        if (($class$org$codehaus$groovy$ast$stmt$EmptyStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$EmptyStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$EmptyStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$EmptyStatement = class$("org.codehaus.groovy.ast.stmt.EmptyStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$DeclarationExpression() {
        Class $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
        if (($class$org$codehaus$groovy$ast$expr$DeclarationExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$DeclarationExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$DeclarationExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$DeclarationExpression = class$("org.codehaus.groovy.ast.expr.DeclarationExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArgumentListExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArgumentListExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression = class$("org.codehaus.groovy.ast.expr.ArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$FieldExpression() {
        Class $class$org$codehaus$groovy$ast$expr$FieldExpression;
        if (($class$org$codehaus$groovy$ast$expr$FieldExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$FieldExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$FieldExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$FieldExpression = class$("org.codehaus.groovy.ast.expr.FieldExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$FieldExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstantExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstantExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ConstantExpression = class$("org.codehaus.groovy.ast.expr.ConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ForStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ForStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ForStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ForStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ForStatement = class$("org.codehaus.groovy.ast.stmt.ForStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ForStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MethodCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$MethodCallExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MethodCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MethodCallExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MethodCallExpression = class$("org.codehaus.groovy.ast.expr.MethodCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$SwitchStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$SwitchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$SwitchStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$SwitchStatement = class$("org.codehaus.groovy.ast.stmt.SwitchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
        if (($class$org$codehaus$groovy$ast$expr$BooleanExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BooleanExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BooleanExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BooleanExpression = class$("org.codehaus.groovy.ast.expr.BooleanExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BooleanExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArrayExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArrayExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArrayExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ArrayExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArrayExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ArrayExpression = class$("org.codehaus.groovy.ast.expr.ArrayExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArrayExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression() {
        Class $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
        if (($class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = class$("org.codehaus.groovy.ast.expr.UnaryPlusExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
        }
        return $class$org$codehaus$groovy$ast$Parameter;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = TreeNodeBuildingVisitor.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (TreeNodeBuildingVisitor.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$WhileStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$WhileStatement;
        if (($class$org$codehaus$groovy$ast$stmt$WhileStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$WhileStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$WhileStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$WhileStatement = class$("org.codehaus.groovy.ast.stmt.WhileStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$WhileStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$DoWhileStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$DoWhileStatement;
        if (($class$org$codehaus$groovy$ast$stmt$DoWhileStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$DoWhileStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$DoWhileStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$DoWhileStatement = class$("org.codehaus.groovy.ast.stmt.DoWhileStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$DoWhileStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClosureListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClosureListExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClosureListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClosureListExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClosureListExpression = class$("org.codehaus.groovy.ast.expr.ClosureListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BinaryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BinaryExpression;
        if (($class$org$codehaus$groovy$ast$expr$BinaryExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BinaryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BinaryExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BinaryExpression = class$("org.codehaus.groovy.ast.expr.BinaryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BinaryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ExpressionStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ExpressionStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ExpressionStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ExpressionStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ExpressionStatement = class$("org.codehaus.groovy.ast.stmt.ExpressionStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MethodPointerExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
        if (($class$org$codehaus$groovy$ast$expr$MethodPointerExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MethodPointerExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MethodPointerExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MethodPointerExpression = class$("org.codehaus.groovy.ast.expr.MethodPointerExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClosureExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClosureExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClosureExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClosureExpression = class$("org.codehaus.groovy.ast.expr.ClosureExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClosureExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CatchStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$CatchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CatchStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$CatchStatement = class$("org.codehaus.groovy.ast.stmt.CatchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CatchStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
        if (($class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = class$("org.codehaus.groovy.ast.expr.BitwiseNegationExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingVisitor.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingVisitor.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$TupleExpression() {
        Class $class$org$codehaus$groovy$ast$expr$TupleExpression;
        if (($class$org$codehaus$groovy$ast$expr$TupleExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$TupleExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$TupleExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$TupleExpression = class$("org.codehaus.groovy.ast.expr.TupleExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$TupleExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClassExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClassExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClassExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClassExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClassExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ClassExpression = class$("org.codehaus.groovy.ast.expr.ClassExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClassExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = class$("org.codehaus.groovy.ast.expr.ConstructorCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
        if (($class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = class$("org.codehaus.groovy.ast.expr.ElvisOperatorExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$SpreadExpression() {
        Class $class$org$codehaus$groovy$ast$expr$SpreadExpression;
        if (($class$org$codehaus$groovy$ast$expr$SpreadExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$SpreadExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$SpreadExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$SpreadExpression = class$("org.codehaus.groovy.ast.expr.SpreadExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$SpreadExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$TryCatchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$TryCatchStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$TryCatchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$TryCatchStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$TryCatchStatement = class$("org.codehaus.groovy.ast.stmt.TryCatchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = class$("org.codehaus.groovy.ast.expr.StaticMethodCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$SpreadMapExpression() {
        Class $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
        if (($class$org$codehaus$groovy$ast$expr$SpreadMapExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$SpreadMapExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$SpreadMapExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$SpreadMapExpression = class$("org.codehaus.groovy.ast.expr.SpreadMapExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ThrowStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ThrowStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ThrowStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ThrowStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ThrowStatement = class$("org.codehaus.groovy.ast.stmt.ThrowStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$GStringExpression() {
        Class $class$org$codehaus$groovy$ast$expr$GStringExpression;
        if (($class$org$codehaus$groovy$ast$expr$GStringExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$GStringExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$GStringExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$GStringExpression = class$("org.codehaus.groovy.ast.expr.GStringExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$GStringExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PostfixExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PostfixExpression;
        if (($class$org$codehaus$groovy$ast$expr$PostfixExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PostfixExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PostfixExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PostfixExpression = class$("org.codehaus.groovy.ast.expr.PostfixExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PostfixExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$AttributeExpression() {
        Class $class$org$codehaus$groovy$ast$expr$AttributeExpression;
        if (($class$org$codehaus$groovy$ast$expr$AttributeExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$AttributeExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$AttributeExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$AttributeExpression = class$("org.codehaus.groovy.ast.expr.AttributeExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$AttributeExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ReturnStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ReturnStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ReturnStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ReturnStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ReturnStatement = class$("org.codehaus.groovy.ast.stmt.ReturnStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$RegexExpression() {
        Class $class$org$codehaus$groovy$ast$expr$RegexExpression;
        if (($class$org$codehaus$groovy$ast$expr$RegexExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$RegexExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$RegexExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$RegexExpression = class$("org.codehaus.groovy.ast.expr.RegexExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$RegexExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ListExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ListExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$ListExpression = class$("org.codehaus.groovy.ast.expr.ListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$AssertStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$AssertStatement;
        if (($class$org$codehaus$groovy$ast$stmt$AssertStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$AssertStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$AssertStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$AssertStatement = class$("org.codehaus.groovy.ast.stmt.AssertStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$AssertStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
        if (($class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = class$("org.codehaus.groovy.ast.stmt.SynchronizedStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CaseStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$CaseStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CaseStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$CaseStatement = class$("org.codehaus.groovy.ast.stmt.CaseStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CaseStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$IfStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$IfStatement;
        if (($class$org$codehaus$groovy$ast$stmt$IfStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$IfStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$IfStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$IfStatement = class$("org.codehaus.groovy.ast.stmt.IfStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$IfStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ContinueStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ContinueStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ContinueStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ContinueStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$ContinueStatement = class$("org.codehaus.groovy.ast.stmt.ContinueStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PrefixExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PrefixExpression;
        if (($class$org$codehaus$groovy$ast$expr$PrefixExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PrefixExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PrefixExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PrefixExpression = class$("org.codehaus.groovy.ast.expr.PrefixExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PrefixExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$classgen$BytecodeExpression() {
        Class $class$org$codehaus$groovy$classgen$BytecodeExpression;
        if (($class$org$codehaus$groovy$classgen$BytecodeExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$classgen$BytecodeExpression) == null) {
            $class$org$codehaus$groovy$classgen$BytecodeExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$classgen$BytecodeExpression = class$("org.codehaus.groovy.classgen.BytecodeExpression"));
        }
        return $class$org$codehaus$groovy$classgen$BytecodeExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$CastExpression() {
        Class $class$org$codehaus$groovy$ast$expr$CastExpression;
        if (($class$org$codehaus$groovy$ast$expr$CastExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$CastExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$CastExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$CastExpression = class$("org.codehaus.groovy.ast.expr.CastExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$CastExpression;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TreeNodeBuildingVisitor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TreeNodeBuildingVisitor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$RangeExpression() {
        Class $class$org$codehaus$groovy$ast$expr$RangeExpression;
        if (($class$org$codehaus$groovy$ast$expr$RangeExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$RangeExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$RangeExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$RangeExpression = class$("org.codehaus.groovy.ast.expr.RangeExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$RangeExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$BlockStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$BlockStatement;
        if (($class$org$codehaus$groovy$ast$stmt$BlockStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$BlockStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$BlockStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$BlockStatement = class$("org.codehaus.groovy.ast.stmt.BlockStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$BlockStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$BreakStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$BreakStatement;
        if (($class$org$codehaus$groovy$ast$stmt$BreakStatement = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$BreakStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$BreakStatement = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$stmt$BreakStatement = class$("org.codehaus.groovy.ast.stmt.BreakStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$BreakStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MapExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MapExpression;
        if (($class$org$codehaus$groovy$ast$expr$MapExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MapExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MapExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$MapExpression = class$("org.codehaus.groovy.ast.expr.MapExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MapExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PropertyExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PropertyExpression;
        if (($class$org$codehaus$groovy$ast$expr$PropertyExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PropertyExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PropertyExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$PropertyExpression = class$("org.codehaus.groovy.ast.expr.PropertyExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PropertyExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$NotExpression() {
        Class $class$org$codehaus$groovy$ast$expr$NotExpression;
        if (($class$org$codehaus$groovy$ast$expr$NotExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$NotExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$NotExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$NotExpression = class$("org.codehaus.groovy.ast.expr.NotExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$NotExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$TernaryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$TernaryExpression;
        if (($class$org$codehaus$groovy$ast$expr$TernaryExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$TernaryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$TernaryExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$TernaryExpression = class$("org.codehaus.groovy.ast.expr.TernaryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$TernaryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression() {
        Class $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
        if (($class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = (TreeNodeBuildingVisitor.$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = class$("org.codehaus.groovy.ast.expr.UnaryMinusExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
