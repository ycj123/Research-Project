// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Groovysh$_createDefaultRegistrar_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
    
    public Groovysh$_createDefaultRegistrar_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object shell) {
        final Object shell2 = new Reference(shell);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object r = $getCallSiteArray[0].callConstructor($get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar(), ((Reference<Object>)shell2).get(), $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[2].call(r, $getCallSiteArray[3].call($getCallSiteArray[4].callCurrent(this), "commands.xml"));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Groovysh$_createDefaultRegistrar_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Groovysh$_createDefaultRegistrar_closure3.$callSiteArray == null || ($createCallSiteArray = Groovysh$_createDefaultRegistrar_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Groovysh$_createDefaultRegistrar_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar() {
        Class $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
        if (($class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = Groovysh$_createDefaultRegistrar_closure3.$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar) == null) {
            $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = (Groovysh$_createDefaultRegistrar_closure3.$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar = class$("org.codehaus.groovy.tools.shell.util.XmlCommandRegistrar"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar;
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
