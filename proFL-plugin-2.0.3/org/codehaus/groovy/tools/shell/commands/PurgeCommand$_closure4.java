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

class PurgeCommand$_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    
    public PurgeCommand$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Preferences());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(this)))) {
            return $getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(this)), "Preferences purged");
        }
        return null;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$PurgeCommand$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (PurgeCommand$_closure4.$callSiteArray == null || ($createCallSiteArray = PurgeCommand$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            PurgeCommand$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = PurgeCommand$_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (PurgeCommand$_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = PurgeCommand$_closure4.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (PurgeCommand$_closure4.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
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
