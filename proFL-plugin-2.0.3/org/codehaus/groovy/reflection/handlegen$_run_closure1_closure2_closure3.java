// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class handlegen$_run_closure1_closure2_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> arg2;
    private Reference<Object> arg1;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public handlegen$_run_closure1_closure2_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> arg2, final Reference<Object> arg1) {
        final Reference arg3 = new Reference((T)arg1);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.arg2 = arg2;
        this.arg1 = arg3.get();
    }
    
    public Object doCall(final Object arg3) {
        final Object arg4 = new Reference(arg3);
        return $getCallSiteArray()[0].callCurrent(this, new GStringImpl(new Object[] { this.arg1.get(), this.arg2.get(), ((Reference<Object>)arg4).get() }, new String[] { "public Object invoke(Object receiver, ", " arg1, ", " arg2, ", " arg3) throws Throwable { return invoke(receiver,ArrayUtil.createArray(arg1,arg2,arg3)); }" }));
    }
    
    public Object getArg2() {
        $getCallSiteArray();
        return this.arg2.get();
    }
    
    public Object getArg1() {
        $getCallSiteArray();
        return this.arg1.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$reflection$handlegen$_run_closure1_closure2_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (handlegen$_run_closure1_closure2_closure3.$callSiteArray == null || ($createCallSiteArray = handlegen$_run_closure1_closure2_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            handlegen$_run_closure1_closure2_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
