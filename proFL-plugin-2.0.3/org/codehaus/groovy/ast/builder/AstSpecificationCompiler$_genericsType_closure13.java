// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.ClassNode;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_genericsType_closure13 extends Closure implements GeneratedClosure
{
    private Reference<Object> type;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$GenericsType;
    private static /* synthetic */ Class array$$class$org$codehaus$groovy$ast$ClassNode;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_genericsType_closure13(final Object _outerInstance, final Object _thisObject, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.type = type;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$ast$GenericsType(), $getCallSiteArray[1].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_genericsType_closure13.$const$0), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), $get$array$$class$org$codehaus$groovy$ast$ClassNode()), $getCallSiteArray[4].call($getCallSiteArray[5].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_genericsType_closure13.$const$1));
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_genericsType_closure13(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_genericsType_closure13.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_genericsType_closure13.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_genericsType_closure13.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_genericsType_closure13.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_genericsType_closure13.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_genericsType_closure13.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_genericsType_closure13.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$GenericsType() {
        Class $class$org$codehaus$groovy$ast$GenericsType;
        if (($class$org$codehaus$groovy$ast$GenericsType = AstSpecificationCompiler$_genericsType_closure13.$class$org$codehaus$groovy$ast$GenericsType) == null) {
            $class$org$codehaus$groovy$ast$GenericsType = (AstSpecificationCompiler$_genericsType_closure13.$class$org$codehaus$groovy$ast$GenericsType = class$("org.codehaus.groovy.ast.GenericsType"));
        }
        return $class$org$codehaus$groovy$ast$GenericsType;
    }
    
    private static /* synthetic */ Class $get$array$$class$org$codehaus$groovy$ast$ClassNode() {
        Class array$$class$org$codehaus$groovy$ast$ClassNode;
        if ((array$$class$org$codehaus$groovy$ast$ClassNode = AstSpecificationCompiler$_genericsType_closure13.array$$class$org$codehaus$groovy$ast$ClassNode) == null) {
            array$$class$org$codehaus$groovy$ast$ClassNode = (AstSpecificationCompiler$_genericsType_closure13.array$$class$org$codehaus$groovy$ast$ClassNode = class$("[Lorg.codehaus.groovy.ast.ClassNode;"));
        }
        return array$$class$org$codehaus$groovy$ast$ClassNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_genericsType_closure13.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_genericsType_closure13.$class$java$lang$Class = class$("java.lang.Class"));
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
