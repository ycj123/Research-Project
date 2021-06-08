// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildOperationMapFrom_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private Reference<Object> object;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    
    public JmxMetaMapBuilder$_buildOperationMapFrom_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> map, final Reference<Object> object) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
        this.object = object;
    }
    
    public Object doCall(final Object opName) {
        final Object opName2 = new Reference(opName);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object method = new Reference(null);
        final Object m = new Reference(null);
        final Object call = $getCallSiteArray[0].call($getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty(this.object.get())));
        while (((Iterator)call).hasNext()) {
            ((Reference<Object>)m).set(((Iterator<T>)call).next());
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(((Reference<Object>)m).get()), ((Reference<Object>)opName2).get()))) {
                ((Reference<Object>)method).set(((Reference<Object>)m).get());
                break;
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)method).get())) {
            return $getCallSiteArray[5].call(this.map.get(), ((Reference<Object>)opName2).get(), $getCallSiteArray[6].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), this.object.get(), ((Reference<Object>)method).get(), "*"));
        }
        return null;
    }
    
    public Object getMap() {
        $getCallSiteArray();
        return this.map.get();
    }
    
    public Object getObject() {
        $getCallSiteArray();
        return this.object.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildOperationMapFrom_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildOperationMapFrom_closure6.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildOperationMapFrom_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildOperationMapFrom_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildOperationMapFrom_closure6.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildOperationMapFrom_closure6.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
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
