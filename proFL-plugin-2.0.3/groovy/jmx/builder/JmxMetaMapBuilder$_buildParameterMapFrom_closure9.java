// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JmxMetaMapBuilder$_buildParameterMapFrom_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> map;
    private Reference<Object> method;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderTools;
    
    public JmxMetaMapBuilder$_buildParameterMapFrom_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> map, final Reference<Object> method) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.map = map;
        this.method = method;
    }
    
    public Object doCall(final Object param, final Object paramMap) {
        final Object param2 = new Reference(param);
        final Object paramMap2 = new Reference(paramMap);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object type = new Reference($getCallSiteArray[0].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), this.method.get(), $getCallSiteArray[1].call($get$$class$groovy$jmx$builder$JmxBuilderTools(), ((Reference<Object>)param2).get())));
        if (DefaultTypeTransformation.booleanUnbox(((Reference<Object>)type).get())) {
            return $getCallSiteArray[2].call(this.map.get(), $getCallSiteArray[3].callGetProperty(((Reference<Object>)type).get()), $getCallSiteArray[4].callStatic($get$$class$groovy$jmx$builder$JmxMetaMapBuilder(), this.method.get(), ((Reference<Object>)type).get(), ((Reference<Object>)paramMap2).get()));
        }
        return null;
    }
    
    public Object call(final Object param, final Object paramMap) {
        final Object param2 = new Reference(param);
        final Object paramMap2 = new Reference(paramMap);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)param2).get(), ((Reference<Object>)paramMap2).get());
    }
    
    public Object getMap() {
        $getCallSiteArray();
        return this.map.get();
    }
    
    public Object getMethod() {
        $getCallSiteArray();
        return this.method.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxMetaMapBuilder$_buildParameterMapFrom_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$callSiteArray == null || ($createCallSiteArray = JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxMetaMapBuilder() {
        Class $class$groovy$jmx$builder$JmxMetaMapBuilder;
        if (($class$groovy$jmx$builder$JmxMetaMapBuilder = JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$class$groovy$jmx$builder$JmxMetaMapBuilder) == null) {
            $class$groovy$jmx$builder$JmxMetaMapBuilder = (JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$class$groovy$jmx$builder$JmxMetaMapBuilder = class$("groovy.jmx.builder.JmxMetaMapBuilder"));
        }
        return $class$groovy$jmx$builder$JmxMetaMapBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderTools() {
        Class $class$groovy$jmx$builder$JmxBuilderTools;
        if (($class$groovy$jmx$builder$JmxBuilderTools = JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$class$groovy$jmx$builder$JmxBuilderTools) == null) {
            $class$groovy$jmx$builder$JmxBuilderTools = (JmxMetaMapBuilder$_buildParameterMapFrom_closure9.$class$groovy$jmx$builder$JmxBuilderTools = class$("groovy.jmx.builder.JmxBuilderTools"));
        }
        return $class$groovy$jmx$builder$JmxBuilderTools;
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
