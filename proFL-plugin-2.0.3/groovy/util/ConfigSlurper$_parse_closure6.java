// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConfigSlurper$_parse_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> prefix;
    private Reference<Object> assignName;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public ConfigSlurper$_parse_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> prefix, final Reference<Object> assignName) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.prefix = prefix;
        this.assignName = assignName;
    }
    
    public Object doCall(final String name, final Object value) {
        final String name2 = (String)new Reference(name);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.assignName.get(), $getCallSiteArray[1].call(this.prefix.get(), ((Reference<Object>)name2).get()), ((Reference<Object>)value2).get());
    }
    
    public Object call(final String name, final Object value) {
        final String name2 = (String)new Reference(name);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getPrefix() {
        $getCallSiteArray();
        return this.prefix.get();
    }
    
    public Object getAssignName() {
        $getCallSiteArray();
        return this.assignName.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper$_parse_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper$_parse_closure6.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper$_parse_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper$_parse_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
