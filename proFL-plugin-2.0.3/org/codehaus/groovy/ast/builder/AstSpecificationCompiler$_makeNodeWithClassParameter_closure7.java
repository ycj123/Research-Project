// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_makeNodeWithClassParameter_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> spec;
    private Reference<Object> alias;
    private Reference<Object> target;
    private Reference<Object> type;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public AstSpecificationCompiler$_makeNodeWithClassParameter_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> spec, final Reference<Object> alias, final Reference<Object> target, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.spec = spec;
        this.alias = alias;
        this.target = target;
        this.type = type;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$const$0, $getCallSiteArray[2].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), this.type.get()));
        return $getCallSiteArray[3].call(this.target.get(), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[] { $getCallSiteArray[4].callCurrent(this, this.alias.get(), this.spec.get()) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(0, Integer.TYPE)) }));
    }
    
    public List<Class> getSpec() {
        $getCallSiteArray();
        return (List<Class>)ScriptBytecodeAdapter.castToType(this.spec.get(), $get$$class$java$util$List());
    }
    
    public String getAlias() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.alias.get(), $get$$class$java$lang$String());
    }
    
    public Class getTarget() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.target.get(), $get$$class$java$lang$Class());
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[5].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_makeNodeWithClassParameter_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$util$List) == null) {
            $class$java$util$List = (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (AstSpecificationCompiler$_makeNodeWithClassParameter_closure7.$class$java$lang$Class = class$("java.lang.Class"));
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
