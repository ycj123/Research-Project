// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_mixin_closure16 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> modifiers;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$MixinNode;
    private static /* synthetic */ Class $class$java$util$ArrayList;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$ClassNode;
    
    public AstSpecificationCompiler$_mixin_closure16(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> modifiers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.modifiers = modifiers;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this)), AstSpecificationCompiler$_mixin_closure16.$const$0)) {
            return $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$ast$MixinNode(), this.name.get(), this.modifiers.get(), $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_mixin_closure16.$const$1), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[5].callConstructor($get$$class$java$util$ArrayList(), $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_mixin_closure16.$const$0)), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), $get$array$$class$org$codehaus$groovy$ast$ClassNode()));
        }
        return $getCallSiteArray[8].callConstructor($get$$class$org$codehaus$groovy$ast$MixinNode(), this.name.get(), this.modifiers.get(), $getCallSiteArray[9].call($getCallSiteArray[10].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_mixin_closure16.$const$1));
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
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_mixin_closure16(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_mixin_closure16.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_mixin_closure16.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_mixin_closure16.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_mixin_closure16.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_mixin_closure16.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_mixin_closure16.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_mixin_closure16.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$MixinNode() {
        Class $class$org$codehaus$groovy$ast$MixinNode;
        if (($class$org$codehaus$groovy$ast$MixinNode = AstSpecificationCompiler$_mixin_closure16.$class$org$codehaus$groovy$ast$MixinNode) == null) {
            $class$org$codehaus$groovy$ast$MixinNode = (AstSpecificationCompiler$_mixin_closure16.$class$org$codehaus$groovy$ast$MixinNode = class$("org.codehaus.groovy.ast.MixinNode"));
        }
        return $class$org$codehaus$groovy$ast$MixinNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$ArrayList() {
        Class $class$java$util$ArrayList;
        if (($class$java$util$ArrayList = AstSpecificationCompiler$_mixin_closure16.$class$java$util$ArrayList) == null) {
            $class$java$util$ArrayList = (AstSpecificationCompiler$_mixin_closure16.$class$java$util$ArrayList = class$("java.util.ArrayList"));
        }
        return $class$java$util$ArrayList;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_mixin_closure16.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_mixin_closure16.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$ClassNode() {
        Class array$$class$org$codehaus$groovy$ast$ClassNode;
        if ((array$$class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_mixin_closure16.array$$class$org$codehaus$groovy$ast$ClassNode) == null) {
            array$$class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_mixin_closure16.array$$class$org$codehaus$groovy$ast$ClassNode = class$("[Lorg.codehaus.groovy.ast.ClassNode;"));
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
