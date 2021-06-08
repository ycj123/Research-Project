// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.tools.shell.Command;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class XmlCommandRegistrar$_register_closure1_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Command;
    private static /* synthetic */ Class $class$java$lang$Class;
    
    public XmlCommandRegistrar$_register_closure1_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object element) {
        final Object element2 = new Reference(element);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String classname = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(((Reference<Object>)element2).get()), $get$$class$java$lang$String());
        final Class type = (Class)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), classname), $get$$class$java$lang$Class());
        final Command command = (Command)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(type, $getCallSiteArray[4].callGroovyObjectGetProperty(this)), $get$$class$org$codehaus$groovy$tools$shell$Command()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)))) {
            $getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { $getCallSiteArray[9].callGetProperty(((Reference<Object>)command).get()), ((Reference<Object>)command).get() }, new String[] { "Created command '", "': ", "" }));
        }
        return $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty(this), ((Reference<Object>)command).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar$_register_closure1_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (XmlCommandRegistrar$_register_closure1_closure2.$callSiteArray == null || ($createCallSiteArray = XmlCommandRegistrar$_register_closure1_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            XmlCommandRegistrar$_register_closure1_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = XmlCommandRegistrar$_register_closure1_closure2.$class$java$lang$String) == null) {
            $class$java$lang$String = (XmlCommandRegistrar$_register_closure1_closure2.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Command() {
        Class $class$org$codehaus$groovy$tools$shell$Command;
        if (($class$org$codehaus$groovy$tools$shell$Command = XmlCommandRegistrar$_register_closure1_closure2.$class$org$codehaus$groovy$tools$shell$Command) == null) {
            $class$org$codehaus$groovy$tools$shell$Command = (XmlCommandRegistrar$_register_closure1_closure2.$class$org$codehaus$groovy$tools$shell$Command = class$("org.codehaus.groovy.tools.shell.Command"));
        }
        return $class$org$codehaus$groovy$tools$shell$Command;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = XmlCommandRegistrar$_register_closure1_closure2.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (XmlCommandRegistrar$_register_closure1_closure2.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
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
