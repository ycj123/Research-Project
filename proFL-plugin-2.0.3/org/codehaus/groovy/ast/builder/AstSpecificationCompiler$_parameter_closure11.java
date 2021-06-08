// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_parameter_closure11 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ClassHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$Parameter;
    
    public AstSpecificationCompiler$_parameter_closure11(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object name, final Object type) {
        final Object name2 = new Reference(name);
        final Object type2 = new Reference(type);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$ast$Parameter(), $getCallSiteArray[3].call($get$$class$org$codehaus$groovy$ast$ClassHelper(), ((Reference<Object>)type2).get()), ((Reference<Object>)name2).get()));
    }
    
    public Object call(final Object name, final Object type) {
        final Object name2 = new Reference(name);
        final Object type2 = new Reference(type);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)type2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_parameter_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_parameter_closure11.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_parameter_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_parameter_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ClassHelper() {
        Class $class$org$codehaus$groovy$ast$ClassHelper;
        if (($class$org$codehaus$groovy$ast$ClassHelper = AstSpecificationCompiler$_parameter_closure11.$class$org$codehaus$groovy$ast$ClassHelper) == null) {
            $class$org$codehaus$groovy$ast$ClassHelper = (AstSpecificationCompiler$_parameter_closure11.$class$org$codehaus$groovy$ast$ClassHelper = class$("org.codehaus.groovy.ast.ClassHelper"));
        }
        return $class$org$codehaus$groovy$ast$ClassHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$Parameter() {
        Class $class$org$codehaus$groovy$ast$Parameter;
        if (($class$org$codehaus$groovy$ast$Parameter = AstSpecificationCompiler$_parameter_closure11.$class$org$codehaus$groovy$ast$Parameter) == null) {
            $class$org$codehaus$groovy$ast$Parameter = (AstSpecificationCompiler$_parameter_closure11.$class$org$codehaus$groovy$ast$Parameter = class$("org.codehaus.groovy.ast.Parameter"));
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
