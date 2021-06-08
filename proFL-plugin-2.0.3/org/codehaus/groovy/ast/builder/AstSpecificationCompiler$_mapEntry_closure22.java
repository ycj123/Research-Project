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

class AstSpecificationCompiler$_mapEntry_closure22 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
    
    public AstSpecificationCompiler$_mapEntry_closure22(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression(), $getCallSiteArray[3].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), $getCallSiteArray[4].callGetProperty(((Reference<Object>)it2).get())), $getCallSiteArray[5].callConstructor($get$$class$org$codehaus$groovy$ast$expr$ConstantExpression(), $getCallSiteArray[6].callGetProperty(((Reference<Object>)it2).get()))));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_mapEntry_closure22(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_mapEntry_closure22.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_mapEntry_closure22.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_mapEntry_closure22.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_mapEntry_closure22.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_mapEntry_closure22.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$MapEntryExpression() {
        Class $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
        if (($class$org$codehaus$groovy$ast$expr$MapEntryExpression = AstSpecificationCompiler$_mapEntry_closure22.$class$org$codehaus$groovy$ast$expr$MapEntryExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$MapEntryExpression = (AstSpecificationCompiler$_mapEntry_closure22.$class$org$codehaus$groovy$ast$expr$MapEntryExpression = class$("org.codehaus.groovy.ast.expr.MapEntryExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$MapEntryExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$ConstantExpression() {
        Class $class$org$codehaus$groovy$ast$expr$ConstantExpression;
        if (($class$org$codehaus$groovy$ast$expr$ConstantExpression = AstSpecificationCompiler$_mapEntry_closure22.$class$org$codehaus$groovy$ast$expr$ConstantExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$ConstantExpression = (AstSpecificationCompiler$_mapEntry_closure22.$class$org$codehaus$groovy$ast$expr$ConstantExpression = class$("org.codehaus.groovy.ast.expr.ConstantExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$ConstantExpression;
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
