// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class SetCommand$_createCompletors_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    
    public SetCommand$_createCompletors_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object list = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final Object keys = $getCallSiteArray[0].call($get$$class$org$codehaus$groovy$tools$shell$util$Preferences());
        $getCallSiteArray[1].call(keys, new SetCommand$_createCompletors_closure1_closure3(this, this.getThisObject(), (Reference<Object>)list));
        return ((Reference<Object>)list).get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$SetCommand$_createCompletors_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SetCommand$_createCompletors_closure1.$callSiteArray == null || ($createCallSiteArray = SetCommand$_createCompletors_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SetCommand$_createCompletors_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = SetCommand$_createCompletors_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (SetCommand$_createCompletors_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = SetCommand$_createCompletors_closure1.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (SetCommand$_createCompletors_closure1.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
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
