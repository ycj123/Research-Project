// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ComplexCommandSupport$_createCompletors_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> c;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ComplexCommandSupport$_createCompletors_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> c) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.c = c;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].call(this.c.get(), ((Reference<Object>)it2).get());
    }
    
    public Object getC() {
        $getCallSiteArray();
        return this.c.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ComplexCommandSupport$_createCompletors_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ComplexCommandSupport$_createCompletors_closure2.$callSiteArray == null || ($createCallSiteArray = ComplexCommandSupport$_createCompletors_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ComplexCommandSupport$_createCompletors_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ComplexCommandSupport$_createCompletors_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ComplexCommandSupport$_createCompletors_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
