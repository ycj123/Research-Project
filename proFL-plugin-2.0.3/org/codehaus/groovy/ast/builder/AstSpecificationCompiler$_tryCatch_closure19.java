// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_tryCatch_closure19 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
    
    public AstSpecificationCompiler$_tryCatch_closure19(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object result = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$stmt$TryCatchStatement(), $getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_tryCatch_closure19.$const$0), $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_tryCatch_closure19.$const$1)));
        final Object catchStatements = $getCallSiteArray[5].call($getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this)));
        $getCallSiteArray[8].call(catchStatements, new AstSpecificationCompiler$_tryCatch_closure19_closure32(this, this.getThisObject(), (Reference<Object>)result));
        return ((Reference<Object>)result).get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_tryCatch_closure19(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_tryCatch_closure19.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_tryCatch_closure19.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_tryCatch_closure19.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_tryCatch_closure19.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_tryCatch_closure19.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$TryCatchStatement() {
        Class $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
        if (($class$org$codehaus$groovy$ast$stmt$TryCatchStatement = AstSpecificationCompiler$_tryCatch_closure19.$class$org$codehaus$groovy$ast$stmt$TryCatchStatement) == null) {
            $class$org$codehaus$groovy$ast$stmt$TryCatchStatement = (AstSpecificationCompiler$_tryCatch_closure19.$class$org$codehaus$groovy$ast$stmt$TryCatchStatement = class$("org.codehaus.groovy.ast.stmt.TryCatchStatement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$TryCatchStatement;
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
