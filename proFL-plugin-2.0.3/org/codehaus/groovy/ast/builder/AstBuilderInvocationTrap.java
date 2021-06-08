// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.ImportNode;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.io.ReaderSource;
import java.util.List;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.ast.CodeVisitorSupport;

private class AstBuilderInvocationTrap extends CodeVisitorSupport implements GroovyObject
{
    private final List<String> factoryTargets;
    private final ReaderSource source;
    private final SourceUnit sourceUnit;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204698;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$io$ReaderSource;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$SourceUnit;
    private static /* synthetic */ Class $class$org$codehaus$groovy$syntax$SyntaxException;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$messages$SyntaxErrorMessage;
    
    public AstBuilderInvocationTrap(final List<ImportNode> imports, final List<String> importPackages, final ReaderSource source, final SourceUnit sourceUnit) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.factoryTargets = (List<String>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        if (!DefaultTypeTransformation.booleanUnbox(source)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: source");
        }
        if (!DefaultTypeTransformation.booleanUnbox(sourceUnit)) {
            throw (Throwable)$getCallSiteArray[1].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: sourceUnit");
        }
        this.source = (ReaderSource)ScriptBytecodeAdapter.castToType(source, $get$$class$org$codehaus$groovy$control$io$ReaderSource());
        this.sourceUnit = (SourceUnit)ScriptBytecodeAdapter.castToType(sourceUnit, $get$$class$org$codehaus$groovy$control$SourceUnit());
        $getCallSiteArray[2].call(this.factoryTargets, "org.codehaus.groovy.ast.builder.AstBuilder");
        $getCallSiteArray[3].callSafe(imports, new AstBuilderInvocationTrap$_closure1(this, this));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(importPackages, "org.codehaus.groovy.ast.builder."))) {
            $getCallSiteArray[5].call(this.factoryTargets, "AstBuilder");
        }
    }
    
    private void addError(final String msg, final ASTNode expr) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Integer line = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[6].call(expr), $get$$class$java$lang$Integer());
        final Integer col = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[7].call(expr), $get$$class$java$lang$Integer());
        $getCallSiteArray[8].call($getCallSiteArray[9].call(this.sourceUnit), $getCallSiteArray[10].callConstructor($get$$class$org$codehaus$groovy$control$messages$SyntaxErrorMessage(), $getCallSiteArray[11].callConstructor($get$$class$org$codehaus$groovy$syntax$SyntaxException(), $getCallSiteArray[12].call(msg, "\n"), line, col), this.sourceUnit));
    }
    
    @Override
    public void visitMethodCallExpression(final MethodCallExpression call) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].callCurrent(this, call))) {
            final Object closureExpression = $getCallSiteArray[14].callSafe($getCallSiteArray[15].callGetProperty($getCallSiteArray[16].callGetProperty(call)), new AstBuilderInvocationTrap$_visitMethodCallExpression_closure2(this, this));
            final Object otherArgs = $getCallSiteArray[17].callSafe($getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callGetProperty(call)), new AstBuilderInvocationTrap$_visitMethodCallExpression_closure3(this, this));
            final String source = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[20].callCurrent(this, closureExpression), $get$$class$java$lang$String());
            $getCallSiteArray[21].call(otherArgs, $getCallSiteArray[22].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), source));
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[23].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression(), otherArgs), $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), call, "arguments");
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[24].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), "buildFromBlock"), $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), call, "method");
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), call, "spreadSafe");
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), call, "safe");
            ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), call, "implicitThis");
        }
        else {
            $getCallSiteArray[25].call($getCallSiteArray[26].call(call), this);
            $getCallSiteArray[27].call($getCallSiteArray[28].call(call), this);
            $getCallSiteArray[29].call($getCallSiteArray[30].call(call), this);
        }
    }
    
    private boolean isBuildInvocation(final MethodCallExpression call) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(call, null)) {
            throw (Throwable)$getCallSiteArray[31].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: call");
        }
        if (DefaultTypeTransformation.booleanUnbox(($getCallSiteArray[32].callGetProperty(call) instanceof ConstantExpression && ScriptBytecodeAdapter.compareEqual("buildFromCode", $getCallSiteArray[33].callGetPropertySafe($getCallSiteArray[34].callGetProperty(call)))) ? Boolean.TRUE : Boolean.FALSE)) {
            final String name = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[35].callGetPropertySafe($getCallSiteArray[36].callGetPropertySafe($getCallSiteArray[37].callGetProperty(call))), $get$$class$java$lang$String());
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(name) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[38].call(this.factoryTargets, name))) ? Boolean.TRUE : Boolean.FALSE) && (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[39].callGetProperty(call)) && $getCallSiteArray[40].callGetProperty(call) instanceof TupleExpression) ? Boolean.TRUE : Boolean.FALSE) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[41].callSafe($getCallSiteArray[42].callGetProperty($getCallSiteArray[43].callGetProperty(call)), new AstBuilderInvocationTrap$_isBuildInvocation_closure4(this, this))))) {
                return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
            }
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    private String convertClosureToSource(final ClosureExpression expression) {
        final ClosureExpression expression2 = (ClosureExpression)new Reference(expression);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)expression2).get(), null)) {
            throw (Throwable)$getCallSiteArray[44].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: expression");
        }
        final Object lineRange = ScriptBytecodeAdapter.createRange($getCallSiteArray[45].callGetProperty(((Reference<Object>)expression2).get()), $getCallSiteArray[46].callGetProperty(((Reference<Object>)expression2).get()), true);
        final Object source = $getCallSiteArray[47].callSafe($getCallSiteArray[48].callSafe($getCallSiteArray[49].call(lineRange, new AstBuilderInvocationTrap$_convertClosureToSource_closure5(this, this, (Reference<Object>)expression2)), "\n"));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[50].call(source, "{"))) {
            $getCallSiteArray[51].callCurrent(this, $getCallSiteArray[52].call("Error converting ClosureExpression into source code. ", new GStringImpl(new Object[] { source }, new String[] { "Closures must start with {. Found: ", "" })), ((Reference<Object>)expression2).get());
        }
        return (String)ScriptBytecodeAdapter.castToType(source, $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstBuilderInvocationTrap.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstBuilderInvocationTrap.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AstBuilderInvocationTrap.__timeStamp__239_neverHappen1292524204698 = 0L;
        AstBuilderInvocationTrap.__timeStamp = 1292524204698L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[53];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBuilderInvocationTrap.$callSiteArray == null || ($createCallSiteArray = AstBuilderInvocationTrap.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBuilderInvocationTrap.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = AstBuilderInvocationTrap.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (AstBuilderInvocationTrap.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstBuilderInvocationTrap.$class$java$util$List) == null) {
            $class$java$util$List = (AstBuilderInvocationTrap.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstBuilderInvocationTrap.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstBuilderInvocationTrap.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap() {
        Class $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
        if (($class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap) == null) {
            $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap = class$("org.codehaus.groovy.ast.builder.AstBuilderInvocationTrap"));
        }
        return $class$org$codehaus$groovy$ast$builder$AstBuilderInvocationTrap;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArgumentListExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArgumentListExpression = AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArgumentListExpression = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$expr$ArgumentListExpression = class$("org.codehaus.groovy.ast.expr.ArgumentListExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArgumentListExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstantExpression = AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$expr$ConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstantExpression = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$ast$expr$ConstantExpression = class$("org.codehaus.groovy.ast.expr.ConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstBuilderInvocationTrap.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstBuilderInvocationTrap.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstBuilderInvocationTrap.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstBuilderInvocationTrap.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$io$ReaderSource() {
        Class $class$org$codehaus$groovy$control$io$ReaderSource;
        if (($class$org$codehaus$groovy$control$io$ReaderSource = AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$io$ReaderSource) == null) {
            $class$org$codehaus$groovy$control$io$ReaderSource = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$io$ReaderSource = class$("org.codehaus.groovy.control.io.ReaderSource"));
        }
        return $class$org$codehaus$groovy$control$io$ReaderSource;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstBuilderInvocationTrap.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstBuilderInvocationTrap.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$SourceUnit() {
        Class $class$org$codehaus$groovy$control$SourceUnit;
        if (($class$org$codehaus$groovy$control$SourceUnit = AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$SourceUnit) == null) {
            $class$org$codehaus$groovy$control$SourceUnit = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$SourceUnit = class$("org.codehaus.groovy.control.SourceUnit"));
        }
        return $class$org$codehaus$groovy$control$SourceUnit;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$syntax$SyntaxException() {
        Class $class$org$codehaus$groovy$syntax$SyntaxException;
        if (($class$org$codehaus$groovy$syntax$SyntaxException = AstBuilderInvocationTrap.$class$org$codehaus$groovy$syntax$SyntaxException) == null) {
            $class$org$codehaus$groovy$syntax$SyntaxException = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$syntax$SyntaxException = class$("org.codehaus.groovy.syntax.SyntaxException"));
        }
        return $class$org$codehaus$groovy$syntax$SyntaxException;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$messages$SyntaxErrorMessage() {
        Class $class$org$codehaus$groovy$control$messages$SyntaxErrorMessage;
        if (($class$org$codehaus$groovy$control$messages$SyntaxErrorMessage = AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$messages$SyntaxErrorMessage) == null) {
            $class$org$codehaus$groovy$control$messages$SyntaxErrorMessage = (AstBuilderInvocationTrap.$class$org$codehaus$groovy$control$messages$SyntaxErrorMessage = class$("org.codehaus.groovy.control.messages.SyntaxErrorMessage"));
        }
        return $class$org$codehaus$groovy$control$messages$SyntaxErrorMessage;
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
