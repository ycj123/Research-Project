// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.LinkedList;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConfigSlurper$_parse_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> stack;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    
    public ConfigSlurper$_parse_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> stack) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.stack = stack;
    }
    
    public Object doCall(final Object name, final Object co) {
        final Object name2 = new Reference(name);
        final Object co2 = new Reference(co);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object current = $getCallSiteArray[0].callGetProperty(this.stack.get());
        $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty(current), ((Reference<Object>)name2).get(), ((Reference<Object>)co2).get());
        final CallSite callSite = $getCallSiteArray[3];
        final Object callGetProperty = $getCallSiteArray[4].callGetProperty(current);
        final Object value = ((Reference<Object>)name2).get();
        final Object value2 = ((Reference<Object>)co2).get();
        callSite.call(callGetProperty, value, value2);
        return value2;
    }
    
    public Object call(final Object name, final Object co) {
        final Object name2 = new Reference(name);
        final Object co2 = new Reference(co);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)co2).get());
    }
    
    public LinkedList getStack() {
        $getCallSiteArray();
        return (LinkedList)ScriptBytecodeAdapter.castToType(this.stack.get(), $get$$class$java$util$LinkedList());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper$_parse_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper$_parse_closure3.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper$_parse_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper$_parse_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = ConfigSlurper$_parse_closure3.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (ConfigSlurper$_parse_closure3.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
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
