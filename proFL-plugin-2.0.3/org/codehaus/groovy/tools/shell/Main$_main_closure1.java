// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

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

class Main$_main_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Main;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public Main$_main_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].callStatic($get$$class$org$codehaus$groovy$tools$shell$Main(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(((Reference<Object>)it2).get(), $get$$class$java$lang$String()), $get$$class$java$lang$String()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Main$_main_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main$_main_closure1.$callSiteArray == null || ($createCallSiteArray = Main$_main_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main$_main_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Main$_main_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Main$_main_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Main() {
        Class $class$org$codehaus$groovy$tools$shell$Main;
        if (($class$org$codehaus$groovy$tools$shell$Main = Main$_main_closure1.$class$org$codehaus$groovy$tools$shell$Main) == null) {
            $class$org$codehaus$groovy$tools$shell$Main = (Main$_main_closure1.$class$org$codehaus$groovy$tools$shell$Main = class$("org.codehaus.groovy.tools.shell.Main"));
        }
        return $class$org$codehaus$groovy$tools$shell$Main;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Main$_main_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (Main$_main_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
