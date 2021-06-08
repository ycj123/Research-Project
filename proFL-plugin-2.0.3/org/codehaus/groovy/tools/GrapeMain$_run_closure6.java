// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.List;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure6 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$java$util$List;
    
    public GrapeMain$_run_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final String prop) {
        final String prop2 = (String)new Reference(prop);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final List list = (List)ScriptBytecodeAdapter.asType($getCallSiteArray[0].call(((Reference<Object>)prop2).get(), "=", GrapeMain$_run_closure6.$const$0), $get$$class$java$util$List());
        final Object k = $getCallSiteArray[1].call(list, 0);
        final Object v = $getCallSiteArray[2].call(list, 1);
        final CallSite callSite = $getCallSiteArray[3];
        final Class $get$$class$java$lang$System = $get$$class$java$lang$System();
        final Object o = k;
        Object o2;
        if (!DefaultTypeTransformation.booleanUnbox(o2 = v)) {
            o2 = "";
        }
        return callSite.call($get$$class$java$lang$System, o, o2);
    }
    
    public Object call(final String prop) {
        final String prop2 = (String)new Reference(prop);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)prop2).get());
    }
    
    static {
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure6.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = GrapeMain$_run_closure6.$class$java$lang$System) == null) {
            $class$java$lang$System = (GrapeMain$_run_closure6.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = GrapeMain$_run_closure6.$class$java$util$List) == null) {
            $class$java$util$List = (GrapeMain$_run_closure6.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
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
