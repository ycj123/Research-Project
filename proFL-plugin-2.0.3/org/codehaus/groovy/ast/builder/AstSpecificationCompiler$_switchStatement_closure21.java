// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_switchStatement_closure21 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
    
    public AstSpecificationCompiler$_switchStatement_closure21(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object switchExpression = $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this));
        final Object caseStatements = $getCallSiteArray[2].call($getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this)));
        final Object defaultExpression = $getCallSiteArray[5].call($getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this)));
        return $getCallSiteArray[8].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement(), switchExpression, caseStatements, defaultExpression);
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_switchStatement_closure21(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_switchStatement_closure21.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_switchStatement_closure21.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_switchStatement_closure21.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_switchStatement_closure21.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_switchStatement_closure21.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$SwitchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$SwitchStatement = AstSpecificationCompiler$_switchStatement_closure21.$class$org$codehaus$groovy$ast$stmt$SwitchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$SwitchStatement = (AstSpecificationCompiler$_switchStatement_closure21.$class$org$codehaus$groovy$ast$stmt$SwitchStatement = class$("org.codehaus.groovy.ast.stmt.SwitchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$SwitchStatement;
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
