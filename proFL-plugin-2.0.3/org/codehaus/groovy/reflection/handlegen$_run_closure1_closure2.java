// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class handlegen$_run_closure1_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> arg1;
    private Reference<Object> types;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public handlegen$_run_closure1_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> arg1, final Reference<Object> types) {
        final Reference types2 = new Reference((T)types);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.arg1 = arg1;
        this.types = types2.get();
    }
    
    public Object doCall(final Object arg2) {
        final Object arg3 = new Reference(arg2);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { this.arg1.get(), ((Reference<Object>)arg3).get() }, new String[] { "public Object invoke(Object receiver, ", " arg1, ", " arg2) throws Throwable { return invoke(receiver,ArrayUtil.createArray(arg1,arg2)); }" }));
        final CallSite callSite = $getCallSiteArray[1];
        final Object value = this.types.get();
        final Object thisObject = this.getThisObject();
        final Object arg5 = arg3;
        final Reference arg4 = this.arg1;
        return callSite.call(value, new handlegen$_run_closure1_closure2_closure3(this, thisObject, (Reference<Object>)arg5, arg4));
    }
    
    public Object getArg1() {
        $getCallSiteArray();
        return this.arg1.get();
    }
    
    public Object getTypes() {
        $getCallSiteArray();
        return this.types.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$reflection$handlegen$_run_closure1_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (handlegen$_run_closure1_closure2.$callSiteArray == null || ($createCallSiteArray = handlegen$_run_closure1_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            handlegen$_run_closure1_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
