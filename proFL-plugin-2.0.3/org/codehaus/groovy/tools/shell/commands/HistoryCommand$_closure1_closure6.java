// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HistoryCommand$_closure1_closure6 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public HistoryCommand$_closure1_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object item, final Object i) {
        final Object item2 = new Reference(item);
        final Object j = new Reference(i);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ((Reference<Object>)j).set($getCallSiteArray[0].call($getCallSiteArray[1].call(((Reference<Object>)j).get()), HistoryCommand$_closure1_closure6.$const$0, " "));
        return $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { ((Reference<Object>)j).get(), ((Reference<Object>)item2).get() }, new String[] { " @|bold ", "|@  ", "" }));
    }
    
    public Object call(final Object item, final Object i) {
        final Object item2 = new Reference(item);
        final Object j = new Reference(i);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)item2).get(), ((Reference<Object>)j).get());
    }
    
    static {
        $const$0 = 3;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand$_closure1_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HistoryCommand$_closure1_closure6.$callSiteArray == null || ($createCallSiteArray = HistoryCommand$_closure1_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HistoryCommand$_closure1_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
