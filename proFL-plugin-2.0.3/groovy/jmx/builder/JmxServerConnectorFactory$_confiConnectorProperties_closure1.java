// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.HashMap;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxServerConnectorFactory$_confiConnectorProperties_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> env;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$HashMap;
    
    public JmxServerConnectorFactory$_confiConnectorProperties_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> env) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.env = env;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[0].call(this.env.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public HashMap<String, Object> getEnv() {
        $getCallSiteArray();
        return (HashMap<String, Object>)ScriptBytecodeAdapter.castToType(this.env.get(), $get$$class$java$util$HashMap());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxServerConnectorFactory$_confiConnectorProperties_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxServerConnectorFactory$_confiConnectorProperties_closure1.$callSiteArray == null || ($createCallSiteArray = JmxServerConnectorFactory$_confiConnectorProperties_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxServerConnectorFactory$_confiConnectorProperties_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$HashMap() {
        Class $class$java$util$HashMap;
        if (($class$java$util$HashMap = JmxServerConnectorFactory$_confiConnectorProperties_closure1.$class$java$util$HashMap) == null) {
            $class$java$util$HashMap = (JmxServerConnectorFactory$_confiConnectorProperties_closure1.$class$java$util$HashMap = class$("java.util.HashMap"));
        }
        return $class$java$util$HashMap;
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
