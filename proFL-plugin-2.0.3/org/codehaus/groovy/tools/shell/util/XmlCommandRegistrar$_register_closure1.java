// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class XmlCommandRegistrar$_register_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$util$XmlParser;
    
    public XmlCommandRegistrar$_register_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object reader) {
        final Object reader2 = new Reference(reader);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object doc = $getCallSiteArray[0].call($getCallSiteArray[1].callConstructor($get$$class$groovy$util$XmlParser()), ((Reference<Object>)reader2).get());
        return $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(doc), new XmlCommandRegistrar$_register_closure1_closure2(this, this.getThisObject()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$XmlCommandRegistrar$_register_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (XmlCommandRegistrar$_register_closure1.$callSiteArray == null || ($createCallSiteArray = XmlCommandRegistrar$_register_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            XmlCommandRegistrar$_register_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$XmlParser() {
        Class $class$groovy$util$XmlParser;
        if (($class$groovy$util$XmlParser = XmlCommandRegistrar$_register_closure1.$class$groovy$util$XmlParser) == null) {
            $class$groovy$util$XmlParser = (XmlCommandRegistrar$_register_closure1.$class$groovy$util$XmlParser = class$("groovy.util.XmlParser"));
        }
        return $class$groovy$util$XmlParser;
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
