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

class AstSpecificationCompiler$_staticMethodCall_closure26 extends Closure implements GeneratedClosure
{
    private Reference<Object> name;
    private Reference<Object> target;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$expr$Expression;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_staticMethodCall_closure26(final Object _outerInstance, final Object _thisObject, final Reference<Object> name, final Reference<Object> target) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.name = name;
        this.target = target;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_staticMethodCall_closure26.$const$0, this.name.get());
        $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_staticMethodCall_closure26.$const$0, $getCallSiteArray[4].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.target.get()));
        return $getCallSiteArray[5].callConstructor($get$$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[6].callCurrent(this, "staticMethodCall", ScriptBytecodeAdapter.createList(new Object[] { $get$$class$org$codehaus$groovy$ast$ClassNode(), $get$$class$java$lang$String(), $get$$class$org$codehaus$groovy$ast$expr$Expression() })) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public String getName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.name.get(), $get$$class$java$lang$String());
    }
    
    public Class getTarget() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.target.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_staticMethodCall_closure26(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_staticMethodCall_closure26.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_staticMethodCall_closure26.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_staticMethodCall_closure26.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression() {
        Class $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
        if (($class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression) == null) {
            $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression = class$("org.codehaus.groovy.ast.expr.StaticMethodCallExpression"));
        }
        return $class$org$codehaus$groovy$ast$expr$StaticMethodCallExpression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$expr$Expression() {
        Class $class$org$codehaus$groovy$ast$expr$Expression;
        if (($class$org$codehaus$groovy$ast$expr$Expression = AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$expr$Expression) == null) {
            $class$org$codehaus$groovy$ast$expr$Expression = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$expr$Expression = class$("org.codehaus.groovy.ast.expr.Expression"));
        }
        return $class$org$codehaus$groovy$ast$expr$Expression;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassNode() {
        Class $class$org$codehaus$groovy$ast$ClassNode;
        if (($class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$ClassNode) == null) {
            $class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$org$codehaus$groovy$ast$ClassNode = class$("org.codehaus.groovy.ast.ClassNode"));
        }
        return $class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_staticMethodCall_closure26.$class$java$lang$Class = class$("java.lang.Class"));
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
