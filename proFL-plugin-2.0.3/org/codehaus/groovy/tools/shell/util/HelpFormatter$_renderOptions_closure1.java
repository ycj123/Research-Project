// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HelpFormatter$_renderOptions_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public HelpFormatter$_renderOptions_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object a, final Object b) {
        final Object a2 = new Reference(a);
        final Object b2 = new Reference(b);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return ScriptBytecodeAdapter.compareTo(ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty(((Reference<Object>)a2).get()), " ") ? $getCallSiteArray[1].callGetProperty(((Reference<Object>)a2).get()) : $getCallSiteArray[2].callGetProperty(((Reference<Object>)a2).get()), ScriptBytecodeAdapter.compareEqual($getCallSiteArray[3].callGetProperty(((Reference<Object>)b2).get()), " ") ? $getCallSiteArray[4].callGetProperty(((Reference<Object>)b2).get()) : $getCallSiteArray[5].callGetProperty(((Reference<Object>)b2).get()));
    }
    
    public Object call(final Object a, final Object b) {
        final Object a2 = new Reference(a);
        final Object b2 = new Reference(b);
        return $getCallSiteArray()[6].callCurrent(this, ((Reference<Object>)a2).get(), ((Reference<Object>)b2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter$_renderOptions_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpFormatter$_renderOptions_closure1.$callSiteArray == null || ($createCallSiteArray = HelpFormatter$_renderOptions_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpFormatter$_renderOptions_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
