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

class AstSpecificationCompiler$_parameter_closure10_closure30 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> type;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    
    public AstSpecificationCompiler$_parameter_closure10_closure30(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.type = type;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$Parameter(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()), this.name.get(), $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_parameter_closure10_closure30.$const$0));
    }
    
    public Object getName() {
        $getCallSiteArray();
        return this.name.get();
    }
    
    public Object getType() {
        $getCallSiteArray();
        return this.type.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_parameter_closure10_closure30(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_parameter_closure10_closure30.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_parameter_closure10_closure30.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_parameter_closure10_closure30.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_parameter_closure10_closure30.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_parameter_closure10_closure30.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_parameter_closure10_closure30.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_parameter_closure10_closure30.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = AstSpecificationCompiler$_parameter_closure10_closure30.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (AstSpecificationCompiler$_parameter_closure10_closure30.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
        }
        return $class$org$codehaus$groovy$ast$Parameter;
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
