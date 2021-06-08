// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HistoryCommand$_closure4 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    
    public HistoryCommand$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object args) {
        final Object args2 = new Reference(args);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object line = new Reference(null);
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[0].call(((Reference<Object>)args2).get()), HistoryCommand$_closure4.$const$0)) {
            $getCallSiteArray[1].callCurrent(this, "History recall requires a single history identifer");
        }
        final Object id = new Reference($getCallSiteArray[2].call(((Reference<Object>)args2).get(), HistoryCommand$_closure4.$const$1));
        try {
            ((Reference<Object>)id).set($getCallSiteArray[3].call($get$$class$java$lang$Integer(), ((Reference<Object>)id).get()));
            ((Reference<Object>)line).set($getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), ((Reference<Object>)id).get()));
        }
        catch (Exception value) {
            final Exception e = (Exception)new Reference(value);
            $getCallSiteArray[7].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)id).get() }, new String[] { "Invalid history identifier: ", "" }), ((Reference<Object>)e).get());
        }
        $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { ((Reference<Object>)id).get(), ((Reference<Object>)line).get() }, new String[] { "Recalling history item #", ": ", "" }));
        return $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty(this), ((Reference<Object>)line).get());
    }
    
    static {
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HistoryCommand$_closure4.$callSiteArray == null || ($createCallSiteArray = HistoryCommand$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HistoryCommand$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = HistoryCommand$_closure4.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (HistoryCommand$_closure4.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
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
