// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StringUtil$_expandHyphen_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StringUtil$_expandHyphen_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object all, final Object begin, final Object end) {
        final Object begin2 = new Reference(begin);
        final Object end2 = new Reference(end);
        return $getCallSiteArray()[0].call(ScriptBytecodeAdapter.createRange(((Reference<Object>)begin2).get(), ((Reference<Object>)end2).get(), true));
    }
    
    public Object call(final Object all, final Object begin, final Object end) {
        final Object begin2 = new Reference(begin);
        final Object end2 = new Reference(end);
        return $getCallSiteArray()[1].callCurrent(this, all, ((Reference<Object>)begin2).get(), ((Reference<Object>)end2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$util$StringUtil$_expandHyphen_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StringUtil$_expandHyphen_closure2.$callSiteArray == null || ($createCallSiteArray = StringUtil$_expandHyphen_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StringUtil$_expandHyphen_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
