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

class AstSpecificationCompiler$_fieldNode_closure23 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> owner;
    private Reference<Object> modifiers;
    private Reference<Object> type;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$FieldNode;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$Expression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_fieldNode_closure23(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> owner, final Reference<Object> modifiers, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.owner = owner;
        this.modifiers = modifiers;
        this.type = type;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_fieldNode_closure23.$const$0, $getCallSiteArray[2].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.owner.get()));
        $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_fieldNode_closure23.$const$0, $getCallSiteArray[5].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()));
        $getCallSiteArray[6].call($getCallSiteArray[7].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_fieldNode_closure23.$const$0, this.modifiers.get());
        $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_fieldNode_closure23.$const$0, this.name.get());
        return $getCallSiteArray[10].callConstructor($get$$class$org$codehaus$groovy$ast$FieldNode(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[11].callCurrent(this, "fieldNode", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$java$lang$String(), $get$$class$java$lang$Integer(), $get$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$org$codehaus$groovy$ast$expr$Expression() })) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
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
    
    public int getModifiers() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.modifiers.get(), $get$$class$java$lang$Integer()));
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[12].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_fieldNode_closure23(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_fieldNode_closure23.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_fieldNode_closure23.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_fieldNode_closure23.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$FieldNode() {
        Class $class$org$codehaus$groovy$ast$FieldNode;
        if (($class$org$codehaus$groovy$ast$FieldNode = AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$FieldNode) == null) {
            $class$org$codehaus$groovy$ast$FieldNode = (AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$FieldNode = class$("org.codehaus.groovy.ast.FieldNode"));
        }
        return $class$org$codehaus$groovy$ast$FieldNode;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$Expression() {
        Class $class$org$codehaus$groovy$ast$expr$Expression;
        if (($class$org$codehaus$groovy$ast$expr$Expression = AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$expr$Expression) == null) {
            $class$org$codehaus$groovy$ast$expr$Expression = (AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$expr$Expression = class$("org.codehaus.groovy.ast.expr.Expression"));
        }
        return $class$org$codehaus$groovy$ast$expr$Expression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassNode() {
        Class $class$org$codehaus$groovy$ast$ClassNode;
        if (($class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$ClassNode) == null) {
            $class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_fieldNode_closure23.$class$org$codehaus$groovy$ast$ClassNode = class$("org.codehaus.groovy.ast.ClassNode"));
        }
        return $class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_fieldNode_closure23.$class$java$lang$Class = class$("java.lang.Class"));
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
