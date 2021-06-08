// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import javax.management.ObjectName;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxTimerFactory$_getNormalizedRecipientList_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$ObjectName;
    
    public JmxTimerFactory$_getNormalizedRecipientList_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> result) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
    }
    
    public Object doCall(final Object name) {
        final Object name2 = new Reference(name);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object on = new Reference(null);
        if (((Reference<Object>)name2).get() instanceof String) {
            ((Reference<Object>)on).set($getCallSiteArray[0].callConstructor($get$$class$javax$management$ObjectName(), ((Reference<Object>)name2).get()));
        }
        if (((Reference<Object>)name2).get() instanceof ObjectName) {
            ((Reference<Object>)on).set(((Reference<Object>)name2).get());
        }
        return $getCallSiteArray[1].call(this.result.get(), ((Reference<Object>)on).get());
    }
    
    public Object getResult() {
        $getCallSiteArray();
        return this.result.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxTimerFactory$_getNormalizedRecipientList_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxTimerFactory$_getNormalizedRecipientList_closure1.$callSiteArray == null || ($createCallSiteArray = JmxTimerFactory$_getNormalizedRecipientList_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxTimerFactory$_getNormalizedRecipientList_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$ObjectName() {
        Class $class$javax$management$ObjectName;
        if (($class$javax$management$ObjectName = JmxTimerFactory$_getNormalizedRecipientList_closure1.$class$javax$management$ObjectName) == null) {
            $class$javax$management$ObjectName = (JmxTimerFactory$_getNormalizedRecipientList_closure1.$class$javax$management$ObjectName = class$("javax.management.ObjectName"));
        }
        return $class$javax$management$ObjectName;
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
