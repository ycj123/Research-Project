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
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_constructor_closure28 extends Closure implements GeneratedClosure
{
    private Reference<Object> modifiers;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$Parameter;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ConstructorNode;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$stmt$Statement;
    
    public AstSpecificationCompiler$_constructor_closure28(final Object _outerInstance, final Object _thisObject, final Reference<Object> modifiers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.modifiers = modifiers;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_constructor_closure28.$const$0, this.modifiers.get());
        return $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$ast$ConstructorNode(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[3].callCurrent(this, "constructor", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$java$lang$Integer(), $get$array$$class$org$codehaus$groovy$ast$Parameter(), $get$array$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$org$codehaus$groovy$ast$stmt$Statement() })) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public int getModifiers() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.modifiers.get(), $get$$class$java$lang$Integer()));
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
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_constructor_closure28(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_constructor_closure28.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_constructor_closure28.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_constructor_closure28.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_constructor_closure28.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_constructor_closure28.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$Parameter() {
        Class array$$class$org$codehaus$groovy$ast$Parameter;
        if ((array$$class$org$codehaus$groovy$ast$Parameter = AstSpecificationCompiler$_constructor_closure28.array$$class$org$codehaus$groovy$ast$Parameter) == null) {
            array$$class$org$codehaus$groovy$ast$Parameter = (AstSpecificationCompiler$_constructor_closure28.array$$class$org$codehaus$groovy$ast$Parameter = class$("[Lorg.codehaus.groovy.ast.Parameter;"));
        }
        return array$$class$org$codehaus$groovy$ast$Parameter;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ConstructorNode() {
        Class $class$org$codehaus$groovy$ast$ConstructorNode;
        if (($class$org$codehaus$groovy$ast$ConstructorNode = AstSpecificationCompiler$_constructor_closure28.$class$org$codehaus$groovy$ast$ConstructorNode) == null) {
            $class$org$codehaus$groovy$ast$ConstructorNode = (AstSpecificationCompiler$_constructor_closure28.$class$org$codehaus$groovy$ast$ConstructorNode = class$("org.codehaus.groovy.ast.ConstructorNode"));
        }
        return $class$org$codehaus$groovy$ast$ConstructorNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_constructor_closure28.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_constructor_closure28.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$ClassNode() {
        Class array$$class$org$codehaus$groovy$ast$ClassNode;
        if ((array$$class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_constructor_closure28.array$$class$org$codehaus$groovy$ast$ClassNode) == null) {
            array$$class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_constructor_closure28.array$$class$org$codehaus$groovy$ast$ClassNode = class$("[Lorg.codehaus.groovy.ast.ClassNode;"));
        }
        return array$$class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$stmt$Statement() {
        Class $class$org$codehaus$groovy$ast$stmt$Statement;
        if (($class$org$codehaus$groovy$ast$stmt$Statement = AstSpecificationCompiler$_constructor_closure28.$class$org$codehaus$groovy$ast$stmt$Statement) == null) {
            $class$org$codehaus$groovy$ast$stmt$Statement = (AstSpecificationCompiler$_constructor_closure28.$class$org$codehaus$groovy$ast$stmt$Statement = class$("org.codehaus.groovy.ast.stmt.Statement"));
        }
        return $class$org$codehaus$groovy$ast$stmt$Statement;
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
