// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HelpFormatter$_renderOptions_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> lpad;
    private Reference<Object> prefixes;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public HelpFormatter$_renderOptions_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> lpad, final Reference<Object> prefixes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.lpad = lpad;
        this.prefixes = prefixes;
    }
    
    public Object doCall(final Object option) {
        final Object option2 = new Reference(option);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object buff = new Reference($getCallSiteArray[0].callConstructor($get$$class$java$lang$StringBuffer(), HelpFormatter$_renderOptions_closure2.$const$0));
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[1].callGetProperty(((Reference<Object>)option2).get()), " ")) {
            $getCallSiteArray[2].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { this.lpad.get(), $getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGetProperty(((Reference<Object>)option2).get()) }, new String[] { "", "    ", "", "" }));
        }
        else {
            $getCallSiteArray[5].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { this.lpad.get(), $getCallSiteArray[6].callGroovyObjectGetProperty(this), $getCallSiteArray[7].callGetProperty(((Reference<Object>)option2).get()) }, new String[] { "", "", "", "" }));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(((Reference<Object>)option2).get()))) {
                $getCallSiteArray[9].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { $getCallSiteArray[10].callGroovyObjectGetProperty(this), $getCallSiteArray[11].callGetProperty(((Reference<Object>)option2).get()) }, new String[] { ", ", "", "" }));
            }
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[12].call(((Reference<Object>)option2).get()))) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].call(((Reference<Object>)option2).get()))) {
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(((Reference<Object>)option2).get()))) {
                    $getCallSiteArray[15].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { $getCallSiteArray[16].callGetProperty(((Reference<Object>)option2).get()) }, new String[] { "[=", "]" }));
                }
                else {
                    $getCallSiteArray[17].call(((Reference<Object>)buff).get(), new GStringImpl(new Object[] { $getCallSiteArray[18].callGetProperty(((Reference<Object>)option2).get()) }, new String[] { "=", "" }));
                }
            }
            else {
                $getCallSiteArray[19].call(((Reference<Object>)buff).get(), " ");
            }
        }
        return $getCallSiteArray[20].call(this.prefixes.get(), ((Reference<Object>)buff).get());
    }
    
    public String getLpad() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.lpad.get(), $get$$class$java$lang$String());
    }
    
    public List getPrefixes() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.prefixes.get(), $get$$class$java$util$List());
    }
    
    static {
        $const$0 = 8;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[21];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter$_renderOptions_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpFormatter$_renderOptions_closure2.$callSiteArray == null || ($createCallSiteArray = HelpFormatter$_renderOptions_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpFormatter$_renderOptions_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = HelpFormatter$_renderOptions_closure2.$class$java$util$List) == null) {
            $class$java$util$List = (HelpFormatter$_renderOptions_closure2.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = HelpFormatter$_renderOptions_closure2.$class$java$lang$String) == null) {
            $class$java$lang$String = (HelpFormatter$_renderOptions_closure2.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = HelpFormatter$_renderOptions_closure2.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (HelpFormatter$_renderOptions_closure2.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
