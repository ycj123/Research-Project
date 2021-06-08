// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

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

class JmxMetaMapBuilder$_buildListenerMapFrom_closure11 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    
    public JmxMetaMapBuilder$_buildListenerMapFrom_closure11(final Object _outerInstance, final Object _thisObject, final Reference<Object> map) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
    }
    
    public Object doCall(final Object name, final Object listenerMap) {
        final Object name2 = new Reference(name);
        final Object listenerMap2 = new Reference(listenerMap);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.map.get(), ((Reference<Object>)name2).get(), $getCallSiteArray[1].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), ((Reference<Object>)listenerMap2).get()));
    }
    
    public Object call(final Object name, final Object listenerMap) {
        final Object name2 = new Reference(name);
        final Object listenerMap2 = new Reference(listenerMap);
        return $getCallSiteArray()[2].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)listenerMap2).get());
    }
    
    public Object getMap() {
        $getCallSiteArray();
        return this.map.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildListenerMapFrom_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildListenerMapFrom_closure11.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildListenerMapFrom_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildListenerMapFrom_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildListenerMapFrom_closure11.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildListenerMapFrom_closure11.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
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
