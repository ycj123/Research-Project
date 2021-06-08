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

class AstSpecificationCompiler$_makeNodeFromList_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> target;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_makeNodeFromList_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> target) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.target = target;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.target.get(), $getCallSiteArray[1].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[2].callGroovyObjectGetProperty(this)));
    }
    
    public Class getTarget() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.target.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_makeNodeFromList_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_makeNodeFromList_closure4.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_makeNodeFromList_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_makeNodeFromList_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_makeNodeFromList_closure4.$class$java$lang$Class = class$("java.lang.Class"));
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
