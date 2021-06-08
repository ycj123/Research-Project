// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.MethodClosure;
import groovy.lang.Range;
import java.util.Map;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import org.codehaus.groovy.control.CompilePhase;
import groovy.lang.PackageScope;
import groovy.lang.GroovyInterceptable;

@PackageScope
public class AstSpecificationCompiler implements GroovyInterceptable
{
    private boolean returnScriptBodyOnly;
    private CompilePhase phase;
    private String source;
    private final List<ASTNode> expression;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203783;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$syntax$Token;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ImportNode;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$FieldExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$AnnotationNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$GenericsType;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$WhileStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$org$codehaus$groovy$syntax$Types;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BinaryExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$Expression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$DynamicVariable;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$Statement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$TupleExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ClassExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$FieldNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$SpreadExpression;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$GStringExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PostfixExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$AttributeExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$RegexExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$IfStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PrefixExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$CastExpression;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$Parameter;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$RangeExpression;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MapExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$BreakStatement;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$PropertyExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$NotExpression;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$TernaryExpression;
    
    public AstSpecificationCompiler(final Closure spec) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.returnScriptBodyOnly = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.expression = (List<ASTNode>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        ScriptBytecodeAdapter.setGroovyObjectProperty(this, $get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler(), spec, "delegate");
        $getCallSiteArray[0].call(spec);
    }
    
    public List<ASTNode> getExpression() {
        $getCallSiteArray();
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType(this.expression, $get$$class$java$util$List());
    }
    
    private List<ASTNode> enforceConstraints(final String methodName, final List<Class> spec) {
        final String methodName2 = (String)new Reference(methodName);
        final List spec2 = (List)new Reference(spec);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[1].call(((Reference)spec2).get()), $getCallSiteArray[2].call(this.expression))) {
            throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { ((Reference<Object>)methodName2).get(), ((Reference)spec2).get(), $getCallSiteArray[4].callSafe(this.expression, new AstSpecificationCompiler$_enforceConstraints_closure1(this, this)) }, new String[] { "", " could not be invoked. Expected to receive parameters ", " but found ", "" }));
        }
        return (List<ASTNode>)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(ScriptBytecodeAdapter.createRange(AstSpecificationCompiler.$const$0, $getCallSiteArray[6].call($getCallSiteArray[7].call(((Reference)spec2).get()), AstSpecificationCompiler.$const$1), true), new AstSpecificationCompiler$_enforceConstraints_closure2(this, this, (Reference<Object>)spec2, (Reference<Object>)methodName2)), $get$$class$java$util$List());
    }
    
    private void captureAndCreateNode(final String name, final Closure argBlock, final Closure constructorStatement) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(argBlock)) {
            throw (Throwable)$getCallSiteArray[8].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { name }, new String[] { "nodes of type ", " require arguments to be specified" }));
        }
        final Object oldProps = $getCallSiteArray[9].callConstructor($get$$class$java$util$ArrayList(), this.expression);
        $getCallSiteArray[10].call(this.expression);
        $getCallSiteArray[11].callConstructor($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler(), argBlock);
        final Object result = $getCallSiteArray[12].call(constructorStatement, this.expression);
        $getCallSiteArray[13].call(this.expression);
        $getCallSiteArray[14].call(this.expression, oldProps);
        $getCallSiteArray[15].call(this.expression, result);
    }
    
    private void makeNode(final Class target, final String typeAlias, final List<Class<? super ASTNode>> ctorArgs, final Closure argBlock) {
        final Class target2 = (Class)new Reference(target);
        final String typeAlias2 = (String)new Reference(typeAlias);
        final List ctorArgs2 = (List)new Reference(ctorArgs);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[16].callCurrent(this, $getCallSiteArray[17].callGetProperty($getCallSiteArray[18].callGetProperty(((Reference)target2).get())), argBlock, new AstSpecificationCompiler$_makeNode_closure3(this, this, (Reference<Object>)ctorArgs2, (Reference<Object>)typeAlias2, (Reference<Object>)target2));
    }
    
    private void makeNodeFromList(final Class target, final Closure argBlock) {
        final Class target2 = (Class)new Reference(target);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[19].callCurrent(this, $getCallSiteArray[20].callGetProperty(((Reference)target2).get()), argBlock, new AstSpecificationCompiler$_makeNodeFromList_closure4(this, this, (Reference<Object>)target2));
    }
    
    private void makeListOfNodes(final Closure argBlock, final String input) {
        $getCallSiteArray()[21].callCurrent(this, input, argBlock, new AstSpecificationCompiler$_makeListOfNodes_closure5(this, this));
    }
    
    private void makeArrayOfNodes(final Object target, final Closure argBlock) {
        final Object target2 = new Reference(target);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[22].callCurrent(this, $getCallSiteArray[23].callGetProperty($getCallSiteArray[24].callGetProperty(((Reference<Object>)target2).get())), argBlock, new AstSpecificationCompiler$_makeArrayOfNodes_closure6(this, this, (Reference<Object>)target2));
    }
    
    private void makeNodeWithClassParameter(final Class target, final String alias, final List<Class> spec, final Closure argBlock, final Class type) {
        final Class target2 = (Class)new Reference(target);
        final String alias2 = (String)new Reference(alias);
        final List spec2 = (List)new Reference(spec);
        final Class type2 = (Class)new Reference(type);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[25].callCurrent(this, $getCallSiteArray[26].callGetProperty($getCallSiteArray[27].callGetProperty(((Reference)target2).get())), argBlock, new AstSpecificationCompiler$_makeNodeWithClassParameter_closure7(this, this, (Reference<Object>)spec2, (Reference<Object>)alias2, (Reference<Object>)target2, (Reference<Object>)type2));
    }
    
    private void makeNodeWithStringParameter(final Class target, final String alias, final List<Class> spec, final Closure argBlock, final String text) {
        final Class target2 = (Class)new Reference(target);
        final String alias2 = (String)new Reference(alias);
        final List spec2 = (List)new Reference(spec);
        final String text2 = (String)new Reference(text);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[28].callCurrent(this, $getCallSiteArray[29].callGetProperty($getCallSiteArray[30].callGetProperty(((Reference)target2).get())), argBlock, new AstSpecificationCompiler$_makeNodeWithStringParameter_closure8(this, this, (Reference<Object>)spec2, (Reference<Object>)text2, (Reference<Object>)alias2, (Reference<Object>)target2));
    }
    
    private void cast(final Class type, final Closure argBlock) {
        $getCallSiteArray()[31].callCurrent(this, ArrayUtil.createArray($get$$class$org$codehaus$groovy$ast$expr$CastExpression(), "cast", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock, type));
    }
    
    private void constructorCall(final Class type, final Closure argBlock) {
        $getCallSiteArray()[32].callCurrent(this, ArrayUtil.createArray($get$$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression(), "constructorCall", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock, type));
    }
    
    private void methodCall(final Closure argBlock) {
        $getCallSiteArray()[33].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$MethodCallExpression(), "methodCall", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void annotationConstant(final Closure argBlock) {
        $getCallSiteArray()[34].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression(), "annotationConstant", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$AnnotationNode() }), argBlock);
    }
    
    private void postfix(final Closure argBlock) {
        $getCallSiteArray()[35].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$PostfixExpression(), "postfix", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$syntax$Token() }), argBlock);
    }
    
    private void field(final Closure argBlock) {
        $getCallSiteArray()[36].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$FieldExpression(), "field", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$FieldNode() }), argBlock);
    }
    
    private void map(final Closure argBlock) {
        $getCallSiteArray()[37].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$MapExpression(), argBlock);
    }
    
    private void tuple(final Closure argBlock) {
        $getCallSiteArray()[38].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$TupleExpression(), argBlock);
    }
    
    private void mapEntry(final Closure argBlock) {
        $getCallSiteArray()[39].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression(), "mapEntry", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void gString(final String verbatimText, final Closure argBlock) {
        $getCallSiteArray()[40].callCurrent(this, ArrayUtil.createArray($get$$class$org$codehaus$groovy$ast$expr$GStringExpression(), "gString", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$java$lang$String(), $get$$class$java$util$List(), $get$$class$java$util$List() }), argBlock, verbatimText));
    }
    
    private void methodPointer(final Closure argBlock) {
        $getCallSiteArray()[41].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$MethodPointerExpression(), "methodPointer", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void property(final Closure argBlock) {
        $getCallSiteArray()[42].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$PropertyExpression(), "property", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void range(final Closure argBlock) {
        $getCallSiteArray()[43].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$RangeExpression(), "range", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$java$lang$Boolean() }), argBlock);
    }
    
    private void empty() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[44].call(this.expression, $getCallSiteArray[45].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$EmptyStatement()));
    }
    
    private void label(final String label) {
        $getCallSiteArray()[46].call(this.expression, label);
    }
    
    private void importNode(final Class target, final String alias) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[47].call(this.expression, $getCallSiteArray[48].callConstructor($get$$class$org$codehaus$groovy$ast$ImportNode(), $getCallSiteArray[49].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), target), alias));
    }
    
    private void catchStatement(final Closure argBlock) {
        $getCallSiteArray()[50].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement(), "catchStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$Parameter(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void throwStatement(final Closure argBlock) {
        $getCallSiteArray()[51].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$ThrowStatement(), "throwStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void synchronizedStatement(final Closure argBlock) {
        $getCallSiteArray()[52].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement(), "synchronizedStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void returnStatement(final Closure argBlock) {
        $getCallSiteArray()[53].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$ReturnStatement(), "returnStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void ternary(final Closure argBlock) {
        $getCallSiteArray()[54].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$TernaryExpression(), "ternary", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void elvisOperator(final Closure argBlock) {
        $getCallSiteArray()[55].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression(), "elvisOperator", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void breakStatement(final String label) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(label)) {
            $getCallSiteArray[56].call(this.expression, $getCallSiteArray[57].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$BreakStatement(), label));
        }
        else {
            $getCallSiteArray[58].call(this.expression, $getCallSiteArray[59].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$BreakStatement()));
        }
    }
    
    private void continueStatement(final Closure argBlock) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(argBlock)) {
            $getCallSiteArray[60].call(this.expression, $getCallSiteArray[61].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$ContinueStatement()));
        }
        else {
            $getCallSiteArray[62].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$ContinueStatement(), "continueStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$java$lang$String() }), argBlock);
        }
    }
    
    private void caseStatement(final Closure argBlock) {
        $getCallSiteArray()[63].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement(), "caseStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void defaultCase(final Closure argBlock) {
        $getCallSiteArray()[64].callCurrent(this, argBlock);
    }
    
    private void prefix(final Closure argBlock) {
        $getCallSiteArray()[65].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$PrefixExpression(), "prefix", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$syntax$Token(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void not(final Closure argBlock) {
        $getCallSiteArray()[66].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$NotExpression(), "not", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void dynamicVariable(final String variable, final boolean isStatic) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[67].call(this.expression, $getCallSiteArray[68].callConstructor($get$$class$org$codehaus$groovy$ast$DynamicVariable(), variable, DefaultTypeTransformation.box(isStatic)));
    }
    
    private void exceptions(final Closure argBlock) {
        $getCallSiteArray()[69].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[0]), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), argBlock);
    }
    
    private void annotations(final Closure argBlock) {
        $getCallSiteArray()[70].callCurrent(this, argBlock, "List<AnnotationNode>");
    }
    
    private void strings(final Closure argBlock) {
        $getCallSiteArray()[71].callCurrent(this, argBlock, "List<ConstantExpression>");
    }
    
    private void values(final Closure argBlock) {
        $getCallSiteArray()[72].callCurrent(this, argBlock, "List<Expression>");
    }
    
    private void inclusive(final boolean value) {
        $getCallSiteArray()[73].call(this.expression, DefaultTypeTransformation.box(value));
    }
    
    private void constant(final Object value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[74].call(this.expression, $getCallSiteArray[75].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), value));
    }
    
    private void ifStatement(final Closure argBlock) {
        $getCallSiteArray()[76].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$IfStatement(), "ifStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), $get$$class$org$codehaus$groovy$ast$stmt$Statement(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void spread(final Closure argBlock) {
        $getCallSiteArray()[77].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$SpreadExpression(), "spread", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void spreadMap(final Closure argBlock) {
        $getCallSiteArray()[78].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$SpreadMapExpression(), "spreadMap", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    @Deprecated
    private void regex(final Closure argBlock) {
        $getCallSiteArray()[79].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$RegexExpression(), "regex", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void whileStatement(final Closure argBlock) {
        $getCallSiteArray()[80].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$WhileStatement(), "whileStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void forStatement(final Closure argBlock) {
        $getCallSiteArray()[81].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$ForStatement(), "forStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$Parameter(), $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void closureList(final Closure argBlock) {
        $getCallSiteArray()[82].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$ClosureListExpression(), argBlock);
    }
    
    private void declaration(final Closure argBlock) {
        $getCallSiteArray()[83].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$DeclarationExpression(), "declaration", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$syntax$Token(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void list(final Closure argBlock) {
        $getCallSiteArray()[84].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$ListExpression(), argBlock);
    }
    
    private void bitwiseNegation(final Closure argBlock) {
        $getCallSiteArray()[85].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression(), "bitwiseNegation", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void closure(final Closure argBlock) {
        $getCallSiteArray()[86].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression(), "closure", ScriptBytecodeAdapter.createList(new Object[] { $get$array$$class$org$codehaus$groovy$ast$Parameter(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() }), argBlock);
    }
    
    private void booleanExpression(final Closure argBlock) {
        $getCallSiteArray()[87].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), "booleanExpression", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void binary(final Closure argBlock) {
        $getCallSiteArray()[88].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$BinaryExpression(), "binary", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$syntax$Token(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void unaryPlus(final Closure argBlock) {
        $getCallSiteArray()[89].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression(), "unaryPlus", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void classExpression(final Class type) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[90].call(this.expression, $getCallSiteArray[91].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ClassExpression(), $getCallSiteArray[92].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), type)));
    }
    
    private void unaryMinus(final Closure argBlock) {
        $getCallSiteArray()[93].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression(), "unaryMinus", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void attribute(final Closure argBlock) {
        $getCallSiteArray()[94].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$AttributeExpression(), "attribute", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void expression(final Closure argBlock) {
        $getCallSiteArray()[95].callCurrent(this, $get$$class$org$codehaus$groovy$ast$stmt$ExpressionStatement(), "expression", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$Expression() }), argBlock);
    }
    
    private void namedArgumentList(final Closure argBlock) {
        $getCallSiteArray()[96].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression(), argBlock);
    }
    
    private void interfaces(final Closure argBlock) {
        $getCallSiteArray()[97].callCurrent(this, argBlock, "List<ClassNode>");
    }
    
    private void mixins(final Closure argBlock) {
        $getCallSiteArray()[98].callCurrent(this, argBlock, "List<MixinNode>");
    }
    
    private void genericsTypes(final Closure argBlock) {
        $getCallSiteArray()[99].callCurrent(this, argBlock, "List<GenericsTypes>");
    }
    
    private void classNode(final Class target) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[100].call(this.expression, $getCallSiteArray[101].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), target, Boolean.FALSE));
    }
    
    private void parameters(final Closure argBlock) {
        $getCallSiteArray()[102].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[0]), $get$array$$class$org$codehaus$groovy$ast$Parameter()), $get$array$$class$org$codehaus$groovy$ast$Parameter()), argBlock);
    }
    
    private void block(final Closure argBlock) {
        $getCallSiteArray()[103].callCurrent(this, "BlockStatement", argBlock, new AstSpecificationCompiler$_block_closure9(this, this));
    }
    
    private void parameter(final Map<String, Class> args, final Closure argBlock) {
        final Closure argBlock2 = (Closure)new Reference(argBlock);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(args)) {
            throw (Throwable)$getCallSiteArray[104].callConstructor($get$$class$java$lang$IllegalArgumentException());
        }
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[105].call(args), AstSpecificationCompiler.$const$1)) {
            throw (Throwable)$getCallSiteArray[106].callConstructor($get$$class$java$lang$IllegalArgumentException());
        }
        if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)argBlock2).get())) {
            $getCallSiteArray[107].call(args, new AstSpecificationCompiler$_parameter_closure10(this, this, (Reference<Object>)argBlock2));
        }
        else {
            $getCallSiteArray[108].call(args, new AstSpecificationCompiler$_parameter_closure11(this, this));
        }
    }
    
    private void array(final Class type, final Closure argBlock) {
        final Class type2 = (Class)new Reference(type);
        $getCallSiteArray()[109].callCurrent(this, "ArrayExpression", argBlock, new AstSpecificationCompiler$_array_closure12(this, this, (Reference<Object>)type2));
    }
    
    private void genericsType(final Class type, final Closure argBlock) {
        final Class type2 = (Class)new Reference(type);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(argBlock)) {
            $getCallSiteArray[110].callCurrent(this, "GenericsType", argBlock, new AstSpecificationCompiler$_genericsType_closure13(this, this, (Reference<Object>)type2));
        }
        else {
            $getCallSiteArray[111].call(this.expression, $getCallSiteArray[112].callConstructor($get$$class$org$codehaus$groovy$ast$GenericsType(), $getCallSiteArray[113].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), ((Reference)type2).get())));
        }
    }
    
    private void upperBound(final Closure argBlock) {
        $getCallSiteArray()[114].callCurrent(this, argBlock, "List<ClassNode>");
    }
    
    private void lowerBound(final Class target) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[115].call(this.expression, $getCallSiteArray[116].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), target));
    }
    
    private void member(final String name, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        $getCallSiteArray()[117].callCurrent(this, "Annotation Member", argBlock, new AstSpecificationCompiler$_member_closure14(this, this, (Reference<Object>)name2));
    }
    
    private void argumentList(final Closure argBlock) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(argBlock)) {
            $getCallSiteArray[118].call(this.expression, $getCallSiteArray[119].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression()));
        }
        else {
            $getCallSiteArray[120].callCurrent(this, $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression(), argBlock);
        }
    }
    
    private void annotation(final Class target, final Closure argBlock) {
        final Class target2 = (Class)new Reference(target);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(argBlock)) {
            $getCallSiteArray[121].callCurrent(this, "ArgumentListExpression", argBlock, new AstSpecificationCompiler$_annotation_closure15(this, this, (Reference<Object>)target2));
        }
        else {
            $getCallSiteArray[122].call(this.expression, $getCallSiteArray[123].callConstructor($get$$class$org$codehaus$groovy$ast$AnnotationNode(), $getCallSiteArray[124].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), ((Reference)target2).get())));
        }
    }
    
    private void mixin(final String name, final int modifiers, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        $getCallSiteArray()[125].callCurrent(this, "AttributeExpression", argBlock, new AstSpecificationCompiler$_mixin_closure16(this, this, (Reference<Object>)name2, (Reference<Object>)modifiers2));
    }
    
    private void classNode(final String name, final int modifiers, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        $getCallSiteArray()[126].callCurrent(this, "ClassNode", argBlock, new AstSpecificationCompiler$_classNode_closure17(this, this, (Reference<Object>)name2, (Reference<Object>)modifiers2));
    }
    
    private void assertStatement(final Closure argBlock) {
        $getCallSiteArray()[127].callCurrent(this, "AssertStatement", argBlock, new AstSpecificationCompiler$_assertStatement_closure18(this, this));
    }
    
    private void tryCatch(final Closure argBlock) {
        $getCallSiteArray()[128].callCurrent(this, "TryCatchStatement", argBlock, new AstSpecificationCompiler$_tryCatch_closure19(this, this));
    }
    
    private void variable(final String variable) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[129].call(this.expression, $getCallSiteArray[130].callConstructor($get$$class$org$codehaus$groovy$ast$expr$VariableExpression(), variable));
    }
    
    private void method(final String name, final int modifiers, final Class returnType, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        final Class returnType2 = (Class)new Reference(returnType);
        $getCallSiteArray()[131].callCurrent(this, "MethodNode", argBlock, new AstSpecificationCompiler$_method_closure20(this, this, (Reference<Object>)name2, (Reference<Object>)modifiers2, (Reference<Object>)returnType2));
    }
    
    private void token(final String value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)$getCallSiteArray[132].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: value");
        }
        Object tokenID = $getCallSiteArray[133].call($get$$class$org$codehaus$groovy$syntax$Types(), value);
        if (ScriptBytecodeAdapter.compareEqual(tokenID, $getCallSiteArray[134].callGetProperty($get$$class$org$codehaus$groovy$syntax$Types()))) {
            tokenID = $getCallSiteArray[135].call($get$$class$org$codehaus$groovy$syntax$Types(), value);
        }
        if (ScriptBytecodeAdapter.compareEqual(tokenID, $getCallSiteArray[136].callGetProperty($get$$class$org$codehaus$groovy$syntax$Types()))) {
            throw (Throwable)$getCallSiteArray[137].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { value }, new String[] { "could not find token for ", "" }));
        }
        $getCallSiteArray[138].call(this.expression, $getCallSiteArray[139].callConstructor($get$$class$org$codehaus$groovy$syntax$Token(), tokenID, value, AstSpecificationCompiler.$const$2, AstSpecificationCompiler.$const$2));
    }
    
    private void range(final Range range) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(range, null)) {
            throw (Throwable)$getCallSiteArray[140].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: range");
        }
        $getCallSiteArray[141].call(this.expression, $getCallSiteArray[142].callConstructor($get$$class$org$codehaus$groovy$ast$expr$RangeExpression(), $getCallSiteArray[143].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), $getCallSiteArray[144].call(range)), $getCallSiteArray[145].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), $getCallSiteArray[146].call(range)), Boolean.TRUE));
    }
    
    private void switchStatement(final Closure argBlock) {
        $getCallSiteArray()[147].callCurrent(this, "SwitchStatement", argBlock, new AstSpecificationCompiler$_switchStatement_closure21(this, this));
    }
    
    private void mapEntry(final Map map) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[148].call($getCallSiteArray[149].call(map), new AstSpecificationCompiler$_mapEntry_closure22(this, this));
    }
    
    private void fieldNode(final String name, final int modifiers, final Class type, final Class owner, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        final Class type2 = (Class)new Reference(type);
        final Class owner2 = (Class)new Reference(owner);
        $getCallSiteArray()[150].callCurrent(this, "FieldNode", argBlock, new AstSpecificationCompiler$_fieldNode_closure23(this, this, (Reference<Object>)name2, (Reference<Object>)owner2, (Reference<Object>)modifiers2, (Reference<Object>)type2));
    }
    
    private void innerClass(final String name, final int modifiers, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        $getCallSiteArray()[151].callCurrent(this, "InnerClassNode", argBlock, new AstSpecificationCompiler$_innerClass_closure24(this, this, (Reference<Object>)name2, (Reference<Object>)modifiers2));
    }
    
    private void propertyNode(final String name, final int modifiers, final Class type, final Class owner, final Closure argBlock) {
        final String name2 = (String)new Reference(name);
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        final Class type2 = (Class)new Reference(type);
        final Class owner2 = (Class)new Reference(owner);
        $getCallSiteArray()[152].callCurrent(this, "PropertyNode", argBlock, new AstSpecificationCompiler$_propertyNode_closure25(this, this, (Reference<Object>)name2, (Reference<Object>)owner2, (Reference<Object>)type2, (Reference<Object>)modifiers2));
    }
    
    private void staticMethodCall(final Class target, final String name, final Closure argBlock) {
        final Class target2 = (Class)new Reference(target);
        final String name2 = (String)new Reference(name);
        $getCallSiteArray()[153].callCurrent(this, "StaticMethodCallExpression", argBlock, new AstSpecificationCompiler$_staticMethodCall_closure26(this, this, (Reference<Object>)name2, (Reference<Object>)target2));
    }
    
    private void staticMethodCall(final MethodClosure target, final Closure argBlock) {
        final MethodClosure target2 = (MethodClosure)new Reference(target);
        $getCallSiteArray()[154].callCurrent(this, "StaticMethodCallExpression", argBlock, new AstSpecificationCompiler$_staticMethodCall_closure27(this, this, (Reference<Object>)target2));
    }
    
    private void constructor(final int modifiers, final Closure argBlock) {
        final Integer modifiers2 = (Integer)new Reference(DefaultTypeTransformation.box(modifiers));
        $getCallSiteArray()[155].callCurrent(this, "ConstructorNode", argBlock, new AstSpecificationCompiler$_constructor_closure28(this, this, (Reference<Object>)modifiers2));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstSpecificationCompiler.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstSpecificationCompiler.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    private void importNode(final Class target) {
        $getCallSiteArray()[156].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(target, $get$$class$java$lang$Class()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String()), $get$$class$java$lang$String()));
    }
    
    private void breakStatement() {
        $getCallSiteArray()[157].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$String()), $get$$class$java$lang$String()));
    }
    
    private void continueStatement() {
        $getCallSiteArray()[158].callCurrent(this, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
    }
    
    private void dynamicVariable(final String variable) {
        $getCallSiteArray()[159].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(variable, $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.FALSE, Boolean.TYPE));
    }
    
    private void parameter(final Map<String, Class> args) {
        $getCallSiteArray()[160].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(args, $get$$class$java$util$Map()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
    }
    
    private void genericsType(final Class type) {
        final Class type2 = (Class)new Reference(type);
        $getCallSiteArray()[161].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(((Reference)type2).get(), $get$$class$java$lang$Class()), $get$$class$java$lang$Class()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
    }
    
    private void annotation(final Class target) {
        final Class target2 = (Class)new Reference(target);
        $getCallSiteArray()[162].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(((Reference)target2).get(), $get$$class$java$lang$Class()), $get$$class$java$lang$Class()), ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure()));
    }
    
    static {
        AstSpecificationCompiler.__timeStamp__239_neverHappen1292524203783 = 0L;
        AstSpecificationCompiler.__timeStamp = 1292524203783L;
        $const$2 = -1;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[163];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$syntax$Token() {
        Class $class$org$codehaus$groovy$syntax$Token;
        if (($class$org$codehaus$groovy$syntax$Token = AstSpecificationCompiler.$class$org$codehaus$groovy$syntax$Token) == null) {
            $class$org$codehaus$groovy$syntax$Token = (AstSpecificationCompiler.$class$org$codehaus$groovy$syntax$Token = class$("org.codehaus.groovy.syntax.Token"));
        }
        return $class$org$codehaus$groovy$syntax$Token;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
        if (($class$org$codehaus$groovy$ast$expr$MapEntryExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MapEntryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MapEntryExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MapEntryExpression = class$("org.codehaus.groovy.ast.expr.MapEntryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$VariableExpression() {
        Class $class$org$codehaus$groovy$ast$expr$VariableExpression;
        if (($class$org$codehaus$groovy$ast$expr$VariableExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$VariableExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$VariableExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$VariableExpression = class$("org.codehaus.groovy.ast.expr.VariableExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$VariableExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = AstSpecificationCompiler.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (AstSpecificationCompiler.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression = class$("org.codehaus.groovy.ast.expr.AnnotationConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$AnnotationConstantExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ImportNode() {
        Class $class$org$codehaus$groovy$ast$ImportNode;
        if (($class$org$codehaus$groovy$ast$ImportNode = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ImportNode) == null) {
            $class$org$codehaus$groovy$ast$ImportNode = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ImportNode = class$("org.codehaus.groovy.ast.ImportNode"));
        }
        return $class$org$codehaus$groovy$ast$ImportNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$EmptyStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
        if (($class$org$codehaus$groovy$ast$stmt$EmptyStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$EmptyStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$EmptyStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$EmptyStatement = class$("org.codehaus.groovy.ast.stmt.EmptyStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$EmptyStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$DeclarationExpression() {
        Class $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
        if (($class$org$codehaus$groovy$ast$expr$DeclarationExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$DeclarationExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$DeclarationExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$DeclarationExpression = class$("org.codehaus.groovy.ast.expr.DeclarationExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$DeclarationExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArgumentListExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArgumentListExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression = class$("org.codehaus.groovy.ast.expr.ArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$FieldExpression() {
        Class $class$org$codehaus$groovy$ast$expr$FieldExpression;
        if (($class$org$codehaus$groovy$ast$expr$FieldExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$FieldExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$FieldExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$FieldExpression = class$("org.codehaus.groovy.ast.expr.FieldExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$FieldExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstantExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstantExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ConstantExpression = class$("org.codehaus.groovy.ast.expr.ConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ForStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ForStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ForStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ForStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ForStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ForStatement = class$("org.codehaus.groovy.ast.stmt.ForStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ForStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler() {
        Class $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
        if (($class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler) == null) {
            $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler = class$("org.codehaus.groovy.ast.builder.AstSpecificationCompiler"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MethodCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$MethodCallExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MethodCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MethodCallExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MethodCallExpression = class$("org.codehaus.groovy.ast.expr.MethodCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MethodCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
        if (($class$org$codehaus$groovy$ast$expr$BooleanExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BooleanExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BooleanExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BooleanExpression = class$("org.codehaus.groovy.ast.expr.BooleanExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BooleanExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$AnnotationNode() {
        Class $class$org$codehaus$groovy$ast$AnnotationNode;
        if (($class$org$codehaus$groovy$ast$AnnotationNode = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$AnnotationNode) == null) {
            $class$org$codehaus$groovy$ast$AnnotationNode = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$AnnotationNode = class$("org.codehaus.groovy.ast.AnnotationNode"));
        }
        return $class$org$codehaus$groovy$ast$AnnotationNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression() {
        Class $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
        if (($class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$UnaryPlusExpression = class$("org.codehaus.groovy.ast.expr.UnaryPlusExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$UnaryPlusExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$GenericsType() {
        Class $class$org$codehaus$groovy$ast$GenericsType;
        if (($class$org$codehaus$groovy$ast$GenericsType = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$GenericsType) == null) {
            $class$org$codehaus$groovy$ast$GenericsType = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$GenericsType = class$("org.codehaus.groovy.ast.GenericsType"));
        }
        return $class$org$codehaus$groovy$ast$GenericsType;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
        }
        return $class$org$codehaus$groovy$ast$Parameter;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$WhileStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$WhileStatement;
        if (($class$org$codehaus$groovy$ast$stmt$WhileStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$WhileStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$WhileStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$WhileStatement = class$("org.codehaus.groovy.ast.stmt.WhileStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$WhileStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassNode() {
        Class $class$org$codehaus$groovy$ast$ClassNode;
        if (($class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ClassNode) == null) {
            $class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$ClassNode = class$("org.codehaus.groovy.ast.ClassNode"));
        }
        return $class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstSpecificationCompiler.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstSpecificationCompiler.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$syntax$Types() {
        Class $class$org$codehaus$groovy$syntax$Types;
        if (($class$org$codehaus$groovy$syntax$Types = AstSpecificationCompiler.$class$org$codehaus$groovy$syntax$Types) == null) {
            $class$org$codehaus$groovy$syntax$Types = (AstSpecificationCompiler.$class$org$codehaus$groovy$syntax$Types = class$("org.codehaus.groovy.syntax.Types"));
        }
        return $class$org$codehaus$groovy$syntax$Types;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClosureListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClosureListExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClosureListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClosureListExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClosureListExpression = class$("org.codehaus.groovy.ast.expr.ClosureListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClosureListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BinaryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BinaryExpression;
        if (($class$org$codehaus$groovy$ast$expr$BinaryExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BinaryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BinaryExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BinaryExpression = class$("org.codehaus.groovy.ast.expr.BinaryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BinaryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression = class$("org.codehaus.groovy.ast.expr.NamedArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$NamedArgumentListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ExpressionStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ExpressionStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ExpressionStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ExpressionStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ExpressionStatement = class$("org.codehaus.groovy.ast.stmt.ExpressionStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ExpressionStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MethodPointerExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
        if (($class$org$codehaus$groovy$ast$expr$MethodPointerExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MethodPointerExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MethodPointerExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MethodPointerExpression = class$("org.codehaus.groovy.ast.expr.MethodPointerExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MethodPointerExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$Expression() {
        Class $class$org$codehaus$groovy$ast$expr$Expression;
        if (($class$org$codehaus$groovy$ast$expr$Expression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$Expression) == null) {
            $class$org$codehaus$groovy$ast$expr$Expression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$Expression = class$("org.codehaus.groovy.ast.expr.Expression"));
        }
        return $class$org$codehaus$groovy$ast$expr$Expression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$DynamicVariable() {
        Class $class$org$codehaus$groovy$ast$DynamicVariable;
        if (($class$org$codehaus$groovy$ast$DynamicVariable = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$DynamicVariable) == null) {
            $class$org$codehaus$groovy$ast$DynamicVariable = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$DynamicVariable = class$("org.codehaus.groovy.ast.DynamicVariable"));
        }
        return $class$org$codehaus$groovy$ast$DynamicVariable;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClosureExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClosureExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClosureExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClosureExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClosureExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClosureExpression = class$("org.codehaus.groovy.ast.expr.ClosureExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClosureExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CatchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CatchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CatchStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$CatchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CatchStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$CatchStatement = class$("org.codehaus.groovy.ast.stmt.CatchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CatchStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$Statement() {
        Class $class$org$codehaus$groovy$ast$stmt$Statement;
        if (($class$org$codehaus$groovy$ast$stmt$Statement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$Statement) == null) {
            $class$org$codehaus$groovy$ast$stmt$Statement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$Statement = class$("org.codehaus.groovy.ast.stmt.Statement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$Statement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
        if (($class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression = class$("org.codehaus.groovy.ast.expr.BitwiseNegationExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BitwiseNegationExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$TupleExpression() {
        Class $class$org$codehaus$groovy$ast$expr$TupleExpression;
        if (($class$org$codehaus$groovy$ast$expr$TupleExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$TupleExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$TupleExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$TupleExpression = class$("org.codehaus.groovy.ast.expr.TupleExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$TupleExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ClassExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ClassExpression;
        if (($class$org$codehaus$groovy$ast$expr$ClassExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClassExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ClassExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ClassExpression = class$("org.codehaus.groovy.ast.expr.ClassExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ClassExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$FieldNode() {
        Class $class$org$codehaus$groovy$ast$FieldNode;
        if (($class$org$codehaus$groovy$ast$FieldNode = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$FieldNode) == null) {
            $class$org$codehaus$groovy$ast$FieldNode = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$FieldNode = class$("org.codehaus.groovy.ast.FieldNode"));
        }
        return $class$org$codehaus$groovy$ast$FieldNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ConstructorCallExpression = class$("org.codehaus.groovy.ast.expr.ConstructorCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstructorCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
        if (($class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression = class$("org.codehaus.groovy.ast.expr.ElvisOperatorExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ElvisOperatorExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstSpecificationCompiler.$class$java$util$List) == null) {
            $class$java$util$List = (AstSpecificationCompiler.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$SpreadExpression() {
        Class $class$org$codehaus$groovy$ast$expr$SpreadExpression;
        if (($class$org$codehaus$groovy$ast$expr$SpreadExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$SpreadExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$SpreadExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$SpreadExpression = class$("org.codehaus.groovy.ast.expr.SpreadExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$SpreadExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = AstSpecificationCompiler.$class$java$util$Map) == null) {
            $class$java$util$Map = (AstSpecificationCompiler.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$SpreadMapExpression() {
        Class $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
        if (($class$org$codehaus$groovy$ast$expr$SpreadMapExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$SpreadMapExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$SpreadMapExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$SpreadMapExpression = class$("org.codehaus.groovy.ast.expr.SpreadMapExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$SpreadMapExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ThrowStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ThrowStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ThrowStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ThrowStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ThrowStatement = class$("org.codehaus.groovy.ast.stmt.ThrowStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ThrowStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$GStringExpression() {
        Class $class$org$codehaus$groovy$ast$expr$GStringExpression;
        if (($class$org$codehaus$groovy$ast$expr$GStringExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$GStringExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$GStringExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$GStringExpression = class$("org.codehaus.groovy.ast.expr.GStringExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$GStringExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PostfixExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PostfixExpression;
        if (($class$org$codehaus$groovy$ast$expr$PostfixExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PostfixExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PostfixExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PostfixExpression = class$("org.codehaus.groovy.ast.expr.PostfixExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PostfixExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$AttributeExpression() {
        Class $class$org$codehaus$groovy$ast$expr$AttributeExpression;
        if (($class$org$codehaus$groovy$ast$expr$AttributeExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$AttributeExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$AttributeExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$AttributeExpression = class$("org.codehaus.groovy.ast.expr.AttributeExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$AttributeExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ReturnStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ReturnStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ReturnStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ReturnStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ReturnStatement = class$("org.codehaus.groovy.ast.stmt.ReturnStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ReturnStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ListExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ListExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$ListExpression = class$("org.codehaus.groovy.ast.expr.ListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$RegexExpression() {
        Class $class$org$codehaus$groovy$ast$expr$RegexExpression;
        if (($class$org$codehaus$groovy$ast$expr$RegexExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$RegexExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$RegexExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$RegexExpression = class$("org.codehaus.groovy.ast.expr.RegexExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$RegexExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
        if (($class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$SynchronizedStatement = class$("org.codehaus.groovy.ast.stmt.SynchronizedStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$SynchronizedStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$CaseStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$CaseStatement;
        if (($class$org$codehaus$groovy$ast$stmt$CaseStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$CaseStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$CaseStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$CaseStatement = class$("org.codehaus.groovy.ast.stmt.CaseStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$CaseStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$IfStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$IfStatement;
        if (($class$org$codehaus$groovy$ast$stmt$IfStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$IfStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$IfStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$IfStatement = class$("org.codehaus.groovy.ast.stmt.IfStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$IfStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$ContinueStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
        if (($class$org$codehaus$groovy$ast$stmt$ContinueStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ContinueStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$ContinueStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$ContinueStatement = class$("org.codehaus.groovy.ast.stmt.ContinueStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$ContinueStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PrefixExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PrefixExpression;
        if (($class$org$codehaus$groovy$ast$expr$PrefixExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PrefixExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PrefixExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PrefixExpression = class$("org.codehaus.groovy.ast.expr.PrefixExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PrefixExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$CastExpression() {
        Class $class$org$codehaus$groovy$ast$expr$CastExpression;
        if (($class$org$codehaus$groovy$ast$expr$CastExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$CastExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$CastExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$CastExpression = class$("org.codehaus.groovy.ast.expr.CastExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$CastExpression;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$Parameter() {
        Class array$$class$org$codehaus$groovy$ast$Parameter;
        if ((array$$class$org$codehaus$groovy$ast$Parameter = AstSpecificationCompiler.array$$class$org$codehaus$groovy$ast$Parameter) == null) {
            array$$class$org$codehaus$groovy$ast$Parameter = (AstSpecificationCompiler.array$$class$org$codehaus$groovy$ast$Parameter = class$("[Lorg.codehaus.groovy.ast.Parameter;"));
        }
        return array$$class$org$codehaus$groovy$ast$Parameter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstSpecificationCompiler.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstSpecificationCompiler.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$RangeExpression() {
        Class $class$org$codehaus$groovy$ast$expr$RangeExpression;
        if (($class$org$codehaus$groovy$ast$expr$RangeExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$RangeExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$RangeExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$RangeExpression = class$("org.codehaus.groovy.ast.expr.RangeExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$RangeExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstSpecificationCompiler.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstSpecificationCompiler.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MapExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MapExpression;
        if (($class$org$codehaus$groovy$ast$expr$MapExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MapExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MapExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$MapExpression = class$("org.codehaus.groovy.ast.expr.MapExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MapExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$BreakStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$BreakStatement;
        if (($class$org$codehaus$groovy$ast$stmt$BreakStatement = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$BreakStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$BreakStatement = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$stmt$BreakStatement = class$("org.codehaus.groovy.ast.stmt.BreakStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$BreakStatement;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$PropertyExpression() {
        Class $class$org$codehaus$groovy$ast$expr$PropertyExpression;
        if (($class$org$codehaus$groovy$ast$expr$PropertyExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PropertyExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$PropertyExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$PropertyExpression = class$("org.codehaus.groovy.ast.expr.PropertyExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$PropertyExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$NotExpression() {
        Class $class$org$codehaus$groovy$ast$expr$NotExpression;
        if (($class$org$codehaus$groovy$ast$expr$NotExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$NotExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$NotExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$NotExpression = class$("org.codehaus.groovy.ast.expr.NotExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$NotExpression;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$ClassNode() {
        Class array$$class$org$codehaus$groovy$ast$ClassNode;
        if ((array$$class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler.array$$class$org$codehaus$groovy$ast$ClassNode) == null) {
            array$$class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler.array$$class$org$codehaus$groovy$ast$ClassNode = class$("[Lorg.codehaus.groovy.ast.ClassNode;"));
        }
        return array$$class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression() {
        Class $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
        if (($class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$UnaryMinusExpression = class$("org.codehaus.groovy.ast.expr.UnaryMinusExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$UnaryMinusExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$TernaryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$TernaryExpression;
        if (($class$org$codehaus$groovy$ast$expr$TernaryExpression = AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$TernaryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$TernaryExpression = (AstSpecificationCompiler.$class$org$codehaus$groovy$ast$expr$TernaryExpression = class$("org.codehaus.groovy.ast.expr.TernaryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$TernaryExpression;
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
