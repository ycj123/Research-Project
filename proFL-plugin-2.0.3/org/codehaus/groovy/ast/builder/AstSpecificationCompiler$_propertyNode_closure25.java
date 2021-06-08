// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ArrayUtil;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_propertyNode_closure25 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> owner;
    private Reference<Object> type;
    private Reference<Object> modifiers;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$PropertyNode;
    
    public AstSpecificationCompiler$_propertyNode_closure25(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> owner, final Reference<Object> type, final Reference<Object> modifiers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.modifiers = modifiers;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$PropertyNode(), ArrayUtil.createArray(this.name.get(), this.modifiers.get(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()), $getCallSiteArray[2].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.owner.get()), $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_propertyNode_closure25.$const$0), $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_propertyNode_closure25.$const$1), $getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_propertyNode_closure25.$const$2)));
    }
    
    public String getName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.name.get(), $get$$class$java$lang$String());
    }
    
    @Override
    public Class getOwner() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.owner.get(), $get$$class$java$lang$Class());
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    public int getModifiers() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.modifiers.get(), $get$$class$java$lang$Integer()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[9].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[10];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_propertyNode_closure25(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_propertyNode_closure25.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_propertyNode_closure25.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_propertyNode_closure25.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_propertyNode_closure25.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_propertyNode_closure25.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_propertyNode_closure25.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$PropertyNode() {
        Class $class$org$codehaus$groovy$ast$PropertyNode;
        if (($class$org$codehaus$groovy$ast$PropertyNode = AstSpecificationCompiler$_propertyNode_closure25.$class$org$codehaus$groovy$ast$PropertyNode) == null) {
            $class$org$codehaus$groovy$ast$PropertyNode = (AstSpecificationCompiler$_propertyNode_closure25.$class$org$codehaus$groovy$ast$PropertyNode = class$("org.codehaus.groovy.ast.PropertyNode"));
        }
        return $class$org$codehaus$groovy$ast$PropertyNode;
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
