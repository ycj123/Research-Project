// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstSpecificationCompiler$_parameter_closure10 extends Closure implements GeneratedClosure
{
    private Reference<Object> argBlock;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstSpecificationCompiler$_parameter_closure10(final Object _outerInstance, final Object _thisObject, final Reference<Object> argBlock) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.argBlock = argBlock;
    }
    
    public Object doCall(final Object name, final Object type) {
        final Object name2 = new Reference(name);
        final Object type2 = new Reference(type);
        return $getCallSiteArray()[0].callCurrent(this, "Parameter", this.argBlock.get(), new AstSpecificationCompiler$_parameter_closure10_closure30(this, this.getThisObject(), (Reference<Object>)name2, (Reference<Object>)type2));
    }
    
    public Object call(final Object name, final Object type) {
        final Object name2 = new Reference(name);
        final Object type2 = new Reference(type);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)type2).get());
    }
    
    public Closure getArgBlock() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(this.argBlock.get(), $get$$class$groovy$lang$Closure());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$ast$builder$AstSpecificationCompiler$_parameter_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstSpecificationCompiler$_parameter_closure10.$callSiteArray == null || ($createCallSiteArray = AstSpecificationCompiler$_parameter_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstSpecificationCompiler$_parameter_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstSpecificationCompiler$_parameter_closure10.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstSpecificationCompiler$_parameter_closure10.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
