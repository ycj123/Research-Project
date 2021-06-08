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
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_array_closure12 extends Closure implements GeneratedClosure
{
    private Reference<Object> type;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ArrayExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_array_closure12(final Object _outerInstance, final Object _thisObject, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.type = type;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ArrayExpression(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()), $getCallSiteArray[2].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[3].callGroovyObjectGetProperty(this)));
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_array_closure12(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_array_closure12.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_array_closure12.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_array_closure12.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ArrayExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ArrayExpression;
        if (($class$org$codehaus$groovy$ast$expr$ArrayExpression = AstSpecificationCompiler$_array_closure12.$class$org$codehaus$groovy$ast$expr$ArrayExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ArrayExpression = (AstSpecificationCompiler$_array_closure12.$class$org$codehaus$groovy$ast$expr$ArrayExpression = class$("org.codehaus.groovy.ast.expr.ArrayExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ArrayExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_array_closure12.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_array_closure12.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_array_closure12.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_array_closure12.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_array_closure12.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_array_closure12.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_array_closure12.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_array_closure12.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
