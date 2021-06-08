// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure3_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> results;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public GrapeMain$_run_closure3_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> results) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.results = results;
    }
    
    public Object doCall(final Object dep) {
        final Object dep2 = new Reference(dep);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object call = $getCallSiteArray[0].call(this.results.get(), $getCallSiteArray[1].call($getCallSiteArray[2].call($getCallSiteArray[3].call($getCallSiteArray[4].call($getCallSiteArray[5].call("org=\"", $getCallSiteArray[6].callGetProperty(((Reference<Object>)dep2).get())), "\" name=\""), $getCallSiteArray[7].callGetProperty(((Reference<Object>)dep2).get())), "\" revision=\""), $getCallSiteArray[8].callGetProperty(((Reference<Object>)dep2).get())));
        this.results.set(call);
        return call;
    }
    
    public Object getResults() {
        $getCallSiteArray();
        return this.results.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure3_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure3_closure9.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure3_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure3_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
