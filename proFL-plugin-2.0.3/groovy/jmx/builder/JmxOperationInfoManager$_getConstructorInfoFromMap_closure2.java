// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.management.Descriptor;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxOperationInfoManager$_getConstructorInfoFromMap_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> desc;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$Descriptor;
    
    public JmxOperationInfoManager$_getConstructorInfoFromMap_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> desc) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.desc = desc;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[0].call(this.desc.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Descriptor getDesc() {
        $getCallSiteArray();
        return (Descriptor)ScriptBytecodeAdapter.castToType(this.desc.get(), $get$$class$javax$management$Descriptor());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxOperationInfoManager$_getConstructorInfoFromMap_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxOperationInfoManager$_getConstructorInfoFromMap_closure2.$callSiteArray == null || ($createCallSiteArray = JmxOperationInfoManager$_getConstructorInfoFromMap_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxOperationInfoManager$_getConstructorInfoFromMap_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$Descriptor() {
        Class $class$javax$management$Descriptor;
        if (($class$javax$management$Descriptor = JmxOperationInfoManager$_getConstructorInfoFromMap_closure2.$class$javax$management$Descriptor) == null) {
            $class$javax$management$Descriptor = (JmxOperationInfoManager$_getConstructorInfoFromMap_closure2.$class$javax$management$Descriptor = class$("javax.management.Descriptor"));
        }
        return $class$javax$management$Descriptor;
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
