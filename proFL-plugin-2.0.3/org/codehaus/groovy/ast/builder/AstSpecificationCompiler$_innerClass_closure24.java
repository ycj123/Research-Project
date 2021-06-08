// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.ast.MixinNode;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.ClassNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_innerClass_closure24 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> modifiers;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$MixinNode;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$InnerClassNode;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$ClassNode;
    
    public AstSpecificationCompiler$_innerClass_closure24(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> modifiers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.modifiers = modifiers;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$InnerClassNode(), ArrayUtil.createArray($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_innerClass_closure24.$const$0), this.name.get(), this.modifiers.get(), $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_innerClass_closure24.$const$1), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[5].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_innerClass_closure24.$const$2)), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[8].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[9].call($getCallSiteArray[10].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_innerClass_closure24.$const$3)), $get$array$$class$org$codehaus$groovy$ast$MixinNode()), $get$array$$class$org$codehaus$groovy$ast$MixinNode())));
    }
    
    public String getName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.name.get(), $get$$class$java$lang$String());
    }
    
    public int getModifiers() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.modifiers.get(), $get$$class$java$lang$Integer()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[11].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$3 = 3;
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_innerClass_closure24(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_innerClass_closure24.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_innerClass_closure24.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_innerClass_closure24.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$MixinNode() {
        Class array$$class$org$codehaus$groovy$ast$MixinNode;
        if ((array$$class$org$codehaus$groovy$ast$MixinNode = AstSpecificationCompiler$_innerClass_closure24.array$$class$org$codehaus$groovy$ast$MixinNode) == null) {
            array$$class$org$codehaus$groovy$ast$MixinNode = (AstSpecificationCompiler$_innerClass_closure24.array$$class$org$codehaus$groovy$ast$MixinNode = class$("[Lorg.codehaus.groovy.ast.MixinNode;"));
        }
        return array$$class$org$codehaus$groovy$ast$MixinNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_innerClass_closure24.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_innerClass_closure24.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$InnerClassNode() {
        Class $class$org$codehaus$groovy$ast$InnerClassNode;
        if (($class$org$codehaus$groovy$ast$InnerClassNode = AstSpecificationCompiler$_innerClass_closure24.$class$org$codehaus$groovy$ast$InnerClassNode) == null) {
            $class$org$codehaus$groovy$ast$InnerClassNode = (AstSpecificationCompiler$_innerClass_closure24.$class$org$codehaus$groovy$ast$InnerClassNode = class$("org.codehaus.groovy.ast.InnerClassNode"));
        }
        return $class$org$codehaus$groovy$ast$InnerClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_innerClass_closure24.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$ClassNode() {
        Class array$$class$org$codehaus$groovy$ast$ClassNode;
        if ((array$$class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_innerClass_closure24.array$$class$org$codehaus$groovy$ast$ClassNode) == null) {
            array$$class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_innerClass_closure24.array$$class$org$codehaus$groovy$ast$ClassNode = class$("[Lorg.codehaus.groovy.ast.ClassNode;"));
        }
        return array$$class$org$codehaus$groovy$ast$ClassNode;
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
