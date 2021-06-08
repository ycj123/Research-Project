// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ShellRunner$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1;
    
    public ShellRunner$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object e) {
        final Object e2 = new Reference(e);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)e2).get());
        final Boolean false = Boolean.FALSE;
        ScriptBytecodeAdapter.setGroovyObjectProperty(false, $get$$class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1(), this, "running");
        return false;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShellRunner$_closure1.$callSiteArray == null || ($createCallSiteArray = ShellRunner$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShellRunner$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1() {
        Class $class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1;
        if (($class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1 = ShellRunner$_closure1.$class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1) == null) {
            $class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1 = (ShellRunner$_closure1.$class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1 = class$("org.codehaus.groovy.tools.shell.ShellRunner$_closure1"));
        }
        return $class$org$codehaus$groovy$tools$shell$ShellRunner$_closure1;
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
