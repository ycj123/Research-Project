// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_method_closure20 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> modifiers;
    private Reference<Object> returnType;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$MethodNode;
    
    public AstSpecificationCompiler$_method_closure20(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> modifiers, final Reference<Object> returnType) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.modifiers = modifiers;
        this.returnType = returnType;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object result = new Reference($getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$MethodNode(), ArrayUtil.createArray(this.name.get(), this.modifiers.get(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.returnType.get()), $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_method_closure20.$const$0), $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_method_closure20.$const$1), $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_method_closure20.$const$2))));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_method_closure20.$const$3))) {
            final Object annotations = $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_method_closure20.$const$3);
            $getCallSiteArray[12].call(((Reference<Object>)result).get(), $getCallSiteArray[13].callConstructor($get$$class$java$util$ArrayList(), annotations));
        }
        return ((Reference<Object>)result).get();
    }
    
    public String getName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.name.get(), $get$$class$java$lang$String());
    }
    
    public int getModifiers() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.modifiers.get(), $get$$class$java$lang$Integer()));
    }
    
    public Class getReturnType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.returnType.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[14].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$3 = 3;
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[15];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_method_closure20(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_method_closure20.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_method_closure20.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_method_closure20.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_method_closure20.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_method_closure20.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_method_closure20.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_method_closure20.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_method_closure20.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_method_closure20.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_method_closure20.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_method_closure20.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_method_closure20.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_method_closure20.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_method_closure20.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_method_closure20.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$MethodNode() {
        Class $class$org$codehaus$groovy$ast$MethodNode;
        if (($class$org$codehaus$groovy$ast$MethodNode = AstSpecificationCompiler$_method_closure20.$class$org$codehaus$groovy$ast$MethodNode) == null) {
            $class$org$codehaus$groovy$ast$MethodNode = (AstSpecificationCompiler$_method_closure20.$class$org$codehaus$groovy$ast$MethodNode = class$("org.codehaus.groovy.ast.MethodNode"));
        }
        return $class$org$codehaus$groovy$ast$MethodNode;
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
