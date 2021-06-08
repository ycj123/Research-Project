// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Main$_main_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> list;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$io$File;
    
    public Main$_main_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> list) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.list = list;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.list.get(), $getCallSiteArray[1].call(((Reference<Object>)it2).get(), $getCallSiteArray[2].callGetProperty($get$$class$java$io$File())));
    }
    
    public Object getList() {
        $getCallSiteArray();
        return this.list.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$groovydoc$Main$_main_closure1(), names);
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
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Main$_main_closure1.$class$java$io$File) == null) {
            $class$java$io$File = (Main$_main_closure1.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
