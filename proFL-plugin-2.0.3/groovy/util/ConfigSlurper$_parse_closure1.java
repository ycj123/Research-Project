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

class ConfigSlurper$_parse_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> last;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public ConfigSlurper$_parse_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> last) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.last = last;
    }
    
    public Object doCall(final Object k2, final Object v2) {
        final Object k3 = new Reference(k2);
        final Object v3 = new Reference(v2);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value = this.last.get();
        final Object value2 = ((Reference<Object>)k3).get();
        final Object value3 = ((Reference<Object>)v3).get();
        callSite.call(value, value2, value3);
        return value3;
    }
    
    public Object call(final Object k2, final Object v2) {
        final Object k3 = new Reference(k2);
        final Object v3 = new Reference(v2);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)k3).get(), ((Reference<Object>)v3).get());
    }
    
    public Object getLast() {
        $getCallSiteArray();
        return this.last.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper$_parse_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper$_parse_closure1.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper$_parse_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper$_parse_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
