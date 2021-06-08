// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ShadowCommand$_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3;
    
    public ShadowCommand$_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callGetProperty = $getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGroovyObjectGetProperty(this)));
        ScriptBytecodeAdapter.setProperty(callGetProperty, $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3(), $get$$class$org$codehaus$groovy$tools$shell$util$Preferences(), "verbosity");
        return callGetProperty;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShadowCommand$_closure3.$callSiteArray == null || ($createCallSiteArray = ShadowCommand$_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShadowCommand$_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ShadowCommand$_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ShadowCommand$_closure3.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = ShadowCommand$_closure3.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (ShadowCommand$_closure3.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3() {
        Class $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3;
        if (($class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3 = ShadowCommand$_closure3.$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3 = (ShadowCommand$_closure3.$class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3 = class$("org.codehaus.groovy.tools.shell.commands.ShadowCommand$_closure3"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$ShadowCommand$_closure3;
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
