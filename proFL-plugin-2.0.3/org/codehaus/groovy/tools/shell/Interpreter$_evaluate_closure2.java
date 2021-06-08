// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.reflect.Method;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Interpreter$_evaluate_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> type;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$MethodClosure;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public Interpreter$_evaluate_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> type) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.type = type;
    }
    
    public Object doCall(final Method m) {
        final Method i = (Method)new Reference(m);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.isCase($getCallSiteArray[0].callGetProperty(((Reference<Object>)i).get()), ScriptBytecodeAdapter.createList(new Object[] { "main", "run" })) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty(((Reference<Object>)i).get()), "super$"))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(((Reference<Object>)i).get()), "class$"))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(((Reference<Object>)i).get()), "$"))) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { ((Reference<Object>)i).get() }, new String[] { "Saving method definition: ", "" }));
            final CallSite callSite = $getCallSiteArray[9];
            final Object callGroovyObjectGetProperty = $getCallSiteArray[10].callGroovyObjectGetProperty(this);
            final GStringImpl gStringImpl = new GStringImpl(new Object[] { $getCallSiteArray[11].callGetProperty(((Reference<Object>)i).get()) }, new String[] { "", "" });
            final Object callConstructor = $getCallSiteArray[12].callConstructor($get$$class$org$codehaus$groovy$runtime$MethodClosure(), $getCallSiteArray[13].call(this.type.get()), $getCallSiteArray[14].callGetProperty(((Reference<Object>)i).get()));
            callSite.call(callGroovyObjectGetProperty, gStringImpl, callConstructor);
            return callConstructor;
        }
        return null;
    }
    
    public Object call(final Method m) {
        final Method i = (Method)new Reference(m);
        return $getCallSiteArray()[15].callCurrent(this, ((Reference<Object>)i).get());
    }
    
    public Class getType() {
        $getCallSiteArray();
        return (Class)ScriptBytecodeAdapter.castToType(this.type.get(), $get$$class$java$lang$Class());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Interpreter$_evaluate_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Interpreter$_evaluate_closure2.$callSiteArray == null || ($createCallSiteArray = Interpreter$_evaluate_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Interpreter$_evaluate_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$MethodClosure() {
        Class $class$org$codehaus$groovy$runtime$MethodClosure;
        if (($class$org$codehaus$groovy$runtime$MethodClosure = Interpreter$_evaluate_closure2.$class$org$codehaus$groovy$runtime$MethodClosure) == null) {
            $class$org$codehaus$groovy$runtime$MethodClosure = (Interpreter$_evaluate_closure2.$class$org$codehaus$groovy$runtime$MethodClosure = class$("org.codehaus.groovy.runtime.MethodClosure"));
        }
        return $class$org$codehaus$groovy$runtime$MethodClosure;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = Interpreter$_evaluate_closure2.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (Interpreter$_evaluate_closure2.$class$java$lang$Class = class$("java.lang.Class"));
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
