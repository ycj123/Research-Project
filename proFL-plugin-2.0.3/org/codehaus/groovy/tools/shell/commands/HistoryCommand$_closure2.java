// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HistoryCommand$_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public HistoryCommand$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)))) {
            return $getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), "History cleared");
        }
        return null;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$HistoryCommand$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HistoryCommand$_closure2.$callSiteArray == null || ($createCallSiteArray = HistoryCommand$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HistoryCommand$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = HistoryCommand$_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (HistoryCommand$_closure2.$class$java$lang$Object = class$("java.lang.Object"));
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
