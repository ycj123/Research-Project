// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_assertStatement_closure18 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$Expression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$AssertStatement;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
    
    public AstSpecificationCompiler$_assertStatement_closure18(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareLessThan($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this)), AstSpecificationCompiler$_assertStatement_closure18.$const$0)) {
            return $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$AssertStatement(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[3].callCurrent(this, "assertStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression() })) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
        }
        return $getCallSiteArray[4].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$AssertStatement(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[5].callCurrent(this, "assertStatement", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression(), $get$$class$org$codehaus$groovy$ast$expr$Expression() })) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_assertStatement_closure18(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_assertStatement_closure18.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_assertStatement_closure18.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_assertStatement_closure18.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_assertStatement_closure18.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_assertStatement_closure18.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$Expression() {
        Class $class$org$codehaus$groovy$ast$expr$Expression;
        if (($class$org$codehaus$groovy$ast$expr$Expression = AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$expr$Expression) == null) {
            $class$org$codehaus$groovy$ast$expr$Expression = (AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$expr$Expression = class$("org.codehaus.groovy.ast.expr.Expression"));
        }
        return $class$org$codehaus$groovy$ast$expr$Expression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$AssertStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$AssertStatement;
        if (($class$org$codehaus$groovy$ast$stmt$AssertStatement = AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$stmt$AssertStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$AssertStatement = (AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$stmt$AssertStatement = class$("org.codehaus.groovy.ast.stmt.AssertStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$AssertStatement;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$BooleanExpression() {
        Class $class$org$codehaus$groovy$ast$expr$BooleanExpression;
        if (($class$org$codehaus$groovy$ast$expr$BooleanExpression = AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$expr$BooleanExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$BooleanExpression = (AstSpecificationCompiler$_assertStatement_closure18.$class$org$codehaus$groovy$ast$expr$BooleanExpression = class$("org.codehaus.groovy.ast.expr.BooleanExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$BooleanExpression;
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
