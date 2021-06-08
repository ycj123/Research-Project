// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Main$_main_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> code;
    private Reference<Object> io;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$IO;
    
    public Main$_main_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> code, final Reference<Object> io) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.code = code;
        this.io = io;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.code.get(), null)) {
            $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(this.io.get()));
            $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(this.io.get()), "@|red WARNING:|@ Abnormal JVM shutdown detected");
        }
        return $getCallSiteArray[4].call(this.io.get());
    }
    
    public Object getCode() {
        $getCallSiteArray();
        return this.code.get();
    }
    
    public IO getIo() {
        $getCallSiteArray();
        return (IO)ScriptBytecodeAdapter.castToType(this.io.get(), $get$$class$org$codehaus$groovy$tools$shell$IO());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[5].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Main$_main_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main$_main_closure2.$callSiteArray == null || ($createCallSiteArray = Main$_main_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main$_main_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Main$_main_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Main$_main_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$IO() {
        Class $class$org$codehaus$groovy$tools$shell$IO;
        if (($class$org$codehaus$groovy$tools$shell$IO = Main$_main_closure2.$class$org$codehaus$groovy$tools$shell$IO) == null) {
            $class$org$codehaus$groovy$tools$shell$IO = (Main$_main_closure2.$class$org$codehaus$groovy$tools$shell$IO = class$("org.codehaus.groovy.tools.shell.IO"));
        }
        return $class$org$codehaus$groovy$tools$shell$IO;
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
